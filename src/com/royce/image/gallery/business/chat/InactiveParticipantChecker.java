package com.royce.image.gallery.business.chat;

import java.util.Date;
import java.util.Map;

/**
 * This class is used to periodically remove inactive participants from a chat room.
 * @author yinli
 *
 */
public class InactiveParticipantChecker extends Thread{
	
	private int checkInterval = 1* 1000;
	private int inactivePeriod = 10 * 1000;
	
	private Map<String, Participant> participantMap;
	
	public InactiveParticipantChecker(Map<String, Participant> participantMap){
		this.participantMap = participantMap;
	}

	@Override
	public void run(){
		while(true){
			try {
				Thread.sleep(checkInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			check(participantMap);
		}
	}

	public void check(Map<String, Participant> participantMap) {
		Date threshold = new Date(System.currentTimeMillis() - inactivePeriod);
		for(Map.Entry<String, Participant> entry : participantMap.entrySet()){
			Participant participant = (Participant)entry.getValue();
			if(participant.getLastActive().compareTo(threshold)<0){
				participant.leave();
				participantMap.remove(entry.getKey());
			}
		}
	}

	public void setCheckInterval(int checkInterval) {
		this.checkInterval = checkInterval;
	}

	public int getCheckInterval() {
		return checkInterval;
	}

	public void setInactivePeriod(int inactivePeriod) {
		this.inactivePeriod = inactivePeriod;
	}

	public int getInactivePeriod() {
		return inactivePeriod;
	}
}
