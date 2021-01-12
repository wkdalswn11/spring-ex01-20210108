package org.zerock.domain;

import java.beans.PropertyEditorSupport;

public class CustomBookEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException { // 웹에서 받아오는 text를 spring에 맞는 
																		 // 타입으로 변경 
		// text : java-spring 이 들어온다고 가정.
		int dash = text.indexOf("-");
		String title = text.substring(0, dash);
		String writer = text.substring(dash+1);
		
		
		Book book = new Book();
		book.setTitle(title);
		book.setWriter(writer);
		
		setValue(book);
	}
}
