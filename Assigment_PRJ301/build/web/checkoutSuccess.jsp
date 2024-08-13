<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check out success</title>
        <style>
            body {
                margin: 0;
                padding: 0;
                background: url(images/pexels-photo-5864245.jpg) no-repeat center center fixed;
                background-size: cover;
            }

            .verify-container {
                width: 700px;
                height: 450px;
                background-color: #ffffff;
                border: 2px solid black;
                position: relative;
                top: 150px;
                left: 50%;
                transform: translateX(-50%);
                box-shadow: 10px 10px 7px rgba(0, 0, 0, 0.8);
            }

            .mark {
                width: 100%;
                height: 50%;
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .mark img {
                width: 50%;
                height: 80%;
            }

            .verify-container h2, h1, p {
                text-align: center;
            }

            .verify-container p {
                color: orange;
            }

            .home {
                text-align: center;
                margin-top: 20px;
                margin-bottom: 20px;
            }

            .homepage {
                padding: 10px 30px;
                width: 250px;
                border-radius: 5px;
                background-color: orange;
                color: #FFF;
                border: none;
                text-align: center;
                text-decoration: none;
            }

            .homepage:hover {
                color: black;
                background-color: darkseagreen;
            }
        </style>
    </head>

    <body>
        <c:if test="${sessionScope.LOGIN_USER.roleID eq 'AD'}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
        <c:if test="${sessionScope.LOGIN_USER == null}">
            <c:redirect url="homepage.jsp"></c:redirect>
        </c:if>
        <div class="verify-container">
            <div class="mark">
                <img src="https://t3.ftcdn.net/jpg/05/66/60/32/360_F_566603211_eQVWq7TQfzJ1osI8AmwvifZ4WVFOXeRn.jpg" alt="checkMark">
            </div>
            <h1>Paid successfully!</h1>
            <h2>Thank you for ordering!</h2>
            <p>Your bill has been sent to your email!</p>
            <c:url var="homeLink" value="MainController">
                <c:param name="action" value="home"></c:param>
            </c:url>
            <div class="home">
                <a class="homepage" href="${homeLink}">Back to homepage</a>
            </div>
        </div>
    </body>
</html>
