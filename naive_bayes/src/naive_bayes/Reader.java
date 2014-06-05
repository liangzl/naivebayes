package naive_bayes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// this class is for how to read 

public class Reader {
	String data;
	String file;
	public Reader(){
		
	}
	public Reader(String file){
		this.file = file;
	}
	public String getData(){
		return data;	
	}
	
	// read data and put into a string
	public String readFile(String file){
		StringBuffer sb = new StringBuffer();
		String s = "";
		FileReader fr = null;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		try {
			while((s = br.readLine()) !=null){
			    sb.append(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.data = sb.toString();
		return this.data;
	}
	

}
