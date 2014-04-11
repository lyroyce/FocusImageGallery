package com.royce.image.gallery.business;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * This class represents a gallery which manages images in file system.
 * @author yinli
 *
 */
public class FileSystemGallery implements IGallery {

	private File baseDir;
	private static Map<String, Image> imageMap = new ConcurrentHashMap<String, Image>();

	public FileSystemGallery(File baseDir) {
		this.baseDir = baseDir;
	}

	@Override
	public List<Image> getImages() {
		List<Image> images = new ArrayList<Image>();
		if(this.baseDir.exists()){
			String[] imageFiles = this.baseDir.list(new ImageFileFilter());
			for(String filename : imageFiles){
				images.add(findImage(baseDir.getAbsolutePath() + File.separator + filename));
			}
		}
		return images;
	}
	
	public static Image findImage(String filename){
		if(!imageMap.containsKey(filename)){
			imageMap.put(filename, new Image(new File(filename)));
		}
		return imageMap.get(filename);
	}

}
