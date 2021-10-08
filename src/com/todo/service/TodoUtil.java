package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

/*
 * 모든 메소드들이 class 메소드로 모든 기능 구성. 별도의 객체를 안 만듬. 다 static 
 */
public class TodoUtil {
	
	/*
	 * 객체 하나 생성 하고 list에 넣는다. 
	 */
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		//category 입력 받기
		sc.nextLine();
		System.out.println("[항목 추가]\n"  + "카테고리 > ");
		category = sc.nextLine().trim(); //좌우여백 제거. 
		
		//title 입력 받기
		//제목 중복 검사하고, 중복되면 추가 안 함.
		System.out.println("제목 > "); 
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("(경고)제목이 중복되어 사용할 수 없습니다!");
			return;
		}
		//desc입력받기 
		//엔터를 한번 제거 해준다. 그래야 공백을 제거하고 내용을 정확하게 입력 가능. 
		sc.nextLine();
		System.out.println("내용 > ");
		desc = sc.nextLine().trim(); //좌우여백 제거. 
		
		//due date입력받기 
		//엔터를 한번 제거 해준다. 그래야 공백을 제거하고 내용을 정확하게 입력 가능. 
		sc.nextLine();
		System.out.println("마감일자 > (형식: YYYY/MM/DD)");
		due_date = sc.nextLine().trim(); //좌우여백 제거. 
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
		System.out.println("성공적으로 내용이 추가되었습니다! :) ");
	}

	public static void deleteItem(TodoList l) {
		int editnum = 0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[항목 삭제]\n" + "삭제할 항목의 번호를 입력하세요 > ");
		editnum = sc.nextInt();
		if(editnum == 0 || editnum > l.numberofItem()) {
			System.out.println("없는 번호 입니다.");
			return;
		}
		/*
		 * 제목 일치하면 삭제!제목이 중복되는 것이 없어서 쉽게 삭제 가능. 
		 * */
		String reallydel;
		for (TodoItem item : l.getList()) {
			if (l.indexOf(item) == (editnum-1)) {
				System.out.println(item.toString());
				System.out.println("정말로 삭제하시겠습니까? [y/n]");
				reallydel = sc.next();
				if(reallydel.equals("y")) {
					l.deleteItem(item);
					System.out.println("성공적으로 내용이 삭제되었습니다! :) ");
					break;
				}
			}
		}
	}

	public static void updateItem(TodoList l) {
		int editnum = 0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[항목 수정]" + "수정할 항목의 번호를 입력하세요 > ");
		editnum = sc.nextInt();
		if(editnum == 0 || editnum > l.numberofItem()) {
			System.out.println("없는 번호 입니다.");
			return;
		}
		
		//수정할 데이터 보여주기.
		for (TodoItem item : l.getList()) {
			if(l.indexOf(item) == (editnum-1)) {
				System.out.println(item.toString());
				System.out.println("새로운 제목을 입력하세요 > ");
				String new_title = sc.next().trim();
				
				if (l.isDuplicate(new_title)) {
					System.out.println("중복되는 제목입니다. :(");
					return;
				}
				sc.nextLine();
				System.out.print("새 카테고리를 입력하세요 > ");
				String new_category = sc.nextLine().trim();

			
				sc.nextLine();
				System.out.print("새 내용을 입력하세요 > ");
				String new_description = sc.nextLine().trim();
				
				sc.nextLine();
				System.out.print("새 마감일자를 입력하세요 > ");
				String new_due_date = sc.nextLine().trim();
	
				
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
				l.addItem(t);
				System.out.println("수정되었습니다. ");
				break;
			}	
			
		}
	}

	public static void listAll(TodoList l) {
		System.out.println("[전체 목록, 총 "+  l.numberofItem() + " 개]");
		for (TodoItem item : l.getList()) {
			System.out.print((l.indexOf(item) + 1) + ". " );
			System.out.println(item.toString());
		}
	}

	public static void saveList(TodoList l, String filename) {
		// TODO Auto-generated method stub

		try {
			Writer w = new FileWriter("todolist.txt");
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			
			System.out.println("성공적으로 파일에 저장했습니다! ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void loadList(TodoList l, String filename) {
		// TODO Auto-generated method stub
		int count = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader("todolist.txt"));
			String oneline;
			while((oneline = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String category = st.nextToken();
				String title = st.nextToken();
			    String desc = st.nextToken();
			    String due_date = st.nextToken();
			    String current_date = st.nextToken();
			    
			    TodoItem item = new TodoItem(category, title, desc, due_date, current_date);
			    l.addItem(item);
			    count ++;
			}
			br.close();
			System.out.println( count + "개의 항목을 읽었습니다.");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("불러온 파일 없음.");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
		
	}
	
	public static void findInList(TodoList l) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("검색할 단어를 입력하세요. > ");
		String findword = sc.nextLine();
		
		//제목에서 키워드 검색 
		for (TodoItem item2 : l.getList()) {
			int num = item2.getTitle().indexOf(findword);
			if(num >= 0) {
				System.out.print((l.indexOf(item2) + 1) + ". " );
				System.out.println(item2.toString());
				continue;
			}
		}
		//내용에서 키워드 검색 
		for (TodoItem item3 : l.getList()) {
			int num = item3.getDesc().indexOf(findword);
			if(num >= 0) {
				System.out.print((l.indexOf(item3) + 1) + ". " );
				System.out.println(item3.toString());
				continue;
			}
		}
	}

	public static void findCate(TodoList l) {
				Scanner sc = new Scanner(System.in);
				System.out.println("검색할 단어를 입력하세요. > ");
				String findword = sc.nextLine();

				//카테고리에서 키워드 검색 
				for (TodoItem item : l.getList()) {
					int num = item.getCategory().indexOf(findword);
					if(num >= 0) {
						System.out.print((l.indexOf(item) + 1) + ". " );
						System.out.println(item.toString());
						continue;
					}
				}
	}

	public static void lsCategory(TodoList l) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("현재 리스트의 카테고리입니다.");
		List<String> cate = new ArrayList<String>();
		for (TodoItem item : l.getList()) {
			cate.add(item.getCategory());
		}
		HashSet <String> newcate = new HashSet<String>(cate);
		int listsize = newcate.size();
		
		for(String i : newcate) {
		System.out.print(i + " /");
		}
		
	}
	
}