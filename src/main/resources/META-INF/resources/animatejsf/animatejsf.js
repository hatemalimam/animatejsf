/**
 * 
 */

function animatejsf(id, type) {
	$(document).ready(function() {
		var element = document.getElementById(id);
		element.classList.add("animated");
		element.classList.add(type);
    });
}