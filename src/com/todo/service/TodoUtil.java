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
	public static void createItem(TodoList l) {
		
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
		if (l.isDuplicate(title)) {
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
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		if(l.addItem(t) > 0)
			System.out.println("추가되었습니다.");
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 삭제]\n"
				+ "삭제할 항목의 번호를 입력하시오 > ");
		int index = sc.nextInt();
		if(l.deleteItem(index) > 0)
			System.out.println("삭제되었습니다. ");
	}

	public static void updateItem(TodoList l) {
		String new_title, new_desc, new_category, new_due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 수정]\n"
						+ "수정할 항목의 번호를 입력하세요 > ");
		int index = sc.nextInt();
		
		System.out.print("새 제목 > ");
		new_title = sc.next().trim();
		System.out.print("새 카테고리  > ");
		new_category = sc.next().trim();
		sc.nextLine();
		System.out.print("새 내용 > ");
		new_desc = sc.next().trim();
		System.out.print("새 마감일자 > ");
		new_due_date = sc.next().trim();
		
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date);
		t.setId(index);
		if(l.updateItem(t) > 0)
			System.out.println("성공적으로 수정되었습니다. ");
	}
	
	public static void listAll(TodoList l) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}

	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}

	public static void findCate(TodoList l, String cate) {
			int count = 0;
			for(TodoItem item : l.getListCategory(cate)) {
				System.out.println(item.toString());
				count++;
			}
			System.out.printf("\n총 %d개의 항목을 찾았습니다. \n", count);
	}

	public static void listCateAll(TodoList l) {
		int count = 0;
		for(String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.printf("\n 총 %d개의 카테고리가 등록되어 있습니다. \n", count);
	}
	

	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for(TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}
	
	public static void findCateList(TodoList l, String cate) {
		int count = 0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n 총 %d개의 항목을 찾았습니다. \n", count);
	}

}
















