package cn.jbit.cms.util;

import java.io.*;

public class FileIO {
	//读取文本文件内容
	public String resdFile(String filePath) {
		BufferedReader reader=null;
		StringBuffer sb = new StringBuffer();
		try {
			reader=new BufferedReader(new FileReader(filePath));
			String line=null;
			while((line=reader.readLine())!=null) {
				sb.append(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (null != reader)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	//写入文本文件内容
	public void writeFile(String filePath,String str) {
		BufferedWriter w=null;
		try {
			w=new BufferedWriter(new FileWriter(filePath));
			w.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(w!=null) {
				try {
					w.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} 
	}
}
