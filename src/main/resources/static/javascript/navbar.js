function toggleDropdown() {
    var dropdown = document.getElementById("profile-dropdown");

    // Toggle 'show' class instead of directly modifying styles
    if (dropdown.classList.contains("show")) {
        dropdown.classList.remove("show");
    } else {
        dropdown.classList.add("show");
    }
}

// Hide dropdown when clicking outside
document.addEventListener("click", function(event) {
    var profilePic = document.querySelector(".profile-pic");
    var dropdown = document.getElementById("profile-dropdown");

    // Close dropdown only if clicked outside both profile pic & dropdown
    if (!profilePic.contains(event.target) && !dropdown.contains(event.target)) {
        dropdown.classList.remove("show");
    }
});
