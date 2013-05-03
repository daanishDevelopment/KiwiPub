package com.example.android.notepad;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;



public class Help extends Activity {
	
	/*
	 * 
	 * The help document is laid out in the following format
	 * 
	 * HEADER ("Help")
	 * 
	 * GENERAL ("General")
	 * GENERALCONTENT (explains main activity and general structure of app.  How to enter both reader and editor modes)
	 * 
	 * READER ("Reader")
	 * READERCONTENT (explains purpose of this mode and a few features)
	 * 
	 * EDITOR ("Editor")
	 * EDITORCONTENT (explains purpose of this mode and a few features)
	 * 
	 * FORMAT ("Format")
	 * FORMATCONTENT (explains the format of files that can be used as input here)
	 * 
	 */
	
	
	
	private final String HEADER = "Help\n";
	private final String GENERAL = "General";
	private final String READER = "Reader";
	private final String EDITOR = "Editor";
	private final String FORMAT = "Format";
 
	private final String GENERALCONTENT = "This application is intended for the creation and viewing of documents with visual aids.  From the main screen, users can " +
			"choose from two main modes: editing and reading." + "\n" + "Users enter reading mode by clicking on the icon of a folder and inputting the file " + 
			"path of the desired document.  This must be to a file of the specified format.  Said format is detailed below.  " + "Users can also enter reader mode by opening" + 
			"a file of the specified format from elswhere on their device (i.e. email)  " +
			"Users can enter editing mode by either selecting the compose icon to compose a new document, or by selecting a previously created document " + 
			"from the list" + "\n";
	private final String READERCONTENT = "In reader mode, users can scroll through documents, viewing associated media and text.  Users can adjust " +
			"font size up or down by pressing the associated icons in the action bar.  Users can also launch KitWare's app KiwiViewer by selecting its' icon in " +
			"the action bar" + "\n";	
	private final String EDITORCONTENT = "Editor mode works like any mobile device text editor.  Users can enter and edit text.  By selecting the plus button in the " +
			"action bar, users can enter the URL of an image they wish to add to the document.  " + "Users with a 'menu' button on their device may select it to " +
					"edit the title of their document." + "\n";
	private final String FORMATCONTENT = "Input files must be a .zip consiting of two components\n" + "1) A .txt file with the text of the document \n" +
					"2) An image file " +"\n" + "\n" + "For more detailed help, go to https://sites.google.com/site/comp524group10/home/help";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.help);

		TextView helpMainHeaderTV = (TextView) findViewById(R.id.help_main_header);
		TextView helpGeneralHeaderTV = (TextView) findViewById(R.id.help_general_header);
		TextView helpGeneralContentTV = (TextView) findViewById(R.id.help_general_content);
		TextView helpEditorHeaderTV = (TextView) findViewById(R.id.help_editor_header);
		TextView helpEditorContentTV = (TextView) findViewById(R.id.help_editor_content);
		TextView helpReaderHeaderTV = (TextView) findViewById(R.id.help_reader_header);
		TextView helpReaderContentTV = (TextView) findViewById(R.id.help_reader_content);
		TextView helpFormatHeaderTV = (TextView) findViewById(R.id.help_format_header);
		TextView helpFormatContentTV = (TextView) findViewById(R.id.help_format_content);
		
		helpMainHeaderTV.setText(HEADER);
		helpGeneralHeaderTV.setText(GENERAL);
		helpReaderHeaderTV.setText(READER);
		helpEditorHeaderTV.setText(EDITOR);
		helpGeneralContentTV.setText(GENERALCONTENT);
		helpReaderContentTV.setText(READERCONTENT);
		helpEditorContentTV.setText(EDITORCONTENT);
		helpFormatHeaderTV.setText(FORMAT);
		helpFormatContentTV.setText(FORMATCONTENT);
		


	}

}
