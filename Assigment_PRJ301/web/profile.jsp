<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Page</title>
        <link href="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
        <link rel="icon" href="images/OLuna_FULL.png" type="image/x-icon" />
        <style type="text/css">
            body{margin-top:20px;}
            .avatar{
                width:200px;
                height:200px;
            }
            div>img{
                border-radius: 50%;
                height:200px;
                width: 200px;
            }
        </style>
    </head>
    <body>
        <c:if test="${empty sessionScope.LOGIN_USER || sessionScope.LOGIN_USER.roleID eq 'AD'}">
            <c:redirect>login.jsp</c:redirect>
        </c:if>
        <div class="container bootstrap snippets bootdey">
            <h1 class="text-primary">Edit Profile</h1>
            <hr>
            <div class="row">

                <div class="col-md-3">
                    <img src="https://thumbs.dreamstime.com/b/default-avatar-profile-icon-vector-social-media-user-image-182145777.jpg">
                </div>

                <div class="col-md-9 personal-info">
                    <form action="MainController" class="form-horizontal" role="form" method="POST">
                        <c:if test="${!sessionScope.LOGIN_USER.userID.contains('G')}">
                            <div style="display:flex; justify-content: space-between">
                                <h3>Personal info</h3> <span><a href="MainController?action=ChangePasssword_Page">Change Password</a></span>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">ID:</label>
                                <div class="col-lg-8">
                                    <input class="form-control" type="text" name="userID" value="${sessionScope.LOGIN_USER.userID}" readonly="">
                                </div>
                            </div>
                        </c:if>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Username:</label>
                            <div class="col-lg-8">
                                <input class="form-control" type="text" name="name" value="${sessionScope.LOGIN_USER.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Email:</label>
                            <div class="col-lg-8">
                                <input class="form-control" type="text" name="email" value="${sessionScope.LOGIN_USER.email}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Address:</label>
                            <div class="col-lg-8">
                                <input class="form-control" type="text" name="address" value="${sessionScope.LOGIN_USER.address}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Phone:</label>
                            <div class="col-lg-8">
                                <input class="form-control" type="tel" name="phone" maxlength="11" value="${sessionScope.LOGIN_USER.phone}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Role ID:</label>
                            <div class="col-lg-8">
                                <input class="form-control" type="text" name="roleID" value="US" readonly="">
                            </div>
                        </div>
                        <div style="display: flex; justify-content: space-around">
                            <a style="margin-top: 16px; margin-right: 30px" href="homepage.jsp"><- Back to homepage</a>
                            <span style="margin-top: 16px; margin-right: 30px">${requestScope.INFO}</span>
                            <input type="submit" name="action" value="Update" style="padding: 6px 16px; background-color: greenyellow; border: 1px solid; margin: 10px 0; border-radius:50%">
                            <input type="hidden" name="updateType" value="updateUser">
                            <input type="hidden" name="roleUpdate" value="user">
                            <c:if test="${sessionScope.LOGIN_USER.userID.contains('G')}">
                                <input type="hidden" name="userID" value="${sessionScope.LOGIN_USER.userID}"/>
                            </c:if>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <hr>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        <script type="text/javascript">

        </script>
    </body>
</html>
