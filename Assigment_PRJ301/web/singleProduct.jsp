
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>View Product Page</title>
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/stylesSingleProduct.css" rel="stylesheet" />
        <meta name="format-detection" content="telephone=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="author" content="">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <link rel="icon" href="images/OLuna_FULL.png" type="image/x-icon" />
        <link rel="stylesheet" type="text/css" href="css/normalize.css">
        <link rel="stylesheet" type="text/css" href="icomoon/icomoon.css">
        <link rel="stylesheet" type="text/css" media="all" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="css/vendor.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
        <style>
            .tagA:hover{
                color: #6995b1;
            }
            .info{
                color: #bbb;
                margin-top: 2px;
            }
            --''
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER.roleID eq 'AD'}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
        <div class="search-popup">
            <div class="search-popup-container">
                <form role="search" method="POST" class="search-form" action="MainController">
                    <input type="search" id="search-form" class="search-field" placeholder="Type and press enter" value="${param.search}" name="search" />
                    <span class="info">You can search name's product, category or color</span>
                    <input type="hidden" name="searchType" value="searchProduct"/>
                    <button type="submit" name="action" value="Search" class="search-submit"><a href="#"><i class="icon icon-search"></i></a></button>
                </form>
            </div>
        </div>
        <div class="preloader-wrapper">
            <div class="preloader">
            </div>
        </div>
        <header id="header">
            <div id="header-wrap">
                <nav class="secondary-nav border-bottom" style="background: #f5f5f5">
                    <div class="container">
                        <div class="row d-flex align-items-center">
                            <div class="col-md-6 header-contact">
                                <p>Let's talk! <strong>+84 999999999</strong>
                                </p>
                            </div>
                            <div class="col-md-6 col-sm-12 user-items">
                                <ul class="d-flex justify-content-end list-unstyled">
                                    <c:if test="${empty sessionScope.LOGIN_USER}">
                                        <li>
                                            <label class="popup">
                                                <input type="checkbox" style="display: none" onclick="redirect()"/>
                                                <div tabindex="0" class="burger" style="
                                                     width: 18px;
                                                     height: 18px;
                                                     background-color: none;">
                                                    <svg
                                                        viewBox="0 0 24 24"
                                                        fill="#8D8D8D"
                                                        height="18"
                                                        width="18"
                                                        xmlns="http://www.w3.org/2000/svg"
                                                        >
                                                    <path
                                                        d="M12 2c2.757 0 5 2.243 5 5.001 0 2.756-2.243 5-5 5s-5-2.244-5-5c0-2.758 2.243-5.001 5-5.001zm0-2c-3.866 0-7 3.134-7 7.001 0 3.865 3.134 7 7 7s7-3.135 7-7c0-3.867-3.134-7.001-7-7.001zm6.369 13.353c-.497.498-1.057.931-1.658 1.302 2.872 1.874 4.378 5.083 4.972 7.346h-19.387c.572-2.29 2.058-5.503 4.973-7.358-.603-.374-1.162-.811-1.658-1.312-4.258 3.072-5.611 8.506-5.611 10.669h24c0-2.142-1.44-7.557-5.631-10.647z"
                                                        ></path> 
                                                    </svg>
                                                </div>
                                            </label>
                                        </li>
                                    </c:if>
                                    <c:if test="${not empty sessionScope.LOGIN_USER}">
                                        <li>
                                            <label class="popup">
                                                <input type="checkbox" style="display: none"/>
                                                <div tabindex="0" class="burger" style="
                                                     width: 18px;
                                                     height: 18px;
                                                     background-color: none;">
                                                    <svg
                                                        viewBox="0 0 24 24"
                                                        fill="#8D8D8D"
                                                        height="18"
                                                        width="18"
                                                        xmlns="http://www.w3.org/2000/svg"
                                                        >
                                                    <path
                                                        d="M12 2c2.757 0 5 2.243 5 5.001 0 2.756-2.243 5-5 5s-5-2.244-5-5c0-2.758 2.243-5.001 5-5.001zm0-2c-3.866 0-7 3.134-7 7.001 0 3.865 3.134 7 7 7s7-3.135 7-7c0-3.867-3.134-7.001-7-7.001zm6.369 13.353c-.497.498-1.057.931-1.658 1.302 2.872 1.874 4.378 5.083 4.972 7.346h-19.387c.572-2.29 2.058-5.503 4.973-7.358-.603-.374-1.162-.811-1.658-1.312-4.258 3.072-5.611 8.506-5.611 10.669h24c0-2.142-1.44-7.557-5.631-10.647z"
                                                        ></path> 
                                                    </svg>
                                                </div>
                                                <nav class="popup-window"style="padding-top: 5px;padding-bottom: 5px;">
                                                    <ul>
                                                        <form action="MainController" method="POST" style="margin-bottom: 0px;">
                                                            <li style="padding-right: 0px;">
                                                                <button type="submit" name="action" value="Profile_Page" style="display: float;align-items: center;justify-content: center;height: 22.75px;">
                                                                    <span style="text-align: center">Account</span>
                                                                </button>
                                                            </li>
                                                            <li>
                                                                <button type="submit" name="action" value="Logout" style="display: float;align-items: center;justify-content: center;height: 17.75px;">
                                                                    <span style="text-align: center">Log out</span>
                                                                </button>
                                                            </li>
                                                        </form>
                                                    </ul>
                                                </nav>
                                            </label>
                                        </li>
                                    </c:if>
                                    <li class="cart">
                                        <c:url var="viewCartLink" value="MainController">
                                            <c:param name="action" value="View"></c:param>
                                        </c:url>
                                        <a href="${viewCartLink}">
                                            <i class="icon icon-shopping-cart"></i>
                                        </a>
                                    </li>
                                    <li class="seaheader-attribute user-items search-item pe-3">
                                        <a href="#" class="search-button">
                                            <i class="icon icon-search"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </nav>
            </div>
        </header>
        <nav class="primary-nav padding-small" style="border-bottom: 1px solid">
            <div class="container">
                <div class="row d-flex align-items-center">
                    <div class="col-lg-2 col-md-2">
                        <div class="main-logo">
                            <a href="MainController">
                                <img src="images/OLuna.png" alt="logo">
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-10 col-md-10">
                        <div class="navbar">
                            <div id="main-nav" class="stellarnav d-flex justify-content-end right">
                                <ul class="menu-list">
                                    <li class="menu-item has-sub">
                                        <a href="#" class="item-anchor d-flex align-item-center" data-effect="Shop">Shop<i class="icon icon-chevron-down"></i></a>
                                        <ul class="submenu" style="width: 200px;">
                                            <li><a href="MainController?action=Search&searchType=searchProduct&search=${param.all}" class="item-anchor">All Products</a></li>
                                            <li><a href="MainController?action=Search&searchType=searchProduct&search=bestSelling" class="item-anchor">Best Selling Products</span></a></li>
                                            <li><a href="MainController?action=Search&searchType=searchProduct&search=newArrivals" class="item-anchor">New Arrivals</span></a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
        <!-- Product section-->
        <c:set var="product" value="${requestScope.GET_PRODUCT}"></c:set>
            <section class="py-5">
                <div class="container px-4 px-lg-5 my-5">
                    <div class="row gx-4 gx-lg-5 align-items-center">
                        <div class="col-md-6" style="height:700px">
                            <img class="card-img-top mb-5 mb-md-0" src="${product.images}" style="max-height: 700px; width: 600px" alt="${product.productID}" />
                    </div>
                    <div class="col-md-6">
                        <div class="small mb-1">${product.productID}</div>
                        <h1 class="display-5 fw-bolder">${product.name}</h1>
                        <div class="fs-5 mb-5">
                            <span>${product.price}</span>$
                        </div>
                        <h5>${product.collection}</h5>
                        <h5>${product.brand}</h5>
                        <form action="MainController" method="POST">
                            <input class="form-control text-center me-3" id="inputQuantity" type="num" value="1" name="quantityBuy" style="max-width: 3rem" onkeydown="return event.key !== 'Enter';"/>
                            <button class="btn btn-outline-dark flex-shrink-0" type="submit" name="action" value="Add">
                                <i class="bi-cart-fill me-1"></i>
                                Add to cart
                                <input type="hidden" name="productID" value="${product.productID}">
                            </button>
                            <label style="color: #57b846">${requestScope.MESSAGE}</label>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Related items section-->
    <section class="py-5 bg-light">
        <div class="container px-4 px-lg-5 mt-5">
            <h2 class="fw-bolder mb-4">Related products</h2>
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                <c:forEach var="productRandom" items="${requestScope.LIST_RELATED}">
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <div class="image" style="height: 350px; width: 265.500px; padding: 0 5px 0 5px; display:flex; justify-content: center; align-items: center; border: 2px solid #f5f5f5; background: var(--light-color);
                                         border: 1px solid var(--grey-color);
                                         border-radius: 6px;">
                            <img class="card-img-top" style="max-height: 345px;" src="${productRandom.images}" alt="${productRandom.productID}" />
                            </div>
                            <!-- Product details-->
                            <c:url var="viewProduct" value="MainController">
                                <c:param name="action" value="View_Product"></c:param>
                                <c:param name="productID" value="${productRandom.productID}"></c:param>
                            </c:url>
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder"><a class="tagA" href="${viewProduct}">${productRandom.name}</a></h5>
                                    <h6>${productRandom.collection}</h6>
                                    <h6>${productRandom.brand}</h6>
                                    <!-- Product price-->
                                    <span>${productRandom.price}</span>$
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="js/scripts.js"></script>
    <script src="js/jquery-1.11.0.min.js"></script>
    <script src="js/plugins.js"></script>
    <script src="js/script.js"></script>
    <script>
                                function redirect() {
                                    window.location.href = 'login.jsp';
                                }
    </script>
</body>
</html>
