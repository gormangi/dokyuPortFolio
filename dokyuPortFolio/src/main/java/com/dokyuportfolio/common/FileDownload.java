package com.dokyuportfolio.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dokyuportfolio.admin.vo.FileVO;

public class FileDownload {
	
	public static void download(HttpServletRequest request , HttpServletResponse response , FileVO fileInfo) throws Exception{
		
		String fileName = "";
		
		try {
			String browser = request.getHeader("User-Agent"); 
			
			if(browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")){
				fileName = URLEncoder.encode(fileInfo.getOriginFileName(), "UTF-8").replaceAll("\\+","%20");
			}else{
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			}
		}catch(UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		
		response.setContentType("application/octer-stream");
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		
		OutputStream os = null;
		FileInputStream fis = null;
		
		try {
			os = response.getOutputStream();
			fis = new FileInputStream(fileInfo.getSaveFilePath());

			int ncount = 0;
			byte[] bytes = new byte[512];

			while ((ncount = fis.read(bytes)) != -1 ){
				os.write(bytes, 0, ncount);
			}
			
		}catch(FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex){
			ex.printStackTrace();
		}finally{
			fis.close();
			os.close();
		}
		
	}
	
}
