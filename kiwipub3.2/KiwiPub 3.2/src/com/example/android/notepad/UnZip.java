package com.example.android.notepad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnZip {
	List<String> fileList;
	private String input_zip_file;
	private static final String OUTPUT_FOLDER = "zipFiles";
	private boolean textFound;

	/**
	 * Unzip it
	 * 
	 * @param zipFilePath
	 *            input zip file
	 * @param OUTPUT_FOLDER
	 *            zip file output folder
	 * @param allText
	 *  		  contains all the content of the text file
	 * @param allImagePaths
	 *            contains the paths to all the images in the unzipped folder
	 * 			  
	 */

	public void unZipIt(String zipFilePath) {
		this.input_zip_file = zipFilePath;
		this.textFound = false;
		
		List<String> allText = new ArrayList<String>();
		List<String> allImagePaths = new ArrayList<String>();
		
		File unzipDir = null;

		byte[] buffer = new byte[1024];

		try {

			// create output directory is not exists
			File folder = new File(OUTPUT_FOLDER);
			if (!folder.exists()) {
				folder.mkdir();
			}

			// get the zip file content
			ZipInputStream zis = new ZipInputStream(new FileInputStream(
					zipFilePath));
			// get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();

			while (ze != null) {

				String fileName = ze.getName();
				File newFile = new File(OUTPUT_FOLDER + File.separator
						+ fileName);

				System.out.println("file unzip : " + newFile.getAbsoluteFile());

				// create all non exists folders
				// else you will hit FileNotFoundException for compressed folder
				new File(newFile.getParent()).mkdirs();

				FileOutputStream fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				// retrieve text as string list
				if (fileName.endsWith(".txt") && this.textFound == false) {
					this.textFound = true;
					allText = TxtFileReader.readText(newFile.getAbsolutePath());
					unzipDir = newFile.getParentFile();
				}

				fos.close();
				ze = zis.getNextEntry();
			}

			if (unzipDir.exists() && this.textFound == true) {
				// retrieve all image paths
				allImagePaths = TxtFileReader.readImages(unzipDir);
			}
			// resetS
			this.textFound = false;

			zis.closeEntry();
			zis.close();

			/* pass allText and allImagePaths as intent to Reader
			 * 
			 *  Shuang: 
			 *  this cannot be done here.  Only an Activity can start an Activity and pass Intents
			 *  
			 *  */
			System.out.println("File unzipped successfully.");  // Nixon removed 'Passing Intents' from this string, as the intents will not be passed here

		} catch (IOException ex) {
			System.out.println("Error: failed to unzip file at \"" + zipFilePath + "\"");
		}
	}
}