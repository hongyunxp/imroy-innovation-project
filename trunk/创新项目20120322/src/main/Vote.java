package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import org.jfree.ui.RefineryUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Vote extends JFrame {

	private JPanel contentPane;
	JCheckBox checkBox_two,checkBox_three,checkBox_four,checkBox_five;
	JRadioButton radioButton_isMultiple;
	JButton btn_ON,btn_OFF;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vote frame = new Vote();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Vote() {
	    setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 435);
		setTitle("课堂投票统计-创新项目-电子课堂辅助系统");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 10, 590, 103);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("常规设置");
		label.setFont(new Font("宋体", Font.BOLD, 18));
		label.setBounds(10, 10, 85, 21);
		panel.add(label);
				
		checkBox_two = new JCheckBox("双选项");
		checkBox_two.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {		        
		        checkBox_three.setSelected(false);
		        checkBox_four.setSelected(false);
		        checkBox_five.setSelected(false);
		        btn_ON.setEnabled(true);
		        if(!checkBox_two.isSelected())
		            btn_ON.setEnabled(false);		            
		    }
		});
		checkBox_two.setBounds(101, 35, 74, 23);
		panel.add(checkBox_two);
		
		checkBox_three = new JCheckBox("三选项");
		checkBox_three.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBox_two.setSelected(false);
                checkBox_four.setSelected(false);
                checkBox_five.setSelected(false);
                btn_ON.setEnabled(true);
                if(!checkBox_three.isSelected())
                    btn_ON.setEnabled(false);
            }
        });
		checkBox_three.setBounds(177, 35, 74, 23);
		panel.add(checkBox_three);
		
		checkBox_four = new JCheckBox("四选项");
		checkBox_four.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBox_three.setSelected(false);
                checkBox_two.setSelected(false);
                checkBox_five.setSelected(false);
                btn_ON.setEnabled(true);
                if(!checkBox_four.isSelected())
                    btn_ON.setEnabled(false);
            }
        });
		checkBox_four.setBounds(253, 35, 74, 23);
		panel.add(checkBox_four);
		
		checkBox_five = new JCheckBox("五选项");
		checkBox_five.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBox_three.setSelected(false);
                checkBox_four.setSelected(false);
                checkBox_two.setSelected(false);
                btn_ON.setEnabled(true);
                if(!checkBox_five.isSelected())
                    btn_ON.setEnabled(false);
            }
        });
		checkBox_five.setBounds(329, 35, 74, 23);
		panel.add(checkBox_five);
		
		radioButton_isMultiple = new JRadioButton("可多选");
		radioButton_isMultiple.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		        if(radioButton_isMultiple.getText().equals("可多选")){
		            radioButton_isMultiple.setText("不可多选");
		        }
		        else
		            radioButton_isMultiple.setText("可多选");
		    }
		});

		radioButton_isMultiple.setBounds(101, 60, 74, 23);
		panel.add(radioButton_isMultiple);
		
		btn_ON = new JButton("开启投票");
		btn_ON.setBounds(487, 11, 93, 23);
		btn_ON.setEnabled(false);
		panel.add(btn_ON);
		
		btn_OFF = new JButton("关闭投票");
		btn_OFF.setBounds(487, 70, 93, 23);
		btn_OFF.setEnabled(false);
		panel.add(btn_OFF);
		
/*		BarChart3DDemo4 localBarChart3DDemo4 =new BarChart3DDemo4("JFreeChart: BarChart3DDemo4.java");
	    localBarChart3DDemo4.pack();
	    RefineryUtilities.centerFrameOnScreen(localBarChart3DDemo4);
	    localBarChart3DDemo4.setVisible(true);*/
	}
}
