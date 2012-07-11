package main;


import java.util.*;
public class Studentinfor {
	
	public String id;
	public String name;
	public String major;
	public double att;
	public int att_time;

	
	public Studentinfor(String infor){
		
		String[]tem;
		tem = infor.split(",");
		id = tem[0];
		name = tem[1];
		major = tem[2];
		att = Double.valueOf(tem[3]);
		att_time = Integer.valueOf(tem[4]);
		

	}
	public String getid(){
		return id;
	}
	public String getname(){
		return name;
	}
	public String getmajor(){
		return major;
	}
	public double getatt(){
		return att;
	}
	public int getatt_time(){
		return att_time;
	}
		

}
