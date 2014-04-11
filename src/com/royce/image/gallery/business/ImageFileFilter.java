package com.royce.image.gallery.business;

import java.io.File;
import java.io.FilenameFilter;
/**
 * This class is used to filter image files by file type.
 * @author yinli
 *
 */
public class ImageFileFilter implements FilenameFilter {
	
	private static String[] accpectedExtensions = {".jpg",".jpeg",".png","gif"};

	@Override
	public boolean accept(File dir, String name) {
        return accept(name.toLowerCase());
	}

	public static boolean accept(String name){
		name = name.toLowerCase();
		for(String ext : accpectedExtensions){
			if(name.endsWith(ext)) return true;
		}
		return false;
	}
}
