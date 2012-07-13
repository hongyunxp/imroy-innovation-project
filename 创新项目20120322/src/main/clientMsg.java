package main;

public class clientMsg {
	String content;
	boolean havenew=false;
	String id;
	
	clientMsg(String s){
		id=s;
	}
	
public void getNews(String s){
	content = s;
	havenew=true;
}
public String sendNews(){
	if(havenew=true){
		havenew=false;
		return content;}
	else return null;
}
public String getid(){
	return id;
}
}
