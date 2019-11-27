package com.indriyanto.rupitesting.Api;


import com.google.gson.annotations.SerializedName;


public class ValueMessage{

	@SerializedName("error")
	private String error;

	public void setError(String error){
		this.error = error;
	}

	public String getError(){
		return error;
	}

	@Override
 	public String toString(){
		return 
			"ValueMessage{" + 
			"error = '" + error + '\'' + 
			"}";
		}
}