<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recaptcha Page</title>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER.roleID ne 'AD' || sessionScope.LOGIN_USER == null}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
        <div class="g-recaptcha" data-sitekey="6Lfbnv8pAAAAALP743Z57hgcKsIKoMLe0YorySZt" data-callback="recaptchaCallback"></div>
        
        <script>
            function recaptchaCallback() {
                window.parent.postMessage({type: 'recaptcha_success'}, '*');
            }
        </script>
    </body>
</html>
