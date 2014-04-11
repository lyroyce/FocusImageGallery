package com.royce.image.gallery.business;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

import com.royce.image.gallery.business.chat.ChatRoom;
import com.royce.image.gallery.business.chat.InactiveParticipantChecker;
import com.royce.image.gallery.business.chat.Participant;

public class InactiveParticipantCheckerTest extends BaseGalleryTest{

	@Test
	public void testParticipantMapCheck() throws InterruptedException{
		Map<String, Participant> participantMap = generateParticipantMap(null);
		InactiveParticipantChecker checker = new InactiveParticipantChecker(participantMap);
		checker.setInactivePeriod(100);
		assertEquals(2, participantMap.size());
		
		checker.check(participantMap);
		assertEquals(2, participantMap.size());
		
		Thread.sleep(101);
		checker.check(participantMap);
		assertEquals(0, participantMap.size());
	}

	@Test
	public void testChatRoomCheck() throws InterruptedException{
		ChatRoom room = generateChatRoom("image1.jpg");
		Map<String, Participant> participantMap = generateParticipantMap(room);
		InactiveParticipantChecker checker = new InactiveParticipantChecker(participantMap);
		checker.setInactivePeriod(100);
		assertEquals(2, room.getParticipants().size());
		
		checker.check(participantMap);
		assertEquals(2, room.getParticipants().size());
		
		Thread.sleep(101);
		checker.check(participantMap);
		assertEquals(0, room.getParticipants().size());
	}
	
	private Map<String, Participant> generateParticipantMap(ChatRoom room){
		Map<String, Participant> participantMap = new ConcurrentHashMap<String, Participant>();
		Participant participant1 = new Participant("abc");
		Participant participant2 = new Participant("123");
		participant1.join(room);
		participant2.join(room);
		participantMap.put(participant1.getName(), participant1);
		participantMap.put(participant2.getName(), participant2);
		return participantMap;
	}
	
}
