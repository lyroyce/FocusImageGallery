package com.royce.image.gallery.business;

import static org.junit.Assert.assertEquals;

import java.io.File;

import com.royce.image.gallery.business.Image;
import com.royce.image.gallery.business.chat.ChatRoom;

public class BaseGalleryTest {

	protected File currentDir = new File(this.getClass().getResource("").getFile());
	
	protected ChatRoom generateChatRoom(String imageName){
		Image image = new Image(new File(currentDir+File.separator+imageName));
		ChatRoom room = new ChatRoom(image);
		assertEquals(image, room.getImage());
		assertEquals(0, room.getParticipants().size());
		return room;
	}

}
