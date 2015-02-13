package edu.sc.cse.rdc.todoapp;

import java.io.Serializable;

public class Person implements Serializable{
	private String name;
	private int age;
	private double height;
	
	public Person(String _name, int _age, double _height)
	{
		name = _name;
		age = _age;
		height = _height;
	}
	
	public String getName(){return name;}
	public int getAge(){return age;}
	public double getHeight(){return height;}
	
	public void setName(String _name)
	{
		name = _name;
	}
	
	public void setAge(int _age)
	{
		age = _age;
	}
	
	public void setHeight(double _height)
	{
		height = _height;
	}
}
