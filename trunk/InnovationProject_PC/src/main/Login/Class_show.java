package main.Login;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Class_show extends JDialog {
	private JTextField classname;
	private JTextField coursecategory;
	private JTextField stunum;
	private JTextField credit;
	private JTextField classnum;
	private JTextField onnum;
	private JTextField institute;


	/**
	 * Create the dialog.
	 * @throws IOException 
	 */
	public Class_show(String s) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(s+".txt"));
		String[] m;
		m = br.readLine().split(",");
        String[] infor = {
        		m[1],
        		m[2],
        		m[3],
        		m[4],
        		m[5],
        		m[6],
        		m[7]
        };
		
		br.close();
		
		setTitle("课程信息-"+s);
		setBounds(100, 100, 400, 209);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("课程名称：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(10, 10, 68, 22);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("课程分类：");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_1.setBounds(10, 42, 68, 22);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("选课人数：");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_2.setBounds(10, 74, 68, 22);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("学分：");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_3.setBounds(10, 106, 68, 22);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("课时数：");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_4.setBounds(197, 10, 68, 22);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("上课次数：");
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_5.setBounds(197, 42, 68, 22);
		getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("院系\\专业：");
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_6.setBounds(197, 74, 68, 22);
		getContentPane().add(label_6);
		
		classname = new JTextField();
		classname.setText(infor[0]);
		classname.setEditable(false);
		classname.setBounds(71, 11, 116, 21);
		getContentPane().add(classname);
		classname.setColumns(10);
		
		coursecategory = new JTextField();
		coursecategory.setText(infor[1]);
		coursecategory.setEditable(false);
		coursecategory.setColumns(10);
		coursecategory.setBounds(71, 43, 116, 21);
		getContentPane().add(coursecategory);
		
		stunum = new JTextField();
		stunum.setText(infor[2]);
		stunum.setEditable(false);
		stunum.setColumns(10);
		stunum.setBounds(71, 75, 58, 21);
		getContentPane().add(stunum);
		
		credit = new JTextField();
		credit.setText(infor[3]);
		credit.setEditable(false);
		credit.setColumns(10);
		credit.setBounds(71, 106, 58, 21);
		getContentPane().add(credit);
		
		classnum = new JTextField();
		classnum.setText(infor[4]);
		classnum.setEditable(false);
		classnum.setColumns(10);
		classnum.setBounds(258, 11, 58, 21);
		getContentPane().add(classnum);
		
		onnum = new JTextField();
		onnum.setText(infor[5]);
		onnum.setEditable(false);
		onnum.setColumns(10);
		onnum.setBounds(258, 43, 58, 21);
		getContentPane().add(onnum);
		
		institute = new JTextField();
		institute.setText(infor[6]);
		institute.setEditable(false);
		institute.setColumns(10);
		institute.setBounds(258, 75, 116, 21);
		getContentPane().add(institute);
		
		JButton load_class = new JButton("载入课程");
		load_class.setBounds(71, 138, 93, 23);
		getContentPane().add(load_class);
		
		JButton back = new JButton("返回");
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login.dialog.setVisible(true);
				dispose();
				}
		});
		back.setBounds(258, 138, 93, 23);
		getContentPane().add(back);
		
		
	}
}
