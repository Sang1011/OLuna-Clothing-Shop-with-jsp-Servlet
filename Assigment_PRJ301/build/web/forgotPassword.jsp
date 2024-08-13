<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
        <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://unpkg.com/bs-brain@2.0.4/components/password-resets/password-reset-7/assets/css/password-reset-7.css">
        <link rel="stylesheet" href="css/forgotpasswordcustom.css">
        <style>
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
            .red{
                color: red;
            }
            .black{
                color: black;
            }
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER.roleID eq 'AD'}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
        <!-- Password Reset 7 - Bootstrap Brain Component -->
        <section class="bg-light p-3 p-md-4 p-xl-5">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12 col-md-9 col-lg-7 col-xl-6 col-xxl-5">
                        <div class="rounded-4 box-my">
                            <div class="card-body p-3 p-md-4 p-xl-5">
                                <div class="row">
                                    <div class="col-12">
                                        <div class="mb-5">
                                            <h2 class="h4 text-center white">Forgot Password</h2>
                                            <h3 class="fs-6 fw-normal text-center m-0 white">Provide the email address associated with your account to recover your password.</h3>
                                        </div>
                                    </div>
                                </div>
                                <form action="MainController">
                                    <div class="row gy-3 overflow-hidden">
                                        <div class="col-12">
                                            <div class="form-floating mb-3">
                                                <input type="email" class="form-control" name="email" id="email" placeholder="name@example.com" required>
                                                <label for="email" class="form-label black">Email</label>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="d-grid">
                                                <button class="btn bsb-btn-xl btn-primary" type="submit" name="action" value="ForgotPassword">Submit</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <div class="row">
                                    <div class="col-12">
                                        <hr class="mt-5 mb-4 border-secondary-subtle">
                                        <span clas="red">${requestScope.INFO}</span>
                                        <div class="d-flex gap-2 gap-md-4 flex-column flex-md-row justify-content-md-end">
                                            <a href="MainController?action=Login_Page" class="white text-decoration-none">Login</a>
                                            <a href="MainController?action=Register_Page" class="white text-decoration-none">Register</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
