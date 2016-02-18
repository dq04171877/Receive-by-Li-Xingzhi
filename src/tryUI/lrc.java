package tryUI;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;











import com.sun.awt.AWTUtilities;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.SystemColor;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class lrc extends JFrame {
	private static final long serialVersionUID = 1L;

	public  MyPanel contentPane;
	private Point origin;
	public boolean flag=false;
	public boolean create=false;
	public static boolean dm=false;
	public static JButton danmu;
	public static boolean kl=false;
	public static JButton kouling;
	

	public lrc() {
		super();
		setAlwaysOnTop(true);
		setVisible(false);
		Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(100, (int)screensize.getHeight()-100,(int) screensize.getWidth()-200, 80);
		this.setUndecorated(true);
		this.setResizable(true);
		setWindowOpaque(false);
		
		contentPane = new MyPanel("语音——字幕系统");
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		
		danmu =new JButton("弹幕已关闭");
		danmu.setBorder(null);
		danmu.setBounds(1013, 0, 80, 25);
		contentPane.add(danmu);
		
		danmu.addActionListener(new ActionListener(){
		@SuppressWarnings("null")
		public void actionPerformed (ActionEvent e){
			DanMuThread dmt=null;
			if(dm==false){
				danmu.setText("弹幕已开启");
				dm=true;
				dmt=new DanMuThread();
				dmt.start();
			}else{
			dm=false;
			danmu.setText("弹幕已关闭");
			First.lrcframe.repaint();
			dmt.close();
			}
		}
			
		});
		
		kouling =new JButton("口令已关闭");
		kouling.setBorder(null);
		kouling.setBounds(926, 0, 80, 25);
		contentPane.add(kouling);
		
		kouling.addActionListener(new ActionListener(){
		public void actionPerformed (ActionEvent e){
			if(kl==false&&flag==true){
				kouling.setText("口令已开启");
				kl=true;
				First.lrcframe.repaint();
			}
		}
			
		});
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(lrc.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlay.png")));
		btnNewButton.setBounds(1100, 0, 25, 25);
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				
				//姝ゅ鍐欐挱鏀鹃敭鐨勫姩浣�,鍒妸涓嬮潰閭ｈrepaint鍒犳帀,寰幆repait()
				//public void getshowords(String sw)
				//if (First.one.getState()==Thread.State.NEW||First.one.getState()==Thread.State.TERMINATED)
				//{First.one.start();}
				if (create==false){
					First.one=new UDPRecv();
					First.one.createfile();
					create=true;
					}
				if (flag==false)
				{
					flag=true;
					contentPane.getshowords("Loading...");
					First.lrcframe.repaint();
					Thread temp=new Thread(First.one);
					temp.start();
					
					}
			}
		}
				);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(lrc.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPause.png")));
		button.setBounds(1132, 0, 25, 25);
		contentPane.add(button);
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				if (flag==true){
				//姝ゅ鍐欏仠姝㈤敭鐨勫姩浣�
				try{
					flag=false;
					First.one.close();
					contentPane.getshowords("语音——字幕系统");
					First.lrcframe.repaint();
				}
				catch(Exception ee){
					ee.printStackTrace();
				}
				}
			}
		}
				);
		
		contentPane.addMouseListener( new MouseAdapter(){
		  	@Override
	          public void mousePressed(MouseEvent e){
	        	origin=new Point();
	            origin.x = e.getX();
	            origin.y = e.getY();
	          }
	          public void mouseReleased(MouseEvent e) {
	            super.mouseReleased(e);
	          }
	          public void mouseEntered (MouseEvent e){
	        	  contentPane.setOpaque(true);
	        	  setWindowOpaque(true);
	        	  }
	          public void mouseExited (MouseEvent e){
	        	  contentPane.setOpaque(false);
	        	  setWindowOpaque(false);
	        	  }
		}
				);
		contentPane.addMouseMotionListener(
		        new MouseMotionAdapter(){
			          public void mouseDragged(MouseEvent e){
			            Point p =    getLocation();
			            setLocation(p.x + e.getX()- origin.x , p.y + e.getY() - origin.y );
			          }          
			        }
			    );
		
	

	}
	public void getshowords (String ss){
		contentPane.getshowords(ss);
	}
	

	public void repaint(){
		contentPane.repaint();
	}
	private void setWindowOpaque (boolean b) {
			
			AWTUtilities.setWindowOpaque(this, b);
		}
}



class MyPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private String show;
	
	MyPanel(String s){
		show=s;
	}
	
	public void getshowords(String sw){
		show=sw;
		
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Font font;
		try {
			font = new Font(settings.sr.find("3"), Font.BOLD,settings.sr.findint("2"));
			g.setFont(font);
			g.setColor(new Color(settings.sr.findint("7"), settings.sr.findint("8"), settings.sr.findint("9")));//shadow color
			g.drawString(show,12 ,(int)(20+25+(55-settings.sr.findint("2"))*0.5+2) );//shadow location
			g.setColor(new Color(settings.sr.findint("4"), settings.sr.findint("5"), settings.sr.findint("6")));//word color
			g.drawString(show, 10,(int)(20+25+(55-settings.sr.findint("2"))*0.5));//word location
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
