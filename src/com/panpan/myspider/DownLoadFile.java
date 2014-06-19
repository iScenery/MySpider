package com.panpan.myspider;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLException;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class DownLoadFile {
	//根据url和网页类型生成要保存的文件名，除去url和非文件字符
	public String getFileNameByUrl(String url,String contentType){
		//除去http://
		url = url.substring(7);
		//text 和html类型
		if(contentType.indexOf("html") != -1){
			url = url.replaceAll("[\\?/:*|<>\"]", "_") + ".html";
			return url;
		}
		//如application pdf类型
		else{
			return url.replaceAll("[\\?/:*|<>\"]", "_") + "." +
					contentType.substring(contentType.lastIndexOf("/") + 1);
		}
		
	}
	//将网页的二进制字节数组保存到本地，fileName为相对文件地址
	private void saveToLocal(byte[] data,String fileName){
		if(fileName == null){
			throw new IllegalArgumentException("filename is null");
		}
		try {
			DataOutputStream out  = new DataOutputStream(new FileOutputStream(new File(fileName)));
			for(int i = 0;i<data.length;i++){
				out.write(data[i]);
			}
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//下载指定url指向的页面
	public   String downLoadFile(String url){
		String filePath = null;
		// 1 、生成HttpClient对象并设置参数
		//设置参数 链接超时 5s
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(5000).build();
		
		HttpGet httpGet = new HttpGet(url);
		//请求重试处理
		HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {

		    public boolean retryRequest(
		            IOException exception,
		            int executionCount,
		            HttpContext context) {
		        if (executionCount >= 5) {
		            // Do not retry if over max retry count
		            return false;
		        }
		        if (exception instanceof InterruptedIOException) {
		            // Timeout
		            return false;
		        }
		        if (exception instanceof UnknownHostException) {
		            // Unknown host
		            return false;
		        }
		        if (exception instanceof ConnectTimeoutException) {
		            // Connection refused
		            return false;
		        }
		        if (exception instanceof SSLException) {
		            // SSL handshake exception
		            return false;
		        }
		        HttpClientContext clientContext = HttpClientContext.adapt(context);
		        HttpRequest request = clientContext.getRequest();
		        boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
		        if (idempotent) {
		            // Retry if the request is considered idempotent
		            return true;
		        }
		        return false;
		    }

		};
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).setRetryHandler(myRetryHandler).build();
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
				System.out.println("Method failed:"+response.getStatusLine().getStatusCode());
				filePath = null;
			}
			byte[] responseBody = EntityUtils.toByteArray(response.getEntity());
			System.out.println("ContentType.toString:"+response.getEntity().getContentType().toString());
			System.out.println("ContentType.getValue:"+response.getEntity().getContentType().getValue());
			filePath = getFileNameByUrl(url, response.getEntity().getContentType().getValue());
			System.out.println("filePath:"+filePath);
			saveToLocal(responseBody, filePath);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;
	}

}




