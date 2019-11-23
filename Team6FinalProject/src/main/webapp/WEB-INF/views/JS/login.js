document.addEventListener("DOMContentLoaded", function () {

    document.getElementById("loginaccount").addEventListener("input", check_account);
    document.getElementById("loginpassword").addEventListener("input", check_password);
});
let check_account_ = false;
let check_password_ = false;
function check_account() {
    if (document.getElementById("loginaccount").value != "") {
        check_account_ = true;
        document.getElementById("account_msg").innerHTML = "<img src='Images/yes.jpg'>";
    }else {
    	document.getElementById("account_msg").innerHTML = "<img src='Images/noway.jpg'>不可空白";
    }
    if (check_account_ && check_password_) {

        document.getElementById("submit").innerHTML = '<input name="login" type="submit" value="登入"> <input name="fergetpwd" type="submit" value="忘記密碼"></input>';

    }
}

function check_password() {
    if (document.getElementById("loginpassword").value != "") {
        check_password_ = true;
        document.getElementById("passowrd_msg").innerHTML = "<img src='Images/yes.jpg'>";
    }else{

        document.getElementById("passowrd_msg").innerHTML = "<img src='Images/noway.jpg'>不可空白";
    }
    if (check_account_ && check_password_) {

        document.getElementById("submit").innerHTML = '<input name="login" type="submit" value="登入"> <input name="fergetpwd" type="submit" value="忘記密碼"></input>';

    }
}
