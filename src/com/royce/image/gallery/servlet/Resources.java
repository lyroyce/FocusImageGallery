package com.royce.image.gallery.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class makes page forwarding easily.
 * @author yinli
 *
 */
public enum Resources {
	GALLERY_ROOT("res/gallery"),
	GALLERY_PAGE("/WEB-INF/page/gallery.jsp"),
	CHAT_PAGE("/WEB-INF/page/chat.jsp");

    private  String relativePath;
    
    private Resources(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getRelativePath() {
        return relativePath;
    }
    
    public String getAbsolutePath(ServletContext context) {
        return context.getRealPath(relativePath);
    }

    public void forward(ServletContext context, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        context.getRequestDispatcher(relativePath).forward(request, response);
    }

}
