package com.royce.image.gallery.business;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.royce.image.gallery.business.chat.ChatRoom;
import com.royce.image.gallery.business.chat.Message;
import com.royce.image.gallery.business.chat.Participant;


public class ParticipantTest extends BaseGalleryTest{
	
	@Test
	public void testNormalChat(){
		ChatRoom room1 = generateChatRoom("image1.jpg");

		Participant participant1 = new Participant("abc", false);
		Participant participant2 = new Participant("123", false);
		participant1.join(room1);
		participant2.join(room1);
		assertEquals(0, participant2.getMessages().size());
		
		participant1.say("hello");
		assertEquals(1, participant2.getMessages().size());
		assertParticipantRead(participant1, "hello", participant1, 0);
		assertParticipantRead(participant2, "hello", participant1, 0);
		
		participant2.say("good bye");
		assertEquals(2, participant1.getMessages().size());
		assertParticipantRead(participant1, "good bye", participant2, 1);
		assertParticipantRead(participant2, "good bye", participant2, 1);
	}

	@Test
	public void testSwitchRoom(){
		ChatRoom room1 = generateChatRoom("image1.jpg");
		ChatRoom room2 = generateChatRoom("image2.jpg");

		Participant participant1 = new Participant("abc", false);
		Participant participant2 = new Participant("123", false);
		participant1.join(room1);
		participant2.join(room1);
		participant1.say("hello");
		assertEquals(1, participant2.getMessages().size());

		participant2.join(room2);
		assertEquals(0, participant2.getMessages().size());
	}
	
	@Test
	public void testJoinRoomTwice(){
		ChatRoom room1 = generateChatRoom("image1.jpg");
		ChatRoom room2 = generateChatRoom("image2.jpg");

		Participant participant1 = new Participant("abc", false);
		participant1.join(room1);
		assertEquals(1, room1.getParticipants().size());
		participant1.join(room1);
		assertEquals(1, room1.getParticipants().size());
		participant1.join(room2);
		assertEquals(0, room1.getParticipants().size());
	}

	@Test
	public void testGetRecentMessage(){
		ChatRoom room1 = generateChatRoom("image1.jpg");
		Participant participant1 = new Participant("abc", false);
		addMessage(room1, "hello", participant1, 31);
		addMessage(room1, "hello", participant1, 1);
		addMessage(room1, "hello", participant1, 0);
		
		Participant participant2 = new Participant("123", false);
		participant2.join(room1);
		// The reason participant2 would get one message is because the message is posted
		// at the same time participant2 join the room
		assertEquals(1, participant2.getMessages().size());
		assertEquals(2, getMessagesCount(participant2, 1, 0));
		assertEquals(2, getMessagesCount(participant2, 2, 0));
		assertEquals(2, getMessagesCount(participant2, 30, 0));
		assertEquals(3, getMessagesCount(participant2, 32, 0));
		
		assertEquals(0, getMessagesCount(participant2, 0, 1));
	}
	
	private int getMessagesCount(Participant participant, int minutesBefore, int offset){
		return participant.getMessages(new Date(System.currentTimeMillis()-minutesBefore*60000), offset).size();
	}
	
	private void addMessage(ChatRoom room, String message, Participant participant, int minutesBefore){
		room.addMessage(new Message(message, participant, new Date(System.currentTimeMillis()-minutesBefore*60000)));
	}
	
	private void assertParticipantRead(Participant reader, String message, Participant from, int position){
		assertEquals(message, reader.getMessages().get(position).getContent());
		assertEquals(from, reader.getMessages().get(position).getFrom());
	}
	
}
