<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<style>
		#modDiv{
		width:300px;
		height:100px;
		background-color:aqua;
		position:absolute;
		top:50%;
		left:50%;
		margin-top:-50px;
		margin-left:-150px;
		padding:10px;
		z-index:1000;
		}
	</style>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul id="replies">
		
	</ul>
	<div>
		<div>
			댓글 글쓴이 <input type="text" name="replyer" id="newReplyWriter">
		</div>
		<div>
			댓글 내용 <input type="text" name="reply" id="newReplyText">
		</div>
	
		<button id="replyAddBtn">댓글 추가</button>
	</div>
	
	<!-- modal은 일종의 팝업 
		단, 새창을 띄우지 않고 css를 이용해 특정 태그가 조건부로 보이거나 안보이도록 해서
		새창이 띄워지는것처럼 만듦
		따라서 눈에 보이지 않아도 modal을 구성하는 태그 자체는 화면에 미리 적혀있어야함-->
		
		<div id="modDiv" style="display:none;">
			<div class="modal-title">
			</div>
				<div>
					<input type="text" id="reply">
				</div>
			<div>
				<button type="button" id="replyModBtn">수정</button>
				<button type="button" id="replyDelBtn">삭제</button>
				<button type="button" id="closeBtn">닫기</button>
			</div>
		</div>
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	
	<script>
	
		//글번호
		let bno=123;
		
		//댓글조회
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
					str +="<li data-rno='"+this.rno+"'class='replyLi'>"+this.rno+":"+this.reply+
					"<button>수정/삭제</button></li>";
				});
					console.log(str);			
					$("#replies").html(str);
				});
		}
		getAllList();
		
		//댓글추가
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
						getAllList();
						$("#newReplyWriter").val("");
						 $("#newReplyText").val("");
					}
				}
			});
		});
		
		// (수정/삭제) 버튼
		$("#replies").on("click",".replyLi button",function(){
				// 클릭한 요소가 this이고, 현재 button에 걸렸기 때문에
				// this는 button 입니다. buttonm의 부모가 바로 .replyLi 입니다.
				// 즉 ,클릭한 버튼과 연계된 li 태그를 replytag 변수에 저장합니다.
				let replytag = $(this).parent();
				console.log(replytag);
				
				// 클릭한 버튼과 연계된 li태그의 data-rno에 든 값 가져와 변수 rno에 저장
				let rno = replytag.attr("data-rno");
				console.log(rno);
				
				// 내부 text 가져옴
				let reply=replytag.text();
				
				$(".modal-title").html(rno); //modal-title 부분에 글번호 입력
				$("#reply").val(reply); // reply 영역에 리플 내용 기입(수정/삭제)
				$("#modDiv").show("slow"); //버튼누르면 모달 열림
				
				//closeBtn 버튼 클릭 시 모달창(#modDiv) 닫음 
				$("#closeBtn").on("click",function(){
					$("#modDiv").hide("slow");
					});
				
				// 댓글삭제
				$("#replyDelBtn").on("click",function(){
					let rno =$(".modal-title").html();
					$.ajax({
						type :'delete',
						url :'/replies/'+rno,
						header:{
							"X-HTTP-Method-Override":"DELETE"
						},
						dataType:'text',
						success:function(result){
							console.log("result:"+result);
							if(result=='SUCCESS'){
								alert("삭제되었습니다.");
								$("#modDiv").hide("slow");
								getAllList(); //삭제반영한 댓글목록갱신
							}
						}
					});
				});
				
				//댓글 수정
				$("#replyModBtn").on("click",function(){
					let rno=$(".modal-title").html();
					let reply=$("#reply").val();
					$.ajax({
						type : 'patch',
						url : '/replies/'+rno,
						header:{
							"Content-Type":"application/json",
							"X-HTTP-Method-Override":"PATCH"
						},
						contentType:"application/json",//json 자료를 추가로 입력받기 때문에
						data:JSON.stringify({
							reply : reply
						}),
						dataType:'text',
						success:function(result){
							console.log("result:"+result);
							if(result=='SUCCESS'){
								alert("수정되었습니다.");
								$("#modDiv").hide("slow");
								getAllList();
							}
						}
					});
				});
			});
	</script>
</body>
</html>