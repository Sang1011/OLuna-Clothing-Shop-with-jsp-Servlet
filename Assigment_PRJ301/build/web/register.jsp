<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <meta name="description" content="Colorlib Templates">
        <meta name="author" content="Colorlib">
        <meta name="keywords" content="Colorlib Templates">

        <!-- Icons font CSS-->
        <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
        <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
        <!-- Font special for pages-->
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

        <!-- Vendor CSS-->
        <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
        <link href="vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

        <!-- Main CSS-->
        <link href="css/main.css" rel="stylesheet" media="all">
        <style>
            #aTag {
                padding: 10px;
                border-radius: 10px;
                border: 1px solid #dc3545;
            }
            #aTag:hover{
                background-color: #dc3545;
                border: 1px solid #dc3545;

            }
            #aTag:hover svg path {
                fill: white; 
            }
            .bg-light {
                background: url(images/pexels-photo-5864245.jpg) no-repeat center/ cover;
                display: flex;
                justify-content: center; /* Căn giữa theo chiều ngang */
                align-items: center;
                min-height: 100vh;
            }

            .white {
                color: white;
            }
            .box-my{
                border:none;
                background: none;
                box-shadow: 0px 0px 20px rgba(217,217,217,0.8);
            }

        </style>
    </head>
    <body>
        <div class="page-wrapper bg-light p-t-130 p-b-100 font-poppins">
            <div class="wrapper wrapper--w680">
                <div class="card card-4 box-my">
                    <div class="card-body">
                        <c:if test="${sessionScope.LOGIN_USER.roleID eq 'AD'}">
                            <h2 class="title white" style="text-align: center">Add User</h2>
                        </c:if>
                        <c:if test="${sessionScope.LOGIN_USER.roleID ne 'AD' || sessionScope.LOGIN_USER == null}">
                            <h2 class="title white" style="text-align: center">Registration Form</h2>
                        </c:if>
                        <form action="MainController" method="POST">
                            <div class="row row-space">
                                <div class="col-2">
                                    <div class="input-group">
                                        <label class="label white">Id</label>
                                        <input class="input--style-4" type="text" name="userID" required="">
                                        <span style="color: #fff300">${requestScope.USER_ERROR.userIDError}</span>
                                    </div>
                                </div>
                                <div class="col-2">
                                    <div class="input-group">
                                        <label class="label white">Phone Number</label>
                                        <input class="input--style-4" type="tel" name="phone" maxlength="11" required="">
                                        <span style="color: #fff300">${requestScope.USER_ERROR.phoneError}</span>
                                    </div>
                                </div>
                            </div>
                            <div class="row row-space">
                                <div class="col-12">
                                    <div class="input-group">
                                        <label class="label white">Name</label>
                                        <input class="input--style-4" type="text" name="name" required="">
                                        <span style="color: #fff300">${requestScope.USER_ERROR.nameError}</span>
                                    </div>
                                </div>
                            </div>
                            <div class="row row-space">
                                <div class="col-12">
                                    <div class="input-group">
                                        <label class="label white">Email</label>
                                        <input class="input--style-4" type="email" name="email" required="">
                                        <span style="color: #fff300">${requestScope.USER_ERROR.emailError}</span>
                                    </div>
                                </div>
                            </div>
                            <div class="row row-space">
                                <div class="col-2">
                                    <div class="input-group">
                                        <label class="label white">Password</label>
                                        <input class="input--style-4" type="password" name="password" required="">
                                    </div>
                                </div>
                                <div class="col-2">
                                    <div class="input-group">
                                        <label class="label white">Confirm Password</label>
                                        <input class="input--style-4" type="password" name="confirmPassword" required="">
                                        <span style="color: #fff300">${requestScope.USER_ERROR.confirmError}</span>
                                    </div>
                                </div>
                            </div>
                            <div class="row row-space">
                                <div class="col-12">
                                    <div class="input-group">
                                        <label class="label white">Address</label>
                                        <input class="input--style-4" type="text" name="address" required="">
                                        <span style="color: #fff300">${requestScope.USER_ERROR.addressError}</span>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${sessionScope.LOGIN_USER.roleID eq 'AD'}">
                                <div class="row row-space">
                                    <div class="col-12">
                                        <div class="input-group">
                                            <label class="label white">Role ID (typing 'US' or 'AD')</label>
                                            <input class="input--style-4" type="text" maxlength="2" name="roleID" required="">
                                            <span style="color: #fff300">${requestScope.USER_ERROR.roleIDError}</span>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                            <div class="p-t-15" style="display: flex; align-items: center; justify-content: space-between">
                                <button class="btn btn--radius-2 btn--blue" type="submit" name="action" value="Register">Submit</button>
                                <input type="hidden" name="search" value="${param.search}"/>
                                <input type="hidden" name="searchType" value="${param.searchType}"/>
                                <c:if test="${sessionScope.LOGIN_USER.roleID eq 'AD'}">
                                    <span style="color: #fff300">${requestScope.INFO}</span>
                                    <a style="color: rgb(0,229,255);" href="MainController?action=Search&searchType=searchUser&search=${param.search}"><-Back</a>
                                </c:if>
                                <c:if test="${sessionScope.LOGIN_USER.roleID ne 'AD' || sessionScope.LOGIN_USER == null}">
                                    <span class="white">Already have an account? <a href="MainController?action=Login_Page" style="text-decoration: none; color: rgb(0,229,255)">Sign in</a></span>
                                </c:if>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Jquery JS-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <!-- Vendor JS-->
        <script src="vendor/select2/select2.min.js"></script>
        <script src="vendor/datepicker/moment.min.js"></script>
        <script src="vendor/datepicker/daterangepicker.js"></script>

        <!-- Main JS-->
        <script src="js/global.js"></script>



    </body>
</html>
