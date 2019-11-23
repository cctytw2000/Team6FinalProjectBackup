//datepicker 
var $s1=jQuery.noConflict();
	$s1(function() {
		$s1( "#datepicker1" ).datepicker({
			changeMonth: true,
			changeYear: true
		});
	});
	
	var $s2=jQuery.noConflict();
	$s2(function() {
		$s2( "#datepicker2" ).datepicker({
			changeMonth: true,
			changeYear: true
		});
	});
	
	var $s3=jQuery.noConflict();
	$s3(function() {
		$s3( "#datepicker3" ).datepicker({
			changeMonth: true,
			changeYear: true
		});
	});
	
//timepicker 
	var $y=jQuery.noConflict();
 	$y(function(){
		$y('#timepicker').mdtimepicker();
	});
 	
 //到回上一頁	
 	function GoBack() {
 		history.go(-1)
 	}
 	
 	
// 	 隱藏或顯現活動時間為一天的活動 
 	function TimePick() {
 		var x = document.getElementById("oneDayOnly");
 		var y = document.getElementById("manyDays");
 		  if (x.style.display === "none") {
 		    x.style.display = "block";
 		    y.style.display = "none";
 		  } else {
 		    x.style.display = "none";
 		    y.style.display = "block";
 		  }
 		}