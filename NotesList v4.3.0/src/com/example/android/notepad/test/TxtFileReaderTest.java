package com.example.android.notepad.test;

import java.io.File;

import android.test.AndroidTestCase;
import junit.*;
import org.junit.*;
import com.example.android.notepad.TxtFileReader;

;

public class TxtFileReaderTest extends AndroidTestCase {

	private static final File dir = new File("sample filepath");
	AndroidTestCase txtfilereaderTest;
	TxtFileReader testingClass;

	public TxtFileReaderTest() {
		AndroidTestCase txtfilereaderTest = new AndroidTestCase();
	}

	@Before
	public void setup() {
		TxtFileReader testingClass = new TxtFileReader();
	}

	@Test
	public void readImageTest() {
		txtfilereaderTest.assertNull(
				"This should be true because I am feeding a null file",
				testingClass.readImages(null));

	}

	@Test
	public void readTextTest() {
		txtfilereaderTest.assertEquals(testingClass.readText(null), null);
		txtfilereaderTest.assertNull(testingClass.readText("false filepath"));
	}

	@Test
	public void readModels() {
		txtfilereaderTest.assertNotSame(testingClass.readImages(dir),
				testingClass.readModels(dir));
		txtfilereaderTest.assertNotSame(testingClass.readModels(dir),
				testingClass.readModels(null));
	}
}
