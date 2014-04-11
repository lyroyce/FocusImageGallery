package com.royce.image.gallery.business.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.royce.image.gallery.business.Image;
/**
 * This class represents a chat room of a image. 
 * Participants can join and discuss about the image in the room.
 * @author yinli
 *
 */
public class ChatRoom {

	private Image image;
	private List<Participant> participants;
	private List<Message> messages;

	public ChatRoom(Image image) {
		this.image = image;
		this.image.setChatRoom(this);
		this.participants = Collections.synchronizedList(new ArrayList<Participant>());
		this.messages = Collections.synchronizedList(new ArrayList<Message>());
	}

	public Image getImage() {
		return image;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public void addParticipant(Participant participant) {
		this.participants.add(participant);
	}

	public void removeParticipant(Participant participant) {
		this.participants.remove(participant);
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void addMessage(Message message) {
		this.messages.add(message);
	}

}
