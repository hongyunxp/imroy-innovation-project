package main.Login;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class Class_add extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField name;
	private JTextField category;
	private JTextField stu_num;
	private JTextField credit;
	private JTextField class_num;
	private JTextField finish_num;
	private JTextField institute;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Class_add dialog = new Class_add();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Class_add() {
	    setResizable(false);
		setTitle("添加课程");
		setBounds(100, 100, 467, 326);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("课程名称：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setBounds(32, 6, 101, 24);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("类别：");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_1.setBounds(68, 40, 101, 24);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("修读人数：");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_2.setBounds(32, 78, 101, 24);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel("学分：");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_3.setBounds(68, 116, 101, 24);
		contentPanel.add(label_3);
		
		JLabel label_4 = new JLabel("课时数：");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_4.setBounds(51, 154, 101, 24);
		contentPanel.add(label_4);
		
		JLabel label_5 = new JLabel("已完成课时数：");
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_5.setBounds(0, 188, 126, 24);
		contentPanel.add(label_5);
		
		JLabel label_6 = new JLabel("所属专业：");
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_6.setBounds(32, 222, 101, 24);
		contentPanel.add(label_6);
		
		name = new JTextField();
		name.setBounds(143, 10, 118, 21);
		contentPanel.add(name);
		name.setColumns(10);
		
		category = new JTextField();
		category.setColumns(10);
		category.setBounds(143, 46, 118, 21);
		contentPanel.add(category);
		
		stu_num = new JTextField();
		stu_num.setColumns(10);
		stu_num.setBounds(143, 82, 118, 21);
		contentPanel.add(stu_num);
		
		credit = new JTextField();
		credit.setColumns(10);
		credit.setBounds(143, 118, 118, 21);
		contentPanel.add(credit);
		
		class_num = new JTextField();
		class_num.setColumns(10);
		class_num.setBounds(143, 154, 118, 21);
		contentPanel.add(class_num);
		
		finish_num = new JTextField();
		finish_num.setColumns(10);
		finish_num.setBounds(143, 192, 118, 21);
		contentPanel.add(finish_num);
		
		institute = new JTextField();
		institute.setColumns(10);
		institute.setBounds(143, 226, 118, 21);
		contentPanel.add(institute);
		
		JButton add = new JButton("注册");
		add.setBounds(348, 81, 93, 23);
		contentPanel.add(add);
		
		JButton back = new JButton("返回");
		back.setBounds(348, 153, 93, 23);
		contentPanel.add(back);
		
		JLabel lblNewLabel = new JLabel("导入学生信息：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel.setBounds(0, 256, 133, 24);
		contentPanel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(143, 257, 189, 24);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JButton scan = new JButton("浏览");
		scan.setBounds(348, 259, 93, 23);
		contentPanel.add(scan);
	}
}
