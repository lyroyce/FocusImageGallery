package com.royce.image.gallery.business;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.royce.image.gallery.business.ImageFileFilter;


public class ImageFilterTest extends BaseGalleryTest{

	@Test
	public void testImageFilter(){
		ImageFileFilter image = new ImageFileFilter();
		assertTrue(image.accept(new File(""), "1.jpg"));
		assertTrue(image.accept(new File(""), "TEST 10.JPG"));
		assertTrue(image.accept(new File(""), "a.b.c.JPEG"));
		assertTrue(image.accept(new File(""), "abcdefghij1234567890abcdefghij1234567890.png"));
		assertTrue(image.accept(new File(""), "_.gif"));
	}

}
