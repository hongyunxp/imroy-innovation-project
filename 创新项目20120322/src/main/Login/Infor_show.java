package main.Login;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import main.MainFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Infor_show extends JDialog {

	private static final long serialVersionUID = 3353827014941313072L;
	private final JPanel contentPanel = new JPanel();
    private static user_infor infor;
    private JComboBox choice_class=null;


	/**
	 * Create the dialog.
	 */
	public Infor_show(user_infor infor) {
		setTitle("��¼��Ϣ");
		this.infor=infor;
		
		setResizable(false);
		setBounds(100, 100, 341, 216);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("������");
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 10, 67, 24);
		contentPanel.add(lblNewLabel);
		
		JLabel label = new JLabel("ְ�ƣ�");
		label.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label.setBounds(10, 44, 67, 24);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("Ժϵ��");
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_1.setBounds(10, 78, 67, 24);
		contentPanel.add(label_1);
		
		JTextPane name = new JTextPane();
		name.setBackground(UIManager.getColor("Panel.background"));
		name.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		name.setEditable(false);
		name.setText(infor.getname());
		name.setBounds(71, 8, 84, 24);
		contentPanel.add(name);
		
		JTextPane position = new JTextPane();
		position.setBackground(UIManager.getColor("Panel.background"));
		position.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		position.setEditable(false);
		position.setText(infor.getposition());
		position.setBounds(71, 42, 84, 24);
		contentPanel.add(position);
		
		JTextPane institute = new JTextPane();
		institute.setBackground(UIManager.getColor("Panel.background"));
		institute.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		institute.setEditable(false);
		institute.setText(infor.getinsitute());
		institute.setBounds(71, 76, 84, 24);
		contentPanel.add(institute);
		
		choice_class = new JComboBox(infor.subject);
		choice_class.setBounds(165, 51, 142, 24);
		contentPanel.add(choice_class);
		
		JLabel label_2 = new JLabel("��ѡ�����Ŀγ̣�");
		label_2.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_2.setBounds(165, 17, 160, 24);
		contentPanel.add(label_2);
		
		JButton load_class = new JButton("����γ�");
		load_class.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					MainFrame mainframe = new MainFrame(choice_class.getSelectedItem().toString());
					mainframe.setVisible(true);
					setVisible(false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		load_class.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		load_class.setBounds(10, 136, 103, 30);
		contentPanel.add(load_class);
		
		JButton show_class = new JButton("�鿴�γ���Ϣ");
		show_class.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Class_show class_show =new Class_show(choice_class.getSelectedItem().toString());
				    class_show.setVisible(true);
				    setVisible(false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		show_class.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		show_class.setBounds(170, 85, 135, 30);
		contentPanel.add(show_class);
		
		JButton add_class = new JButton("��ӿγ�");
		add_class.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		add_class.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		add_class.setBounds(134, 136, 103, 30);
		contentPanel.add(add_class);
		
		JButton logoff = new JButton("ע��");
		logoff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login.main(null);
				dispose();
				
			}
		});
		logoff.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		logoff.setBounds(258, 136, 67, 30);
		contentPanel.add(logoff);
	}
}
