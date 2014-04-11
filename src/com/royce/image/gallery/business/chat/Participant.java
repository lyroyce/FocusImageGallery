package com.royce.image.gallery.business.chat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * This class represents a user who is visiting the web site.
 * @author yinli
 *
 */
public class Participant {

	private String name;
	private ChatRoom currentRoom;
	private long lastActive;
	private boolean systemMessageEnabled;

	public Participant(String name) {
		this(name, false);
	}

	public Participant(String name, boolean systemMessageEnabled) {
		this.name = name;
		this.systemMessageEnabled = systemMessageEnabled;
		this.lastActive = System.currentTimeMillis();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void join(ChatRoom newRoom) {
		this.lastActive = System.currentTimeMillis();
		if(newRoom!=null && !newRoom.equals(currentRoom)){
			if(this.currentRoom!=null) this.leave(this.currentRoom);
			newRoom.addParticipant(this);
			this.currentRoom = newRoom;
			if(isSystemMessageEnabled())this.say(this.getName() + " has joined the room.");
		}
	}

	public void leave(ChatRoom newRoom) {
		this.lastActive = System.currentTimeMillis();
		if(newRoom!=null && newRoom.equals(currentRoom)){
			if(isSystemMessageEnabled())this.say(this.getName() + " has left the room.");
			newRoom.removeParticipant(this);
			this.currentRoom = null;
		}
	}

	public void leave() {
		this.leave(this.currentRoom);
	}
	
	public void say(String message) {
		this.lastActive = System.currentTimeMillis();
		this.currentRoom.addMessage(new Message(message, this));
//		System.out.println(this.getName() + ": " +message);
	}

	public List<Message> getMessages() {
		return this.getMessages(new Date());
	}

	public List<Message> getMessages(Date date) {
		return this.getMessages(date, 0);
	}

	public List<Message> getMessages(Date date, int offset) {
		List<Message> messages = this.currentRoom.getMessages();
		List<Message> recentMessages = new ArrayList<Message>();
		for(int i=0;i<messages.size();i++){
			if(messages.get(i).getTime().compareTo(date)>=0){
				if(offset==0)recentMessages.add(messages.get(i));
				else offset--;
			}
		}
		return recentMessages;
	}

	public Date getLastActive() {
		return new Date(this.lastActive);
	}

	public ChatRoom getChatRoom() {
		return currentRoom;
	}

	public void setSystemMessageEnabled(boolean systemMessageEnabled) {
		this.systemMessageEnabled = systemMessageEnabled;
	}

	public boolean isSystemMessageEnabled() {
		return systemMessageEnabled;
	}

	public String getStatusAsJson(Date since, int offset){
		StringBuffer buffer = new StringBuffer();
		buffer.append("{\"timestamp\":").append(new Date().getTime()).append(",");
		buffer.append("\"since\":").append(since.getTime()).append(",");
		buffer.append("\"offset\":\"").append(offset).append("\",");
		buffer.append("\"name\":\"").append(this.getName()).append("\",");
		buffer.append("\"messages\":[");
		for(Message message : this.getMessages(since, offset)){
			buffer.append("{");
			buffer.append("\"content\":\"").append(StringEscapeUtils.escapeEcmaScript(message.getContent())).append("\",");
			buffer.append("\"from\":\"").append(message.getFrom().getName()).append("\",");
			buffer.append("\"time\":").append(message.getTime().getTime()).append("");
			buffer.append("},");
		}
		if(','==buffer.charAt(buffer.length()-1)) buffer.deleteCharAt(buffer.length()-1);
		buffer.append("],");
		buffer.append("\"participants\":[");
		for(Participant p : this.getChatRoom().getParticipants()){
			buffer.append("{");
			buffer.append("\"name\":\"").append(p.getName()).append("\"");
			buffer.append("},");
		}
		if(','==buffer.charAt(buffer.length()-1)) buffer.deleteCharAt(buffer.length()-1);
		buffer.append("]}");
		return buffer.toString();
	}
}
