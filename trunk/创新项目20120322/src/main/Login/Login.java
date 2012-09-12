package main.Login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField username;
	private JPasswordField password;
	public static ArrayList<user_infor> all_user_infor = new ArrayList<user_infor>();
    public static Infor_show dialog = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    try{
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
            UIManager.getLookAndFeelDefaults().put("defaultFont",new Font("Microsoft Yahei",Font.PLAIN,12));
           }catch(Exception e){
            e.printStackTrace();
           } 
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws IOException 
	 */
	public Login() throws IOException {
		/*开始初始化教师信息*/
		this.Initialize();
		System.out.println(new Date());
		/*开始初始化UI*/
		setTitle("创新项目-登陆界面");
		setResizable(false);
		setBounds(400,300, 420, 232);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(UIManager.getColor("Panel.background"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel username_lable = new JLabel("用户名");
		username_lable.setBounds(69, 42, 63, 32);
		username_lable.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		username_lable.setBackground(Color.DARK_GRAY);
		
		JLabel password_lable = new JLabel("   密码");
		password_lable.setBounds(69, 85, 63, 32);
		password_lable.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		password_lable.setBackground(Color.DARK_GRAY);
		
		username = new JTextField();
		username.setBounds(161, 46, 193, 29);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(161, 89, 193, 29);
		
		JButton button = new JButton("登陆");
		button.setBounds(55, 154, 74, 31);
		
		//登陆按钮事件*************************************************************
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(username.getText().equals("")||password.getText().equals("")){
					JOptionPane.showMessageDialog(null,"请输入用户名或密码");
					
				}
				else{
					int i=0;
					for(user_infor s:all_user_infor){
						if(s.getusername().equals(username.getText())){
								if(s.getpassword().equals(password.getText())){
/*******************************教师信息界面生成***************/
									dialog = new Infor_show(s);
									dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
									dialog.setVisible(true);
									dispose();
									
/********************************关闭登陆界面******************/
							break;
							}
								else
									{JOptionPane.showMessageDialog(null,"密码错误");
									username.setText("");
									password.setText("");
								    break;}
						}
						i++;	
					}
					if(all_user_infor.size()==i){
						JOptionPane.showMessageDialog(null,"该用户名不存在");
						username.setText("");
						password.setText("");
					}
					
					
				}
					
				
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		
		JButton button_1 = new JButton("注册");
		button_1.setBounds(167, 154, 74, 31);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Register dialog = new Register();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				dispose();
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		
		JButton button_2 = new JButton("退出");
		button_2.setBounds(277, 154, 74, 31);
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(1);
			}
		});
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		contentPanel.setLayout(null);
		contentPanel.add(button);
		contentPanel.add(password_lable);
		contentPanel.add(username_lable);
		contentPanel.add(button_1);
		contentPanel.add(button_2);
		contentPanel.add(password);
		contentPanel.add(username);
	}
	
	public void Initialize() throws IOException{
	/*初始化教师信息*/
		BufferedReader br = new BufferedReader(new FileReader("user_infor.txt"));
		String s;
		while ((s = br.readLine()) != null) {
		    all_user_infor.add(new user_infor(s));
		    }
		br.close();
	}
}
