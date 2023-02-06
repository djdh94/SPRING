<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h2>Ajax 테스트</h2>
		<ul id="replies">
		
		</ul>
		
		<ul id="test">
		
		</ul>
		
		<button id="testBtn">테스트</button>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
		
		<!-- 비동기 부분 -->
		<script type="text/javascript">
		
		var bno=123;
		
		function getAllList(){
				    //주소              //콜백함수 주소 요청으로 얻어온 json 어떻게 처리할지
			$.getJSON("/replies/all/"+bno,function(data){
				let str ="";
				
				console.log(data);
				
				//$(JSON형식데이터).each => 내부 json을 향상된 for문으로 처리
				$(data).each(function(){
					// 각 데이터는 this라는 키워드로 표현됨
					console.log("------------------");
					console.log(this);
					str +="<li data-rno='"+this.rno+"'class='replyLi'>"+this.rno+":"+this.reply+"</li>";
				});
					console.log(str);			
					$("#replies").html(str);
			});
		}
		getAllList();
		// 버튼(testBtn)클릭시 발동되는 이벤트
		$("#testBtn").on("click",function(){
			let test = "<a href='https://www.daum.net'>다음</a>";
				$("#test").html(test);
		});
		</script>
</body>
</html>