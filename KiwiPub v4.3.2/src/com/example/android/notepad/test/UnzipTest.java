package com.example.android.notepad.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

public class UnzipTest {

	/*
	 * {@link UnZip} Change prefix of OUTPUT_FOLDER, SAVE_FOLDER, TEMP_FOLDER to
	 * known path on your device(windows, ubuntu) instead of
	 * environment.getExternalStorageDirectory()
	 */
	String a = "asdfadsfewrsersasd.zip \r\n aaaaaaaaaaaaaaaaaaaaaaaaa \r\n nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn \r\n";
	ArrayList<String> ls = new ArrayList<String>();
	String pathToTestZip = "C:\\Users\\shuangy\\Downloads\\test1.zip";
	String pathToImage = "C:\\Users\\shuangy\\Downloads\\open_file.png";
	String saveFileName = "testSaveIt";

	@Test
	public void testSave() {
		ls.add(this.pathToTestZip);
		ls.add(this.pathToImage);
		com.example.android.notepad.UnZip.save(a, ls, saveFileName);
		// File textFile = new
		// File("[Path_to_new_TEMP_FOLDER]/textFile_test1_temp_1.txt");
		File imageFile = new File("[PATH_TO_NEW_SAVE_FOLDER]\\testSaveit.zip");
		assertTrue(imageFile.exists());
	}
	
	@Test
	public void testUnZip() {
		testSave();
		com.example.android.notepad.UnZip
				.unZipIt("[PATH_TO_NEW_SAVE_FOLDER]\\testSaveit.zip");
		File resultImage = new File(
				"[PATH_TO_NEW_OUTPUT_FOLDER]\\open_file.png");
		File resultFile1 = new File("[PATH_TO_NEW_OUTPUT_FOLDER]\\test1.zip");
		assertTrue(resultImage.exists() && resultFile1.exists());
	}

	
}
