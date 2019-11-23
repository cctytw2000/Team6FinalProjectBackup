
//應用程式編號，進入 https://developers.facebook.com/apps/ 即可看到
let FB_appID = "689357491476626";

//FB Login 官方文件：https://developers.facebook.com/docs/facebook-login/web

// Load the Facebook Javascript SDK asynchronously
(function (d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id))
		return;
	js = d.createElement(s);
	js.id = id;
	js.src = "https://connect.facebook.net/en_US/sdk.js";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

window.fbAsyncInit = function () {
	FB.init({
		appId: FB_appID,//FB appID
		cookie: true, // enable cookies to allow the server to access the session

		xfbml: true, // parse social plugins on this page
		version: 'v3.0' // use graph api version
	});

};

//使用自己客製化的按鈕來登入
function FBLogin() {

	FB.login(function (response) {
		//debug用
		console.log(response);
		if (response.status === 'connected') {
			FB.api('/me', { locale: 'zh_TW', fields: 'name, email,id' },
				function (response) {
					console.log('Good to see you, ' + response.name + '.');
					console.log('id, ' + response.id + '.');
					console.log(response.email);
					check_Login(response.email, response.name,"Facebook")
				});
		} else {
			// user FB取消授權
			alert("Facebook帳號無法登入");
		}
	}, {
		scope: 'public_profile,email'
	});

}








//進入 https://console.developers.google.com/，找「憑證」頁籤(記得先選對專案)，即可找到用戶端ID
let Google_appId = "894099305493-6e891p90q17r6rf2114i0f8b4gr8f51l.apps.googleusercontent.com";

//參考文章：http://usefulangle.com/post/55/google-login-javascript-api 

// Called when Google Javascript API Javascript is loaded
function HandleGoogleApiLibrary() {
	// Load "client" & "auth2" libraries
	gapi
		.load(
			'client:auth2',
			{
				callback: function () {
					// Initialize client & auth libraries
					gapi.client.init(
						{
							clientId: Google_appId,
							scope: 'https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/plus.me'
						}).then(function (success) {

							console.log("Google Libraries are initialized successfully");
						},
							function (error) {
								// Error occurred
								console.log(error);// to find the reason 
							});
				},
				onerror: function () {
					// Failed to load libraries
					console
						.log("Failed to load libraries");
				}
			});
}

function GoogleLogin() {
	// API call for Google login  
	gapi.auth2.getAuthInstance().signIn().then(
		function (success) {
			// API call to get user information
			gapi.client.request(
				{
					path: 'https://www.googleapis.com/plus/v1/people/me'
				}).then(
					// On success
					function (success) {
						console.log(success);
						var user_info = JSON.parse(success.body);
						console.log(user_info);
						console.log(user_info.displayName);
						console.log(user_info.id);
						console.log(user_info.gender);
						console.log(user_info.emails[0].value);

						check_Login(user_info.emails[0].value, user_info.displayName,"Google")


					},
					// On error
					function (error) {
						$("#login-button").removeAttr('disabled');
						alert('Error : Failed to get user user information');
					});

		}, function (error) {
			// Error occurred
			// console.log(error) to find the reason
			console.log(error);
		});

}






function check_Login(account, username , type) {
	$.ajax({
		url: "member/thirdPartyLogin",
		data: {
			account: account,
			username: username,
			type : type
		},
		type: "POST",
		success: function (data) {
			if (data == true) {
				console.log(data);
//				location.href = "/Team6FinalProject"
					location.reload();
			} else {
				console.log(data);
				alert("此帳號沒註冊請至註冊頁面註冊")
				location.href = "/Team6FinalProject"
				
			}
		}
	})
}



//刪除使用者已授權你的FB App，好讓使用者下次重新授權你的FB App
//參考：https://stackoverflow.com/questions/6634212/remove-the-application-from-a-user-using-graph-api/7741978#7741978
function Del_FB_App() {
	FB.getLoginStatus(function (response) {//取得目前user是否登入FB網站
		//debug用
		console.log(response);
		if (response.status === 'connected') {
			// Logged into Facebook.
			//抓userID
			FB.api("/me/permissions", "DELETE", function (
				response) {
				console.log("刪除結果");
				console.log(response); //gives true on app delete success 
			});
		} else {
			// FB取消授權
			console.log("無法刪除FB App");
		}
	});

}



//參考：https://developers.google.com/identity/sign-in/web/disconnect

function Google_disconnect() {
	var auth2 = gapi.auth2.getAuthInstance();
	auth2.disconnect().then(function () {
		console.log('User disconnect.');
	});
}
