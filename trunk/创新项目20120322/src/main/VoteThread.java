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
                       if(msg.sendVoteNew().charAt(6)=='A')
                           Vote.count[0]++;
                       if(msg.sendVoteNew().charAt(7)=='B')
                           Vote.count[1]++;
                       if(msg.sendVoteNew().charAt(8)=='C')
                           Vote.count[2]++;
                       if(msg.sendVoteNew().charAt(9)=='D')
                           Vote.count[3]++;
                       if(msg.sendVoteNew().charAt(10)=='E')
                           Vote.count[4]++;
                       if(msg.sendVoteNew().charAt(11)=='X')
                           Vote.count[5]++;                       
                       }
                   }
               Vote.intCountAll=Vote.count[0]+Vote.count[1]+Vote.count[2]+Vote.count[3]+Vote.count[4]+Vote.count[5];
               }
            }
        }
}
