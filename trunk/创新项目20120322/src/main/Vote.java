package main;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.CategoryTextAnnotation;
import org.jfree.chart.axis.CategoryAnchor;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.Layer;
import org.jfree.ui.TextAnchor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Vote extends JFrame {

    public static boolean isCollecting=false;
    public static int[] count = new int[6];
    static int intChoiceNum,intCountAll;
    
	private JPanel contentPane;
	JCheckBox checkBox_two,checkBox_three,checkBox_four,checkBox_five;
	JRadioButton radioButton_isMultiple;
	static JRadioButton radioButton_isWaiverable;
	JButton btn_ON,btn_OFF;
	ChartPanel localChartPanel;
	VoteThread temVoteThread;
	JLabel show;

	/**
	 * Create the frame.
	 */
	public Vote() {
	    setResizable(false);
		setBounds(100, 100, 628, 467);
		setTitle("课堂投票统计-创新项目-电子课堂辅助系统");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

        temVoteThread = new VoteThread();
		
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                if(temVoteThread.isAlive())
                temVoteThread.stop();
                this.windowClosed(e);
            }
    });
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 10, 602, 103);
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
		        else intChoiceNum=2;
		        //System.out.println(intChoiceNum);
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
                else intChoiceNum=3;
                //System.out.println(intChoiceNum);
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
                else intChoiceNum=4;
                //System.out.println(intChoiceNum);
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
                else intChoiceNum=5;
                //System.out.println(intChoiceNum);
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
		
	    radioButton_isWaiverable = new JRadioButton("可弃权");
	    radioButton_isWaiverable.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(radioButton_isWaiverable.getText().equals("可弃权")){
                    radioButton_isWaiverable.setText("不可弃权");
                }
                else
                    radioButton_isWaiverable.setText("可弃权");
            }
        });
	    radioButton_isWaiverable.setBounds(177, 60, 121, 23);
	    panel.add(radioButton_isWaiverable);
		
		btn_ON = new JButton("开启投票");
		btn_ON.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
	              //发送开启投票统计权限
                for(ServerThread s:Socketinitialize.allthread){
                    try {
                        s.givetoclient("voteON,"+intChoiceNum+","
                    +radioButton_isMultiple.getText()+","
                    +radioButton_isWaiverable.getText());
                        
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                checkBox_two.setSelected(false);
                checkBox_three.setSelected(false);
                checkBox_four.setSelected(false);
                checkBox_five.setSelected(false);
                radioButton_isMultiple.setSelected(false);
                radioButton_isWaiverable.setSelected(false);
                btn_ON.setEnabled(false);
                isCollecting=true;                
                temVoteThread.start();
		        btn_OFF.setEnabled(true);
		        
		        show.setText("已开启投票统计功能（"+intChoiceNum+"选项|"+radioButton_isMultiple.getText()+"|"
		        +radioButton_isWaiverable.getText()+"），正在回收投票信息，请稍候……");
		    }
		});
		btn_ON.setBounds(487, 11, 93, 23);
		btn_ON.setEnabled(false);
		panel.add(btn_ON);
		
		btn_OFF = new JButton("关闭投票");
		btn_OFF.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        System.out.print(count[0]);
		        isCollecting=false;
		        localChartPanel.setVisible(false);
		        CategoryDataset localCategoryDataset = createNewDataset(count);
		        JFreeChart localJFreeChart = createChart(localCategoryDataset);
		        localChartPanel = new ChartPanel(localJFreeChart);
		        localChartPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		        localChartPanel.setBounds(10, 123, 602, 274);
		        localChartPanel.setPreferredSize(new Dimension(500, 270));
		        localChartPanel.setVisible(true);
		        contentPane.add(localChartPanel);
		        localChartPanel.validate();
		        show.setText("已关闭投票统计功能，本次投票统计结果如图所示");
		    }
		});
		btn_OFF.setBounds(487, 70, 93, 23);
		btn_OFF.setEnabled(false);
		panel.add(btn_OFF);
		
        CategoryDataset localCategoryDataset = createDataset();
        JFreeChart localJFreeChart = createChart(localCategoryDataset);
        localChartPanel = new ChartPanel(localJFreeChart);
        localChartPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        localChartPanel.setBounds(10, 123, 602, 274);
        localChartPanel.setPreferredSize(new Dimension(500, 270));
        contentPane.add(localChartPanel);
        
        show = new JLabel("请设置常规选项后开启投票统计功能……");
        show.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        show.setFont(new Font("宋体", Font.PLAIN, 15));
        show.setBounds(10, 407, 602, 22);
        contentPane.add(show);
        
        }
    
    private static CategoryDataset createNewDataset(int[] temCount)
    {
      DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
      for(int i =0;i<intChoiceNum;i++){
          switch(i){
              case 0:localDefaultCategoryDataset.addValue((float)temCount[i]/intCountAll, "", "A");break;
              case 1:localDefaultCategoryDataset.addValue((float)temCount[i]/intCountAll, "", "B");break;
              case 2:localDefaultCategoryDataset.addValue((float)temCount[i]/intCountAll, "", "C");break;
              case 3:localDefaultCategoryDataset.addValue((float)temCount[i]/intCountAll, "", "D");break;
              case 4:localDefaultCategoryDataset.addValue((float)temCount[i]/intCountAll, "", "E");break;
              }
      }
      if(!radioButton_isWaiverable.isSelected())
          localDefaultCategoryDataset.addValue((float)temCount[5]/intCountAll, "", "Waiver");
      
      return localDefaultCategoryDataset;
    }
    private static CategoryDataset createDataset()
    {
      DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
      localDefaultCategoryDataset.addValue(0.99, "", "A");
      localDefaultCategoryDataset.addValue(0.99, "", "B");
      localDefaultCategoryDataset.addValue(0.99, "", "C");
      localDefaultCategoryDataset.addValue(0.99, "", "D");
      localDefaultCategoryDataset.addValue(0.99, "", "E");
      localDefaultCategoryDataset.addValue(0.99, "", "Waiver");
      return localDefaultCategoryDataset;
    }
    private static JFreeChart createChart(CategoryDataset paramCategoryDataset)
    {
      JFreeChart localJFreeChart = ChartFactory.createBarChart3D("", "", "", paramCategoryDataset, PlotOrientation.VERTICAL, false, true, false);
      CategoryPlot localCategoryPlot = (CategoryPlot)localJFreeChart.getPlot();
      CustomBarRenderer3D localCustomBarRenderer3D = new CustomBarRenderer3D();
      localCustomBarRenderer3D.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
      localCustomBarRenderer3D.setBaseItemLabelsVisible(true);
      localCustomBarRenderer3D.setItemLabelAnchorOffset(10.0D);
      localCustomBarRenderer3D.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
      localCategoryPlot.setRenderer(localCustomBarRenderer3D);
      //ValueMarker localValueMarker = new ValueMarker(0.7D, new Color(200, 200, 255), new BasicStroke(1.0F), new Color(200, 200, 255), new BasicStroke(1.0F), 1.0F);
      //localCategoryPlot.addRangeMarker(localValueMarker, Layer.BACKGROUND);
      localCustomBarRenderer3D.setBaseItemLabelsVisible(true);
      localCustomBarRenderer3D.setMaximumBarWidth(0.05D);
      //CategoryTextAnnotation localCategoryTextAnnotation = new CategoryTextAnnotation("Minimum grade to pass", "Robert", 0.71D);
      //localCategoryTextAnnotation.setCategoryAnchor(CategoryAnchor.START);
      //localCategoryTextAnnotation.setFont(new Font("SansSerif", 0, 12));
      //localCategoryTextAnnotation.setTextAnchor(TextAnchor.BOTTOM_LEFT);
      //localCategoryPlot.addAnnotation(localCategoryTextAnnotation);
      NumberAxis localNumberAxis = (NumberAxis)localCategoryPlot.getRangeAxis();
      localNumberAxis.setNumberFormatOverride(NumberFormat.getPercentInstance());
      localNumberAxis.setUpperMargin(0.1D);
      ChartUtilities.applyCurrentTheme(localJFreeChart);
      return localJFreeChart;
    }
    public static JPanel createDemoPanel()
    {
      JFreeChart localJFreeChart = createChart(createDataset());
      return new ChartPanel(localJFreeChart);
    }
    static class CustomBarRenderer3D extends BarRenderer3D
    {
      public Paint getItemPaint(int paramInt1, int paramInt2)
      {
        CategoryDataset localCategoryDataset = getPlot().getDataset();
        double d = localCategoryDataset.getValue(paramInt1, paramInt2).doubleValue();
        if (d >= 0.7D)
          return Color.green;
        return Color.green;
      }
    }
}
