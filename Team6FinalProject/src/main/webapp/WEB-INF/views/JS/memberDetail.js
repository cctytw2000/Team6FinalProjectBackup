let open = 0
let telopen = 0
function openUpdate(nickname) {
	if (open == 0) {
		document.getElementById("nickname").innerHTML = '<label>暱稱：</label> <br><input type="text"\
				 name="nickname" placeholder="來個新名字吧(ゝ∀･)"\
				 value="'+nickname+'"></input> <br><input type="submit" value="更新"> <button id="update"\
				 type="button" onclick="openUpdate('+"'"+nickname+"'"+')">取消</button>'
		open = 1
	} else {
		document.getElementById("nickname").innerHTML = "<label>暱稱：</label> <br><input style='border-width: 0; background-color: white' type='text'"
				+ "name='nickname' placeholder='來個新名字吧(ゝ∀･)' disabled='disabled' readonly='readonly'"
				+ "value='"+nickname+"'></input> <button id='update'"
				+ "type='button' onclick='openUpdate("+'"'+nickname+'"'+")'>編輯</button>"
		open = 0
	}

}

function headshot(id) {
	let chenge = ""
	chenge += '<input name="memberId" type="hidden" value="' + id + '"/>'
	chenge += '<input id="memberimg" name="memberimg" type="file" onchange="readURL(this)"/> <input type="submit" />'
	document.getElementById("Changeheadshot").innerHTML = chenge

}


function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#previewHeadShot").attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

function showall(allTel,tel){
	if(telopen == 0){
		document.getElementById("showTel").innerHTML=allTel
		document.getElementById("tel").innerHTML="取消"
		tel
		telopen=1
	}else {

		document.getElementById("showTel").innerHTML=tel
		document.getElementById("tel").innerHTML="查看"
		telopen=0
	}
	
}