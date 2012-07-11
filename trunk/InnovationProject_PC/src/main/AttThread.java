package main;

import javax.swing.JFrame;

public class AttThread extends Thread{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			if(Socketinitialize.allmsg.size()!=0){
			for(clientmsg msg:Socketinitialize.allmsg){
				for(int i=0;i<MainFrame.inforshow.getRowCount();i++){
					if(msg.id.equals(MainFrame.inforshow.getValueAt(i,1).toString())){
						String[]tem={
								"●已到",
								MainFrame.inforshow.getValueAt(i,1).toString(),
								MainFrame.inforshow.getValueAt(i,2).toString(),
								MainFrame.inforshow.getValueAt(i,3).toString(),
								MainFrame.inforshow.getValueAt(i,4).toString(),
						};
						MainFrame.att_tableModel.addRow(tem);
						MainFrame.unatt_tableModel.removeRow(i);
						for(Studentinfor stuinfor:MainFrame.allstudents){
							if(stuinfor.id.equals(MainFrame.inforshow.getValueAt(i,1).toString())){
								stuinfor.att_time++;
							}
						}
					}
				}
			}
		}
			else
				System.out.println("暂时没有通信通道");
		}
		
	}

}
