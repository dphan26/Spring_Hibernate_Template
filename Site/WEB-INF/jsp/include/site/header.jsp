<ul id="menu">
    <li><a href="#">Home</a></li>
    <li>
        <a href="#">Categories</a>
        <ul>
            <li>
            	<a href="#">CSS</a>
            	<ul>
						<li><a href="">Item 11</a></li>
						<li><a href="">Item 12</a></li>
						<li><a href="">Item 13</a></li>
						<li><a href="">Item 14</a></li>
				</ul>
            </li>
            <li>
            	<a href="#">Graphic design</a>
            	<ul>
						<li><a href="">Item 11</a></li>
						<li><a href="">Item 12</a></li>
						<li><a href="">Item 13</a></li>
						<li><a href="">Item 14</a></li>
				</ul>
            </li>
            <li><a href="#">Development tools</a></li>
            <li><a href="#">Web design</a></li>
        </ul>
    </li>
    <li><a href="#">Work</a></li>
    <li><a href="#">About</a></li>
    <li><a href="#">Contact</a></li>
</ul>
<hr>
<script type="text/javascript">
    $(function() {
		if ($.browser.msie && $.browser.version.substr(0,1)<7)
		{
		$('li').has('ul').mouseover(function(){
			$(this).children('ul').css('visibility','visible');
			}).mouseout(function(){
			$(this).children('ul').css('visibility','hidden');
			})
		}

		/* Mobile */
		$('#menu-wrap').prepend('<div id="menu-trigger">Menu</div>');		
		$("#menu-trigger").on("click", function(){
			$("#menu").slideToggle();
		});

		// iPad
		var isiPad = navigator.userAgent.match(/iPad/i) != null;
		if (isiPad) $('#menu ul').addClass('no-transition');      
    });          
</script>
