package main;

public class clientMsg {
	String content,voteContent;
	boolean havenew=false;
	boolean haveNewVote=false;
	String id;
	
	clientMsg(String s){
		id=s;
	}
	
public void getNews(String s){
	content = s;
	havenew=true;
}
public void getNewVote(String s){
    voteContent=s;
    haveNewVote=true;
}
public String sendNews(){
	if(havenew=true){
		havenew=false;
		return content;}
	else return null;
}
public String sendVoteNew(){
    if(haveNewVote=true){
        haveNewVote=false;
        return voteContent;}
    else return null;
}
public String getid(){
	return id;
}
}
