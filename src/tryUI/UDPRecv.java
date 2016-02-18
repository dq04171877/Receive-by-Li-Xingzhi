package tryUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class UDPRecv implements Runnable {
	private String timenow;
	private String strRecv = null;
	private File file;
	private DatagramSocket ds;
	private Properties p;

	public void createfile() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//设置日期格式
		timenow=df.format(new Date());
        // new Date()为获取当前系统时间
		try {
			File file=new File(settings.filepath+File.separator+timenow+".txt");
			if(file.createNewFile())System.out.println(timenow);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}



	public void run() {
		
		try {
			ds = new DatagramSocket(7280);
			byte[] data = new byte[200];
			DatagramPacket dp = new DatagramPacket(data, 200);
			ds.setSoTimeout(60000);
			
			

			while(!ds.isClosed()){
				try {
					First.lrcframe.repaint();
					ds.receive(dp);	
			        strRecv = new String(dp.getData(), 0, dp.getData().length);
			        if(strRecv.substring(0,0)=="1"){
			        First.lrcframe.getshowords(strRecv.substring(1));
			        writeData(strRecv.substring(1));
			        
			        if(lrc.kl==true){
			        	InputStreamReader reader=new InputStreamReader(new FileInputStream("src/res/kouling.properties"),"gbk");
			    		p.load(reader);
			    		reader.close();
			    		String exec=p.getProperty(strRecv.trim());
			        	
			        	Runtime rt=Runtime .getRuntime();
			    		try {
			    			Process pro=rt.exec(exec);
			    		} catch (IOException e) {
			    			// TODO Auto-generated catch block
			    			e.printStackTrace();
			    		}
			        	
			        	lrc.kl=false;
			    		lrc.kouling.setText("口令已关闭");
			    		First.lrcframe.repaint();
			        }
			        }
			        data = new byte[200];
			        }
				catch (Exception e) {
					

					e.printStackTrace();}
				}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			System.out.println("finish!");
			
		}

	}

	public void close() {
		ds.close();
	}

	private void writeData(String data) {
	
		OutputStream os = null;
		try {
			os = new FileOutputStream(file, true);
			byte b[] = data.getBytes();
			os.write(b);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
