$(function() {
	$('.thumbnail-box').hover(function (){
         $(this).find('.caption').show();
         $(this).find('.thumbnail-status').css('opacity', 0.8);
    },function (){
        $(this).find('.caption').hide();
         $(this).find('.thumbnail-status').css('opacity', 0.3);
    });
    
	$('#fileupload').fileupload({
		add: function (e, data) {
	        $('.progress').css('display','block');
	        $('.btn-fileupload').prop( "disabled", true );
	        $('.btn-fileupload span').text( "Uploading..." );
	        data.submit();
        },
		progressall: function (e, data) {
	        var progress = parseInt(data.loaded / data.total * 100, 10);
	        $('.progress .progress-bar').css(
	            'width',
	            progress + '%'
	        );
	        if(progress==100) location.reload();
	    }
	});
});