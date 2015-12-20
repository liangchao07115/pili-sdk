package com.pili.demo;

import java.util.List;

import org.junit.Test;

import com.pili.Hub;
import com.pili.PiliException;
import com.pili.Stream;
import com.pili.Stream.SaveAsResponse;
import com.pili.Stream.Segment;
import com.pili.Stream.SegmentList;
import com.pili.Stream.SnapshotResponse;
import com.pili.Stream.Status;
import com.pili.Stream.StreamList;
import com.qiniu.Credentials;

public class PiliDemo {
	private static final String ACCESS_KEY = "**";
	private static final String SECRET_KEY = "**";

	// Replace with your hub name
	private static final String HUB_NAME = "rwifeng";

	// Instantiate an Hub object
	public Credentials credentials = new Credentials(ACCESS_KEY, SECRET_KEY); // Credentials
																		// Object
	public Hub hub = new Hub(credentials, HUB_NAME); // Hub Object
	
	//createStream
	@Test
	public void createStream(){	
		
		
		// Create a new Stream
		String title = "smile001"; // optional, auto-generated as default
		String publishKey = "helloWorld"; // optional, auto-generated as default
		String publishSecurity = "static"; // optional, can be "dynamic" or
										// "static", "dynamic" as default
		Stream stream = null;
		try {
			stream = hub.createStream(title, publishKey, publishSecurity);
			System.out.println("hub.createStream:");
			System.out.println(stream.toJsonString());
			/*
			 * { "id":"z1.test-hub.55d80075e3ba5723280000d2",
			 * "createdAt":"2015-08-22T04:54:13.539Z",
			 * "updatedAt":"2015-08-22T04:54:13.539Z",
			 * "title":"55d80075e3ba5723280000d2", "hub":"test-hub",
			 * "disabled":false, "publishKey":"ca11e07f094c3a6e",
			 * "publishSecurity":"dynamic", "hosts":{ "publish":{
			 * "rtmp":"ey636h.publish.z1.pili.qiniup.com" }, "live":{
			 * "hdl":"ey636h.live1-hdl.z1.pili.qiniucdn.com",
			 * "hls":"ey636h.live1-hls.z1.pili.qiniucdn.com",
			 * "rtmp":"ey636h.live1-rtmp.z1.pili.qiniucdn.com" }, "playback":{
			 * "hls":"ey636h.playback1.z1.pili.qiniucdn.com" } } }
			 */
		} catch (PiliException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//getStream by streamId
	@Test
	public void getStream(){
		 try {
			 	Stream stream = null;
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

	//list streams
	@Test
	public void listStream(){
		try {
		      String status      = null;      // optional, can only be "connected"
		      String marker      = null;      // optional
		      long limit         = 0;         // optional
		      String titlePrefix = null;      // optional

		      StreamList streamList = hub.listStreams(status, marker, limit, titlePrefix);
		      System.out.println("hub.listStreams()");
		      System.out.println("marker:" + streamList.getMarker());
		      System.out.println("isEnd:" + streamList.isEnd());
		      List<Stream> list = streamList.getStreams();
		      for (Stream s : list) {
		    	  System.out.println(s.toJsonString());
		      }

		      /*
		       marker:10
		       isEnd:false
		       stream object
		       */
		  } catch (PiliException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		  }
	}

	//update stream 更新阻止下一次推流
	@Test
    public void updateStream() throws PiliException{
    	Stream stream = hub.getStream("z1.rwifeng.smile001"); //
    	// Update a Stream
    	String newPublishKey = "hello"; // optional
    	String newPublishSecurity = "static";           // optional, can be "dynamic" or "static"
    	boolean newDisabled        = false;               // optional, can be "true" of "false"
    	try {
    	    Stream newStream = stream.update(newPublishKey, newPublishSecurity, newDisabled);
    	    System.out.println("Stream update()");
    	    System.out.println(newStream.toJsonString());
    	    stream = newStream;
    	    /*
    	    {
    	        "id":"z1.test-hub.55d80075e3ba5723280000d2",
    	        "createdAt":"2015-08-22T04:54:13.539Z",
    	        "updatedAt":"2015-08-22T01:53:02.738973745-04:00",
    	        "title":"55d80075e3ba5723280000d2",
    	        "hub":"test-hub",
    	        "disabled":true,
    	        "publishKey":"new_secret_words",
    	        "publishSecurity":"static",
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

	//disable stream  及时断掉主播推流
	@Test
	public void disableStream() throws PiliException{
		Stream stream = hub.getStream("z1.rwifeng.smile001"); //
		try {
		    Stream disabledStream = stream.disable();
		    System.out.println("Stream disable()");
		    System.out.println(disabledStream.isDisabled());
		    /*
		     * true
		     * 
		     * */
		} catch (PiliException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
	}

	//enable stream
	@Test
	public void enableStream() throws PiliException{
		// Enable a Stream
		Stream stream = hub.getStream("z1.rwifeng.smile001");
		try {
		    Stream enabledStream = stream.enable();
		    System.out.println("Stream enable()");
		    System.out.println(enabledStream.isDisabled());
		    /*
		     * false
		     * 				
		     * */
		} catch (PiliException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}		
	}

	//Get Stream status
	@Test
	public void getStreamStatus() throws PiliException{
		// Get Stream status
		// Enable a Stream
		Stream stream = hub.getStream("z1.rwifeng.smile001");
		try {
		    Status status = stream.status();
		    System.out.println("Stream status()");
		    System.out.println(status.toString());
		    /*
		    {
		        "addr":"222.73.202.226:2572",
		        "startFrom": "2015-09-10T05:58:10.289+08:00",
		        "status":"connected",
		        "bytesPerSecond":16870.200000000001,
		        "framesPerSecond":{
		            "audio":42.200000000000003,
		            "video":14.733333333333333,
		            "data":0.066666666666666666
		         }
		     }
		    */
		} catch (PiliException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}

	// Generate RTMP publish URL
	@Test
	public void generatePublicUrl() throws PiliException{
		// Generate RTMP publish URL
		Stream stream = hub.getStream("z1.rwifeng.smile001");
		try {
		    String publishUrl = stream.rtmpPublishUrl();
		    System.out.println("Stream rtmpPublishUrl()");
		    System.out.println(publishUrl);
		    // rtmp://ey636h.publish.z1.pili.qiniup.com/test-hub/55d810aae3ba5723280000db?nonce=1440223404&token=hIVJje0ZOX9hp7yPIvGBmJ_6Qxo=

		} catch (PiliException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}

	// Generate RTMP live play URLs
	@Test
	public void generatePlayUrls() throws PiliException{
		Stream stream = hub.getStream("z1.rwifeng.smile001");
		
		String originUrl = stream.rtmpLiveUrls().get(Stream.ORIGIN);
		System.out.println("Stream rtmpLiveUrls()");
		System.out.println(originUrl);
		// rtmp://ey636h.live1-rtmp.z1.pili.qiniucdn.com/test-hub/55d8113ee3ba5723280000dc
		
		// Generate HLS play URLs
		String originLiveHlsUrl = stream.hlsLiveUrls().get(Stream.ORIGIN);
		System.out.println("Stream hlsLiveUrls()");
		System.out.println(originLiveHlsUrl);
		// http://ey636h.live1-http.z1.pili.qiniucdn.com/test-hub/55d8119ee3ba5723280000dd.m3u8
		
		// Generate Http-Flv live play URLs
		String originLiveFlvUrl = stream.httpFlvLiveUrls().get(Stream.ORIGIN);
		System.out.println("Stream httpFlvLiveUrls()");
		System.out.println(originLiveFlvUrl);
		// http://ey636h.live1-http.z1.pili.qiniucdn.com/test-hub/55d8119ee3ba5723280000dd.flv
	}

	// Get Stream segments
	@Test
	public void getStreamSegments() throws PiliException{
		Stream stream = hub.getStream("z1.rwifeng.smile001");
		long start = 0;    // optional, in second, unix timestamp
		long end   = 0;    // optional, in second, unix timestamp
		int limit  = 0;    // optional, int
		try {
		    SegmentList segmentList = stream.segments(1450454400, 1450540800, 10000);
		    System.out.println("The earliest data of stream:" + segmentList.getStart()
		            + ",the latest data of stream:" + segmentList.getEnd());

		    System.out.println("The duration of the current segment:" + segmentList.getDuration());

		    System.out.println("Stream segments()");
		    for (Segment segment : segmentList.getSegmentList()) {
		        System.out.println("start:" + segment.getStart() + ",end:" + segment.getEnd());
		    }
		    /*
		         The earliest data of stream:1444298545,the latest data of stream:1444298612
		         The duration of the current segemnt:67
		         start:1440315411,end:1440315435
		     */
		} catch (PiliException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}

	// Generate HLS playback URLs
	@Test
	public void getStreamPlayBackUrls() throws PiliException{
		Stream stream = hub.getStream("z1.rwifeng.smile001");
		long startHlsPlayback     = 1450520151;  // required, in second, unix timestamp
		long endHlsPlayback       = 1450526313;  // required, in second, unix timestamp
		try {
		    String hlsPlaybackUrl = stream.hlsPlaybackUrls(startHlsPlayback, endHlsPlayback).get(Stream.ORIGIN);

		    System.out.println("Stream hlsPlaybackUrls()");
		    System.out.println(hlsPlaybackUrl);
		    // http://ey636h.playback1.z1.pili.qiniucdn.com/test-hub/55d8119ee3ba5723280000dd.m3u8?start=1440315411&end=1440315435
		} catch (PiliException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}

	//
	@Test
	public void saveStreamAsFile() throws PiliException{
		// Save Stream as a file
		Stream stream = hub.getStream("z1.rwifeng.smile001");
		
		String saveAsFormat    = "mp4";                            // required
		String saveAsName      = "videoName" + "." + saveAsFormat; // required
		long saveAsStart       = 1450530000;                       // required, in second, unix timestamp
		long saveAsEnd         = 1450531800;                       // required, in second, unix timestamp
		String saveAsNotifyUrl = null;                             // optional
		try {
		    SaveAsResponse response = stream.saveAs(saveAsName, saveAsFormat, saveAsStart, saveAsEnd, saveAsNotifyUrl);
		    System.out.println("Stream saveAs()");
		    System.out.println(response.toString());
		    /*
		     {
		         "url":"http://ey636h.vod1.z1.pili.qiniucdn.com/recordings/z1.test-hub.55d81a72e3ba5723280000ec/videoName.m3u8",
		         "targetUrl":"http://ey636h.vod1.z1.pili.qiniucdn.com/recordings/z1.test-hub.55d81a72e3ba5723280000ec/videoName.mp4",
		         "persistentId":"z1.55d81c6c7823de5a49ad77b3"
		     }
		    */
		} catch (PiliException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}
	
	// Snapshot Stream
	@Test
	public void snapshotStream() throws PiliException{
		Stream stream = hub.getStream("z1.rwifeng.smile001");
		String format    = "jpg";                      // required
		String name      = "imageName" + "." + format; // required
		long time        = 1450530000;                 // optional, in second, unix timestamp
		String notifyUrl = "http://support2technical.me";                       // optional

		try {
		    SnapshotResponse response = stream.snapshot(name, format, time, notifyUrl);
		    System.out.println("Stream snapshot()");
		    System.out.println(response.toString());
		    /*
		     {
		         "targetUrl":"http://ey636h.static1.z1.pili.qiniucdn.com/snapshots/z1.test-hub.55d81a72e3ba5723280000ec/imageName.jpg",
		         "persistentId":"z1.55d81c247823de5a49ad729c"
		     }
		     */
		} catch (PiliException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}
	
	
	// Delete a Stream
	@Test
	public void deleteStream() throws PiliException{
		Stream stream = hub.getStream("z1.rwifeng.560a24c1eb6f9249f90081d5");
		try {
		    String res = stream.delete();
		    System.out.println("Stream delete()");
		    System.out.println(res);
		    // No Content
		} catch (PiliException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}
}
