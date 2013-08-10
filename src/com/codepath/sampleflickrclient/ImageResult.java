package com.codepath.sampleflickrclient;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageResult {
	private String fullUrl;
	private String thumbUrl;
	
	public String getFullUrl() {
		return fullUrl;
	}
	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}
	public String getThumbUrl() {
		return thumbUrl;
	}
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	public ImageResult(JSONObject json){
		try{
			
//			http://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_[mstzb].jpg
//				or
//			http://farm{farm-id}.staticflickr.com/{server-id}/{id}_{o-secret}_o.(jpg|gif|png)
//
//			farm: "3",
//			id: "9427823406",
//			isfamily: "0",
//			isfriend: "0",
//			ispublic: "1",
//			owner: "34078107@N00",
//			secret: "952eacc956",
//			server: "2814",
			
//			http://farm4.staticflickr.com/3296/2439864402_ce56cba75c_s.jpg
//
//				http://farm4.staticflickr.com/3296/2439864402_ce56cba75c_z.jpg

			String farmId = json.getString("farm");
			String serverId = json.getString("server");
			String id = json.getString("id");
			String secret = json.getString("secret");
			
			this.fullUrl = "http://farm"+farmId+".staticflickr.com/"+serverId+"/"+id+"_"+secret+"_"+"z.jpg";
			this.thumbUrl = "http://farm"+farmId+".staticflickr.com/"+serverId+"/"+id+"_"+secret+"_"+"s.jpg";
		} catch(JSONException e) {
			this.fullUrl = null;
			this.thumbUrl = null;
		}
	}
	
	public static ArrayList<ImageResult> fromJSONArray(JSONArray array){
		ArrayList<ImageResult> results = new ArrayList<ImageResult>();
		for(int x=0; x< array.length();x++){
			try{
				results.add(new ImageResult(array.getJSONObject(x)));
			} catch(JSONException e){
				e.printStackTrace();
			}
		}
		return results;
	}
	
	public String toString(){
		return this.thumbUrl;
	}
	
}
