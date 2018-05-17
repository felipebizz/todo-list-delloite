<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create an account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>

<body>
<div class="container">
    <c:if test="${taskForm.idUser == 0}">
    <form:form method="POST" modelAttribute="taskForm" action="${contextPath}/save" class="form-signin">
    <h2 class="form-signin-heading">New Task</h2>

    <div class="form-group ${error != null ? 'has-error' : ''}">
        <spring:bind path="description">
        <form:input type="text" path="description" class="form-control" placeholder="Description"
                    autofocus="true"></form:input>
        <form:errors path="description"></form:errors>
    </div>
    </spring:bind>
    <button class="btn btn-lg btn-primary btn-block" type="submit">ADD</button>
</div>
</form:form>
</c:if>

<c:if test="${taskForm.idUser > 0}">
    <form:form method="PUT" modelAttribute="taskForm" action="${contextPath}/update" class="form-signin">
        <input type="hidden" name="id" value="${taskForm.id}"/>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <spring:bind path="description">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="description" class="form-control" placeholder="Description"
                                autofocus="true"></form:input>
                    <form:errors path="description"></form:errors>
                    <span>${error}</span>
                </div>
            </spring:bind>
            <div align="center"><span> complete Task ?</span></div>
            <spring:bind path="completed">
                <form:checkbox path="completed" class="form-control"></form:checkbox>
            </spring:bind>
        </div>

        <button class="btn btn-lg btn-primary btn-block" type="submit">UPDATE</button>
    </form:form>
</c:if>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
