<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<div>
			댓글 글쓴이 <input type="text" name="replyer" id="newReplyWriter">
		</div>
		<div>
			댓글 내용 <input type="text" name="reply" id="newReplyText">
		</div>
		<button id="replyAddBtn">댓글 추가</button>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	
	<script>
		let bno=123;
		
		$("#replyAddBtn").on("click",function(){
			let replyer = $("#newReplyWriter").val();
			let reply = $("#newReplyText").val();
			
			$.ajax({
				type:'post',
				url:'/replies',
				headers:{
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				dataType : 'text',
				data : JSON.stringify({
					bno:bno,
					replyer : replyer,
					reply : reply
				}),
				success :function(result){
					if(result =='SUCCESS'){
						alert("등록되었습니다");
					}
				}
			});
		})
	</script>
</body>
</html>