<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page import="java.util.List"%>
        <%@page import="user.UserDTO"%>
            <%@page contentType="text/html" pageEncoding="UTF-8"%>
                <!DOCTYPE html>
                <html>

                <head>
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                    <title>Admin Page</title>
                    <link rel="icon" href="images/OLuna_FULL.png" type="image/x-icon" />
                    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Anton+SC&display=swap">;
                    <style>
                        .header {
                            background-color: mediumaquamarine;
                            position: absolute;
                            top: 0;
                            left: 0;
                            width: 100%;
                            border-bottom: 2px solid;
                        }
                        
                        .header>a:hover {
                            color: red;
                        }
                        
                        .header>h1 {
                            margin-left: 10px;
                        }
                        
                        .header>a {
                            margin-right: 20px;
                            color: #8f00c7;
                        }
                        
                        .under {
                            margin-top: 50px;
                        }
                        
                        .form-search {
                            float: right;
                            margin-bottom: 15px;
                        }
                        
                        .content {
                            display: flex;
                            justify-content: space-around;
                            align-items: center;
                            margin-top: 140px;
                            margin-bottom: 30px;
                        }
                        
                        .content>div {
                            text-align: center;
                            width: 33.3%;
                            height: 120px
                        }
                        
                        .content>div>h3 {
                            padding: 20px 0;
                            background-color: #99d1cc;
                            margin-top: 0;
                            margin-bottom: 0;
                        }
                        
                        .content>div>h4 {
                            padding: 20px 0;
                            margin-top: 0;
                            margin-bottom: 0;
                            background-color: #ebf4f8;
                        }
                        
                        .div1 {
                            border-right: 1px solid;
                        }
                        
                        .div2 {
                            border-right: 1px solid;
                            border-left: 1px solid;
                        }
                        
                        .div3 {
                            border-left: 1px solid;
                        }
                        
                        .type>div {
                            width: 150px;
                            padding: 3px;
                        }
                        
                        .type>div>button {
                            width: 100%;
                            padding: 5px 5px;
                            color: blue;
                            background-color: #f3f3f3;
                        }
                        
                        .type>div>button>a {
                            text-decoration: none;
                        }
                        
                        .type>div>button:hover {
                            background-color: #6995b1;
                            cursor: pointer;
                        }
                        
                        .type>div>button:hover a {
                            color: crimson;
                        }
                        
                        .aTag {
                            margin-right: 15px;
                        }
                        
                        .aTag>a {
                            font-family: 'Anton SC';
                            text-decoration: none;
                        }
                        
                        .aTag>a:hover {
                            text-decoration: underline;
                            color: red;
                        }
                    </style>
                </head>

                <body>
                    <c:if test="${sessionScope.LOGIN_USER.roleID eq 'US' || sessionScope.LOGIN_USER == null}">
                        <c:redirect url="login.jsp" />
                    </c:if>
                    <div class="header" style="display:flex; justify-content: space-between; align-items: center">
                        <h1 style="font-family: 'Anton SC';">welcome back,
                            <span style="color: red">${sessionScope.LOGIN_USER.name}</span></h1>
                        <c:url var="logoutLink" value="MainController">
                            <c:param name="action" value="Logout"></c:param>
                        </c:url>
                        <div class="aTag">
                            <a class="logout" href="${logoutLink}">logout</a>
                        </div>
                    </div>
                    <c:if test="${requestScope.USER_TOTAL == null}">
                        <c:redirect url="/SearchController"></c:redirect>
                    </c:if>
                    <div class="content">
                        <div class="div1">
                            <h3>Revenue</h3>
                            <h4>${requestScope.MONEY_TOTAL}$</h4>
                        </div>
                        <div class="div2">
                            <h3>User Total</h3>
                            <h4>${requestScope.USER_TOTAL}</h4>
                        </div>
                        <div class="div3">
                            <h3>Order Total</h3>
                            <h4>${requestScope.ORDER_TOTAL}</h4>
                        </div>
                    </div>
                    <div class="type" style="display:flex; justify-content: space-around; align-items: center">
                        <div>
                            <c:url var="searchUser" value="MainController">
                                <c:param name="action" value="Search"></c:param>
                                <c:param name="searchType" value="searchUser"></c:param>
                                <c:param name="search" value=""></c:param>
                            </c:url>
                            <button onclick="activateLinkUser()"><a href="${searchUser}">User Management</a></button>
                        </div>
                        <div>
                            <c:url var="searchProduct" value="MainController">
                                <c:param name="action" value="Search"></c:param>
                                <c:param name="searchType" value="searchProduct"></c:param>
                                <c:param name="search" value=""></c:param>
                            </c:url>
                            <button onclick="activateLinkProduct()"><a href="${searchProduct}">Product Management</a></button>
                        </div>
                    </div>
                    <script>
                        function activateLinkProduct() {
                            window.location.href = "${searchProduct}";
                        }

                        function activateLinkUser() {
                            window.location.href = "${searchUser}";
                        }
                    </script>
                </body>

                </html>