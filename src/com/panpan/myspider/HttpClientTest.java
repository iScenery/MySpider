package com.panpan.myspider;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class HttpClientTest {
	private static CloseableHttpClient httpClient = HttpClients.createDefault();
	public static boolean downLoadPage(String path) throws Exception{
		HttpGet httpGet = new HttpGet(path);
		CloseableHttpResponse responsel = httpClient.execute(httpGet);
		System.out.println(responsel.getStatusLine());
		//System.out.println(responsel.getStatusLine().getStatusCode());
		InputStream is = null;
		OutputStream os = null;
		if(responsel.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
			is = responsel.getEntity().getContent();
			int start = path.indexOf('.', path.indexOf('.')+1);
			String subPath = path.substring(path.lastIndexOf('/')+1).length()>0?path.substring(path.lastIndexOf('/')+1):path.substring(start+1,path.lastIndexOf('/'));
			System.out.println(subPath);
			String fileName = subPath+".html";
			os = new FileOutputStream(fileName);
			int tempByte = -1;
			while((tempByte = is.read())>0){
				os.write(tempByte);
			}
			if(is != null){
				is.close();
			}
			if(os != null){
				os.close();
			}
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		try {
			downLoadPage("http://www.zhihu.com");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
//		try {
//			
//			System.out.println(responsel.getStatusLine());
//			HttpEntity entity1 = responsel.getEntity();
//			System.out.println(entity1.getContentType());
//			System.out.println(EntityUtils.toString(entity1,"utf-8"));
//			EntityUtils.consume(entity1);
//			
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		try {
//			responsel.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
