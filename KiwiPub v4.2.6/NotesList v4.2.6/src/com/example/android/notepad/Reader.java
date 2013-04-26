package com.example.android.notepad;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class Reader extends Activity {

	private static final String TAG = "Reader";
	private static int FONTSIZE = 15;
	private List<String> models = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("AAAA", "DEBUGGINGaaaaaa ");

		setContentView(R.layout.reader);
		
		List<String> t = new ArrayList<String>();
		List<String> imgs = new ArrayList<String>();

		Log.d("AAAA", "DEBUGGING33333333 ");

		if (getIntent().hasExtra("path")) {
			Log.d("AAAA", "DEBUGGING1111 ");
			final Intent i = getIntent();
			Log.d("AAAA", "DEBUGGING2222 ");

			Bundle b = i.getBundleExtra("bundle");
			//t = b.getStringArrayList("text");
			//imgs = b.getStringArrayList("images");
			String path = b.getString("path");
			Log.d("AAAA", "DEBUGGING " + path);
			UnZip.unZipIt(path);
			t = UnZip.allText;
			Log.d("AAAA", "DEBUGGING " + t.get(0));

			imgs = UnZip.allImagePaths;
			Log.d("AAAA", "DEBUGGING " + imgs.get(0));
			
			models = UnZip.allModelPaths;
		}
		
		else{
			String path = getIntent().getData().getPath();
			
			 UnZip.unZipIt(path);
			 t = UnZip.allText;
			 imgs = UnZip.allImagePaths;
			 models = UnZip.allModelPaths;
			
			
		}
		
		
		
		//get textviews
		//TextView tv1 = (TextView) findViewById(R.id.text1);
		//TextView tv2 = (TextView) findViewById(R.id.text2);
		//TextView tv3 = (TextView) findViewById(R.id.text3);
		//TextView tv4 = (TextView) findViewById(R.id.text4);
		//TextView tv5 = (TextView) findViewById(R.id.text5);
		TextView tv6 = (TextView) findViewById(R.id.text6);
		
		/* 
		 * Ultimately we will be able also determine the location of images amongst the TextViews 
		 * and at that time we will break up the text into textviews more intelligently
		 * for now, we will go one paragraph per text view until the sixth, and then we will dump everything in the sixth
		 */
		
		String tv6String = "";
		for(int j=0; j < t.size(); j++) {
			tv6String = tv6String + t.get(j) + "\r\n";
		}
		
		
		//set TexViews
		//tv1.setText(t.get(0));
		//tv2.setText(t.get(1));
		//tv3.setText(t.get(2));
		//tv4.setText(t.get(3));
		//tv5.setText(t.get(4));
		tv6.setText(tv6String);
		
		
		//get ImageViews
		ImageView iv1 = (ImageView) findViewById(R.id.image1);
		//ImageView iv2 = (ImageView) findViewById(R.id.image2);
		//ImageView iv3 = (ImageView) findViewById(R.id.image3);
		//ImageView iv4 = (ImageView) findViewById(R.id.image4);
		//ImageView iv5 = (ImageView) findViewById(R.id.image5);
		
		
		//set ImageViews by path
		File imgFile1 = new  File(imgs.get(0));
		 if(imgFile1.exists()){
		    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile1.getAbsolutePath());
		    iv1.setImageBitmap(myBitmap);
		}
		
		/*
		File imgFile2 = new  File(imgs.get(1));
		if(imgFile2.exists()){
		    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile2.getAbsolutePath());
		    iv2.setImageBitmap(myBitmap);
		}
		File imgFile3 = new  File(imgs.get(2));
		if(imgFile3.exists()){
		    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile3.getAbsolutePath());
		    iv3.setImageBitmap(myBitmap);
		}
		File imgFile4 = new  File(imgs.get(3));
		if(imgFile4.exists()){
		    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile4.getAbsolutePath());
		    iv4.setImageBitmap(myBitmap);
		}
		File imgFile5 = new  File(imgs.get(4));
		if(imgFile5.exists()){
		    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile5.getAbsolutePath());
		    iv5.setImageBitmap(myBitmap);
		}
		*/
	
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.reader_menu, menu);
	    return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.menu_font_up:
	        	adjustFontSizeUp();
	            break;
	        case R.id.menu_font_down:
	        	adjustFontSizeDown();
	        	break;
	        case R.id.menu_kiwiviewer:
	        	launchKiwiViewer();
	        	break;
	    }
	      
	    return super.onOptionsItemSelected(item);
	    }
	
	public void launchKiwiViewer() {
		PackageManager pm = getPackageManager();
		try
		{
		    String packageName = "com.kitware.KiwiViewer";
		    Intent launchIntent = pm.getLaunchIntentForPackage(packageName);
		    File file = new File(models.get(0));
		    //Uri uri = new Uri.Builder().build();
		    launchIntent.setData(Uri.fromFile(file));
		    startActivity(launchIntent);
		}
		catch (Exception e1)
		{
		}
	}
	
	
	public void adjustFontSizeUp() {
		if (FONTSIZE < 45) {
			FONTSIZE++;
			setFontSize();
		}

		
	}
	
	public void adjustFontSizeDown() {
		if (FONTSIZE > 10) {
			FONTSIZE--;
			setFontSize();	
		}
	}
	
	public void setFontSize() {
		//TextView tv1 = (TextView) findViewById(R.id.text1);
		//TextView tv2 = (TextView) findViewById(R.id.text2);
		//TextView tv3 = (TextView) findViewById(R.id.text3);
		//TextView tv4 = (TextView) findViewById(R.id.text4);
		//TextView tv5 = (TextView) findViewById(R.id.text5);
		TextView tv6 = (TextView) findViewById(R.id.text6);
		//tv1.setTextSize(FONTSIZE);
		//tv2.setTextSize(FONTSIZE);
		//tv3.setTextSize(FONTSIZE);
		//tv4.setTextSize(FONTSIZE);
		//tv5.setTextSize(FONTSIZE);
		tv6.setTextSize(FONTSIZE);
	}
}
