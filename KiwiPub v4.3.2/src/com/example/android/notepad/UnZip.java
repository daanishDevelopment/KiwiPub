package com.example.android.notepad;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import android.os.Environment;
import android.util.Log;

public class UnZip {
	List<String> fileList;
	private static String input_zip_file;
	// Modified output path to external storage 23/04
	private static final String OUTPUT_FOLDER = Environment
			.getExternalStorageDirectory() + "/KiwiPubUnZip";
	private static final String SAVE_FOLDER = Environment
			.getExternalStorageDirectory() + "/KiwiPubZip";
	private static final String TEMP_FOLDER = Environment
			.getExternalStorageDirectory() + "/KiwiPubTemp";
	private static int count = 0;
	private static boolean textFound, imagesFound, modelsFound;

	public static List<String> allText;
	public static List<String> allImagePaths;
	public static List<String> allModelPaths;

	/**
	 * Unzip it
	 * 
	 * @param zipFilePath
	 *            input zip file
	 * @param OUTPUT_FOLDER
	 *            zip file output folder
	 * @param allText
	 *            contains all the content of the text file
	 * @param allImagePaths
	 *            contains the paths to all the images in the unzipped folder
	 *            
	 * @param allModelPaths
	 * 				contains the paths to all the models in the unzipped folder
	 * 
	 */

	public static void unZipIt(String zipFilePath) {
		input_zip_file = zipFilePath;
		textFound = false;
		imagesFound = false;

		File unzipDir = null;

		byte[] buffer = new byte[2048];

		try {
			// create output directory if not exists
			File folder = new File(OUTPUT_FOLDER);
			
			if (folder.isDirectory()) {
		        String[] children = folder.list();
		        for (int i = 0; i < children.length; i++) {
		            new File(folder, children[i]).delete();
		        }
		    }

			
			if (!folder.exists()) {
				folder.mkdir();
			}

			File zipFile = new File(input_zip_file);
			InputStream in = new FileInputStream(zipFile);
			// get the zip file content
			ZipInputStream zis = new ZipInputStream(in);
			// get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();

			while (ze != null) {

				String fileName = ze.getName();
				File newFile = new File(Environment.getExternalStorageDirectory() + "/KiwiPubUnZip/"  + fileName);
				newFile.deleteOnExit();

				// create all non exists folders
				// else you will hit FileNotFoundException for compressed folder
				new File(newFile.getParent()).mkdirs();
				
				FileOutputStream fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				// retrieve text as string list
				if (fileName.endsWith(".txt") && textFound == false) {
					textFound = true;
					allText = TxtFileReader.readText(newFile.getAbsolutePath());
					unzipDir = newFile.getParentFile();
				}
				

				fos.close();
				ze = zis.getNextEntry();
			}

			
			if (unzipDir.exists() && textFound == true) {
				// retrieve all image paths
				
				
				imagesFound = false;
				allImagePaths = TxtFileReader.readImages(unzipDir);
				imagesFound = true;
			}
			
			if(unzipDir.exists() && imagesFound == true) {
				allModelPaths = TxtFileReader.readModels(unzipDir);
			}
			
			// resetS
			textFound = false;

			zis.closeEntry();
			zis.close();
			Log.d("!!!!", "File unzipped successfully.");

		} catch (FileNotFoundException ex) {
			Log.e("!!!!", "Error: File not found at \"" + zipFilePath
					+ "\"");
		} catch (IOException e) {
			Log.e("!!!!", "Error: IO");		
		}
	}

	/*
	 * save takes in the paths of all the targeted files and a file name used to
	 * name the resulting .zip file
	 */
	public static void save(String text, ArrayList<String> filePaths,
			String fileName) {

		byte[] buffer = new byte[2048];
		new File(SAVE_FOLDER).mkdir();
		new File(TEMP_FOLDER).mkdir();


		String textFilePath = writeTextFile(text, fileName);

		String name = fileName;
		// making sure saving as right file type
		if (!name.endsWith(".zip")) {
			name = name + ".zip";
		}

		try {

			FileOutputStream fos = new FileOutputStream(SAVE_FOLDER + "/"
					+ name);
			ZipOutputStream zos = new ZipOutputStream(fos);

			if (filePaths != null) {
				for (String path : filePaths) {
					File file = new File(path);
					ZipEntry ze = new ZipEntry(file.getName());
					zos.putNextEntry(ze);
					FileInputStream in = new FileInputStream(file);

					int len;
					while ((len = in.read(buffer)) > 0) {
						zos.write(buffer, 0, len);
					}

					in.close();
					zos.closeEntry();
				}
			}else{
				Log.i("UnZip", "text file only.");
			}

			// adding text file to the zip
			File file = new File(textFilePath);
			ZipEntry ze = new ZipEntry(file.getName());
			zos.putNextEntry(ze);
			FileInputStream in = new FileInputStream(file);

			int len;
			while ((len = in.read(buffer)) > 0) {
				zos.write(buffer, 0, len);
			}

			in.close();
			zos.closeEntry();

			// closing stream it
			zos.close();

			Log.i("Save", "Save successful, file \"" + name + "\" created at"
					+ SAVE_FOLDER + ".");
		} catch (FileNotFoundException e) {
			Log.i("Save Error", "Error: file not found.");
		} catch (IOException e) {
			Log.e("Save Error", "Error: IO Exception.");
		}
	}

	private static String writeTextFile(String text, String fileName) {

		try {
			String name = TEMP_FOLDER + "/textFile_" + fileName + "_temp_"
					+ count + ".txt";
			File textFile = new File(name);
			textFile.createNewFile();

			PrintWriter writer = new PrintWriter(name);
			writer.print(text);
			writer.close();

			count++;
			Log.i("Save", ".txt file creating successfully.");
			return textFile.getAbsolutePath();
		} catch (FileNotFoundException e) {
			Log.e("Text File", "Error: file not found.");
			return null;
		} catch (IOException e) {
			Log.e("UnZip", "IO Exception at text writer.");
			return null;
		}
	}
}