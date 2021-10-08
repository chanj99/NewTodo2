package com.todo.service;
import java.util.Comparator;

import com.todo.dao.TodoItem;

/*
 * comparator를 사용. 두 개의 객체를 비교하는 메소드를 override로 구현.
 * get으로 데이터를 가져와서 비교. compareTo로 바로 비교 해서 처리. 
 * 
 * */
public class TodoSortByDate implements Comparator<TodoItem> {
    @Override
    public int compare(TodoItem o1, TodoItem o2) {
        return o1.getCurrent_date().compareTo(o2.getCurrent_date());

    }
}