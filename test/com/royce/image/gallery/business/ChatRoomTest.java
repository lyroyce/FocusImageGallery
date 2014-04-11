package com.royce.image.gallery.business;

import static org.junit.Assert.*;

import org.junit.Test;

import com.royce.image.gallery.business.chat.ChatRoom;
import com.royce.image.gallery.business.chat.Participant;


public class ChatRoomTest extends BaseGalleryTest{
	
	@Test
	public void testAddRemoveParticipant(){
		ChatRoom room = generateChatRoom("image1.jpg");
		Participant participant1 = new Participant("abc", false);
		Participant participant2 = new Participant("123", false);
		assertEquals(0, room.getParticipants().size());
		
		participant1.join(room);
		participant2.join(room);
		assertEquals(2, room.getParticipants().size());
		assertTrue(room.getParticipants().contains(participant1));
		assertTrue(room.getParticipants().contains(participant2));
		
		participant1.leave(room);
		assertFalse(room.getParticipants().contains(participant1));
		assertTrue(room.getParticipants().contains(participant2));
		
		ChatRoom anotherRoom = generateChatRoom("image2.jpg");
		participant2.join(anotherRoom);
		assertEquals(0, room.getParticipants().size());
		assertTrue(anotherRoom.getParticipants().contains(participant2));
	}

	@Test
	public void testGetMessages(){
		ChatRoom room = generateChatRoom("image1.jpg");
		Participant participant1 = new Participant("abc", false);
		Participant participant2 = new Participant("123", false);
		participant1.join(room);
		participant2.join(room);
		participant1.say("something 1");
		participant1.say("something 2");
		participant2.say("something 3");
		assertEquals(3, room.getMessages().size());
	}
	
}
