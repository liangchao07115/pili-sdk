package com.pili.demo;

import org.junit.Test;

import com.pili.Hub;
import com.pili.PiliException;
import com.pili.Stream;
import com.qiniu.Credentials;

public class GetStream {
	private static final String ACCESS_KEY = "4brtJLyWA3bplJKlkn7ZypPbzKcS-58MsF1cnsF4";
	private static final String SECRET_KEY = "jt8qbHTrBFAl6HZNt9Mmd2pcx122aJlJ-5mgS-7g";

	// Replace with your hub name
	private static final String HUB_NAME = "rwifeng";

	public Stream stream = null;
	
	@Test
	public void getExistStream(){
		 Credentials credentials = new Credentials(ACCESS_KEY, SECRET_KEY); // Credentials Object
		 Hub hub = new Hub(credentials, HUB_NAME); // Hub Object
		
		  try {
		    stream = hub.getStream("z1.rwifeng.smile001");
		    System.out.println("hub.getStream:");
		    System.out.println(stream.toJsonString());
		    /*
		    {
		        "id":"z1.test-hub.55d80075e3ba5723280000d2",
		        "createdAt":"2015-08-22T04:54:13.539Z",
		        "updatedAt":"2015-08-22T04:54:13.539Z",
		        "title":"55d80075e3ba5723280000d2",
		        "hub":"test-hub",
		        "disabled":false,
		        "publishKey":"ca11e07f094c3a6e",
		        "publishSecurity":"dynamic",
		        "hosts":{
		            "publish":{
		                  "rtmp":"ey636h.publish.z1.pili.qiniup.com"
		             },
		             "live":{
		                  "hdl":"ey636h.live1-hdl.z1.pili.qiniucdn.com",
		                  "hls":"ey636h.live1-hls.z1.pili.qiniucdn.com",
		                  "rtmp":"ey636h.live1-rtmp.z1.pili.qiniucdn.com"
		             },
		             "playback":{
		                  "hls":"ey636h.playback1.z1.pili.qiniucdn.com"
		             }
		         }
		     }
		     */
		} catch (PiliException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}

}
