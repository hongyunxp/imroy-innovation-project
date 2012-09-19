package main;

public class AnswerRecord {
    private int intCountA,intCountB,intCountC,intCountD,intCountE;
    private int intCountAll,intCountCorrect;
    private char charCorrectAnswer;
    
    AnswerRecord(){
        intCountCorrect
        =intCountAll
        =intCountA
        =intCountB
        =intCountC
        =intCountD
        =intCountE
        =0;
    }
    
    public void setAnswer(char answer){
        this.charCorrectAnswer = answer;
    }
    
    public void increaseA(){
        this.intCountA++;
    }
    public int getCountA(){
        return this.intCountA;
    }
    
    public void increaseB(){
        this.intCountB++;
    }
    public int getCountB(){
        return this.intCountB;
    }
    public void increaseC(){
        this.intCountC++;
    }
    public int getCountC(){
        return this.intCountC;
    }
    public void increaseD(){
        this.intCountD++;
    }
    public int getCountD(){
        return this.intCountD;
    }
    public void increaseE(){
        this.intCountE++;
    }
    public int getCountE(){
        return this.intCountE;
    }
    
    public int getCountAll(){
        return intCountAll=this.intCountA+this.intCountB+this.intCountC+this.intCountD+this.intCountE;
    }
    public int getCountCorrect(){
        switch(charCorrectAnswer){
            case 'A':return this.intCountA;
            case 'B':return this.intCountB;   
            case 'C':return this.intCountC;
            case 'D':return this.intCountD;
            case 'E':return this.intCountE;
        }
        return 0;
    }
}
