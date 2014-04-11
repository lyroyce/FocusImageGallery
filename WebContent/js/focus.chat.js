$(function() {
	initChat();
	
	$(".sidebar .thumbnail").hover(function(){
		$(".preview").show();
	},function(){
		$(".preview").hide();
	});
	
});

var since;
var offset = 0;
var imageRealName = getParameterByName('image');
var queryInterval = 1500;

function initChat(){
	query(1);
	$(".messages ol").append("<li><span>----"+formatDate(new Date().getTime())+"----</span></li>");
	setInterval(query, queryInterval);
	$('.chatbox button').click(onSend);
	$('.chatbox textarea').bind('keypress', function(e){if(e.ctrlKey && (e.keyCode==10||e.keyCode==13)){onSend();}});
}

function onSend(){
	var name = $(".me .name").val();
	var content = $('.chatbox textarea').val();
	if(name==""){
		alert("Please input your name.");
		$(".me .name").focus();
		return;
	}
	if(content==""){
		$(".chatbox textarea").focus();
		return;
	}
	$.post( "chat", { 
		image: imageRealName, 
		name: name, 
		content: content
	});
	$(".chatbox textarea").val("");
}
function query(timestamp){
	$.ajax({url: "chat", data: {
    		image: imageRealName, 
			name: $(".me .name").val(), 
    		since: timestamp?timestamp:since,
    		offset: offset,
    		t: new Date().getTime(), // cache busting token to prevent IE cache
    		json: true
    	}
    }).done(function(response){
    	since = response.since;
    	offset += response.messages.length;
    	if($(".me .name").val()=="")$(".me .name").val(response.name);
    	renderMessages(response.messages);
    	renderParticipants(response.participants);
    });
}
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
function renderMessages(messages){
	$.each(messages, function(index, message){
    	$(".messages ol").append("<li><span>"+message.from+"</span>\ <span>\<"+
    			formatDate(message.time)+"\></span>"+
    			"<span><pre>"+message.content+"</pre></span></li>");
	});
}
function renderParticipants(participants){
	$(".participants ol").empty();
	$.each(participants, function(index, participant){
    	$(".participants ol").append("<li><span>"+participant.name+"</span></li>");
	});
}
function formatDate(timestamp) {
	var date = new Date(timestamp);
    var d = date.getDate();
    var m = date.getMonth() + 1;
    var y = date.getFullYear();
    var h = date.getHours();
    var mi = date.getMinutes();
    var s = date.getSeconds();
    return '' + y + '-' + (m<=9 ? '0' + m : m) + '-' + (d<=9 ? '0' + d : d) + ' ' +
    	(h<=9 ? '0' + h : h) + ':' +(mi<=9 ? '0' + mi : mi) + ':' +(s<=9 ? '0' + s : s);
}