package com.cg.customerjparest.exceptions;

public class CustomerAlreadyExistsException extends Exception{

	public CustomerAlreadyExistsException(){

    }

    public CustomerAlreadyExistsException(String msg){
        super(msg);
    }
}
