let opens1 = false;

document.addEventListener("DOMContentLoaded", function () {
	document.getElementById("re_password").addEventListener("input",
		check_re_PWD);// 事件繫結，事件氣泡(bubbling)
	document.getElementById("re_password").addEventListener("input", open);

});
function check_re_PWD() {
	let newpassword = document.getElementById("password").value;
	let re_password = document.getElementById("re_password").value;
	if (newpassword == re_password) {
		opens1 = true;
		document.getElementById("msg_password").innerHTML = "<img src='../Images/yes.jpg'>與密碼相依";

	} else {
		document.getElementById("msg_password").innerHTML = "<img src='../Images/noway.jpg'>與密碼不相依";

	}
}
function open() {
	if (opens1) {
		document.getElementById("submit").innerHTML = "<input type='submit' value='修改'>";
	}

}