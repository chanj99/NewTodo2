package com.todo.dao;

import java.util.*;

import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

/*
 * 객체들을 array에 담아서 관리하는 class
 * TodoMain에서 불리는 메소드들 
 * */
public class TodoList {
	private List<TodoItem> list;
	/*
	 * list 하나의 객체만 멤버로 다루고 있다. 
	 * */
	
	public TodoList() {
		this.list = new ArrayList<TodoItem>();
	}
	
	public void addItem(TodoItem t) {
		list.add(t);
	}

	public void deleteItem(TodoItem t) {
		list.remove(t);
	}
	
	public int numberofItem() {
		int num;
		num = list.size();
		return num;
	}
	
	/*
	 * 리스트 수정하는 editItem.
	 */
	void editItem(TodoItem t, TodoItem updated) {
		int index = list.indexOf(t);
		list.remove(index);
		list.add(updated);
	}
	
	/*
	 * 전체 리스트를 가지고 오는 getList
	 */
	public ArrayList<TodoItem> getList() {
		return new ArrayList<TodoItem>(list);
	}

	/*
	 * class 를 새롭게 하나 만들어서 정렬. 
	 * */
	public void sortByName() {
		Collections.sort(list, new TodoSortByName());

	}

	public void listAll() {
		System.out.println("\n"
				+ "inside list_All method\n");
		for (TodoItem myitem : list) {
			System.out.println(myitem.getTitle() + myitem.getDesc());
		}
	}
	
	public void reverseList() {
		Collections.reverse(list);
	}

	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate());
	}
	
	/*
	 * 특정 리스트의 순서를 알아내는 메소드 
	 */
	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}
	/*
	 * 중복 제목을 방지하는 메소드 
	 */
	public Boolean isDuplicate(String title) {
		for (TodoItem item : list) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}

	public TodoItem get(int findnum) {
		return get(findnum);
	}
}