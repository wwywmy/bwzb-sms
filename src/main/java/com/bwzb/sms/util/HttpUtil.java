package com.bwzb.sms.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

import com.bwzb.sms.exception.SmsException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpUtil {

	public String postJSON(final String url,final String jsonString)throws Exception {
		StringBuffer sb = new StringBuffer("");


		HttpPost post = new HttpPost(url);
//		post.addHeader(new BasicHeader("Content-Type", "text/x-www-form-urlencoded"));
		post.addHeader(new BasicHeader("Content-Type", "application/json"));

		StringEntity entity = null;
		try {
			entity = new StringEntity(jsonString);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SmsException(e.getMessage());
		}
		post.setEntity(entity);
		
		
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpResponse response = httpclient.execute(post);


		if (200 == response.getStatusLine().getStatusCode()) {
			BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
			
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();

			log.info(sb.toString()); // {"message":"查询结果为空","status":"405"}

		}
		httpclient.close();
		return sb.toString();
	}
}
