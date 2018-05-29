package com.example.demo.entity;

public class User {
	private static final long serialVersionUID = 1L; 
	
    protected int id;  //
    protected String name;  //
    protected int age;  //
    protected String sex;  //
    protected String addr;  //

	public User(){
    }
    
    public void setId(int id){
        this.id = id;  
    }  
      
    public int getId(){  
        return this.id;  
    }  

    public void setName(String name){  
        this.name = name;  
    }  
      
    public String getName(){  
        return this.name;  
    }  

    public void setAge(int age){  
        this.age = age;  
    }  
      
    public int getAge(){  
        return this.age;  
    }  

    public void setSex(String sex){  
        this.sex = sex;  
    }  
      
    public String getSex(){  
        return this.sex;  
    }  

    public void setAddr(String addr){  
        this.addr = addr;  
    }  
      
    public String getAddr(){  
        return this.addr;  
    }  


}