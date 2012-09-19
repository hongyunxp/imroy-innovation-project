package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;

public class Examination extends JFrame {

    private JPanel contentPane;
    JCheckBox checkBox_5,checkBox_10,checkBox_15,checkBox_20,checkBox_three,checkBox_four,checkBox_five;
    JButton btn_ON,btn_OFF;
    static int intProblemNum,intChoiceNum;
    private JLabel label_show,lblNewLabel_1,label,label_1,label_2,label_3,label_4,label_5;
    private JTextField textField_1to5,textField_6to10,textField_11to15,textField_16to20;
    boolean canStart_0=false;
    boolean canStart_1=false;
    static ArrayList<AnswerRecord> arrlistAns = new ArrayList<AnswerRecord>();
    ChartPanel chartpanelAnswer,chartpanelChoice;
    ExamThread temExamThread;

    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                try{
                    UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
                    UIManager.getLookAndFeelDefaults().put("defaultFont",new Font("Microsoft Yahei",Font.PLAIN,12));
                   }catch(Exception e){
                    e.printStackTrace();
                   } 
                try {
                    Examination frame = new Examination();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Examination() {
        temExamThread = new ExamThread();
        
        setResizable(false);
        setBounds(300,75, 760, 696);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                if(temExamThread.isAlive())
                    temExamThread.stop();
                this.windowClosed(e);
                MainFrame.menu_3.setEnabled(true);
            }
    });
        
        JPanel panelSetting = new JPanel();
        panelSetting.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panelSetting.setBounds(10, 10, 736, 196);
        contentPane.add(panelSetting);
        panelSetting.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("常规设置");
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 18));
        lblNewLabel.setBounds(10, 10, 85, 21);
        panelSetting.add(lblNewLabel);
        
        chartpanelAnswer = createDemoPanel();
        chartpanelAnswer.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        chartpanelAnswer.setBounds(10, 216, 360, 410);
        contentPane.add(chartpanelAnswer);
        
        chartpanelChoice = createChoicePanel();
        chartpanelChoice.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        chartpanelChoice.setBounds(385, 216, 360, 410);
        contentPane.add(chartpanelChoice);
        
        checkBox_5 = new JCheckBox("5道题目");
        checkBox_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {                
                checkBox_10.setSelected(false);
                checkBox_15.setSelected(false);
                checkBox_20.setSelected(false);
                if(!checkBox_5.isSelected()){
                    canStart_0=false;
                    btn_ON.setEnabled(false);
                }                    
                else {
                    canStart_0=true;
                    if(canStart_1)
                        btn_ON.setEnabled(true);
                    intProblemNum=5;
                }
            }
        });
        checkBox_5.setBounds(130, 35, 74, 23);
        panelSetting.add(checkBox_5);
        
        checkBox_10 = new JCheckBox("10道题目");
        checkBox_10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBox_5.setSelected(false);
                checkBox_15.setSelected(false);
                checkBox_20.setSelected(false);
                if(!checkBox_10.isSelected()){
                    canStart_0=false;
                    btn_ON.setEnabled(false);
                }                    
                else {
                    canStart_0=true;
                    if(canStart_1)
                        btn_ON.setEnabled(true);
                    intProblemNum=10;
                }
            }
        });
        checkBox_10.setBounds(205, 35, 85, 23);
        panelSetting.add(checkBox_10);
        
        checkBox_15 = new JCheckBox("15道题目");
        checkBox_15.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBox_10.setSelected(false);
                checkBox_5.setSelected(false);
                checkBox_20.setSelected(false);
                if(!checkBox_15.isSelected()){
                    canStart_0=false;
                    btn_ON.setEnabled(false);
                }                    
                else {
                    canStart_0=true;
                    if(canStart_1)
                        btn_ON.setEnabled(true);
                    intProblemNum=15;
                }
            }
        });
        checkBox_15.setBounds(292, 35, 85, 23);
        panelSetting.add(checkBox_15);
        
        checkBox_20 = new JCheckBox("20道题目");
        checkBox_20.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBox_10.setSelected(false);
                checkBox_15.setSelected(false);
                checkBox_5.setSelected(false);
                if(!checkBox_20.isSelected()){
                    canStart_0=false;
                    btn_ON.setEnabled(false);
                }                    
                else {
                    canStart_0=true;
                    if(canStart_1)
                        btn_ON.setEnabled(true);
                    intProblemNum=20;
                }
            }
        });
        checkBox_20.setBounds(379, 35, 85, 23);
        panelSetting.add(checkBox_20);
        
        btn_ON = new JButton("开启测验功能");
        btn_ON.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch(intProblemNum){
                    case 5:
                        textField_1to5.setEnabled(true);
                        break;
                    case 10:
                        textField_1to5.setEnabled(true);
                    textField_6to10.setEnabled(true);
                    break;
                    case 15:
                        textField_1to5.setEnabled(true);
                    textField_6to10.setEnabled(true);
                    textField_11to15.setEnabled(true);
                    break;
                    case 20:
                        textField_1to5.setEnabled(true);
                    textField_6to10.setEnabled(true);
                    textField_11to15.setEnabled(true);
                    textField_16to20.setEnabled(true);
                    break;
                }
                btn_OFF.setEnabled(true);
                btn_ON.setEnabled(false);
                canStart_0 = canStart_1 = false;
                label_show.setText("已开启测验功能，正在收集学生提交的答案，请稍候");
                label_show.setForeground(Color.RED);
                //发送开启当堂测验权限
              for(ServerThread s:Socketinitialize.allthread){
                  try {
                      s.givetoclient("examON,"+intProblemNum+","
                  +intChoiceNum);
                      
                  } catch (IOException e1) {
                      e1.printStackTrace();
                  }
              }
              
              for(int i=0;i<intProblemNum;i++){
                  arrlistAns.add(new AnswerRecord());
              }
              if(!temExamThread.isAlive())
              temExamThread.start();
                
            }
        });
        btn_ON.setEnabled(false);
        btn_ON.setBounds(582, 35, 126, 23);
        panelSetting.add(btn_ON);
        
        btn_OFF = new JButton("关闭测验功能");
        btn_OFF.addMouseListener(new MouseAdapter() {
            @SuppressWarnings("deprecation")
            @Override

//**********************************************************************************
//**********************************************************************************
            public void mouseClicked(MouseEvent e) {                
                String strCorrectAns = textField_1to5.getText().toUpperCase();
                if(textField_16to20.isEnabled()){
                    strCorrectAns = strCorrectAns + textField_6to10.getText().toUpperCase()+
                            textField_11to15.getText().toUpperCase()+
                            textField_16to20.getText().toUpperCase();
                }
                else if(textField_11to15.isEnabled()){
                    strCorrectAns = strCorrectAns + textField_6to10.getText().toUpperCase()+
                            textField_11to15.getText().toUpperCase();
                }
                else if(textField_6to10.isEnabled()){
                    strCorrectAns = strCorrectAns + textField_6to10.getText().toUpperCase();
                }
                if(strCorrectAns.length()!=intProblemNum){
                int i =0;
                for(AnswerRecord ar:arrlistAns){
                    if(i<intProblemNum)
                    ar.setAnswer(strCorrectAns.charAt(i));
                    else break;
                    i++;
                }//获取正确答案，并存入记录学生反馈答案的list中
                chartpanelAnswer.setVisible(false);
                CategoryDataset localCategoryDataset = createNewDataset(arrlistAns);
                JFreeChart localJFreeChart = createChart(localCategoryDataset);
                chartpanelAnswer = new ChartPanel(localJFreeChart);
                chartpanelAnswer.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
                chartpanelAnswer.setBounds(10, 216, 360, 410);
                chartpanelAnswer.setVisible(true);
                contentPane.add(chartpanelAnswer);
                chartpanelAnswer.validate();
                
                chartpanelChoice.setVisible(false);
                CategoryDataset localChoiceDataset = createNewChoiceDataset(arrlistAns);
                JFreeChart fcChoice = createChoiceChart(localChoiceDataset);
                chartpanelChoice = new ChartPanel(fcChoice);
                chartpanelChoice.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
                chartpanelChoice.setBounds(385, 216, 360, 410);
                chartpanelChoice.setVisible(true);
                contentPane.add(chartpanelChoice);
                chartpanelChoice.validate();
                
                btn_OFF.setEnabled(false);
                checkBox_5.setSelected(false);
                checkBox_10.setSelected(false);
                checkBox_15.setSelected(false);
                checkBox_20.setSelected(false);
                checkBox_three.setSelected(false);
                checkBox_four.setSelected(false);
                checkBox_five.setSelected(false);
                textField_1to5.setText("");
                textField_6to10.setText("");
                textField_11to15.setText("");
                textField_16to20.setText("");
                textField_1to5.setEnabled(false);
                textField_6to10.setEnabled(false);
                textField_11to15.setEnabled(false);
                textField_16to20.setEnabled(false);
                label_show.setText("已关闭测验功能，各题正确率如图所示");
                label_show.setForeground(Color.BLACK);
                arrlistAns.clear();
                }else 
                    JOptionPane.showMessageDialog(null,"请填写完整的测试题答案");
            }
        });
        
        btn_OFF.setEnabled(false);
        btn_OFF.setBounds(582, 135, 126, 23);
        panelSetting.add(btn_OFF);
        
        lblNewLabel_1 = new JLabel("题目数量：");
        lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 12));
        lblNewLabel_1.setBounds(50, 39, 74, 15);
        panelSetting.add(lblNewLabel_1);
        
        label = new JLabel("选项数目：");
        label.setFont(new Font("宋体", Font.BOLD, 12));
        label.setBounds(50, 89, 74, 15);
        panelSetting.add(label);
        
        checkBox_three = new JCheckBox("三选项");
        checkBox_three.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBox_four.setSelected(false);
                checkBox_five.setSelected(false);
                if(!checkBox_three.isSelected())
                    btn_ON.setEnabled(false);
                else {
                    canStart_1=true;
                    if(canStart_0)
                        btn_ON.setEnabled(true);
                    intChoiceNum=3;
                }
            }
        });
        checkBox_three.setBounds(130, 85, 74, 23);
        panelSetting.add(checkBox_three);
        
        checkBox_four = new JCheckBox("四选项");
        checkBox_four.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBox_three.setSelected(false);
                checkBox_five.setSelected(false);
                if(!checkBox_four.isSelected())
                    btn_ON.setEnabled(false);
                else {
                    canStart_1=true;
                    if(canStart_0)
                        btn_ON.setEnabled(true);
                    intChoiceNum=4;
                }
            }
        });
        checkBox_four.setBounds(205, 85, 74, 23);
        panelSetting.add(checkBox_four);
        
        checkBox_five = new JCheckBox("五选项");
        checkBox_five.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBox_three.setSelected(false);
                checkBox_four.setSelected(false);
                if(!checkBox_five.isSelected())
                    btn_ON.setEnabled(false);
                else {
                    canStart_1=true;
                    if(canStart_0)
                        btn_ON.setEnabled(true);
                    intChoiceNum=5;
                }
            }
        });
        checkBox_five.setBounds(280, 85, 74, 23);
        panelSetting.add(checkBox_five);
        
        label_1 = new JLabel("测验答案：");
        label_1.setFont(new Font("宋体", Font.BOLD, 12));
        label_1.setBounds(50, 139, 74, 15);
        panelSetting.add(label_1);
        
        label_2 = new JLabel("1-5题");
        label_2.setBounds(121, 139, 51, 15);
        panelSetting.add(label_2);
        
        textField_1to5 = new JTextField();
        textField_1to5.addKeyListener(new KeyAdapter() { 
            public void keyTyped(KeyEvent e) { 
                int MAXLENGTH=5;
                String content = textField_1to5.getText();
                if(content.length()<MAXLENGTH){
                    int code = e.getKeyChar();
                    if(!(code>64&&code<70)&&!(code>96&&code<102)){
                        e.consume();
                    }                        
                }
                else e.consume();
                }
            }); 
        textField_1to5.setHorizontalAlignment(SwingConstants.CENTER);
        textField_1to5.setFont(new Font("宋体", Font.BOLD, 15));
        textField_1to5.setBounds(176, 136, 66, 21);
        textField_1to5.setEnabled(false);
        panelSetting.add(textField_1to5);
        
        textField_6to10 = new JTextField();
        textField_6to10.addKeyListener(new KeyAdapter() { 
            public void keyTyped(KeyEvent e) { 
                int MAXLENGTH=5;
                String content = textField_6to10.getText();
                if(content.length()<MAXLENGTH){
                    int code = e.getKeyChar();
                    if(!(code>64&&code<70)&&!(code>96&&code<102)){
                        e.consume();
                    }                        
                }
                else e.consume();
                }
            }); 
        textField_6to10.setHorizontalAlignment(SwingConstants.CENTER);
        textField_6to10.setFont(new Font("宋体", Font.BOLD, 15));
        textField_6to10.setBounds(338, 136, 66, 21);
        textField_6to10.setEnabled(false);
        panelSetting.add(textField_6to10);
        
        label_3 = new JLabel("6-10题");
        label_3.setBounds(280, 139, 51, 15);
        panelSetting.add(label_3);
        
        textField_11to15 = new JTextField();
        textField_11to15.addKeyListener(new KeyAdapter() { 
            public void keyTyped(KeyEvent e) { 
                int MAXLENGTH=5;
                String content = textField_11to15.getText();
                if(content.length()<MAXLENGTH){
                    int code = e.getKeyChar();
                    if(!(code>64&&code<70)&&!(code>96&&code<102)){
                        e.consume();
                    }                        
                }
                else e.consume();
                }
            }); 
        textField_11to15.setHorizontalAlignment(SwingConstants.CENTER);
        textField_11to15.setFont(new Font("宋体", Font.BOLD, 15));
        textField_11to15.setBounds(176, 164, 66, 21);
        textField_11to15.setEnabled(false);
        panelSetting.add(textField_11to15);
        
        label_4 = new JLabel("11-15题");
        label_4.setBounds(121, 167, 51, 15);
        panelSetting.add(label_4);
        
        textField_16to20 = new JTextField();
        textField_16to20.addKeyListener(new KeyAdapter() { 
            public void keyTyped(KeyEvent e) { 
                int MAXLENGTH=5;
                String content = textField_16to20.getText();
                if(content.length()<MAXLENGTH){
                    int code = e.getKeyChar();
                    if(!(code>64&&code<70)&&!(code>96&&code<102)){
                        e.consume();
                    }                        
                }
                else e.consume();
                }
            });
        textField_16to20.setHorizontalAlignment(SwingConstants.CENTER);
        textField_16to20.setFont(new Font("宋体", Font.BOLD, 15));
        textField_16to20.setBounds(338, 164, 66, 21);
        textField_16to20.setEnabled(false);
        panelSetting.add(textField_16to20);
        
        label_5 = new JLabel("16-20题");
        label_5.setBounds(280, 167, 51, 15);
        panelSetting.add(label_5);
        
        label_show = new JLabel("请进行常规设置后开启测验功能");
        label_show.setFont(new Font("宋体", Font.BOLD, 15));
        label_show.setBounds(10, 636, 736, 25);
        contentPane.add(label_show);
        

        
    }
    private static CategoryDataset createDataset()
    {
      DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
      for(int i=1;i<12;i++){
          localDefaultCategoryDataset.addValue(50.0, "Right", i+"");
          localDefaultCategoryDataset.addValue(0, "-", i+"");
          localDefaultCategoryDataset.addValue(50.0, "Wrong", i+"");
      }
      return localDefaultCategoryDataset;
    }
    
    private static CategoryDataset createNewDataset(ArrayList<AnswerRecord> ar)
    {
      DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
      for(int i=0;i<intProblemNum;i++){
          localDefaultCategoryDataset.addValue((float)(ar.get(i).getCountCorrect()/ar.get(i).getCountAll())*100, "Right", i+1+"");
          localDefaultCategoryDataset.addValue(0, "-", i+1+"");
          localDefaultCategoryDataset.addValue(100-(float)(ar.get(i).getCountCorrect()/ar.get(i).getCountAll())*100, "Wrong", i+1+"");
      }
      return localDefaultCategoryDataset;
    }

    private static JFreeChart createChart(CategoryDataset paramCategoryDataset)
    {
      JFreeChart localJFreeChart = ChartFactory.createStackedBarChart("", "", "%", paramCategoryDataset, PlotOrientation.HORIZONTAL, false, true, false);
      localJFreeChart.getTitle().setMargin(2.0D, 0.0D, 0.0D, 0.0D);

      CategoryPlot localCategoryPlot = (CategoryPlot)localJFreeChart.getPlot();
      LegendItemCollection localLegendItemCollection = new LegendItemCollection();
      localLegendItemCollection.add(new LegendItem("Right", null, null, null, new Rectangle2D.Double(-6.0D, -3.0D, 12.0D, 6.0D), Color.GREEN));
      localLegendItemCollection.add(new LegendItem("Wrong", null, null, null, new Rectangle2D.Double(-6.0D, -3.0D, 12.0D, 6.0D), Color.RED));
      localCategoryPlot.setFixedLegendItems(localLegendItemCollection);
      localCategoryPlot.setInsets(new RectangleInsets(5.0D, 5.0D, 5.0D, 20.0D));
      localCategoryPlot.setDomainGridlinesVisible(true);
      NumberAxis localNumberAxis = (NumberAxis)localCategoryPlot.getRangeAxis();
      localNumberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
      localNumberAxis.setUpperMargin(0.0D);
      BarRenderer localBarRenderer = (BarRenderer)localCategoryPlot.getRenderer();
      localBarRenderer.setDrawBarOutline(false);
      ChartUtilities.applyCurrentTheme(localJFreeChart);
      GradientPaint localGradientPaint1 = new GradientPaint(0.0F, 0.0F, Color.GREEN, 0.0F, 0.0F, new Color(0, 64, 0));
      Color localColor = new Color(0, 0, 0, 0);
      GradientPaint localGradientPaint2 = new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, new Color(64, 0, 0));
      localBarRenderer.setSeriesPaint(0, localGradientPaint1);
      localBarRenderer.setSeriesPaint(1, localColor);
      localBarRenderer.setSeriesPaint(2, localGradientPaint2);
      return localJFreeChart;
    }

    public static ChartPanel createDemoPanel()
    {
      JFreeChart localJFreeChart = createChart(createDataset());
      return new ChartPanel(localJFreeChart);
    }    
    
    //**********************************************************************************
    //**********************************************************************************
    public static CategoryDataset createChoiceDataset()
    {
      DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
      for(int i=1;i<12;i++){
          localDefaultCategoryDataset.addValue(10.0D, "A", i+"");
          localDefaultCategoryDataset.addValue(4.0D, "B", i+"");
          localDefaultCategoryDataset.addValue(15.0D, "C", i+"");
          localDefaultCategoryDataset.addValue(14.0D, "D", i+"");
          localDefaultCategoryDataset.addValue(14.0D, "E", i+"");
      }
      
      return localDefaultCategoryDataset;
    }
    
    public static CategoryDataset createNewChoiceDataset(ArrayList<AnswerRecord> ar)
    {
      DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
      for(int i=0;i<intProblemNum;i++){
          localDefaultCategoryDataset.addValue(ar.get(i).getCountA(), "A", i+1+"");
          localDefaultCategoryDataset.addValue(ar.get(i).getCountB(), "B", i+1+"");
          localDefaultCategoryDataset.addValue(ar.get(i).getCountC(), "C", i+1+"");
          localDefaultCategoryDataset.addValue(ar.get(i).getCountD(), "D", i+1+"");
          localDefaultCategoryDataset.addValue(ar.get(i).getCountE(), "E", i+1+"");
      }
      
      return localDefaultCategoryDataset;
    }

    private static JFreeChart createChoiceChart(CategoryDataset paramCategoryDataset)
    {
      JFreeChart localJFreeChart = ChartFactory.createStackedBarChart3D("", "", "", paramCategoryDataset, PlotOrientation.HORIZONTAL, true, true, false);
      CategoryPlot localCategoryPlot = (CategoryPlot)localJFreeChart.getPlot();
      NumberAxis localNumberAxis = (NumberAxis)localCategoryPlot.getRangeAxis();
      localNumberAxis.setNumberFormatOverride(new DecimalFormat("0%"));
      StackedBarRenderer3D localStackedBarRenderer3D = (StackedBarRenderer3D)localCategoryPlot.getRenderer();
      localStackedBarRenderer3D.setRenderAsPercentages(true);
      localStackedBarRenderer3D.setDrawBarOutline(false);
      localStackedBarRenderer3D.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator("{3}", NumberFormat.getIntegerInstance(), new DecimalFormat("0.0%")));
      localStackedBarRenderer3D.setBaseItemLabelsVisible(true);
      localStackedBarRenderer3D.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
      localStackedBarRenderer3D.setBaseNegativeItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
      
      return localJFreeChart;
    }

    public static ChartPanel createChoicePanel()
    {
      JFreeChart localJFreeChart = createChoiceChart(createChoiceDataset());
      return new ChartPanel(localJFreeChart);
    }
}
