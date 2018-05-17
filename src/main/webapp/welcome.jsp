<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">

        <input type="hidden" name="id" value="${taskForm.id}"/>

        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h4>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h4>

        <form id="newTask" method="GET" action="${contextPath}/new" class="form-signin">
            <div align="center">
                <button class="btn btn-group-lg btn-primary btn-success" type="submit">new task</button>
            </div>
        </form>
        <br>

    </c:if>

    <c:if test="${todoList.size() > 0}">
        <div align="center">
            <table id="table" class="form-signin">
                <thead>
                <tr class="bg-primary">
                    <th align="center">Completed</th>
                    <th align="center">Description</th>
                    <th align="center">Last Update</th>
                    <th align="center"></th>
                    <th align="center"></th>
                </tr>
                </thead>
                <c:forEach items="${todoList}" var="todo">
                <tr>
                    <th data-field="completed" width="200px">
                        <input type="checkbox" name="completed" id="completed" disabled="disabled"
                                <c:if test="${todo.completed == 'true'}"> checked='checked' </c:if>
                        />
                    </th>
                    <th data-field="description" width="400px">${todo.description}</th>

                    <th data-field="updatedAt" width="200px">${todo.updatedAt}</th>

                    <th data-field="edit">
                        <form id="edit" method="POST" action="${contextPath}/edit/${todo.id}">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button class="btn btn-group-lg btn-primary btn-block" type="submit">edit</button>
                        </form>
                    </th>
                    <th data-field="remove">
                        <form id="remove" method="POST" action="${contextPath}/remove/${todo.id}" class="form-signin">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                            <button class="btn btn-group-lg btn-primary btn-danger" type="submit">delete</button>
                        </form>
                    </th>
                </tr>
                </c:forEach>
        </div>
        </table>
    </c:if>
</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
