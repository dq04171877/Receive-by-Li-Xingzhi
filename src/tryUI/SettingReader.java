package tryUI;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Properties;

public class SettingReader
{
	private Properties p;
	private InputStreamReader isreader;
	private BufferedWriter bw;
	
	public SettingReader() throws IOException{
		
		p=new Properties();
		
		}	
	public   String find(String key) throws IOException{
		isreader=new InputStreamReader(new FileInputStream("src/res/save.properties"),"gbk");
		p.load(isreader);
		isreader.close();
		return p.getProperty(key);
		
	}
	
	public int findint (String key) throws NumberFormatException, IOException{
		return Integer.valueOf(this.find(key));
	}
	
	public void replace(String key,String value)throws IOException{
		String tempkey=null;
		String tempvalue=null;
		for (int i=1;i<=12;i++){
			if(Integer.valueOf(key)!=i){
				tempkey=Integer.toString(i);
				tempvalue= this.find(tempkey);
				p.setProperty(tempkey,tempvalue );
			}else{
				p.setProperty(key, value);
			}
			
		}
		
		p.setProperty(key, value);
		
		bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/res/save.properties"),"gbk"));
		p.store(bw, null);
		bw.close();
		
	}
}