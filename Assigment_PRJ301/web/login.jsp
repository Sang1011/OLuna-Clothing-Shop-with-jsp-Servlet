<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Login Page</title>
            <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
            <script src="https://www.google.com/recaptcha/api.js" async defer></script>
            <link rel="icon" href="images/OLuna_FULL.png" type="image/x-icon" />
            <style>
                .bg-light {
                    background: url(images/pexels-photo-5864245.jpg) no-repeat center/ cover;
                    display: flex;
                    justify-content: center;
                    /* Căn giữa theo chiều ngang */
                    align-items: center;
                    min-height: 100vh;
                }
                
                .white {
                    color: white;
                }
                
                .box-my {
                    box-shadow: 0px 0px 20px rgba(217, 217, 217, 0.8);
                }
            </style>
        </head>

        <body>
            <!-- Login 7 - Bootstrap Brain Component -->
            <section class="bg-light p-3 p-md-4 p-xl-5">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-12 col-md-9 col-lg-7 col-xl-6 col-xxl-5">
                            <div class="box-my rounded-4">
                                <div class="card-body p-3 p-md-4 p-xl-5">
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="mb-5">
                                                <div class="text-center mb-4">
                                                </div>
                                                <h3 class="text-center white">Login</h3>
                                            </div>
                                        </div>
                                    </div>
                                    <c:set var="cookie" value="${pageContext.request.cookies}" />
                                    <form action="MainController" method="POST" id="form">
                                        <div class="row gy-3 overflow-hidden">
                                            <div class="col-12">
                                                <div class="form-floating mb-3">
                                                    <input type="text" class="form-control" name="userID" id="id" value="${cookie.CookiesUserID.value}" placeholder="userID" required>
                                                    <label for="id" class="form-label">Id</label>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="form-floating mb-3">
                                                    <input type="password" class="form-control" name="password" id="password" value="${cookie.CookiesPassword.value}" placeholder="Password" required>
                                                    <label for="password" class="form-label">Password</label>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" ${(cookie.CookiesRemember!=null? 'checked': '')} value="ON" name="remember" id="remember_me">
                                                    <div style="display: flex; justify-content: space-between">
                                                        <label class="form-check-label white" for="remember_me">
                                                        Keep me logged in
                                                     </label><span style="color: crimson">${requestScope.ERROR}</span>
                                                    </div>
                                                </div>
                                                <br>
                                                <div class="d-grid" style="position: relative">
                                                    <iframe id="recaptchaFrame" src="recaptcha.jsp" frameborder="0" style="border: none; width: 100%; position: absolute;"></iframe>
                                                    <br><br><br><br><br><br>
                                                    <button class="btn bsb-btn-xl btn-primary" id="submitButton" type="submit" name="action" value="Login" style="top: 30px">Log in now</button>
                                                </div>

                                            </div>
                                    </form>

                                    <div class="row">
                                        <div class="col-12">
                                            <hr class="mt-5 mb-4 border-secondary-subtle">
                                            <div class="d-flex gap-2 gap-md-4 flex-column flex-md-row justify-content-md-end">
                                                <a href="MainController?action=Register_Page" class="white text-decoration-none">Create new account</a>
                                                <a href="MainController?action=ForgotPassword_Page" class="white text-decoration-none">Forgot password</a>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-12">
                                            <br>
                                            <span class="white">Continue with</span>
                                            <div style="margin-left: 50%">
                                                <a href="https://accounts.google.com/o/oauth2/auth?scope=profile email&redirect_uri=http://localhost:8080/Assigment_PRJ301/LoginGoogleHandler&response_type=code&client_id=Your-Client-ID&approval_prompt=force" class="btn btn-lg btn-outline-danger p-3 lh-1">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" fill="currentColor" class="bi bi-google" viewBox="0 0 16 16">
                                                <path d="M15.545 6.558a9.42 9.42 0 0 1 .139 1.626c0 2.434-.87 4.492-2.384 5.885h.002C11.978 15.292 10.158 16 8 16A8 8 0 1 1 8 0a7.689 7.689 0 0 1 5.352 2.082l-2.284 2.284A4.347 4.347 0 0 0 8 3.166c-2.087 0-3.86 1.408-4.492 3.304a4.792 4.792 0 0 0 0 3.063h.003c.635 1.893 2.405 3.301 4.492 3.301 1.078 0 2.004-.276 2.722-.764h-.003a3.702 3.702 0 0 0 1.599-2.431H8v-3.08h7.545z" />
                                                </svg>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
            </section>

            <script>
                let recaptchaCompleted = false; // Initialize the flag

                window.addEventListener('message', function(event) {
                    if (event.data && event.data.type === 'recaptcha_success') {
                        // reCAPTCHA has been completed successfully
                        recaptchaCompleted = true;
                        // Enable the submit button
                        document.getElementById('submitButton').disabled = false;
                    }
                });

                // Submit form function
                document.getElementById('submitButton').addEventListener('click', function(event) {
                    const response = document.getElementById('recaptchaFrame').contentWindow.grecaptcha.getResponse();
                    if (!response) {
                        // reCAPTCHA not completed, display error
                        alert('Please complete the reCAPTCHA!');
                        // Prevent form submission
                        event.preventDefault();
                    } else {
                        // reCAPTCHA has been completed successfully, submit the form
                        document.getElementById('recaptchaFrame').style.zIndex = '-1';
                        document.getElementById('submitButton').style.zIndex = '9999';
                        // Reset the flag
                        recaptchaCompleted = false;
                    }
                });
            </script>
        </body>

        </html>