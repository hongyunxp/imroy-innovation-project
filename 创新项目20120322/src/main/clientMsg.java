package main;

public class clientMsg {
	String content,voteContent,examContent;
	boolean havenew=false;
	boolean haveNewVote=false;
	boolean haveNewAns=false;
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
public void getNewAns(String s){
    examContent = s;
    haveNewAns=true;
}
public String sendNews(){
	if(havenew=true){
		havenew=false;
		return content;}
	else return null;
}
public String sendVoteNew(){
    if(haveNewVote){
        haveNewVote=false;
        return voteContent;}
    else return null;
}
public String sendAnsNew(){
    if(haveNewAns){
        haveNewAns=false;
        return examContent;
    }
    else return null;
}
public String getid(){
	return id;
}
}
