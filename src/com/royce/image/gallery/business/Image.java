package com.royce.image.gallery.business;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import com.royce.image.gallery.business.chat.ChatRoom;
/**
 * This class represents a image.
 * @author yinli
 *
 */
public class Image {

	private File file;
	private ChatRoom chatRoom;

	public Image(File file) {
		this.file = file;
	}

	@Override
	public boolean equals(Object obj){
		if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Image))
            return false;
		return ((Image)obj).getRealName().equals(this.getRealName());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
        int result = 1;
        result = prime * result
                + ((file == null) ? 0 : file.hashCode());
        return result;
    }

	public String getName() {
		String realName = this.getRealName();
		String[] nameParts = realName.split("_",2);
		return nameParts.length>1?nameParts[1]:realName;
	}

	public String getRealName() {
		return file.getName();
	}
	
	public Dimension getResolution() {
		try {
			BufferedImage image = ImageIO.read(file);
			return new Dimension(image.getWidth(), image.getHeight());
		} catch (IOException e) {
			return new Dimension();
		}
	}
	
	public long getSize(){
		return file.length();
	}
	
	public String getReadableSize(){
		double size = this.getSize();
		String[] units = {"B","KB","MB"};
		int unitIndex = 0;
		for(; size>1024 && unitIndex<units.length;unitIndex++){
			size=size/1024;
		}
		double roundedSize = Math.round(size*10)/10.0;
		return String.valueOf(roundedSize) + units[unitIndex];
	}

	public Date getLastModifiedDate() {
		return new Date(file.lastModified());
	}

	public void setChatRoom(ChatRoom chatRoom) {
		this.chatRoom = chatRoom;
	}

	public ChatRoom getChatRoom() {
		return chatRoom;
	}

}
