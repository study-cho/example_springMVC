<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>>
<c:url var="root" value="/"/>

<script>
  alert("로그아웃 되었습니다")
  location.href='${root}main'
</script>