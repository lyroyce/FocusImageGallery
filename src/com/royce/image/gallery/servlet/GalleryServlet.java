package com.royce.image.gallery.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.royce.image.gallery.business.FileSystemGallery;

/**
 * This class is the servlet for gallery display.
 * @author yinli
 *
 */
public class GalleryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FileSystemGallery gallery;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		request.setAttribute("images", this.getGallery().getImages());
		request.setAttribute("galleryPath", Resources.GALLERY_ROOT.getRelativePath());
		Resources.GALLERY_PAGE.forward(this.getServletContext(), request, response);
	}
	
	private FileSystemGallery getGallery(){
		// lazy load after servlet initialization
		if(gallery==null){
			String galleryLocalPath = Resources.GALLERY_ROOT.getAbsolutePath(this.getServletContext());
			this.gallery = new FileSystemGallery(new File(galleryLocalPath));
		}
		return this.gallery;
	}
}
