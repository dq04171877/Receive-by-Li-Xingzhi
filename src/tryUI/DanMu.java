package tryUI;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.awt.AWTUtilities;


public class DanMu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static MyPanel_1 contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					DanMuThread dmt=new DanMuThread();
					dmt.start();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DanMu(int c,int l) {
		super();
		
		setAlwaysOnTop(true);
		setVisible(false);
		Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, l,(int) screensize.getWidth(), 80);
		setUndecorated(true);
		AWTUtilities.setWindowOpaque(this,false);
		
		contentPane = new MyPanel_1();
		contentPane.setBorder(null);
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		contentPane.setcolor(colorswitch(c));
		contentPane.setloc((int)screensize.getWidth());
		setContentPane(contentPane);
		
		
		
	}
	
	private Color colorswitch (int s){
		Color temp=null;
		switch (s){
		case 0: temp=new Color(255,0,0);break;
		case 1: temp=new Color(0,255,0);break;
		case 2: temp=new Color(0,0,255);break;
		case 3: temp=new Color(255,255,0);break;
		case 4: temp=new Color(255,0,255);break;
		case 5: temp=new Color(0,255,255);break;
		}
		return temp;
	}
	

	
	public void setshowords(String sw){
		contentPane.setshowords(sw);
	}
	
	

}

class MyPanel_1 extends JPanel  {
	private static final long serialVersionUID = 1L;
	private String show;
	private int wordloc=0;
	private int tail;
	private Color wordcolor;
	
	
	public int getail(){
		return tail;
	}
	
	public void setshowords(String sw){
		show=sw;
		tail=show.length();
	} 
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Font font;

			font = new Font("楷体", Font.BOLD,25);
			g.setFont(font);
			g.setColor(new Color(0, 0, 0));//shadow color
			g.drawString(show,wordloc+1 ,(int)(20+25+(55-20)*0.5+1) );//shadow location
			g.setColor(wordcolor);//word color
			g.drawString(show, wordloc,(int)(20+25+(55-20)*0.5));//word location
		
	}
	public void setloc(int r){
		wordloc=r;
		
	}
	
	public int getstart(){
		return wordloc;
	}
	
	public void setcolor(Color co){
		wordcolor=co;
	}
	

	
}

