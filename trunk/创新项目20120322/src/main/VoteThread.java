package main;

public class VoteThread extends Thread{
    
    @Override
    public void run() {
        //测试使用的数据
        //int ACount = 25,BCount = 32,CCount = 44,DCount = 50,ECount = 12,XCount = 5;
        //实际初始化各选项所得票数
        int ACount = 0,BCount = 0,CCount = 0,DCount = 0,ECount = 0,XCount = 0;
        
        if(Socketinitialize.allthread.size()!=0){
            while(true){
               for(clientMsg msg:Socketinitialize.allmsg){
                   if(msg.haveNewVote){
                       System.out.print("1");
                       if(msg.sendVoteNew().equals("A"))
                           ACount++;
                       else if(msg.sendVoteNew().equals("B"))
                           BCount++;
                       else if(msg.sendVoteNew().equals("C"))
                           CCount++;
                       else if(msg.sendVoteNew().equals("D"))
                           DCount++;
                       else if(msg.sendVoteNew().equals("E"))
                           ECount++;
                       else if(msg.sendVoteNew().equals("X"))
                           XCount++;                       
                       }
                   }
               Vote.count[0]=ACount;
               Vote.count[1]=BCount;
               Vote.count[2]=CCount;
               Vote.count[3]=DCount;
               Vote.count[4]=ECount;
               Vote.count[5]=XCount;
               Vote.intCountAll=ACount+BCount+CCount+DCount+ECount+XCount;
               }
            }
        else {            
            Vote.count[0]=ACount;
            Vote.count[1]=BCount;
            Vote.count[2]=CCount;
            Vote.count[3]=DCount;
            Vote.count[4]=ECount;
            Vote.count[5]=XCount;
            Vote.intCountAll=ACount+BCount+CCount+DCount+ECount+XCount;
         }
    }
}
