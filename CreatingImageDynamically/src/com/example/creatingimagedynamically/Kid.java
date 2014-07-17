package com.example.creatingimagedynamically;

public class Kid {
	
	int _id;
	String _name;
	String _phone_number;
	

	public Kid(){
		
	}

	public Kid(int id, String name){
		this._id = id;
		this._name = name;
	}
	

	public Kid(String name){
		this._name = name;
		
	}
	// getting ID
	public int getID(){
		return this._id;
	}
	
	// setting id
	public void setID(int id){
		this._id = id;
	}
	
	// getting name
	public String getName(){
		return this._name;
	}
	
	// setting name
	public void setName(String name){
		this._name = name;
	}
	
}
