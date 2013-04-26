package com.example.android.notepad;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Reader extends Activity {

	private static final String TAG = "Reader";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.reader);
		
		if (getIntent().getData().getPath() != null) {
			String path = getIntent().getData().getPath();
			/*
			 * 
			 * SHUANG,
			 * 
			 * I realize this is not efficient, my concern however is getting it to work.  I don't have time right now to make it good.
			 * 
			 * UnZip.unZipIt(path);
			 * ArrayList<String> t = UnZip.allText;
			 * ArrayList<String> imgs = UnZip.allImagePaths;
			 * 
			 * 
			 * 
			 */
		
		}
		
		else{
			final Intent i = getIntent();
			Bundle b = i.getBundleExtra("bundle");
			ArrayList<String> t = b.getStringArrayList("text");
			ArrayList<String> imgs = b.getStringArrayList("images");
		}
		
		
		
		//get textviews
		TextView tv1 = (TextView) findViewById(R.id.text1);
		TextView tv2 = (TextView) findViewById(R.id.text2);
		TextView tv3 = (TextView) findViewById(R.id.text3);
		TextView tv4 = (TextView) findViewById(R.id.text4);
		TextView tv5 = (TextView) findViewById(R.id.text5);
		TextView tv6 = (TextView) findViewById(R.id.text6);
		
		/* 
		 * Ultimately we will be able also determine the location of images amongst the TextViews 
		 * and at that time we will break up the text into textviews more intelligently
		 * for now, we will go one paragraph per text view until the sixth, and then we will dump everything in the sixth
		 */
		
		/*
		 * TEMPORARILY COMMENTED OUT IN ORDER TO TEST LAUNCH BY ZIP FILE
		 * 
		 * 
		 * 
		String tv6String;
		tv6String = t.get(6);
		for(int j=7; j < t.size(); j++) {
			tv6String = tv6String.concat("\n" + t.get(j));
		}
		
		
		//set TexViews
		tv1.setText(t.get(0));
		tv2.setText(t.get(1));
		tv3.setText(t.get(2));
		tv4.setText(t.get(3));
		tv5.setText(t.get(4));
		tv6.setText(tv6String);
		
		
		//get ImageViews
		ImageView iv1 = (ImageView) findViewById(R.id.image1);
		ImageView iv2 = (ImageView) findViewById(R.id.image2);
		ImageView iv3 = (ImageView) findViewById(R.id.image3);
		ImageView iv4 = (ImageView) findViewById(R.id.image4);
		ImageView iv5 = (ImageView) findViewById(R.id.image5);
		
		
		//set ImageViews by path
		File imgFile1 = new  File(imgs.get(0));
		if(imgFile1.exists()){
		    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile1.getAbsolutePath());
		    iv1.setImageBitmap(myBitmap);
		}
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
}
