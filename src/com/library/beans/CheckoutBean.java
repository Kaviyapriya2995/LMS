package com.library.beans;

public class CheckoutBean {
	private String title;
	private String card_no;
	private String due_date;
	public String getTitle() {
		return title;
	}
	
	public CheckoutBean(String title,String card_no,String due_date)
	{
		this.title=title;
		this.card_no=card_no;
		this.due_date=due_date;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
}
