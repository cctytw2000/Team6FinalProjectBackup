
let address_check_val = false;
let birth_check_val = false;
let idnumber_check_val = false;

let pwShown = 0;
document.addEventListener("DOMContentLoaded", function () {


	document.getElementById("alladdress").addEventListener("input", check_address);
	document.getElementById("alladdress").addEventListener("input", opencreate);

	document.getElementById("idnumber").addEventListener("input", check_idnumber);
	document.getElementById("idnumber").addEventListener("input", opencreate);

	document.getElementById("date").addEventListener("input", check_birth);
	document.getElementById("date").addEventListener("input", opencreate);

	

});
function check_idnumber() {
	if (document.getElementById("idnumber").value != "") {
		idnumber_check_val = true;
		document.getElementById("idnumber_password").innerHTML = "<img style='margin-left: -16px' src='../Images/yes.jpg'>"
	} else {
		document.getElementById("idnumber_password").innerHTML = "<img style='margin-left: -16px' src='../Images/noway.jpg'>"

	}
}

function check_address() {
	if (document.getElementById("alladdress").value != "") {
		address_check_val = true;
		document.getElementById("address_password").innerHTML = "<img style='margin-left: -16px' src='../Images/yes.jpg'>"
	} else {
		document.getElementById("address_password").innerHTML = "<img style='margin-left: -16px' src='../Images/noway.jpg'>"

	}
}
function check_birth() {
	if (document.getElementById("date").value != "") {
		birth_check_val = true;
		document.getElementById("date_password").innerHTML = "<img style='margin-left: -16px' src='../Images/yes.jpg'>"
	} else {
		document.getElementById("date_password").innerHTML = "<img style='margin-left: -16px' src='../Images/noway.jpg'>"

	}

}

function opencreate() {

	if (address_check_val && birth_check_val && idnumber_check_val) {
		document.getElementById("submit").innerHTML = "<button type='submit'>新增</button>";
	}
	

	
	
}


