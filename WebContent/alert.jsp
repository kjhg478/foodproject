<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
</body>

<script type="text/javascript">
(function(){
	if (${result}==1) {
		alert("${value}");
		location.href="Main.jsp";
	}else if (${result}==2) {
		alert("${value}");
		location.href="Loginindex.jsp";
	}else if (${result}==3) {
		alert("${value}");
		location.href="index.jsp";
	}else if (${result}==4) {
		alert("${value}");
		location.href="UserModify.jsp";
	}else if (${result}==5) {
		alert("${value}");
		location.href="pagingControll";
	}else if (${result}==6) {
		alert("${value}");
		location.href="AllUserInfo?page=${page}";
	}else if (${result}==7) {
		alert("${value}");
		location.href="isCouponApply";
	}else if (${result}==8) {
	      alert("${value}");
	      location.href="GetStoreInfo?request=${state}";
	   }
	else if (${result}==9) {
	      alert("${value}");
	      location.href="oneCommunityView?Num=${Num}&page=${page}";
	   }
	
	
})();

</script>
</html>