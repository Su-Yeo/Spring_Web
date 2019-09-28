<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MovePage</title>
</head>
<body>
<!-- StyleInsert -->
<c:if test="${url eq 'insertStyle'}">
	<meta charset="UTF-8" http-equiv="refresh" content="0;url=<c:url value='adminStyleList'/>">
</c:if>

<!-- StyleUpdate -->
<c:if test="${url eq 'updateStyle'}">
	<meta charset="UTF-8" http-equiv="refresh" content="0;url=<c:url value='adminStyleList'/>">
</c:if>

<!-- StyleDelete -->
<c:if test="${url eq 'deleteStyle'}">
	<meta charset="UTF-8" http-equiv="refresh" content="0;url=<c:url value='adminStyleList'/>">
</c:if>

<!-- ClosetInsert(url 변경해야함) -->
<c:if test="${url eq 'insertCloset'}">
	<meta charset="UTF-8" http-equiv="refresh" content="0;url=<c:url value=''/>">
</c:if>

<!-- ClosetUpdate(url 변경해야함) -->
<c:if test="${url eq 'updateCloset'}">
	<meta charset="UTF-8" http-equiv="refresh" content="0;url=<c:url value=''/>">
</c:if>

<!-- ClosetDelete(url 변경해야함) -->
<c:if test="${url eq 'deleteCloset'}">
	<meta charset="UTF-8" http-equiv="refresh" content="0;url=<c:url value=''/>">
</c:if>

<!-- MemberInsert -->
<c:if test="${url eq 'insertMember'}">
	<meta charset="UTF-8" http-equiv="refresh" content="0;url=<c:url value='login'/>">
</c:if>

<!-- MemberUpdate(url 변경해야함) -->
<c:if test="${url eq 'updateMember'}">
	<meta charset="UTF-8" http-equiv="refresh" content="0;url=<c:url value='/'/>">
</c:if>

<!-- MemberDelete -->
<c:if test="${url eq 'deleteMember'}">
	<meta charset="UTF-8" http-equiv="refresh" content="0;url=<c:url value='/'/>">
</c:if>

<!-- LoginSuccess -->
<c:if test="${url eq 'loginSuccess'}">
	<meta charset="UTF-8" http-equiv="refresh" content="0;url=<c:url value='isMyPage'/>">
</c:if>

<!-- LoginGhost -->
<c:if test="${url eq 'loginGhost'}">
	<meta charset="UTF-8" http-equiv="refresh" content="0;url=<c:url value='/'/>">
</c:if>

<!-- MyPage(Ghost) -->
<c:if test="${url eq 'GhostMyPage'}">
	<meta charset="UTF-8" http-equiv="refresh" content="0;url=<c:url value='/'/>">
</c:if>

</body>
</html>