let data, pagebotNO;
let member = [];
$(document).ready(function () {
	$.ajax({
		url: "ordersBack.json",
		success: function (response) {
			console.log(response);
			data = response;
			if (response.Orders.length > 0) {
				pagebot(response);				
			}
			else {
				$("tbody#ordersInfo").html("<tr><td colspan='7'>沒訂單資料</td></tr>");
			}
		}
	});
	ordermember();
	ordersales();
	$.ajax({
		url: "membersBack.json",
		success: function (response) {
			for (let i = 0; i < response.Memners.length; i++) {
				member.push(response.Memners[i].member_id);
			}
		}
	});
	$("input#member_id").blur(checkmember);
	$("input#money_1").change(checkmoney);
	$("input#money_4").change(checkmoney);
	$("div#memberwin").on("show.bs.modal", memberwinshow);
	$("div#memberwin").on("hide.bs.modal", memberwinhide);
});
// function showOrdersInfoall(response) {
// 	let info = "";
// 	for (let i = 0; i < response.Orders.length; i++) {
// 		info += "<tr>";
// 		info += '<td><a href="order/?order_id=' + response.Orders[i].order_id
// 			+ '">' + response.Orders[i].order_id + '</a></td>';
// 		info += '<td><a href="member?id=' + response.Orders[i].member.member_id
// 			+ '">' + response.Orders[i].member.username + '</a></td>';
// 		info += "<td>" + response.Orders[i].member.account + "</td>";
// 		info += "<td>" + response.Orders[i].ordertime.replace(".0", "") + "</td>";
// 		info += "<td>" + response.Orders[i].total + "元</td>";

// 		switch (response.Orders[i].state) {
// 			case 1:
// 				info += "<td style='color:red'>未付款</td>";
// 				info += "<td><button type='button' class='btn btn-warning' data-toggle='modal' data-target='#mwin' onclick='deleteOrders(" + response.Orders[i].order_id + ")'>取消</button></td>";
// 				break;
// 			case 4:
// 				info += "<td>已付款</td>";
// 				info += "<td></td>";
// 				break;
// 		}
// 		info += "</tr>";
// 	}
// 	$("tbody#ordersInfo").html(info);
// }
function deleteOrders(orderId) {
	//alert(orderId);	
	$("div#mheader").html(
		'<h5 class="modal-title" id="exampleModalLabel">取消訂單</h5>'
		+ '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'
		+ '<span>&times;</span>'
		+ '</button>');
	$("div#mbody").html(
		'<form method="post" action="./orderBack/delete">'
		+ "<div>確定取消訂單(" + orderId + ")?</div><p>"
		+ '<input type="hidden" name="order_id" value='
		+ orderId
		+ ' /><p>'
		+ '<input type="submit" class="btn btn-warning" value="確認取消"></form>');
}
let item = 5;
function pagebot(response) {
	let pagebottom = "";
	pagebottom += '<li class="page-item" id="previous"><a class="page-link" href="#">Previous</a></li>'
	console.log(response.Orders.length);
	console.log(parseInt(response.Orders.length / item) + 1);

	for (let i = 1; i <= parseInt(response.Orders.length / item) + 1; i++) {
		pagebottom += '<li class="page-item" id="' + i + '"><a class="page-link" href="#">' + i
			+ '</a></li>';
	}
	pagebottom += '<li class="page-item" id="next"><a class="page-link" href="#">Next</a></li>'
	$("ul#pageBottom").html(pagebottom);

	document.getElementById("previous").addEventListener("click", previousChenge);
	for (let i = 1; i <= parseInt(response.Orders.length / item) + 1; i++) {
		document.getElementById(i).addEventListener("click", chengeOrdersInfo);
	}
	document.getElementById("next").addEventListener("click", nextChenge);

	pagebotNO = 1;
	showOrdersInfo(response, pagebotNO);
}
function showOrdersInfo(response, pageNo) {
	let info = "";
	for (let i = (pageNo - 1) * item; i < response.Orders.length; i++) {
		info += "<tr>";
		info += '<td><a class="card-link" data-toggle="collapse" href="#o' + response.Orders[i].order_id + '">' + response.Orders[i].order_id + '</a></td>';
		// info += '<td><a href="order/?order_id=' + response.Orders[i].order_id
		// 	+ '">' + response.Orders[i].order_id + '</a></td>';
		info += '<td><a href="member?id=' + response.Orders[i].member.member_id
			+ '">' + response.Orders[i].member.username + '</a></td>';
		info += "<td>" + response.Orders[i].member.account + "</td>";
		info += "<td>" + response.Orders[i].ordertime.replace(".0", "") + "</td>";
		info += "<td>" + response.Orders[i].total + "元</td>";

		switch (response.Orders[i].state) {
			case 1:
				info += "<td style='color:red'>未付款</td>";
				info += "<td><button type='button' class='btn btn-warning' data-toggle='modal' data-target='#mwin' onclick='deleteOrders(" + response.Orders[i].order_id + ")'>取消</button></td>";
				break;
			case 4:
				info += "<td style='color:green'>已付款</td>";
				info += "<td></td>";
				break;
		}
		info += "</tr>";
		info += "<tr><td colspan='7'>";
		info += "<div id='o" + response.Orders[i].order_id + "' class='collapse' data-parent='#accordion'>";
		info += "<div class='card-body'>";
		info += "<table border='1' style='text-align: center; width: 100%'>";
		info += "<tr><th>商品編號</th><th>商品名稱</th><th>數量</th><th>金額</th></tr>";
		info += "<tbody id='oitem" + response.Orders[i].order_id + "'></tbody>";
		info += "</table>";
		info += "</div>";
		info += "</div>";
		info += "</td></tr>";
		if (i == pageNo * item - 1) {
			break;
		}
	}
	$("tbody#ordersInfo").html(info);
	for (let i = (pageNo - 1) * item; i < response.Orders.length; i++) {
		orderDeail(response.Orders[i].order_id);
	}
	document.getElementById("previous").removeEventListener("click", previousChenge);
	if (pagebotNO == 1) {
		$("li#previous").attr("class", "page-item disabled");
	} else {
		$("li#previous").attr("class", "page-item");
		document.getElementById("previous").addEventListener("click", previousChenge);
	}
	document.getElementById("next").removeEventListener("click", nextChenge);
	if (pagebotNO == parseInt(response.Orders.length / item) + 1) {
		$("li#next").attr("class", "page-item disabled");
	} else {
		$("li#next").attr("class", "page-item");
		document.getElementById("next").addEventListener("click", nextChenge);
	}
	$("div#totalspan").html("第" + pagebotNO + "頁/共" + response.Orders.length + "筆訂單");
}

function chengeOrdersInfo() {
	pagebotNO = this.id;
	showOrdersInfo(data, this.id);
}
function previousChenge() {
	pagebotNO--;
	showOrdersInfo(data, pagebotNO);
}
function nextChenge() {
	pagebotNO++;
	showOrdersInfo(data, pagebotNO);
}
function orderDeail(orderid) {
	$.ajax({
		url: "orderDeail/" + orderid + ".json",
		success: function (response) {
			console.log(response);
			if (response.orderDeail.length > 0) {
				let info = "";
				for (let i = 0; i < response.orderDeail.length; i++) {
					info += "<tr>";
					info += "<td>" + response.orderDeail[i].product.game_id + "</td>";
					info += "<td>" + response.orderDeail[i].product.name + "</td>";
					info += "<td>" + response.orderDeail[i].count + "個</td>";
					info += "<td>" + response.orderDeail[i].subtotal + "元</td>";
					info += "</tr>";
				}
				$("tbody#oitem" + orderid).html(info);
			}
			else {
				$("tbody#oitem" + orderid).html("<tr><td colspan='4'>沒訂單詳細資料</td></tr>");
			}
		}
	});
}

function ordermember() {
	$.ajax({
		url: "memberOrdercount.json",
		success: function (response) {
			//console.log(response);
			console.log(response.countlist);
			let date = [];
			let count = [];
			for (let i = 0; i < response.countlist.length; i++) {
				date.push(response.countlist[i].id + "_" + response.countlist[i].name);
				count.push(response.countlist[i].count);
			}
			console.log(date);
			console.log(count);
			showorderCount(date, count);
		}
	});
}
function showorderCount(date, count) {
	var ctx = document.getElementById("chart").getContext('2d');
	var chart = new Chart(ctx, {
		type: 'bar',
		data: {
			labels: date,
			datasets: [{
				label: '訂單數量',
				data: count,
				backgroundColor: 'blue',
				borderColor: 'black',
				borderWidth: 1
			}]
		}
	});
}
function ordersales() {
	$.ajax({
		url: "dailySales.json",
		success: function (response) {
			//console.log(response);
			console.log(response.sales);
			let date = [];
			let count = [];
			for (let i = 0; i < response.sales.length; i++) {
				date.push(response.sales[i][0]);
				count.push(response.sales[i][1]);
			}
			console.log(date);
			console.log(count);
			showorderCount1(date, count);
		}
	});
}
function showorderCount1(date, count) {
	var ctx = document.getElementById("chart1").getContext('2d');
	var chart = new Chart(ctx, {
		type: 'line',
		data: {
			labels: date,
			datasets: [{
				label: '元',
				data: count,
				fill: false,
				borderColor: "blue", // 設定線的顏色
				backgroundColor: 'blue', // 設定點的顏色
				lineTension: 0,  // 顯示折線圖，不使用曲線				
				borderWidth: 1
			}]
		}
	});
}
function memberorderdata() {
	$.ajax({
		url: "memberOrder/" + $("input#member_id").val() + ".json",
		success: function (response) {
			console.log(response);
			data = response;
			$("tbody#ordersInfo").html("");
			$("div#totalspan").html("");
			$("ul#pageBottom").html("");
			if (response.Orders.length > 0) {
				pagebot(response);				
			}
			else {
				$("tbody#ordersInfo").html("<tr><td colspan='7'>沒訂單資料</td></tr>");
			}
		}
	});

}
function checkmember() {
	$("tbody#ordersInfo").html("");
	$("div#totalspan").html("");
	$("ul#pageBottom").html("");
	$("span#err_msg").html("");
	let memberdata = $("input#member_id").val();
	if (memberdata == "") {
		$.ajax({
			url: "ordersBack.json",
			success: function (response) {
				console.log(response);
				data = response;
				if (response.Orders.length > 0) {
					pagebot(response);					
				}
				else {
					$("tbody#ordersInfo").html("<tr><td colspan='7'>沒訂單資料</td></tr>");
				}
			}
		});
	}
	else {
		let re = /^\+?[1-9][0-9]*$/;
		if (re.test(memberdata)) {
			let falge = false;
			for (let i = 0; i < member.length; i++) {
				if (memberdata == member[i]) {
					falge = true;
				}
			}
			if (falge) {
				memberorderdata();
			} else {
				$("span#err_msg").html("<img src='Images/noway.jpg'>無此會員編號");
			}
		}
		else {
			$("div#memberwin").modal("show");
		}
	}
}

function memberwinshow() {
	$.ajax({
		url: "memberkeyword",
		type: "POST",
		data: {
			keyWord: $("input#member_id").val()
		},
		success: function (response) {
			console.log(response);
			if (response.members.length > 0) {
				showmemberinfo(response);
			}
			else {
				$("tbody#membertable").html("<tr id='err'><td colspan='3'>沒會員資料</td></tr>");
				$("tr#err").click(function () {
					$("input#member_id").val("");
					$("div#memberwin").modal("hide");
				});
			}

		}
	});

}
function showmemberinfo(response) {
	let info = "";
	for (let i = 0; i < response.members.length; i++) {
		info += "<tr id='" + response.members[i].member_id + "'>";
		info += "<td>" + response.members[i].member_id + "</td>";
		info += "<td>" + response.members[i].username + "</td>";
		info += "<td>" + response.members[i].account + "</td>";
		info += "</tr>";
	}
	$("tbody#membertable").html(info);
	for (let i = 0; i < response.members.length; i++) {
		$("tr#" + response.members[i].member_id).click(memberclick);
	}
}
function memberclick() {
	$("input#member_id").val(this.id);
	$("div#memberwin").modal("hide");
}
function memberwinhide() {
	checkmember();
}

function checkmoney() {
	//alert($(this).val());
	let memberdata = $("input#member_id").val();
	$("tbody#ordersInfo").html("");
	$("div#totalspan").html("");
	$("ul#pageBottom").html("");
	if (memberdata == "") {
		if (!$(this).prop("checked")) {
			//alert($(this).prop("checked"));
			switch (parseInt($(this).val())) {
				case 1:
					money(4);
					$("input#money_4").prop("checked", true);
					break;
				case 4:
					money(1);
					$("input#money_1").prop("checked", true);
					break;
			}
		} else {
			$.ajax({
				url: "ordersBack.json",
				success: function (response) {
					console.log(response);
					data = response;
					if (response.Orders.length > 0) {
						pagebot(response);						
					}
					else {
						$("tbody#ordersInfo").html("<tr><td colspan='7'>沒訂單資料</td></tr>");
					}
				}
			});
		}
	} else {
		if (!$(this).prop("checked")) {
			//alert($(this).prop("checked"));
			switch (parseInt($(this).val())) {
				case 1:
					moneymember(4, memberdata);
					$("input#money_4").prop("checked", true);
					break;
				case 4:
					moneymember(1, memberdata);
					$("input#money_1").prop("checked", true);
					break;
			}
		} else {
			memberorderdata();
		}
	}


}
function money(state) {
	$.ajax({
		url: "money",
		type: "POST",
		data: {
			state: state
		},
		success: function (response) {
			console.log(response);
			data = response;
			if (response.Orders.length > 0) {
				pagebot(response);				
			}
			else {
				$("tbody#ordersInfo").html("<tr><td colspan='7'>沒訂單資料</td></tr>");
			}
		}
	});
}
function moneymember(state, memberid) {
	$.ajax({
		url: "moneymember",
		type: "POST",
		data: {
			member_id: memberid,
			state: state
		},
		success: function (response) {
			console.log(response);
			data = response;
			if (response.Orders.length > 0) {
				pagebot(response);				
			}
			else {
				$("tbody#ordersInfo").html("<tr><td colspan='7'>沒訂單資料</td></tr>");
			}
		}
	});
}