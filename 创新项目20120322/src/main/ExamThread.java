package main;

public class ExamThread extends Thread{
    @Override
    public void run() {
        if(Socketinitialize.allthread.size()!=0){
            while(true){
               for(clientMsg msg:Socketinitialize.allmsg){
                   if(msg.haveNewAns){
                       String temAns = msg.sendAnsNew();
                       for(int i=0;i<Examination.intProblemNum;i++){
                           switch (temAns.charAt(6+i)){
                               case 'A':Examination.arrlistAns.get(i).increaseA();break;
                               case 'B':Examination.arrlistAns.get(i).increaseB();break;
                               case 'C':Examination.arrlistAns.get(i).increaseC();break;
                               case 'D':Examination.arrlistAns.get(i).increaseD();break;
                               case 'E':Examination.arrlistAns.get(i).increaseE();break;
                           }
                       }
                   }
                 }
               }
            }
        }
}
