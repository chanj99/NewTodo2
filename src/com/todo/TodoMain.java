 package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false; //처음에는 false
		l.importData("todolist.txt");
		Menu.displaymenu();
		
		do {
			Menu.prompt();
			isList = false; //isList boolean변
			String choice = sc.next(); //scanner 사
			switch (choice) {

			case "add":
				TodoUtil.createItem(l); //TodoUtil이라는 class에 간다.
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				System.out.println("제목 순으로 정렬하였습니다.");
				isList = true; //정렬을 진행하고 true로 세팅, 정렬하고 한번 보여주
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("제목 역순으로 정렬하였습니다.");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("날짜 순으로 정렬하였습니다.");
				isList = true;
				break;
		
			case "help": //메뉴를 보여주는 키워드 help case 추가. 
				Menu.displaymenu();
				break;
				
			case "find":
				String keyword = sc.nextLine().trim();
				TodoUtil.findList(l, keyword);
				break;
				
			case "find_cate":
				TodoUtil.findCate(l);
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				System.out.println("날짜 역순으로 정렬하였습니다.");
				isList = true;
				break;
				
			case "ls_cate":
				TodoUtil.lsCategory(l);
				break;
				
			case "exit":
				quit = true;
				break;

			default:
				System.out.println("please enter one of the above mentioned command");
				break;
			}
			
			if(isList) TodoUtil.listAll(l); //true 면 정렬한 목록 보여주기. 
		} while (!quit); //false 였다가 true 면 프로그램 끝내기, 
		TodoUtil.saveList(l, "todolist.txt");
	}
}