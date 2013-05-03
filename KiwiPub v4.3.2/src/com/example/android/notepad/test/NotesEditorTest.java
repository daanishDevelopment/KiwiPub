package com.example.android.notepad.test;

import org.junit.Before;
import org.junit.Test;

import com.example.android.notepad.NoteEditor;

import android.graphics.Bitmap;
import android.test.AndroidTestCase;

public class NotesEditorTest extends AndroidTestCase {

	private static final String sampleString = null;// please enter a valid
													// string filepath to a
													// bitmap
	private AndroidTestCase NotesEditorTest;
	private NoteEditor testee;
	private Bitmap samplebitmap; // supply a sample bitmap to test

	public NotesEditorTest() {
		AndroidTestCase NotesEditorTest = new AndroidTestCase();
	}

	@Before
	public void setup() {
		NoteEditor testee = new NoteEditor();
	}

	@Test
	public void testBitmaptoString() {
		NotesEditorTest.assertNull(testee.BitMapToString(null));
		NotesEditorTest.assertNotNull(testee.BitMapToString(samplebitmap));
	}

	@Test
	public void testStringtoBitmap() {
		NotesEditorTest.assertNull(testee.StringToBitMap(""));
		NotesEditorTest.assertNotNull(testee.StringToBitMap(sampleString));
	}
}
