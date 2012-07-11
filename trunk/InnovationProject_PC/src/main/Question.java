package main;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.annotation.processing.Messager;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.awt.Font;


public class Question extends JFrame {

	private JPanel contentPane;
	public static JTextArea show = new JTextArea();
	
	/**
	 * Create the frame.
	 */
	public Question() {
		setTitle("������Ŀ-���ý���");
		 this.addWindowListener(new WindowAdapter(){
             public void windowClosing(WindowEvent e){
             MainFrame.menu_1.setVisible(true);
             }
     });
			
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		show.setFont(new Font("����", Font.PLAIN, 20));
		show.setEditable(false);
		show.setWrapStyleWord(true);
		show.setLineWrap(true);
		show.setBounds(82, 35, 33, 69);
		
		JScrollPane js = new JScrollPane(show);
				
		contentPane.add(js);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		QuestionThread thread = new QuestionThread();
		thread.start();
		
		final JButton btnNewButton = new JButton("��ʼ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//���ͽ�����ɣ����������̣߳�������Ϣ��չʾ
				for(ServerThread s:Socketinitialize.allthread){
					try {
						s.givetoclient("#�������ý���");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				show.append("�ѿ������ý���\n");
				btnNewButton.setEnabled(false);
								
			}
		});
		
		JButton button = new JButton("����");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//���͹ر���ɣ�
				for(ServerThread s:Socketinitialize.allthread){
					try {
						s.givetoclient("#�رտ��ý���");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				show.append("�ѹرտ��ý���\n");
				btnNewButton.setEnabled(true);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(94)
					.addComponent(btnNewButton)
					.addGap(129)
					.addComponent(button))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(btnNewButton)
				.addComponent(button)
		);
		panel.setLayout(gl_panel);
		
		
		
	}	
}
