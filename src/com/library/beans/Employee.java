package com.library.beans;



public class Employee {
			private int id;
	    private String name;
	    private String address;
	    private String email;
	    protected String contact;
	    protected String cardno;
	 
	    public Employee() {
	    }
	    public Employee(int id) {
	    	this.id = id;
	    }
	   
	 
	  
	     
	 
	    public Employee(int id,String name,String contact, String address, String cardno,String email) {
	    	this.id = id;
	        this.name = name;
	        this.contact = contact;
	        this.address = address;
	        this.cardno = cardno;
	        this.email = email;
	    }
	    public int getId() {
	        return id;
	    }
	 
	    public void setId(int id) {
	        this.id = id;
	    }
	 
	    public String getName() {
	        return name;
	    }
	 
	    public void setName(String name) {
	    	 this.name = name;
	    }
	 
	    public String getAddress() {
	        return address;
	    }
	 
	    public void setAddress(String address) {
	    	   this.address = address;
	    }
	 
	    public String getContact() {
	        return contact;
	    }
	 
	    public void setContact(String contact) {
	    	 this.contact = contact;
	    }
	    public String getCardno() {
	        return cardno;
	    }
	 
	    public void setCardno(String cardno) {
	    	   this.cardno = cardno;
	    }
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
	 
}
