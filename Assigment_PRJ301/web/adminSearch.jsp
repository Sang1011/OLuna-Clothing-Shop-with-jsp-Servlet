<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Anton+SC&display=swap">;
        <title>Admin Page</title>
        <style>
            .header{
                background-color: mediumaquamarine;
                position: absolute;
                top:0;
                left:0;
                width: 100%;
                border-bottom: 2px solid;
            }
            .header>h1{
                margin-left: 10px;
            }

            .aTag{
                margin-right: 15px;
            }

            .aTag>a{
                font-family: 'Anton SC';
                text-decoration: none;
            }

            .aTag>a:hover{
                text-decoration: underline;
                color: red;
            }

            .form-search{
                float:right;
                margin-bottom: 15px;
            }
            .tag>a{
                border: 1px solid black;
                float:left;
                padding: 5px 10px;
                background-color: #6995b1;   
                color: #FFF;
            }
            .tag>a{
                text-decoration: none;
            }
            .tag>a:hover{
                color: red;
            }

            .under{
                margin-top: 150px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid #000;
                padding: 5px;
                text-align: center;
                vertical-align: middle;
            }
            th {
                background-color: #138a64;
                color: #FFF;
            }
            .product-id {
                width: 5%;
            }
            .image {
                width: 15%;
            }

            .name {
                width: 20%;
            }
            .price, .quantity {
                width: 8%;
            }
            .collection, .brand {
                width: 25%;
            }
            .update {
                width: 5%;
            }
            img {
                max-width: 100%;
                height: auto;
            }
            table input[type="text"], input[type="number"] {
                width: 100%;
                box-sizing: border-box;
            }

            .user-id {
                width: 5%;
            }
            .full-name {
                width: 10%;
            }
            .role-id, .password {
                width: 2%;
            }
            .address{
                width: 20%;
            }

            .email {
                width: 10%;
            }
            .phone {
                width: 8%;
            }

            .status{
                width: 0.5%;
            }
            .delete{
                width: 3%;
            }
            .no{
                width: 2%;
            }

            table a {
                color: blue;
                text-decoration: none;
            }
            table a:hover {
                text-decoration: underline;
            }

            .changeColor{
                background-color: #e6f0ed;
            }
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER.roleID eq 'US' || empty sessionScope.LOGIN_USER}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>

        <div class="header" style="display:flex; justify-content: space-between; align-items: center">
            <h1 style="font-family: 'Anton SC';">welcome back, 
                <span style="color: red">${sessionScope.LOGIN_USER.name}</span></h1>
                <c:url var="logoutLink" value="MainController">
                    <c:param name="action" value="Logout"></c:param>
                </c:url>
            <div class="aTag">
                <a class="back" style="margin-right: 10px;" href="admin.jsp"><- Back</a>
                <a class="logout" href="${logoutLink}">Logout</a>
            </div>
        </div>
        <div class="under">
            <div>
                <form class="form-search" action="MainController" method="POST">
                    Search: <input type="text" name="search" value="${param.search}"/>
                    <input type="submit" name="action" value="Search"/>
                    <input type="hidden" name="searchType" value="${requestScope.TYPE}"/>
                </form>
            </div>
            <c:if test="${requestScope.LIST != null}">
                <c:if test="${not empty requestScope.LIST && requestScope.TYPE eq 'searchUser'}">
                    <div class="tag">
                        <c:url var="registerLink" value="MainController">
                            <c:param name="action" value="Register_Page"></c:param>
                            <c:param name="search" value="${param.search}"></c:param>
                            <c:param name="searchType" value="${requestScope.TYPE}"></c:param>
                        </c:url>
                        <a href="${registerLink}">Add new user</a>
                        <span style="color: crimson; margin-left: 10px">${requestScope.INFO}</span>
                    </div>
                    <table border="1">
                        <thead>
                            <tr>
                                <th class="no">No</th>
                                <th class="user-id">User ID</th>
                                <th class="full-name">Full Name</th>
                                <th class="role-id">Role ID</th>
                                <th class="address">Address</th>
                                <th class="email">Email</th>
                                <th class="phone">Phone</th>
                                <th class="password">Password</th>
                                <th class="status">Status</th>
                                <th class="update">Update</th>
                                <th class="delete">Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" varStatus="counter" items="${requestScope.LIST}">
                            <form action="MainController" method="POST">
                                <tr class="${(counter.count % 2 == 0)?"changeColor":""}">
                                    <td>${counter.count}</td>
                                    <td>
                                        <input type="text" name="userID" value="${user.userID}"/>
                                    </td>
                                    <td>
                                        <input type="text" name="name" value="${user.name}" required=""/>
                                    </td>
                                    <td>
                                        <input type="text" name="roleID" value="${user.roleID}" required=""/>
                                    </td>
                                    <td>
                                        <input type="text" name="address" value="${user.address}" required=""/>
                                    </td>
                                    <td>
                                        <input type="text" name="email" value="${user.email}" required=""/>
                                    </td>
                                    <td>
                                        <input type="text" name="phone" value="${user.phone}" required=""/>
                                    </td>
                                    <td>
                                        ${user.password}
                                    </td>
                                    <td> 
                                        <input type="number" name="status" min="0" max="1" value="${user.status}" required="">
                                    </td>
                                    <td>
                                        <input type="submit" name="action" value="Update"/>
                                        <input type="hidden" name="search" value="${param.search}"/>
                                        <input type="hidden" name="updateType" value="updateUser"/>
                                        <input type="hidden" name="searchType" value="searchUser"/>
                                    </td>
                                    <td>
                                        <c:url var="deleteLink" value="MainController">
                                            <c:param name="action" value="Delete"></c:param>
                                            <c:param name="userID" value="${user.userID}"></c:param>
                                            <c:param name="search" value="${param.search}"></c:param>
                                            <c:param name="searchType" value="searchUser"></c:param>
                                        </c:url>
                                        <a href="${deleteLink}">Delete</a>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${not empty requestScope.LIST && requestScope.TYPE eq 'searchProduct'}">
                    <c:if test="${requestScope.LIST != null && not empty requestScope.LIST}">
                        <div class="tag">
                            <span style="color: crimson; margin-left: 10px">${requestScope.INFO}</span>
                        </div>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th class="no">No</th>
                                    <th class="product-id">Product ID</th>
                                    <th class="image">Image</th>
                                    <th class="name">Name</th>
                                    <th class="price">Price</th>
                                    <th class="quantity">Quantity</th>
                                    <th class="collection">Collection</th>
                                    <th class="brand">Brand</th>
                                    <th class="status">Status</th>
                                    <th class="update">Update</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="product" varStatus="counter" items="${requestScope.LIST}">
                                <form action="MainController" method="POST">
                                    <tr class="${(counter.count % 2 == 0)?"changeColor":""}">
                                        <td>${counter.count}</td>
                                        <td>
                                            <input type="text" name="productID" value="${product.productID}"/>
                                        </td>

                                        <td style="width: 250px; height: 250px">
                                            <img width="250px" height="250px" name="images" src="${product.images}" alt="img"/>
                                        </td>

                                        <td>
                                            <input type="text" name="productName" value="${product.name}"required=""/>
                                        </td>

                                        <td>
                                            <input type="text" name="price" value="${product.price}"required=""/>
                                        </td>
                                        <td>
                                            <input type="text" name="quantity" value="${product.quantity}" required=""/>
                                        </td>
                                        <td>
                                            <input type="text" name="collection" value="${product.collection}" required=""/>
                                        </td>
                                        <td>
                                            <input type="text" name="brand" value="${product.brand}" required=""/>
                                        </td>
                                        <td> 
                                            <input type="number" name="status" min="0" max="1" value="${product.status}" required="">
                                        </td>
                                        <td>
                                            <input type="submit" name="action" value="Update"/>
                                            <input type="hidden" name="search" value="${param.search}"/>
                                            <input type="hidden" name="updateType" value="updateProduct"/>
                                            <input type="hidden" name="searchType" value="searchProduct"/>
                                        </td>
                                    </tr>
                                </form>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </c:if>

            </c:if>
        </div>

    </body>
</html>
