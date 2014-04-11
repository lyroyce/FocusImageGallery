package com.royce.image.gallery.business;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.royce.image.gallery.business.Image;


public class ImageTest extends BaseGalleryTest{

	@Test
	public void testImageName(){
		Image image = new Image(new File(currentDir+File.separator+"123456_image3.jpg"));
		assertEquals("123456_image3.jpg", image.getRealName());
		assertEquals("image3.jpg", image.getName());
	}
	
	@Test
	public void testImageResolution(){
		Image image = new Image(new File(currentDir+File.separator+"image1.jpg"));
		assertEquals(89, image.getResolution().getWidth(), 0);
		assertEquals(66, image.getResolution().getHeight(), 0);
	}

	@Test
	public void testImageSize(){
		Image image = new Image(new File(currentDir+File.separator+"image1.jpg"));
		assertEquals(2767, image.getSize(), 0);
		assertEquals("2.7KB", image.getReadableSize());
	}
	
	@Test
	public void testImageDate(){
		Image image = new Image(new File(currentDir+File.separator+"image1.jpg"));
		assertEquals("Thu Nov 07 16:44:34 CST 2013", image.getLastModifiedDate().toString());
	}
	
	@Test
	public void testImageEquals(){
		Image image1 = new Image(new File(currentDir+File.separator+"image1.jpg"));
		Image image1copy = new Image(new File(currentDir+File.separator+"image1.jpg"));
		assertEquals(image1, image1copy);
	}
}
