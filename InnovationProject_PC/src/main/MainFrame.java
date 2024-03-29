package main;

import java.awt.GridLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTree;
import javax.swing.table.TableColumn;

import java.awt.BorderLayout;
import java.awt.List;
import java.awt.Choice;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JTable;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	public static ArrayList<Studentinfor> allstudents = new ArrayList<Studentinfor>();
	public static ArrayList<Studentinfor> unatt = new ArrayList<Studentinfor>();
	public static ArrayList<Studentinfor> att = new ArrayList<Studentinfor>();
	public static ArrayList<Studentinfor> vacate = new ArrayList<Studentinfor>();

	JMenu menu = new JMenu("考勤控制");
	JMenuItem menuItem = new JMenuItem("开启手机端签到");
	JMenuItem menuItem_1 = new JMenuItem("关闭手机端签到");
	JMenuItem menuItem_2 = new JMenuItem("保存本节课考勤信息");
	static JMenu menu_1 = new JMenu("课堂交流");
	JMenu menu_2 = new JMenu("投票统计");
	JMenu menu_3 = new JMenu("当堂测试");
	JMenu menu_4 = new JMenu("帮助");

	private final JButton att_button = new JButton("已到");
	private final JButton unatt_button = new JButton("未到");
	private final JButton vocate_button = new JButton("请假");
	private final JButton late_button = new JButton("迟到");

	static JTable inforshow;
	static JTable att_inforshow;
	static JTable vocate_inforshow;
	static JTable late_inforshow;

	public static DefaultTableModel late_tableModel = null;
	public static DefaultTableModel unatt_tableModel = null;
	public static DefaultTableModel att_tableModel = null;
	public static DefaultTableModel vocate_tableModel = null;
	private int count;

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public MainFrame(String s) throws IOException {
		// ***************当该窗口关闭时保存学生、课堂信息*******************************
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

			}
		});
		/*
		 * 开始初始化各项信息（学生信息、通信连接）
		 */
		inforinitialize(s);
		socketinitialize();

		/*
		 * 开始初始化UI界面、动作
		 */

		setTitle("创新项目-电子课堂辅助系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1029, 610);
		setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuBar.add(menu);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AttThread attthread = new AttThread();
				attthread.setDaemon(true);
				attthread.start();
				menuItem.setEnabled(false);
			}
		});
		menuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

			}
		});
		menu.add(menuItem);
		menu.add(menuItem_1);
		menu.add(menuItem_2);

		menu_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Question question = new Question();
				question.setVisible(true);
				menu_1.setVisible(false);
			}
		});
		menuBar.add(menu_1);
		menuBar.add(menu_2);
		menuBar.add(menu_3);
		menuBar.add(menu_4);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		att_button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		att_button.setBounds(460, 50, 93, 36);
		contentPane.add(att_button);

		unatt_button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		unatt_button.setBounds(460, 250, 93, 36);
		contentPane.add(unatt_button);

		vocate_button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		vocate_button.setBounds(460, 350, 93, 36);
		contentPane.add(vocate_button);

		late_button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		late_button.setBounds(460, 150, 93, 36);
		contentPane.add(late_button);

		/**************************** 生成未出勤学生信息的JTable ***************************/

		String[] columnNames = { "出勤状态", "学号", "姓名", "专业", "出勤率" }; // 列名
		String[][] tableVales = {}; // 数据
		unatt_tableModel = new DefaultTableModel(tableVales, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		inforshow = new JTable(unatt_tableModel);
		inforshow.setFont(new Font("宋体", Font.PLAIN, 13));

		for (int m = 0; m < this.count; m++) {
			String[] rowValues = { "○未到"/* 需要修改 */,
					this.allstudents.get(m).getid(),
					this.allstudents.get(m).getname(),
					this.allstudents.get(m).getmajor(),
					String.valueOf(this.allstudents.get(m).getatt()) };
			unatt_tableModel.addRow(rowValues);
		}

		inforshow.getColumnModel().getColumn(0).setPreferredWidth(50);
		inforshow.getColumnModel().getColumn(1).setPreferredWidth(80);
		inforshow.getColumnModel().getColumn(4).setPreferredWidth(30);
		contentPane.setLayout(null);
		inforshow.setRowHeight(20);

		JScrollPane scrollPane = new JScrollPane();// 设为可滚动面板
		scrollPane.setBounds(592, 0, 422, 369);
		scrollPane.setViewportView(inforshow);

		contentPane.add(scrollPane);

		/**************************** 生成未出勤学生信息的JTable ***************************/
		/**************************** 生成出勤学生信息的JTable ****************************/

		att_tableModel = new DefaultTableModel(tableVales, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		att_inforshow = new JTable(att_tableModel);
		att_inforshow.setFont(new Font("宋体", Font.PLAIN, 13));
		att_inforshow.setEnabled(true);

		att_inforshow.getColumnModel().getColumn(0).setPreferredWidth(50);
		att_inforshow.getColumnModel().getColumn(1).setPreferredWidth(80);
		att_inforshow.getColumnModel().getColumn(4).setPreferredWidth(30);
		att_inforshow.setRowHeight(20);

		JScrollPane att_scrollPane = new JScrollPane();// 设为可滚动面板
		att_scrollPane.setBounds(0, 0, 422, 369);
		att_scrollPane.setViewportView(att_inforshow);

		contentPane.add(att_scrollPane);
		/**************************** 生成出勤学生信息的JTable ***************************/
		/**************************** 生成请假学生信息的JTable ***************************/

		vocate_tableModel = new DefaultTableModel(tableVales, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		vocate_inforshow = new JTable(vocate_tableModel);
		vocate_inforshow.setFont(new Font("宋体", Font.PLAIN, 13));
		vocate_inforshow.setEnabled(true);

		vocate_inforshow.getColumnModel().getColumn(0).setPreferredWidth(50);
		vocate_inforshow.getColumnModel().getColumn(1).setPreferredWidth(80);
		vocate_inforshow.getColumnModel().getColumn(4).setPreferredWidth(30);
		vocate_inforshow.setRowHeight(20);

		JScrollPane vocate_scrollPane = new JScrollPane();// 设为可滚动面板
		vocate_scrollPane.setBounds(592, 381, 422, 172);
		vocate_scrollPane.setViewportView(vocate_inforshow);

		contentPane.add(vocate_scrollPane);

		/**************************** 生成请假学生信息的JTable ***************************/
		/**************************** 生成迟到学生信息的JTable ***************************/

		late_tableModel = new DefaultTableModel(tableVales, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		late_inforshow = new JTable(late_tableModel);
		late_inforshow.setFont(new Font("宋体", Font.PLAIN, 13));
		late_inforshow.setEnabled(true);

		late_inforshow.getColumnModel().getColumn(0).setPreferredWidth(50);
		late_inforshow.getColumnModel().getColumn(1).setPreferredWidth(80);
		late_inforshow.getColumnModel().getColumn(4).setPreferredWidth(30);
		late_inforshow.setRowHeight(20);

		JScrollPane late_scrollPane = new JScrollPane();// 设为可滚动面板
		late_scrollPane.setBounds(0, 381, 422, 172);
		late_scrollPane.setViewportView(late_inforshow);

		contentPane.add(late_scrollPane);

		/**************************** 生成迟到学生信息的JTable ***************************/

		/**************************** 添加关于Jtable的按钮事件监听器 ***************************/

		att_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String[] tem = {
						"●已到",
						inforshow.getValueAt(inforshow.getSelectedRow(), 1)
								.toString(),
						inforshow.getValueAt(inforshow.getSelectedRow(), 2)
								.toString(),
						inforshow.getValueAt(inforshow.getSelectedRow(), 3)
								.toString(),
						inforshow.getValueAt(inforshow.getSelectedRow(), 4)
								.toString(), };
				for (Studentinfor stuinfor : allstudents) {
					if (stuinfor.id.equals(inforshow.getValueAt(
							inforshow.getSelectedRow(), 1).toString())) {
						stuinfor.att_time++;
					}
				}
				att_tableModel.addRow(tem);
				unatt_tableModel.removeRow(inforshow.getSelectedRow());
			}
		});

		late_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String[] tem = {
						"◎迟到",
						inforshow.getValueAt(inforshow.getSelectedRow(), 1)
								.toString(),
						inforshow.getValueAt(inforshow.getSelectedRow(), 2)
								.toString(),
						inforshow.getValueAt(inforshow.getSelectedRow(), 3)
								.toString(),
						inforshow.getValueAt(inforshow.getSelectedRow(), 4)
								.toString(), };
				for (Studentinfor stuinfor : allstudents) {
					if (stuinfor.id.equals(inforshow.getValueAt(
							inforshow.getSelectedRow(), 1).toString())) {
						stuinfor.att_time++;
					}
				}
				late_tableModel.addRow(tem);
				unatt_tableModel.removeRow(inforshow.getSelectedRow());
			}
		});

		unatt_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String[] tem = {
						"○未到",
						att_inforshow.getValueAt(
								att_inforshow.getSelectedRow(), 1).toString(),
						att_inforshow.getValueAt(
								att_inforshow.getSelectedRow(), 2).toString(),
						att_inforshow.getValueAt(
								att_inforshow.getSelectedRow(), 3).toString(),
						att_inforshow.getValueAt(
								att_inforshow.getSelectedRow(), 4).toString(), };

				unatt_tableModel.addRow(tem);
				att_tableModel.removeRow(att_inforshow.getSelectedRow());
			}
		});

		vocate_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String[] tem = {
						"○未到",
						inforshow.getValueAt(inforshow.getSelectedRow(), 1)
								.toString(),
						inforshow.getValueAt(inforshow.getSelectedRow(), 2)
								.toString(),
						inforshow.getValueAt(inforshow.getSelectedRow(), 3)
								.toString(),
						inforshow.getValueAt(inforshow.getSelectedRow(), 4)
								.toString(), };
				for (Studentinfor stuinfor : allstudents) {
					if (stuinfor.id.equals(inforshow.getValueAt(
							inforshow.getSelectedRow(), 1).toString())) {
						stuinfor.att_time++;
					}
				}
				vocate_tableModel.addRow(tem);
				unatt_tableModel.removeRow(inforshow.getSelectedRow());
			}
		});

		/**************************** 添加关于Jtable的按钮事件监听器 ***************************/
	}

	public void inforinitialize(String classname) throws IOException {
		// 初始化所有学生信息********************************************************
		BufferedReader br = new BufferedReader(new FileReader(classname
				+ ".txt"));
		String s;
		int i = 0;
		while ((s = br.readLine()) != null) {
			if (s.charAt(0) != '#') {
				allstudents.add(new Studentinfor(s));
				i++;
			}
		}
		count = i;
		br.close();
		// 学生信息复制到未出勤list中
		for (int m = 0; m < allstudents.size(); m++) {
			unatt.add(allstudents.get(m));
		}
	}

	public void socketinitialize() {
		Socketinitialize sktinitialize = new Socketinitialize();
		sktinitialize.setDaemon(true);
		sktinitialize.start();
	}

}
