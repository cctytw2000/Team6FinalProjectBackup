let page = 1
let data;

function loginInfo(id) {
//	page=1
	// document.getElementById("pageBottom").innerHTML =''
	let pagebottom = "";
	url = "member/" + id + ".json"
	$.ajax({
		type: "GET",
		url: url,
		success: function (response) {
			pagebottom += '<li class="page-item"><a class="page-link" href="#">Previous</a></li>'
			console.log(response.infoList.length)
			console.log(response.infoList.length / 10)

			for (let i = 1; i <= response.infoList.length / 10 + 1; i++) {
				pagebottom += '<li class="page-item"><a id="' + i
					+ '" class="page-link" href="#">' + i
					+ '</a></li>'
			}
			pagebottom += '<li class="page-item"><a class="page-link" href="#">Next</a></li>'

			document.getElementById("pageBottom").innerHTML = pagebottom

			data = response
			for (let i = 1; i <= response.infoList.length / 10 + 1; i++) {
				document.getElementById(i).addEventListener("click",
					chengeInfo)
			}

			showInfo(data);

		}
	});

}

function showInfo(response) {
	
	console.log("showInfo(response)")
	// document.getElementById("infoBody").innerHTML=''
	showpage = page * 10 - 1
	console.log(page)
console.log(showpage)
	let info = "";
	for (let i = showpage - 9; i < showpage; i++) {

		if (i < data.infoList.length) {
			info += "<tr>"
			info += "<td>" + data.infoList[i].clientIP + "</td>"
			info += "<td>" + data.infoList[i].type + "</td>"
			info += "<td>" + data.infoList[i].loginTime.replace(".0", "")
				+ "</td>"
			info += "<td>" + data.infoList[i].accountType + "</td>"
			if (data.infoList[i].isSuccess == 1) {
				info += "<td style='color:green'>" + "成功" + "</td>"
			} else {
				info += "<td style='color:red'>" + "失敗" + "</td>"

			}

			info += "</tr>"
		} else {
			break
		}
	}
	document.getElementById("infoBody").innerHTML = info
}

function chengeInfo() {
	showpage = this.id * 10 - 1
//	document.getElementById("infoBody").innerHTML = ""
	let info = "";
	for (let i = showpage - 9; i < showpage; i++) {
		if (i < data.infoList.length) {
			info += "<tr>"
			info += "<td>" + data.infoList[i].clientIP + "</td>"
			info += "<td>" + data.infoList[i].type + "</td>"
			info += "<td>" + data.infoList[i].loginTime.replace(".0", "")
				+ "</td>"
			info += "<td>" + data.infoList[i].accountType + "</td>"
			if (data.infoList[i].isSuccess == 1) {
				info += "<td style='color:green'>" + "成功" + "</td>"
			} else {
				info += "<td style='color:red'>" + "失敗" + "</td>"

			}
			info += "</tr>"
		} else {
			break
		}

	}
	document.getElementById("infoBody").innerHTML = info

}




function openUpdate(oldlevelname) {
	let opention = ""
	$.ajax({
		url: "memberLevel.json",
		success: function (response) {
			console.log(response)
			opention += '<td>會員身分</td>'
			opention += '<select name="levelChange">'
			for (let i = 0; i < response.levelList.length; i++) {
				if (response.levelList[i].levelName == oldlevelname) {
					opention += '<option value="' + response.levelList[i].levelId + '" selected>' + response.levelList[i].levelName + '</option>'
				} else {

					opention += '<option value="' + response.levelList[i].levelId + '" >' + response.levelList[i].levelName + '</option>'
				}

			}
			opention += '</select>' + "<input type='submit' value='變更'>"
			document.getElementById("level").innerHTML = opention
		}

	});

}

