function validateForm() {
	var password = document.forms["reg-form"]["pass"].value;
	var repassword = document.forms["reg-form"]["repass"].value;
	if (password != repassword) {
		alert("Confirm password does not match with the provided password");
		return false;
	}
}