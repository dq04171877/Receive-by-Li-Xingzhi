package tryUI;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Colorchooser extends JPanel implements ChangeListener  {

	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JLabel lblR;
	private JLabel lblG;
	private JLabel lblB;
	private JPanel panel;
	private JSlider slider;
	private JSlider slider_1;
	private JSlider slider_2;
	private int R;
	private int G;
	private int B;

	public Colorchooser() {
		super();
		this.setSize(420, 25);
		this.setBorder(null);
		setLayout(null);
		
		label = new JLabel();
		label.setBounds(0, 5, 50, 15);
		add(label);
		
		panel = new JPanel();
		panel.setBackground(new Color(230, 230, 230)); //颜色设置
		panel.setBounds(55, 5, 15, 15);
		add(panel);
		
		slider = new JSlider(0, 255, 230);
		slider.setBounds(210, 5, 90, 20);
		add(slider);
		slider.addChangeListener(this);
		
		slider_1 = new JSlider(0, 255, 230);
		slider_1.setBounds(95, 5, 90, 20);
		add(slider_1);
		slider_1.addChangeListener(this);
		
		slider_2 = new JSlider(0, 255, 230);
		slider_2.setBounds(325, 5, 90, 20);
		add(slider_2);
		slider_2.addChangeListener(this);
		
		lblR = new JLabel("R:");
		lblR.setBounds(80, 5, 15, 15);
		add(lblR);
		
		lblG = new JLabel("G:");
		lblG.setBounds(195, 5, 15, 15);
		add(lblG);
		
		lblB = new JLabel("B:");
		lblB.setBounds(310, 5, 15, 15);
		add(lblB);

	}
	
	public void setlabel(String name){
		label.setText(name);
	}
	
	
	//颜色设置
	public void setcolor(int r,int g,int b){
		panel.setBackground(new Color(r,g,b));
		slider_1.setValue(r);
		slider.setValue(g);
		slider_2.setValue(b);
	}
	
	public int getR(){
		return R;
	}
	
	public int getG(){
		return G;
	}
	
	public int getB(){
		return B;
	}
	public void stateChanged (ChangeEvent e){
		R=slider_1.getValue();G=slider.getValue();B=slider_2.getValue();
			panel.setBackground(new Color(R,G,B));
			panel.repaint();
	}
}
