package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("***** ToDoList 관리 명령어 사용법 *****");
        System.out.println("add - 항목 추가");
        System.out.println("del - 항목 삭제");
        System.out.println("edit - 항목 수정");
        System.out.println("ls - 전체 목록");
        System.out.println("ls_name_asc - 제목 순으로 정렬");
        System.out.println("ls_name_desc - 제목 역순으로 정렬");
        System.out.println("ls_date - 날짜 순으로 정렬");
        System.out.println("ls_date_desc - 날짜 역순으로 정렬");
        System.out.println("ls_cate - 카테고리 출력");
        System.out.println("find - 제목과 내용에서 단어 찾기");
        System.out.println("find_cate - 카테고리에서 단어 찾기");
        System.out.println("exit - 종료");
        System.out.println("help - 선택 메뉴 보이기");
    }
    
    public static void prompt() 
    {
    	System.out.print("\nCommand > ");	
    }


}