<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
        <link rel="icon" href="images/OLuna_FULL.png" type="image/x-icon" />
        <style>
            body {
                margin: 0;
                padding: 0;
                background: url(images/pexels-photo-5864245.jpg) no-repeat center/ cover;
            }

            .box {
                margin: 100px;
                background-color: rgba(0, 0, 0, 0.1);
                box-shadow: 0px 0px 20px rgba(217,217, 217, 0.8);
            }

            .body label {
                color: #FFF;
            }

            .head {
                margin: 20px;
            }

            .body {
                margin: 30px;
            }

            .body>input {
                margin: 15px 15px 15px 15px;
                padding: 10px;
                width: 350px;
            }

            a {
                color: aqua;
            }

            a:hover {
                color: red;
                cursor: pointer;
            }
        </style>
    </head>

    <body>
    <c:if test="${sessionScope.LOGIN_USER.roleID eq 'AD'}">
        <c:redirect url="login.jsp"></c:redirect>
    </c:if>
    <div>
        <div style="display: flex; justify-content: center; align-items: center">
            <div class="box">
                <div class="head">
                    <h1 style="text-align: center; color: #ccc">Change Password</h1>
                </div>
                <form action="MainController" method="POST">

                    <div class="body" style="display: flex; flex-direction: column; align-items: center; justify-content: space-around">
                        <label for="oldPass">Old password</label><input type="password" name="oldPass" id="oldPass" required="" placeholder="Old password..." />
                        <label for="newPass">New password</label><input type="password" name="newPass" id="newPass" required="" placeholder="New password..." />
                        <label for="confirmPass">Confirm password</label><input type="password" name="confirmPass" id="confirmPass" required="" placeholder="Confirm password..." />
                    </div>

                    <div style="display: flex; justify-content: space-between; align-items: center">
                        <c:url var="homeLink" value="MainController">
                            <c:param name="action" value="home"></c:param>
                        </c:url>
                        <a type="submit" value="${homeLink}">
                            <-Back to homepage</a>
                        <lable style="color: crimson">${requestScope.INFO}</lable> 
                        <input style="width: 100px; margin:20px 45px 20px 45px; padding: 10px" type="submit" name="action" value="Change">
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
