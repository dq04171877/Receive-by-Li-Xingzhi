package tryUI;


import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import java.awt.Toolkit;

import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.SystemColor;

import javax.swing.JComboBox;

public class settings extends JFrame {

	private static final long serialVersionUID = 1L;
  	JTabbedPane tp=new JTabbedPane();	
  	JPanel panel = new JPanel();	
    JPanel panel_1 = new JPanel();
	JPanel panel_2 = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	public static File filepath=null;
	private File txtpath=null;
	private JTextArea textArea;
	public static SettingReader sr;
	private Colorchooser cc_1;
	private Colorchooser cc_2;
	private Colorchooser cc_3;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	public JTextArea textArea_2 ;
      
	public settings() throws Exception { 
    	super();
    	setIconImage(Toolkit.getDefaultToolkit().getImage(settings.class.getResource("/res/set.png")));
    	setTitle("设置");
    	this.setVisible(false);
    	
    	sr=new SettingReader();
    	filepath=new File(sr.find("1"));
  
		this.setContentPane(tp);
    	
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		tp.addTab("文件", panel);
		panel.setLayout(null);	
		file();
		
		tp.addTab("字幕", panel_1);
		panel_1.setLayout(null);
		lrc();
		
		JPanel panel_3 = new JPanel();
		tp.addTab("口令", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblnotepad = new JLabel("例如：    打开记事本=notepad");
		lblnotepad.setBounds(10, 10, 205, 15);
		panel_3.add(lblnotepad);
		
		JButton button = new JButton("保存");
		button.setBounds(210, 201, 107, 22);
		panel_3.add(button);
		
		button.addActionListener(
				new ActionListener(){
					public void actionPerformed (ActionEvent e){
						//here!!
						try {
							BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/res/kouling.properties"),"gbk"));
							String large=textArea_2.getText();
							bw.write(large);
							bw.close();
								
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				);
		
		textArea_2 = new JTextArea();
		textArea_2.setBounds(10, 33, 409, 158);
		panel_3.add(textArea_2);
		
		JButton button_1 = new JButton("导入");
		button.setBounds(76, 201, 107, 22);
		panel_3.add(button_1);
		
		button_1.addActionListener(
				new ActionListener(){
					public void actionPerformed (ActionEvent e){
							InputStreamReader read;
							try {
								read = new InputStreamReader(new FileInputStream("src/res/kouling.properties"),"gbk");
											BufferedReader bf=new BufferedReader(read);
			String line=bf.readLine();
			while(line!=null){
				textArea_2.append(line+"\n");
				line=bf.readLine();
				} ;
				
			read.close();
			bf.close();
							} 
							 catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} 

					}
				}
				);
		
	
		
		tp.addTab("关于", null, panel_2, null);
		panel_2.setLayout(null);
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBackground(SystemColor.control);
		textArea_1.setBounds(0, 0, 429, 233);
		panel_2.add(textArea_1);
		about();
		

	}
      
      protected void file(){
  		
  		JLabel label = new JLabel("保存路径");
  		label.setBounds(10, 14, 54, 15);
  		panel.add(label);
  		
  		textField = new JTextField();
  		textField.setBounds(67, 11, 272, 21);
  		panel.add(textField);
  		textField.setText(filepath.getPath());
  		textField.setColumns(10);
  		
  		JButton btnNewButton = new JButton("浏览");
  		btnNewButton.setBounds(352, 10, 67, 22);
  		panel.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener()
		{
		
		public void actionPerformed (ActionEvent e)
		  {
			try {
				JFileChooser fc=new JFileChooser();
				if(filepath!=null)fc.setCurrentDirectory(filepath);
			      fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			      int reval=fc.showOpenDialog(panel);
			      if(fc.getSelectedFile()!=null){
			    	  filepath=fc.getSelectedFile();
			    	  sr.replace("1", filepath.getPath()); //property changes
			      }
			      
			      if(reval==JFileChooser.APPROVE_OPTION)textField.setText(fc.getSelectedFile().getPath());
			      
			} catch (Exception ec) {
				ec.printStackTrace();
			}
		   }
		}
				);
  		
  		JLabel label_1 = new JLabel("打开文件");
  		label_1.setBounds(10, 44, 54, 15);
  		panel.add(label_1);
  		
  		textField_1 = new JTextField();
  		textField_1.setColumns(10);
  		textField_1.setBounds(67, 41, 272, 21);
  		panel.add(textField_1);
  		
  		JButton button = new JButton("打开");
  		button.setBounds(352, 40, 67, 22);
  		panel.add(button);
        button.addActionListener(new ActionListener()
 		{
 		
 		public void actionPerformed (ActionEvent e)
 		  {
 			try {
 				JFileChooser fc=new JFileChooser();
 				if(filepath!=null)fc.setCurrentDirectory(filepath);
 				FileNameExtensionFilter filter=new FileNameExtensionFilter("txt only","txt");
 				fc.setFileFilter(filter);	
 			    int reval=fc.showOpenDialog(panel);
 			    if(reval==JFileChooser.APPROVE_OPTION){
 			    	textField_1.setText(fc.getSelectedFile().getName());
 			    	txtpath=fc.getSelectedFile();
 			    }
 				if(txtpath!=null){
 					InputStreamReader read = new InputStreamReader(new FileInputStream(txtpath),"gbk"); 
 					BufferedReader bf=new BufferedReader(read);
 					String line=bf.readLine();
 					while(line!=null){
 						textArea.append(line+"\n");
 						line=bf.readLine();
 						} ;
 						
 					read.close();
 					bf.close();
 					
 				}
 			} catch (Exception ec) {
 				ec.printStackTrace();
 			}
 		   }
 		}
 				);
  		
  		JScrollPane scrollPane = new JScrollPane();
  		scrollPane.setBounds(10, 69, 409, 164);
  		panel.add(scrollPane);
  		
  		textArea = new JTextArea();
  		textArea.setEditable(false);
  		scrollPane.setViewportView(textArea);
  		

      }

      protected void lrc () throws Exception, IOException{
    	  JLabel lblNewLabel = new JLabel("字体大小");
  		lblNewLabel.setBounds(10, 28, 54, 15);
  		panel_1.add(lblNewLabel);
  		
  		Integer []zitidaxiao=new Integer[26];
  		for (int i=0;i<26;i++){
  			zitidaxiao[i]=new Integer(25+i);
  		}
  		comboBox = new JComboBox(zitidaxiao);
  		comboBox.setSelectedItem(sr.findint("2"));
  		comboBox.setBounds(67, 25, 39, 21);
  		panel_1.add(comboBox);
  		
  		
  		JLabel label_3 = new JLabel("字体");
  		label_3.setBounds(135, 28, 24, 15);
  		panel_1.add(label_3);
  		
  		String []ziti=new String[5];
  		ziti[0]="宋体";
  		ziti[1]="楷体";
  		ziti[2]="微软雅黑";
  		ziti[3]="黑体";
  		ziti[4]="仿宋";
  		comboBox_1 = new JComboBox(ziti);
  		comboBox_1.setSelectedItem(sr.find("3"));
  		comboBox_1.setBounds(169, 25, 80, 21);
  		panel_1.add(comboBox_1);
  		
  		
  		cc_1=new Colorchooser();
  		cc_1.setlabel("字体颜色");
  		cc_1.setLocation(10,71 );
  		cc_1.setcolor(sr.findint("4"), sr.findint("5"), sr.findint("6"));
  		panel_1.add(cc_1);
  		
  		cc_2=new Colorchooser();
  		cc_2.setlabel("阴影颜色");
  		cc_2.setLocation(10,124 );
  		cc_2.setcolor(sr.findint("7"), sr.findint("8"), sr.findint("9"));
  		panel_1.add(cc_2);
  		
  		cc_3=new Colorchooser();
  		cc_3.setlabel("背景颜色");
  		cc_3.setLocation(10,177);
  		cc_3.setcolor(sr.findint("10"), sr.findint("11"), sr.findint("12"));
  		panel_1.add(cc_3);
  		
  		JButton button = new JButton("应用");
  		button.setBounds(339, 207, 80, 23);
  		panel_1.add(button);
  		button.addActionListener(new ActionListener(){
  			public void actionPerformed(ActionEvent e){
  				try {
					
			    sr.replace("2", Integer.toString((int) comboBox.getSelectedItem()));
				sr.replace("3", (String)comboBox_1.getSelectedItem());
				sr.replace("4", Integer.toString(cc_1.getR()));
				sr.replace("5", Integer.toString(cc_1.getG()));
				sr.replace("6", Integer.toString(cc_1.getB()));
				sr.replace("7", Integer.toString(cc_2.getR()));
				sr.replace("8", Integer.toString(cc_2.getG()));
				sr.replace("9", Integer.toString(cc_2.getB()));
				sr.replace("10",Integer.toString(cc_3.getR()));
				sr.replace("11",Integer.toString(cc_3.getG()));
				sr.replace("12",Integer.toString(cc_3.getB()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
  			}
  		});
  		
  		JButton button_1 = new JButton("重置");
  		button_1.setBounds(249, 207, 80, 23);
  		panel_1.add(button_1);
  		button_1.addActionListener(new ActionListener(){
  			public void actionPerformed(ActionEvent E){
  				try {
					sr.replace("2", "35");comboBox.setSelectedItem("35");
					sr.replace("3", "宋体");comboBox_1.setSelectedItem("宋体");
					sr.replace("4", "230");
					sr.replace("5", "230");
					sr.replace("6", "230");cc_1.setcolor(230, 230, 230);
					sr.replace("7", "0");
					sr.replace("8", "0");
					sr.replace("9", "0");cc_2.setcolor(0, 0, 0);
					sr.replace("10", "191");
					sr.replace("11", "205");
					sr.replace("12", "219");cc_3.setcolor(191, 205, 219);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
  			}
  		});
  			

      }
      
      protected void about(){
      }
}

