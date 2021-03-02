<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<!--
	Aesthetic by gettemplates.co
	Twitter: http://twitter.com/gettemplateco
	URL: http://gettemplates.co
-->
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Savory &mdash; Free Website Template, Free HTML5 Template
	by GetTemplates.co</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Free HTML5 Website Template by GetTemplates.co" />
<meta name="keywords"
	content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
<meta name="author" content="GetTemplates.co" />

<!-- Facebook and Twitter integration -->
<meta property="og:title" content="" />
<meta property="og:image" content="" />
<meta property="og:url" content="" />
<meta property="og:site_name" content="" />
<meta property="og:description" content="" />
<meta name="twitter:title" content="" />
<meta name="twitter:image" content="" />
<meta name="twitter:url" content="" />
<meta name="twitter:card" content="" />

<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Kaushan+Script"
	rel="stylesheet">

<!-- Animate.css -->
<link rel="stylesheet" href="css/animate.css">
<!-- Icomoon Icon Fonts-->
<link rel="stylesheet" href="css/icomoon.css">
<!-- Themify Icons-->
<link rel="stylesheet" href="css/themify-icons.css">
<!-- Bootstrap  -->
<link rel="stylesheet" href="css/bootstrap.css">
<!-- Magnific Popup -->
<link rel="stylesheet" href="css/magnific-popup.css">

<!-- Bootstrap DateTimePicker -->
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">

<!-- Owl Carousel  -->
<link rel="stylesheet" href="css/owl.carousel.min.css">
<link rel="stylesheet" href="css/owl.theme.default.min.css">

<!-- Theme style  -->
<link rel="stylesheet" href="css/style.css">

<!-- Modernizr JS -->
<script src="js/modernizr-2.6.2.min.js"></script>
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->
<style>
.Loginbtn {
	border: solid #555555 2px;
	font-size: 1.5rem;
	width: fit-content;
	border-radius: 4px;
}

.btn-signin {
	padding: 5px 20px;
}

.btn-signin:hover {
	background-color: white;
	border-radius: 4px;
	color: #fbb448;
}
</style>

</head>
<body>

	<div class="gtco-loader"></div>

	<div id="page">


		<!-- <div class="page-inner"> -->
		<nav class="gtco-nav" role="navigation">
			<div class="gtco-container">

				<div class="row">
					<div class="col-sm-4 col-xs-12">
						<div id="gtco-logo">
							<a href="isCouponApply">New Folder <em>.</em></a>
						</div>
					</div>
					<div class="col-xs-8 text-right menu-1">
						<ul>
							<li class="has-dropdown"><a href="GetFoodsInfo">Contents</a>
								<ul class="dropdown">
									<li><a href="GetFoodsInfo">VS Game</a></li>
									<li><a href="RandomRecommendControll">Recommend</a></li>
									<li><a href="getTopFoodsInfo">Top Ranks</a></li>
								</ul></li>
							<li class="has-dropdown"><a
								href="FoodsInfoControll?foodeCode=01">Foods</a>
								<ul class="dropdown">
									<li><a href="FoodsInfoControll?foodeCode=01">Korean
											Foods</a></li>
									<li><a href="FoodsInfoControll?foodeCode=02">Western
											Foods</a></li>
									<li><a href="FoodsInfoControll?foodeCode=03">Chinese
											Foods</a></li>
									<li><a href="FoodsInfoControll?foodeCode=04">Asian
											Foods</a></li>
									<li><a href="FoodsInfoControll?foodeCode=05">Fast
											Foods</a></li>
									<li><a href="FoodsInfoControll?foodeCode=06">Coffee&Dessert</a></li>
								</ul></li>
							<!-- <li><a href="foods.html">Foods</a></li> -->
							<li><a href="GetCouponInfo">Event</a></li>
							<li><a href="pagingControll">Community</a></li>
							<c:if test="${level >1}">
								<li class="has-dropdown"><a>Management</a>
									<ul class="dropdown">
										<c:if test="${level eq 2}">
											<li><a href="RegStore.jsp">Registration</a></li>
										</c:if>
										<c:if test="${level >2}">
										<li><a href="GetStoreInfo?request=2">Request</a></li>
											<li><a href="GetStoreInfo?request=3">Launching</a></li>
											<li><a href="AllUserInfo">Member</a></li>
											<li><a href="RegCoupon.jsp">Registration Coupon</a></li>
											<li><a href="getApplicant">Drawlots Coupon</a></li>
										</c:if>
									</ul></li>
							</c:if>

							<li class="btn-cta"><a href="logOutControll"><span>logOut</span></a></li>
							<li class="btn-cta"><a href="UserModify.jsp"><span>Sign Modify</span></a></li>
							
						</ul>
					</div>
				</div>

			</div>
		</nav>

	

		<div id="gtco-counter" class="gtco-section">
			<div class="gtco-container">

				<div class="row">
					<div
						class="col-md-8 col-md-offset-2 text-center gtco-heading animate-box">
						<h2 class="cursive-font primary-color">Total Applicant</h2>
						<p>Are you Ready?</p>
					</div>
				</div>

				<div class="row" style="justify-content: center; align-items: center; display: flex;" >

					
					
					<div class="col-md-3 col-sm-6 animate-box"
						data-animate-effect="fadeInUp">
						<div class="feature-center">
							<span class="counter js-counter" data-from="0" data-speed="5000"
								data-refresh-interval="50" id="totalapply" data-to="${people}" style="font-size: 100px"></span> <span
								class="counter-label">How many people are appling now!!</span>
						</div>
					</div>
				

				</div>
			</div>
		</div>

		

		<div id="gtco-features">
			<div class="gtco-container">
				<div class="row">
					<div
						class="col-md-8 col-md-offset-2 text-center gtco-heading animate-box">
						<h2 class="cursive-font">Warning Sign!</h2>
						<p style="font-size: 25px">
							You can not <span style="color: tomato; font-weight: bold;">return back </span>as soon as press this button<br>
							please press carefully
						</p>
						<p>Get Ready!</p>
					</div>
				</div>
			</div>
		</div>


		<div class="gtco-cover gtco-cover-sm" style="background-image: url(images/img_bg_1.jpg)"
			data-stellar-background-ratio="0.5">
			<div class="overlay" style="text-align: center; justify-content: center; display: flex; align-items: center;" >
			<div class="gtco-container text-center">
				<div class="display-t" >
				<form action="drawlotsRandom" method="post" name="intoDarwlots">
					<div class="display-tc" id="drawResult">
						<h1>&ldquo; How many people do you wanna choose &rdquo;</h1>
						<div class="row form-group" >
									<div class="col-md-12" style="justify-content: center; align-items: center; display: flex; text-align: center;">
										<label class="sr-only" for="email">Title</label> <input style="width:400px; height:80px; text-align:center; font-size: 50px; color: white; font-weight: bold;"
											type="text" id="getpeople" class="form-control" autocomplete="off"
											placeholder="Here!" required name="people">
								</div>
						</div>
						<div class="row animate-box">
					<div class="col-md-8 col-md-offset-4">
							<div class="col-md-6 col-sm-6"><!-- isFine() -->
								<button type="button" class="btn btn-primary btn-block" onclick="isFine()">Draw Lots</button>
							</div>
					</div>
					
					</div>
					
					</div>
					</form>
				</div>
			</div>
			</div>
		</div>


		

	<footer id="gtco-footer" role="contentinfo"
			style="background-image: url(images/img_bg_1.jpg)"
			data-stellar-background-ratio="0.5">
			<div class="overlay"></div>
			<div class="gtco-container">
				<div class="row row-pb-md">




					<div class="col-md-12 text-center">
						<div class="gtco-widget">
							<h3>Get In Touch</h3>
							<ul class="gtco-quick-contact">
								<li><a href="#"><i class="icon-phone"></i> 010 2190
										4168</a></li>
								<li><a href="#"><i class="icon-mail2"></i>kdh4168@naver.com</a></li>
								<li><a href="#"><i class="icon-chat"></i><span
										id="kakaoID1">Kakako</span></a></li>

							</ul>
							<ul class="gtco-quick-contact">
								<li><a href="#"><i class="icon-phone"></i> 010 4846
										1852</a></li>
								<li><a href="#"><i class="icon-mail2"></i>
										kjhg478@naver.com</a></li>
								<li><a href="#"><i class="icon-chat"></i><span
										id="kakaoID2">Kakako</span></a></li>

							</ul>
							<ul class="gtco-quick-contact">
								<li><a href="#"><i class="icon-phone"></i>010 9525 1639 </a></li>
								<li><a href="#"><i class="icon-mail2"></i>
										gmlans9525@naver.com</a></li>
								<li><a href="#"><i class="icon-chat"></i><span
										id="kakaoID3">Kakako</span></a></li>

							</ul>
							<ul class="gtco-quick-contact">
								<li><a href="#"><i class="icon-phone"></i> 010 6615 0501</a></li>
								<li><a href="#"><i class="icon-mail2"></i>
										spqjeks@naver.com</a></li>
								<li><a href="#"><i class="icon-chat"></i><span
										id="kakaoID4">Kakako</span></a></li>
								<script>
								var kakaoid1 = document.getElementById("kakaoID1")
								kakaoid1.addEventListener("mouseover",()=>{kakaoid1.innerHTML="강동훈"});
								kakaoid1.addEventListener("mouseout",()=>{kakaoid1.innerHTML="Kakako"});
								
								var kakaoid2 = document.getElementById("kakaoID2")
								kakaoid2.addEventListener("mouseover",()=>{kakaoid2.innerHTML="김종훈"});
								kakaoid2.addEventListener("mouseout",()=>{kakaoid2.innerHTML="Kakako"});
								
								var kakaoid3 = document.getElementById("kakaoID3")
								kakaoid3.addEventListener("mouseover",()=>{kakaoid3.innerHTML="유상민"});
								kakaoid3.addEventListener("mouseout",()=>{kakaoid3.innerHTML="Kakako"});
								
								var kakaoid4 = document.getElementById("kakaoID4")
								kakaoid4.addEventListener("mouseover",()=>{kakaoid4.innerHTML="조 단"});
								kakaoid4.addEventListener("mouseout",()=>{kakaoid4.innerHTML="Kakako"});
								</script>
							</ul>
						</div>
						<div class="gtco-widget">
							<h3>Get Social</h3>
							<ul class="gtco-social-icons">
								<li><a href="#"><i class="icon-twitter"></i></a></li>
								<li><a href="#"><i class="icon-facebook"></i></a></li>
								<li><a href="#"><i class="icon-linkedin"></i></a></li>
								<li><a href="#"><i class="icon-dribbble"></i></a></li>

							</ul>
						</div>
					</div>

					<div class="col-md-12 text-center copyright">
						Made by <i class="fa fa-love"></i><a
							href="https://bootstrapthemes.co">New Folder</a>
					</div>

				</div>



			</div>
		</footer>
		<!-- </div> -->

	</div>

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
	</div>
	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Carousel -->
	<script src="js/owl.carousel.min.js"></script>
	<!-- countTo -->
	<script src="js/jquery.countTo.js"></script>

	<!-- Stellar Parallax -->
	<script src="js/jquery.stellar.min.js"></script>

	<!-- Magnific Popup -->
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/magnific-popup-options.js"></script>

	<script src="js/moment.min.js"></script>
	<script src="js/bootstrap-datetimepicker.min.js"></script>


	<!-- Main -->
	<script src="js/main.js"> </script>

	<script>
		
	function resultAjax(data){

		 var out = "";
		 out += "<div class='display-tc' style='position:relative;' id='darwResult'>";
		 out += "<h1>&ldquo; Now they got Coupons &rdquo;</h1>";
		 for(let i in data){
			 out += "<p class='primary-color'>&ldquo;" +i+ "  :  " +data[i]+ "&rdquo;</p>";
		 }
		 out += "</div>";
		
		 document.getElementById("drawResult").innerHTML = out;
		
	};

	   function isFine() {
		let people = '${people}';
		let code = '${code}';
		let val = document.getElementById("getpeople").value;
		if(parseInt(people) >= parseInt(val)){
			 $.ajax({ 
		           url : "drawlotsRandom",
		           type: "post",
		           data:{code:code,people:val},
		           datatype : "json",
		            success : function (data) {	
		            	resultAjax(data);
		            	
		           },
		           error : function (data) {
		               
		               alert("데이터를 불러올수 없습니다.");
		           }
		       });
		}else{
			event.preventDefault();
			alert("지원자보다 적은수가 들어가야합니다.");
		}
	}   
		 
	 </script>
</body>
</html>

