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
<title>New Folder &mdash; Free Website Template, Free HTML5
	Template by GetTemplates.co</title>
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
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
</head>


<body>

    <div class="gtco-loader"></div>

    


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
                                </ul>
                            </li>
                            <li class="has-dropdown"><a href="FoodsInfoControll?foodeCode=01">Foods</a>
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
                                </ul>
                            </li>
                            <!-- <li><a href="foods.html">Foods</a></li> -->
                            <li><a href="GetCouponInfo">Event</a></li>
                            <li><a href="pagingControll">Community</a></li>

                            <c:if test="${level >1}">
                                <li class="has-dropdown"> <c:if test="${level eq 2}"><a href="RegStore.jsp">Management</a></c:if><c:if test="${level >2}"><a href="GetStoreInfo?request=2">Management</a></c:if>
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
                                    </ul>
                                </li>
                            </c:if>

                            <li class="btn-cta"><a href="logOutControll"><span>logOut</span></a></li>

                        </ul>
                    </div>
                </div>

            </div>
        </nav>

        <header id="gtco-header" class="gtco-cover gtco-cover-md"
			role="banner" style="background-image: url(images/img_bg_1.jpg)"
			; 
			data-stellar-background-ratio="0.5">
			<div class="overlay"
				style="justify-content: center; display: flex; align-items: center;"></div>
			<div class="gtco-container">
				<div class="row">
					<div class=" col-md-offset-0 text-left" style="font-size:3px;" >

						<h1 style="text-align: center; margin-top: 110px">${value }</h1>
						<div class="row row-mt-15em" style="margin-right:130px;">

							<div class="col-md-4 col-md-push-1 animate-box"
								data-animate-effect="fadeInRight"
								style="width: 100%; margin-right: 100%">
								<div class="form-wrap"
									style="margin-right: 100%; justify-content: center; align-items: center; text-align: center;">
									<div class="tab">

										<div class="tab-content" style="margin: auto; height:400px; font-size: 20px">
										<table class="ta cursive-font" style=" width: 100%; border: none;">
                            <colgroup>
                                    <col width="20%">
                                    <col width="60%">
                                    <col width="20%">
                                </colgroup>
                                <tr style="color: white; font-size: 25px ; font-family: Roboto;" >
                                    <td> StoreName </td>
                                    <td style="height: 80px;">Location </td>
                                    <!-- <th>Comment </th> -->
                                    <td>Launching</td>
                                </tr>
                                
                                
                               	<c:forEach items="${list }" var="list">
								<tr style="font-family: Roboto;">
									<td> <a href="OneStoreInfo?code=${list.getStoreCode()}" style="color: white"> ${list.getStoreName()}</a></td>
									
									<td>${list.getStoreAdd()}</td>
									
									<td> <a href="AdminControll?code=${list.getStoreCode()}&state=${state}" style="color: white;"> ${request}</a></td>
									
								</tr>
							</c:forEach>


                            </table>
                            <br>
										<c:if test="${paging.page<=1}"><button class="btn btn-primary">Prev</button></c:if>

                        <!-- 현재 페이지가 1페이지 보다 클 경우 -->
                        <c:if test="${paging.page>1}">
                            <a href="GetStoreInfo?page=${paging.page-1}&request=3"><button class="btn btn-primary">Prev</button></a>&nbsp;
                        </c:if>
                        
                        <!-- 현재 페이지 표시 -->
                        <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
                            <c:choose>

                                <c:when test="${i eq paging.page}">
                                    <strong style="font-size: 18px;">${i}</strong>
                                </c:when>


                                <c:otherwise>
                                    <a href="GetStoreInfo?page=${i}&request=3" style="text-decoration: none;" id="aa">${i}</a>

                                </c:otherwise>

                            </c:choose>

                        </c:forEach>

                        <!-- 현재 페이지가 끝페이지 이거나 끝페이지보다 큰 경우 -->
                        <c:if test="${paging.page >= paging.maxPage}">&nbsp; <button class="btn btn-primary">Next</button> </c:if>

                        <!-- 현재 페이지가 끝페이지보다 작은 경우 -->
                        <c:if test="${paging.page < paging.maxPage}">
                            &nbsp;<a href="GetStoreInfo?page=${paging.page+1}&request=3" id="a"><button class="btn btn-primary">Next</button></a>
                        </c:if>
											<div class="tab-content-inner active" data-content="signup">
												
											</div>
											
										</div>
									</div>
								</div>
							</div>
						</div>


					</div>
				</div>
			</div>
		</header>

    
   

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
										id="kakaoID1">Kakao</span></a></li>

							</ul>
							<ul class="gtco-quick-contact">
								<li><a href="#"><i class="icon-phone"></i> 010 4846
										1852</a></li>
								<li><a href="#"><i class="icon-mail2"></i>
										kjhg478@naver.com</a></li>
								<li><a href="#"><i class="icon-chat"></i><span
										id="kakaoID2">Kakao</span></a></li>

							</ul>
							<ul class="gtco-quick-contact">
								<li><a href="#"><i class="icon-phone"></i>010 9525 1639 </a></li>
								<li><a href="#"><i class="icon-mail2"></i>
										gmlans9525@naver.com</a></li>
								<li><a href="#"><i class="icon-chat"></i><span
										id="kakaoID3">Kakao</span></a></li>

							</ul>
							<ul class="gtco-quick-contact">
								<li><a href="#"><i class="icon-phone"></i> 010 6615 0501</a></li>
								<li><a href="#"><i class="icon-mail2"></i>
										spqjeks@naver.com</a></li>
								<li><a href="#"><i class="icon-chat"></i><span
										id="kakaoID4">Kakao</span></a></li>
								<script>
								var kakaoid1 = document.getElementById("kakaoID1")
								kakaoid1.addEventListener("mouseover",()=>{kakaoid1.innerHTML="강동훈"});
								kakaoid1.addEventListener("mouseout",()=>{kakaoid1.innerHTML="Kakao"});
								
								var kakaoid2 = document.getElementById("kakaoID2")
								kakaoid2.addEventListener("mouseover",()=>{kakaoid2.innerHTML="김종훈"});
								kakaoid2.addEventListener("mouseout",()=>{kakaoid2.innerHTML="Kakao"});
								
								var kakaoid3 = document.getElementById("kakaoID3")
								kakaoid3.addEventListener("mouseover",()=>{kakaoid3.innerHTML="유상민"});
								kakaoid3.addEventListener("mouseout",()=>{kakaoid3.innerHTML="Kakao"});
								
								var kakaoid4 = document.getElementById("kakaoID4")
								kakaoid4.addEventListener("mouseover",()=>{kakaoid4.innerHTML="조 단"});
								kakaoid4.addEventListener("mouseout",()=>{kakaoid4.innerHTML="Kakao"});
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
							href="#">New Folder</a>
					</div>

				</div>



			</div>
		</footer>
    <!-- </div> -->



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
    <script src="js/main.js"></script>
   

</body>

</html>