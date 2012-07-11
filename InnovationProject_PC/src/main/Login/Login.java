package main.Login;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

public class Login extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField username;
	private JPasswordField password;
	public static ArrayList<user_infor> all_user_infor = new ArrayList<user_infor>();
	public static Infor_show dialog = null;

	/**
	 * Launch the application.
	 * 
	 * @throws UnsupportedLookAndFeelException
	 */
	public static void main(String[] args)
			throws UnsupportedLookAndFeelException {
		// Login.setDefaultLookAndFeelDecorated(true);

		// UIManager.setLookAndFeel(new SubstanceMistAquaLookAndFeel());
		//UIManager.setLookAndFeel(new SubstanceCremeLookAndFeel());

		try {
			// JFrame.setDefaultLookAndFeelDecorated(true);
			//Login.setDefaultLookAndFeelDecorated(true);
			Login mainframe = new Login();
			mainframe.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			mainframe.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @throws IOException
	 */
	public Login() throws IOException {
		/* 开始初始化教师信息 */
		this.Initialize();

		// System.out.println(new Date());
		/* 开始初始化UI */

		//setDefaultLookAndFeelDecorated(true);
		/*
		 * try { UIManager.setLookAndFeel(new SubstanceAutumnLookAndFeel()); }
		 * catch (UnsupportedLookAndFeelException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
		setTitle("创新项目-登陆界面");
		setResizable(false);
		setBounds(100, 100, 420, 232);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(UIManager.getColor("Panel.background"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel username_lable = new JLabel("用户名");
		username_lable.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		username_lable.setBackground(Color.DARK_GRAY);

		JLabel password_lable = new JLabel("   密码");
		password_lable.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		password_lable.setBackground(Color.DARK_GRAY);

		username = new JTextField();
		username.setColumns(10);

		password = new JPasswordField();

		JButton button = new JButton("登陆");

		// 登陆按钮事件*************************************************************
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (username.getText().equals("")
						|| password.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入用户名或密码");

				} else {
					int i = 0;
					for (user_infor s : all_user_infor) {
						if (s.getusername().equals(username.getText())) {
							if (s.getpassword().equals(password.getText())) {
								/******************************* 教师信息界面生成 ***************/
								dialog = new Infor_show(s);
								dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								dialog.setVisible(true);
								dispose();

								/******************************** 关闭登陆界面 ******************/
								break;
							} else {
								JOptionPane.showMessageDialog(null, "密码错误");
								username.setText("");
								password.setText("");
								break;
							}
						}
						i++;
					}
					if (all_user_infor.size() == i) {
						JOptionPane.showMessageDialog(null, "该用户名不存在");
						username.setText("");
						password.setText("");
					}

				}

			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 18));

		JButton button_1 = new JButton("注册");
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));

		JButton button_2 = new JButton("退出");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(1);
			}
		});
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addContainerGap(43, Short.MAX_VALUE)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.TRAILING,
																false)
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addComponent(
																				button,
																				GroupLayout.PREFERRED_SIZE,
																				74,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(32))
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								password_lable,
																								GroupLayout.PREFERRED_SIZE,
																								63,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								username_lable,
																								GroupLayout.PREFERRED_SIZE,
																								63,
																								GroupLayout.PREFERRED_SIZE))
																		.addGap(29)))
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addGap(6)
																		.addComponent(
																				button_1,
																				GroupLayout.PREFERRED_SIZE,
																				74,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(36)
																		.addComponent(
																				button_2,
																				GroupLayout.PREFERRED_SIZE,
																				74,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.LEADING,
																								false)
																						.addComponent(
																								password)
																						.addComponent(
																								username,
																								GroupLayout.DEFAULT_SIZE,
																								193,
																								Short.MAX_VALUE))))
										.addContainerGap(49, Short.MAX_VALUE)));
		gl_contentPanel
				.setVerticalGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addGap(37)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																username,
																GroupLayout.PREFERRED_SIZE,
																29,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																username_lable,
																GroupLayout.PREFERRED_SIZE,
																32,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																password,
																GroupLayout.PREFERRED_SIZE,
																29,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																password_lable,
																GroupLayout.PREFERRED_SIZE,
																32,
																GroupLayout.PREFERRED_SIZE))
										.addGap(36)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																button_2,
																GroupLayout.PREFERRED_SIZE,
																31,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																button_1,
																GroupLayout.PREFERRED_SIZE,
																31,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																button,
																GroupLayout.PREFERRED_SIZE,
																31,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(13, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
	}

	public void Initialize() throws IOException {
		/* 初始化教师信息 */
		BufferedReader br = new BufferedReader(new FileReader("user_infor.txt"));
		String s;
		while ((s = br.readLine()) != null) {
			all_user_infor.add(new user_infor(s));
		}
		br.close();
	}
}
