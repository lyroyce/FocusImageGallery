package com.royce.image.gallery.business;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.royce.image.gallery.business.FileSystemGallery;
import com.royce.image.gallery.business.IGallery;
import com.royce.image.gallery.business.Image;


public class GalleryTest extends BaseGalleryTest{

	@Test
	public void testListImages(){
		IGallery gallery = new FileSystemGallery(currentDir);
		List<Image> images = gallery.getImages();
		List<Image> expectImages = this.generateTestImageList();
		assertTrue(expectImages.equals(images));
	}
	
	private List<Image> generateTestImageList(){
		List<Image> images = new ArrayList<Image>();
		images.add(new Image(new File(currentDir+File.separator+"123456_image3.jpg")));
		images.add(new Image(new File(currentDir+File.separator+"image1.jpg")));
		images.add(new Image(new File(currentDir+File.separator+"image2.jpg")));
		return images;
	}
}
