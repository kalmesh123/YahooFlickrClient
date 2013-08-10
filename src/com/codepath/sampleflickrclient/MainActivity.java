package com.codepath.sampleflickrclient;

import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText textField;
	Button btnSearch;
	GridView gridView;
	ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
	ImageResultAdapter imageResultAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setUpViews();
		
		imageResultAdapter = new ImageResultAdapter(this, imageResults);
		gridView.setAdapter(imageResultAdapter);
	}

	   public void onImageSearch(View v){
	    	String query = this.textField.getText().toString();
	    	Toast.makeText(this, "Searching for " + query, Toast.LENGTH_LONG).show();
	    	
	    	AsyncHttpClient client = new AsyncHttpClient();
	    	client.get("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20flickr.photos.search%20where%20text%3D%22"
	    	+URLEncoder.encode(query)+"%22%20and%20api_key%3D%221c5207d1c96a21b84d1c411d4c7df610%22%20limit%2010&format=json", 
	    			new JsonHttpResponseHandler(){
	    				@Override
	    				public void onSuccess(JSONObject response){
	    					JSONArray imageJsonResults = null;
	    					try{
	    						imageJsonResults = response.getJSONObject("query").getJSONObject("results").getJSONArray("photo");
	    						imageResults.clear();
	    						imageResults.addAll(ImageResult.fromJSONArray(imageJsonResults));
	    						imageResultAdapter.addAll(imageResults);
	    						Log.d("DEBUG", imageResults.toString());
	    					} catch(JSONException e){
	    						e.printStackTrace();
	    					}
	    				}
	    	});
	    }

	
	private void setUpViews(){
		textField = (EditText)findViewById(R.id.textField);
		btnSearch = (Button)findViewById(R.id.buttonSearch);
		gridView = (GridView)findViewById(R.id.gridView);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
