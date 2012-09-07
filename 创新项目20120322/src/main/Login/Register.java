package main.Login;



import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.print.attribute.AttributeSet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class Register extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField username;
	private JTextField name;
	private JTextField position;
	private JTextField institute;
	private JPasswordField pw;
	private JPasswordField pw0;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		try {
			Register dialog = new Register();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public Register() {
		setTitle("ע��");
		setBounds(400, 300, 467, 319);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("�û���:");
		label.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label.setBounds(42, 19, 97, 18);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("����:");
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_1.setBounds(42, 59, 97, 18);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("ȷ������:");
		label_2.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_2.setBounds(42, 100, 97, 18);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel("��ʦ����:");
		label_3.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_3.setBounds(42, 144, 97, 18);
		contentPanel.add(label_3);
		
		JLabel label_4 = new JLabel("��ʦְ��:");
		label_4.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_4.setBounds(42, 187, 97, 18);
		contentPanel.add(label_4);
		
		JLabel label_5 = new JLabel("Ժϵ\\רҵ:");
		label_5.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_5.setBounds(42, 233, 97, 18);
		contentPanel.add(label_5);
		
		LimitedDocument limitedDocument =new LimitedDocument(16);
		limitedDocument.setAllowChar("abcdefghijklmnopqrstuvwxyz0123456789_");
		
		LimitedDocument limitedDocument_pw = new LimitedDocument(16);
		limitedDocument.setAllowChar("abcdefghijklmnopqrstuvwxyz");
		
		username = new JTextField();
		//textField.setDocument(limitedDocument);
		username.setBounds(151, 16, 122, 30);
		contentPanel.add(username);
		username.setColumns(10);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(151, 141, 122, 30);
		contentPanel.add(name);
		
		position = new JTextField();
		position.setColumns(10);
		position.setBounds(151, 184, 122, 30);
		contentPanel.add(position);
		
		institute = new JTextField();
		institute.setColumns(10);
		institute.setBounds(151, 230, 122, 30);
		contentPanel.add(institute);
		
		pw = new JPasswordField();
		//passwordField.setDocument(limitedDocument_pw);
		pw.setBounds(151, 56, 122, 30);
		contentPanel.add(pw);
		
		pw0 = new JPasswordField();
		//passwordField_1.setDocument(limitedDocument_pw);
		pw0.setBounds(151, 97, 122, 30);
		contentPanel.add(pw0);
		
		JButton register = new JButton("ע��");
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					saveInfor();
					JOptionPane.showMessageDialog(null,"ע��ɹ�");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
		});
		register.setBounds(335, 56, 90, 30);
		contentPanel.add(register);
		
		JButton exit = new JButton("����");
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Login.main(null);
				dispose();
			}
		});
		exit.setBounds(335, 184, 90, 30);
		contentPanel.add(exit);
	}
	class LimitedDocument extends PlainDocument {
		private static final long serialVersionUID = 1L;
		private int maxLength = -1;// �������󳤶�
		private String allowCharAsString = null;// ������ַ�����ʽ��0123456789��

		public LimitedDocument() {
			super();
		}

		public LimitedDocument(int maxLength) {
			super();
			this.maxLength = maxLength;
		}

		public void insertString(int offset, String str, AttributeSet attrSet) throws BadLocationException {
			if (str == null) {
				return;
			}
			if (allowCharAsString != null && str.length() == 1) {
				if (allowCharAsString.indexOf(str) == -1) {
					return;// ������Ҫ����ַ���ʽ����ֱ�ӷ��أ���������������
				}
			}
			char[] charVal = str.toCharArray();
			String strOldValue = getText(0, getLength());
			char[] tmp = strOldValue.toCharArray();
			if (maxLength != -1 && (tmp.length + charVal.length > maxLength)) {
				Toolkit.getDefaultToolkit().beep();// ����һ��������
				return;// ���ȴ���ָ���ĳ���maxLength��Ҳֱ�ӷ��أ���������������
			}
			super.insertString(offset, str, (javax.swing.text.AttributeSet) attrSet);
		}

		public void setAllowChar(String str) {
			allowCharAsString = str;
		}
	}
    void saveInfor() throws IOException{
    	//*********************************�ȴ����ע����Ϣ��������*************************
		Login.all_user_infor.add(new user_infor(new String(username.getText()+","+pw.getText()+","
				+name.getText()+","+position.getText()+","+institute.getText())));	
		System.out.println(new String(username.getText()+","+pw.getText()+","
				+name.getText()+","+position.getText()+","+institute.getText()));
		File file = new File("user_infor.txt");
		FileWriter fw = new FileWriter(file);
		BufferedWriter bufw = new BufferedWriter(fw);
		for(user_infor s:Login.all_user_infor){
	        bufw.write(s.getall());
	        bufw.newLine(); 
	        bufw.flush();
	      }
		fw.close();
    }
}
