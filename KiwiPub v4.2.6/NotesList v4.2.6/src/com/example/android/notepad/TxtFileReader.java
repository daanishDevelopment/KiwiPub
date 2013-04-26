package com.example.android.notepad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;
import android.util.Log;

public class TxtFileReader {

	public static List<String> readText(String filePath) {

		List<String> allText = new ArrayList<String>();

		BufferedReader br = null;

		try {
			String sCurrentLine;

			br = new BufferedReader(new FileReader(filePath));

			while ((sCurrentLine = br.readLine()) != null) {
				allText.add(sCurrentLine + "\r\n");
			}

		} catch (IOException e) {
			Log.e("txtFileReader", "Error: failed reading text file from \""
					+ filePath + "\".");
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				Log.e("txtFileReader", "Error: failed closing buffered reader.");
			}
		}

		return allText;
	}

	
	
	public static List<String> readImages(File dir) {
		
		if (!dir.exists()) {
			System.out
					.println("Error: Parent directory for images does not exist.");
			return null;
		}
		

		List<String> allImagePaths = new ArrayList<String>();
		for (File file : dir.listFiles()) {
			String filePath = file.getPath();
			if (file.isFile()
					&& (filePath.endsWith(".png") || filePath.endsWith(".jpg"))) {
				allImagePaths.add(filePath);
			}
		}
		
		/*
		if(filePath.endsWith(".png") || filePath.endsWith(".jpg")) {
			allImagePaths.add(filePath);
		}
		*/
		
	

		return allImagePaths;
	}
	
	
	//new
	public static List<String> readModels(File dir) {
		if (!dir.exists()) {
			System.out
					.println("Error: Parent directory for Models does not exist.");
			return null;
		}

		List<String> allModelPaths = new ArrayList<String>();
		for (File file : dir.listFiles()) {
			String filePath = file.getPath();
			if (file.isFile()
					&& (filePath.endsWith(".vtp"))) {
				allModelPaths.add(filePath);
			}
		}
		

		return allModelPaths;
	}
}
