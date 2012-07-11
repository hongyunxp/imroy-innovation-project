package main.Login;

import java.util.Vector;

public class user_infor {
	String username;
	String password;
	String name;
	String position;
	String institute;
	public Vector<String> subject = new Vector<String>();

		
    public user_infor(String s){
    	String[] tem_subject;
    	String[] tem = s.split(",");
    	username = tem[0];
    	password = tem[1];
    	name = tem[2];
    	position = tem[3];
    	institute = tem[4];
    	if(tem.length>5){
    	tem_subject = tem[5].split(";");
    	int i =0;
    	while(i<tem_subject.length){
    		subject.add(tem_subject[i]);
    		i++;
    	}
    	}
    	else subject.add("ÔÝÎÞ¿Î³Ì");
    	}
    	
    
    public String getusername(){
    	return this.username;
    }
    public String getpassword(){
    	return this.password;
    }
    public String getname(){
    	return this.name;
    }
    public String getposition(){
    	return this.position;
    }
    public String getinsitute(){
    	return this.institute;
    }
}
