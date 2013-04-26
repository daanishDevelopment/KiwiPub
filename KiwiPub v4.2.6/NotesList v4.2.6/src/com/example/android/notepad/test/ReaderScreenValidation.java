package com.example.android.notepad.test;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import android.graphics.Rect;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.notepad.R;
import com.example.android.notepad.Reader;

public class ReaderScreenValidation extends ActivityInstrumentationTestCase2<Reader> {

	public ReaderScreenValidation() { 
		   super("com.example.android.notepad.Reader.java", Reader.class); 
	}
	
	TextView tv6;
	ImageView image;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		tv6 = (TextView) getActivity().findViewById(R.id.text6);
		image = (ImageView) getActivity().findViewById(R.id.image1);
	}

	@Test
	public void test() {
		   View container = getActivity().findViewById(R.id.reader_linear); 
		   int boundaryWidth = container.getWidth(); 
		   int boundaryHeight = container.getHeight(); 

		   int[] location = new int[2]; 
		   container.getLocationOnScreen(location); 

		   int[] tv6Location = new int[2]; 
		   tv6.getLocationOnScreen(tv6Location); 
		   
		   int[] imageLocation = new int[2];
		   image.getLocationOnScreen(imageLocation);

		   Rect textRect = new Rect(); 
		   tv6.getDrawingRect(textRect);
		   
		   Rect imageRect = new Rect();
		   image.getDrawingRect(imageRect);

		   boolean widerThanBoundary = (textRect.width() > boundaryWidth); 
		   boolean tallerThanBoundary = (textRect.height() > boundaryHeight); 
		   boolean extendsOffRight = location[0] + boundaryWidth 
		     > tv6Location[0] + textRect.width(); 
		   assertTrue("Text wider than boundary", widerThanBoundary); 
		   assertTrue("Text taller than boundary", tallerThanBoundary); 
		   assertTrue("Text goes off right side", extendsOffRight);
		   
		   widerThanBoundary = (imageRect.width() > boundaryWidth); 
		   tallerThanBoundary = (imageRect.height() > boundaryHeight); 
		   extendsOffRight = location[0] + boundaryWidth 
		     > imageLocation[0] + imageRect.width(); 
		   assertTrue("Image wider than boundary", widerThanBoundary); 
		   assertTrue("Image taller than boundary", tallerThanBoundary); 
		   assertTrue("Image goes off right side", extendsOffRight);
	}

}
