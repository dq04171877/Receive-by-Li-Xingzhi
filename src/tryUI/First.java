package tryUI;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.awt.AWTUtilities;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Area;
import java.awt.image.PixelGrabber;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;




import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class First extends JFrame {
	 private static final long serialVersionUID = 1L;
	  private Point origin; //用于移动窗体
	  private Image img;//用来设定窗体不规则样式的图片
    private static settings sframe;
    public static lrc lrcframe;
    private static int openornot=0;
    public static UDPRecv one;


	private JPanel contentPane;
	
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					First frame = new First();
					frame.setVisible(true);
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());					
					sframe=new settings();
					lrcframe=new lrc();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public First() { 
		super();
		
		setTitle("First");
		setIconImage(Toolkit.getDefaultToolkit().getImage(First.class.getResource("/res/128.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(UIManager.getColor("control"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton();
		btnNewButton.setIcon(new ImageIcon(First.class.getResource("/res/lrc1.png")));
		btnNewButton.setBounds(227, 184, 60, 50);
		contentPane.add(btnNewButton);
		btnNewButton.setBorderPainted(false);
		
		btnNewButton.addActionListener(new ActionListener()
		{
		
		public void actionPerformed (ActionEvent e)
		{
			if (openornot==0)
			{lrcframe.setVisible(true);
			openornot=1;
			}
			else 	{lrcframe.setVisible(false);
			openornot=0;
			}
			}
		}
				);
		
		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setIcon(new ImageIcon(First.class.getResource("/res/set.png")));
		btnNewButton_1.setBounds(287, 184, 60, 50);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.setBorderPainted(false);
		
		
		btnNewButton_1.addActionListener(new ActionListener()
		{
		
		public void actionPerformed (ActionEvent e)
		{
			try {
				sframe.setVisible(true);
				
			} catch (Exception ec) {
				ec.printStackTrace();
			}
			}
		}
				);
		
		
		JButton btnNewButton_2 = new JButton();
		btnNewButton_2.setIcon(new ImageIcon(First.class.getResource("/res/exit.png")));
		btnNewButton_2.setBounds(347, 184, 60, 50);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.setBorderPainted(false);
		
		btnNewButton_2.addActionListener(new ActionListener()
		{
		
		public void actionPerformed (ActionEvent e)
		{
			System.exit(0);
			}
		}
				);
		    
		    /* 首先初始化一张图片，我们可以选择一张有透明部分的不规则图片
		     * (当然我们要选择支持Alpha(透明)层的图片格式，如PNG)，这张
		     * 图片将被用来生成与其形状相同的不规则窗体"/res/200711221297238_2.jpg"
		    */
		MediaTracker mt=new MediaTracker(this);
	    img=Toolkit.getDefaultToolkit().getImage(First.class.getResource("/res/biankuang2.png"));
	    
	    mt.addImage(img, 0);
	    
	    try {
	      mt.waitForAll();
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	    
	    try {
	      initialize();//窗体初始化
	    } catch (IOException e) {
	      e.printStackTrace();
	    }    
	}
	
	private void initialize() throws IOException {
	    //设定窗体大小和图片一样大
	    this.setSize(img.getWidth(null), img.getHeight(null));
	    //设定禁用窗体装饰，这样就取消了默认的窗体结构
	    this.setUndecorated(true);
	    //初始化用于移动窗体的原点
	    this.origin=new Point();
	    
	    //调用AWTUtilities的setWindowShape方法设定本窗体为制定的Shape形状
	    AWTUtilities.setWindowShape(this,getImageShape(img));    
	    AWTUtilities.setWindowOpaque(this, false);
	    
	    this.setLocationRelativeTo(null);
	    
	    //由于取消了默认的窗体结构，所以我们要手动设置一下移动窗体的方法
	    addMouseListener( 
	        new MouseAdapter(){
	        	@Override
	          public void mousePressed(MouseEvent e){
	            origin.x = e.getX();
	            origin.y = e.getY();
	          }
	          public void mouseReleased(MouseEvent e) {
	            super.mouseReleased(e);
	          }
	          

	        }
	    );

	    addMouseMotionListener(
	        new MouseMotionAdapter(){
	          public void mouseDragged(MouseEvent e){
	            Point p =    getLocation();
	            setLocation(p.x + e.getX()- origin.x , p.y + e.getY() - origin.y );
	          }          
	        }
	    );    
	  }
	
	
	 public Shape getImageShape(Image img) {
		    ArrayList<Integer> x=new ArrayList<Integer>();
		    ArrayList<Integer> y=new ArrayList<Integer>();  
		    int width=img.getWidth(null);//图像宽度
		    int height=img.getHeight(null);//图像高度

		    //筛选像素
		    //首先获取图像所有的像素信息
		    PixelGrabber pgr = new PixelGrabber(img, 0, 0, -1, -1, true);
		    try {
		      pgr.grabPixels();
		    } catch (InterruptedException ex) {
		      ex.getStackTrace();
		    }
		   
		    int pixels[]= (int[]) pgr.getPixels(); 
		    //循环像素
		    for (int i = 0; i < pixels.length; i++) {
		      //筛选，将不透明的像素的坐标加入到坐标ArrayList x和y中      
		      int alpha = getAlpha(pixels[i]);
		      if (alpha==0){
		    	   
		    	continue;        
		      }else{
		        x.add(i%width);
		        y.add(i/width);
		      }      
		    }
		    
		    //建立图像矩阵并初始化(0为透明,1为不透明)
		    int[][] matrix=new int[height][width];    
		    for(int i=0;i<height;i++){
		      for(int j=0;j<width;j++){
		        matrix[i][j]=0;
		      }
		    }
		    
		    //导入坐标ArrayList中的不透明坐标信息
		    for(int c=0;c<x.size();c++){
		      matrix[y.get(c)][x.get(c)]=1;
		    }
		    
		    //由于Area类所表示区域可以进行合并，我们逐一水平"扫描"图像矩阵的每一行，
		     // 将不透明的像素生成为Rectangle，再将每一行的Rectangle通过Area类的rec
		    //对象进行合并，最后形成一个完整的Shape图形
		    
		    Area rec=new Area();
		    
		    for(int i=0;i<height;i++){
			      for(int j=0;j<width;j++){
			    	  if(matrix[i][j]==1){
			    		  Rectangle rectemp=new Rectangle(j,i,1,1);
			              rec.add(new Area(rectemp));
			    	  }
			      }
		    }
		    return rec;
		  }

		  
		  private int getAlpha(int pixel) {
		    return (pixel >> 24) & 0xff;
		  }
		  
		  
		  /* 我们可以选择在窗体上绘制图片，是窗体完全呈现出图片的样式，
		    * 当然我们也可以根据需要不绘制它，而采取其他操作
		    */
		  public void paint(Graphics g) {
		    super.paint(g);
		    g.drawImage(img, 0, 0, null);
		  }
}
