var Post = {
	scrollPost : function () {
		var startNumber = 0;
		var loading = false;
		//Fill 5 first Post to html
		var firstListPost = Post.getPostsInGroup(startNumber, 3);
		console.log(firstListPost);
		$.each(firstListPost, function(index, value){
			$('#results').append("<h1>"+value.postTitle+"</h1>"+"<p>"+value.postContent+"</p>"+"<p>"+value.categoryName+"</p>");
		});
		startNumber += 3;
		//scroll
		$(window).scroll(function(){
			//fill data when scroll
			if($(window).scrollTop() + $(window).height() == $(document).height()) {
				loading = true;
				$('.animation_image').show();
				var listPost = Post.getPostsInGroup(startNumber,1);
				$.each(listPost, function(index, value){
					$('#results').append("<h1>"+value.postTitle+"</h1>"+"<p>"+value.postContent+"</p>"+"<p>"+value.categoryName+"</p>");
				});
				
				$('.animation_image').hide();
				startNumber += 1; //loaded group increment
				loading = false; 
			}
		});
	},
	getPostsInGroup : function(position, numberOfPostInGroup) {
		return DwrPostServiceImpl.getPostsInGroup(position, numberOfPostInGroup, {
			async: false,
			callback :function(result){
			}
		});
	},
	getTotalGroup : function() {
		return DwrPostServiceImpl.getTotalGroup({ 
			async: false,
			callback :function(result){
			}
		});
	},
};

$(document).ready(function() {
	Post.scrollPost();
});
