
$("#header>.menu-button").click(function() {
	$("#sidemenu").toggleClass("open");
	$(".copyright").toggleClass("show");
});
$("#sidemenu, #top-bar, #content-wrapper").click(function(e) {
	$("#sidemenu").removeClass("open");
	$(".copyright").removeClass("show");
});
