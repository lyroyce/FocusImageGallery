package com.royce.image.gallery.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.royce.image.gallery.business.FileSystemGallery;
import com.royce.image.gallery.business.Image;
import com.royce.image.gallery.business.chat.ChatRoom;
import com.royce.image.gallery.business.chat.InactiveParticipantChecker;
import com.royce.image.gallery.business.chat.Participant;
/**
 * This class is the servlet for chatting.
 * @author yinli
 *
 */
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, ChatRoom> roomMap = new ConcurrentHashMap<String, ChatRoom>();
	private Map<String, Participant> participantMap = new ConcurrentHashMap<String, Participant>();
	private InactiveParticipantChecker checker;
	
	public ChatServlet(){
		this.checker = new InactiveParticipantChecker(participantMap);
		checker.setDaemon(true);
		checker.start();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		ChatRoom room = findChatRoom(request.getParameter("image"));
		Participant participant = findParticipant(request.getSession().getId(), request.getParameter("name"));
		participant.join(room);
		
		String content = request.getParameter("content");
		participant.say(content);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		ChatRoom room = findChatRoom(request.getParameter("image"));
		Participant participant = findParticipant(request.getSession().getId(), request.getParameter("name"));
		participant.join(room);
		
		if("true".equals(request.getParameter("json"))){
			if(null==request.getParameter("since")){
				returnStatusAsJson(participant, new Date(), 0, response);
			}else{
				Date since = new Date(Long.parseLong(request.getParameter("since")));
				int offset = Integer.parseInt(request.getParameter("offset"));
				returnStatusAsJson(participant, since, offset, response);
			}
		}else{
			returnChatPage(room, participant, request, response);
		}
	}
	
	private void returnChatPage(ChatRoom room, Participant participant, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("galleryPath", Resources.GALLERY_ROOT.getRelativePath());
		request.setAttribute("room", room);
		request.setAttribute("participant", participant);
		Resources.CHAT_PAGE.forward(this.getServletContext(), request, response);
	}
	
	private void returnStatusAsJson(Participant participant, Date since, int offset, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.getWriter().write(participant.getStatusAsJson(since, offset));
		response.getWriter().flush();
	}

	private ChatRoom findChatRoom(String imageRealName){
		if(!roomMap.containsKey(imageRealName)){
			String imageFile = Resources.GALLERY_ROOT.getAbsolutePath(this.getServletContext()) + File.separator + imageRealName;
			Image image = FileSystemGallery.findImage(imageFile);
			ChatRoom room = new ChatRoom(image);
			roomMap.put(imageRealName, room);
		}
		return roomMap.get(imageRealName);
	}
	
	private Participant findParticipant(String sessionId, String name){
		if(!participantMap.containsKey(sessionId)){
			Participant participant = new Participant(sessionId);
			participantMap.put(sessionId, participant);
		}
		Participant participant = participantMap.get(sessionId);
		if(name!=null && !name.equals(""))participant.setName(name);
		return participant;
	}
}
