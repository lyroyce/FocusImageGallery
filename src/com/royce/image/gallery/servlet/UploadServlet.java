package com.royce.image.gallery.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * This class is the servlet for file uploading.
 * @author yinli
 *
 */
public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			List<FileItem> items = new ServletFileUpload(
					new DiskFileItemFactory()).parseRequest(request);
			System.out.println("received "+items.size());
			for (FileItem item : items) {
				this.parseFileItem(item);
			}
		} catch (FileUploadException e) {
			throw new ServletException("Cant parse multipart request.", e);
		}
	}
	
	private void parseFileItem(FileItem item) throws IOException{
		if (!item.isFormField()) {
			String filename = getLocalName(item.getName());
			InputStream fileStream = item.getInputStream();
			this.saveFileFromStream(fileStream, filename);
		}
	}
	
	private String getLocalName(String filename){
		String randomName = String.valueOf(System.currentTimeMillis())+String.valueOf(new Random().nextInt());
		String galleryPath = Resources.GALLERY_ROOT.getAbsolutePath(this.getServletContext());
		return galleryPath +File.separator+ randomName + "_" + filename;
	}
	
	private void saveFileFromStream(InputStream fileStream, String filename) throws IOException{
		OutputStream os = new FileOutputStream(filename);  
		byte[] buffer = new byte[4096];  
		int bytesRead;  
		while ((bytesRead = fileStream.read(buffer)) != -1) {  
		  os.write(buffer, 0, bytesRead);  
		}  
		fileStream.close();  
		os.close();
	}
	
}
