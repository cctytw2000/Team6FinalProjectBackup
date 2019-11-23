let username_check_val = false;
let password_check_val = false;
let account_check_val = false;
let account_check_val_repeat = false;


let pwShown = 0;
document.addEventListener("DOMContentLoaded", function () {
	document.getElementById("account").addEventListener("input", check_account);
	document.getElementById("account").addEventListener("input", opencreate);

	document.getElementById("password").addEventListener("input", check_password);
	document.getElementById("password").addEventListener("input", opencreate);
	
	document.getElementById("username").addEventListener("input", check_username); 
	document.getElementById("username").addEventListener("input", opencreate);
	
	


	

});

function check_username() {
	let acc = document.getElementById("username").value;
	var acc_length = acc.length;
	var flag4 = false;
	if (acc == "") {
		document.getElementById("msg").innerHTML = "<img style='margin-left: -16px' src='Images/noway.jpg'>"
	} else if (acc_length >= 2) {
		for (let i = 0; i < acc_length; i++) {

			if (acc.charCodeAt(i) >= 0x4e00 && acc.charCodeAt(i) <= 0x9fff) {

				flag4 = true
			} else {
				flag4 = false;
				break;
			}
		}
		if (flag4) {
			document.getElementById("msg").innerHTML = "<img style='margin-left: -16px' src='Images/yes.jpg'>"
			username_check_val = true
		} else {
			document.getElementById("msg").innerHTML = "<img style='margin-left: -16px' src='Images/noway.jpg'>"
		}
	} else {
		document.getElementById("msg").innerHTML = "<img style='margin-left: -16px' src='Images/noway.jpg'>"

	}

}
function opencreate() {
	console.log("opencreate")
	console.log("account_check_val_repeat==="+account_check_val_repeat)
	if (username_check_val && password_check_val && account_check_val && account_check_val_repeat) {
		document.getElementById("submit").innerHTML = "<button type='submit'>註冊</button>";
	}
	

	
	
}


function show() {
	var p = document.getElementById('password');
	p.setAttribute('type', 'text');
}

function hide() {
	var p = document.getElementById('password');
	p.setAttribute('type', 'password');
}

function showHide() {
	if (document.getElementById("password").type == "password") {
		console.log("show");
		console.log(pwShown);
		pwShown = 1;
		show();
	} else {
		console.log("hide");
		console.log(pwShown);
		pwShow = 0;
		hide();
	}

}



function check_account() {

	let PWDVal = document.getElementById("account").value;
	var PWDVallen = PWDVal.length;
	let flag1 = false, flag2 = false, flag3 = false
	let special = ['!', '@', '#', '$', '%', '^', '&', '*']

	if (PWDVal == "") {
		document.getElementById("account_msg").innerHTML = "<img src='Images/noway.jpg'>不可空白"
	} else if (PWDVallen >= 6) {
		for (let i = 0; i < PWDVallen; i++) {
			let chrPwd = PWDVal.charAt(i).toUpperCase();
			if (chrPwd >= "A" && chrPwd <= "Z") {
				flag1 = true
				console.log(chrPwd)
			} else if (chrPwd >= "0" && chrPwd <= "9") {
				flag2 = true
				console.log(chrPwd)
			} else if (special.lastIndexOf(chrPwd)) {
				console.log(chrPwd)
				flag3 = true
			}
			if (flag1 && flag2 && flag3) {
				document.getElementById("account_msg").innerHTML = "<img src='Images/yes.jpg'>格式符合";
				account_check_val = true

			} else {
				document.getElementById("account_msg").innerHTML = "<img src='Images/noway.jpg'>格式不符合"

			}
			;
		}
	} else {
		document.getElementById("account_msg").innerHTML = "<img src='Images/noway.jpg'>至少六個字"
	}
}


function check_password() {
	let PWDVal = document.getElementById("password").value;
	var PWDVallen = PWDVal.length;
	let flag1 = false, flag2 = false
	let special = ['!', '@', '#', '$', '%', '^', '&', '*']

	if (PWDVal == "") {
		document.getElementById("msg_password").innerHTML = "<img src='Images/noway.jpg'>不可空白"
	} else if (PWDVallen >= 6) {
		for (let i = 0; i < PWDVallen; i++) {
			let chrPwd = PWDVal.charAt(i).toUpperCase();
			if (chrPwd >= "A" && chrPwd <= "Z") {
				flag1 = true
				console.log(chrPwd)
			} else if (chrPwd >= "0" && chrPwd <= "9") {
				flag2 = true
				console.log(chrPwd)
			}
			if (flag1 && flag2) {
				document.getElementById("msg_password").innerHTML = "<img src='Images/yes.jpg'>格式符合";
				password_check_val = true

			} else {
				document.getElementById("msg_password").innerHTML = "<img src='Images/noway.jpg'>格式不符合"

			}
			;
		}
	} else {
		document.getElementById("msg_password").innerHTML = "<img src='Images/noway.jpg'>至少六個字"
	}
	// document.getElementById("msg_password").innerHTML = "<img
	// src='image/yes.jpg'>"
}
$(document).ready(function () {
	$("#check").click(function () {
		if ($("#account").val() != "") {
			$.ajax({
				url: "member/checkGeneralRepeat",
				data: {
					account: $("#account").val(),
					type:"General"
				},
				type: "POST",
				success: function (data) {
					if (data == true) {
						account_check_val_repeat = true;
						opencreate();
						alert("您可以使用此帳號");
					} else {
						alert("此帳號已重複註冊")
					}

					console.log(data)
				}
			})
		} else {
			alert("你是不是想找麻煩")
		}
	})
})




function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#preview_progressbarTW_img").attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}