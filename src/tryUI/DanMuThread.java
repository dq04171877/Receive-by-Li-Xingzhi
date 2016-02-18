package tryUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Random;

public class DanMuThread extends Thread {
	private String showords="天津大学电气与自动化工程学院";
	private DatagramSocket ds;
	
	public void setshowords(String sw){
		showords=sw;
	}
	
	public void run(){
		First.lrcframe.repaint();
		
		Random r=new Random();
		try {
			
			ds = new DatagramSocket(7280);
			ds.setSoTimeout(30000);
		} catch (SocketException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		byte[] data = new byte[200];
		DatagramPacket dp = new DatagramPacket(data, 200);
		
		while(!ds.isClosed()){
		try {
			
		data = new byte[200];
		
		ds.receive(dp);	
		
		String temp=new String(dp.getData(), 0, dp.getData().length);
		
        if (temp.substring(0, 0)=="2"){
        	
		setshowords( temp.substring(1));
        Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
		int location =(int)(screensize.getHeight()-200);
		
		DanMu dm = new DanMu(r.nextInt(6),r.nextInt(location) );
		dm.setshowords(showords);
		dm.setVisible(true);
		for (int i=DanMu.contentPane.getstart();i>=-25*DanMu.contentPane.getail();i--){
			DanMu.contentPane.setloc(i);
			DanMu.contentPane.repaint();
			try {
				sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dm.dispose();}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			ds.close();System.out.println("closed!");
		}
		
		}
		

	}
	
	public void close() {
		ds.close();
	}
	

}
