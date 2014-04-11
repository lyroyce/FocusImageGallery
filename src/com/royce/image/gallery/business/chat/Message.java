package com.royce.image.gallery.business.chat;

import java.util.Date;
/**
 * This class represents a message which can be published in a chat room.
 * @author yinli
 *
 */
public class Message {

	private String content;
	private Participant participant;
	private Date time;

	public Message(String message, Participant participant) {
		this(message, participant, new Date());
	}

	public Message(String message, Participant participant, Date date) {
		this.content = message;
		this.participant = participant;
		this.time = date;
	}

	public String getContent() {
		return content;
	}

	public Participant getFrom() {
		return participant;
	}

	public Date getTime() {
		return time;
	}

}
