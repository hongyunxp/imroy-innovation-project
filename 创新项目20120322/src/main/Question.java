package main;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.annotation.processing.Messager;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;


public class Question extends JFrame {

	private JPanel contentPane;
	static JScrollBar bar;
	public static JTextArea show = new JTextArea();
	QuestionThread thread = new QuestionThread();
	/**
	 * Create the frame.
	 */
	public Question() {
	    setResizable(false);
		setTitle("������Ŀ-���ý���");
		 this.addWindowListener(new WindowAdapter(){
             public void windowClosing(WindowEvent e){
                 thread.stop();
             MainFrame.menu_1.setEnabled(true);
             }
     });
		setBounds(100, 100, 626, 435);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("ѡ��");
		menuBar.add(menu);
		
		JMenu mnNewMenu = new JMenu("����");
		menuBar.add(mnNewMenu);
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
		bar=js.getVerticalScrollBar();
	    
				
		contentPane.add(js);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
				
		thread.start();
		
		final JButton btnNewButton = new JButton("������������");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//���ͽ�����ɣ����������̣߳�������Ϣ��չʾ
				for(ServerThread s:Socketinitialize.allthread){
					try {
						s.givetoclient("comON");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				show.append("----------------------�ѿ������ý���\n");
				btnNewButton.setEnabled(false);
								
			}
		});
		
		JButton button = new JButton("�رս�������");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//���͹ر���ɣ�
				for(ServerThread s:Socketinitialize.allthread){
					try {
						s.givetoclient("comOFF");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				show.append("----------------------�ѹرտ��ý���\n");
				btnNewButton.setEnabled(true);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
		    gl_panel.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_panel.createSequentialGroup()
		            .addGap(94)
		            .addComponent(btnNewButton)
		            .addGap(214)
		            .addComponent(button)
		            .addGap(92))
		);
		gl_panel.setVerticalGroup(
		    gl_panel.createParallelGroup(Alignment.LEADING)
		        .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
		            .addComponent(btnNewButton)
		            .addComponent(button))
		);
		panel.setLayout(gl_panel);
		
		
		
	}	
}
