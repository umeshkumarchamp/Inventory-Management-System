
document.addEventListener("DOMContentLoaded", function() {
	var dropdowns = document.querySelectorAll(".dropdown");
	dropdowns.forEach(function(dropdown) {
		var dropbtn = dropdown.querySelector(".dropbtn");
		var dropdownContent = dropdown.querySelector(".dropdown-content");
		dropbtn.addEventListener("click", function() {
			dropdownContent.classList.toggle("show");
		});
	});

	window.addEventListener("click", function(event) {
		dropdowns.forEach(function(dropdown) {
			var dropbtn = dropdown.querySelector(".dropbtn");
			var dropdownContent = dropdown.querySelector(".dropdown-content");
			if (!dropdown.contains(event.target)) {
				dropdownContent.classList.remove("show");
			}
		});
	});
});
