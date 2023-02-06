<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul id="replies">
	
	</ul>
	
	<button id="requestBtn">
	댓글로딩해오기
	</button>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	
	<script type="text/javascript">
	let bno=123;
	
	
	$("#requestBtn").on("click",function(){
		
		$.getJSON("/replies/all/"+bno,function(data){
			
			let str="";
			
			console.log(data);
			
			$(data).each(function(){
				// 각 데이터는 this라는 키워드로 표현됨
				console.log("------------------");
				console.log(this);
				str+="<li>"+this.rno+this.reply+"</li>"
			});
				console.log(str);			
				$("#replies").html(str);
		})
			
	});
	</script>
	
</body>
</html>