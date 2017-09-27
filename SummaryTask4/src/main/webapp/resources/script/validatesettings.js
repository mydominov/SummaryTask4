function validateForm() {
	var password = document.forms["settings-form"]["new-password"].value;
	var repassword = document.forms["settings-form"]["repassword"].value;
	if (password != repassword) {
		alert("New password does not match with the repeated password");
		return false;
	}
}