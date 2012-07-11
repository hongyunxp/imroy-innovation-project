package main;

public class clientmsg {
	String content;
	
	boolean havenew=false;
	
	String id;
	
	clientmsg(String s){
		id=s;
	}
public void getnews(String s){
	content = s;
	havenew=true;
}
public String sendnews(){
	if(havenew=true){
		havenew=false;
		return content;}
	else return null;
}
}
