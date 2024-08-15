<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>O'Luna Clothing</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
            #footer {
                padding-top: 30px;
                margin-top: 50px;
                background-color: #eeeeee;
                color: dimgray;
            }
            .About-content>p{
                margin: 20px;
                color: #357ebd;
            }
            .About-content{
                background-color: #ddd
            }
            .AboutContent{
                margin-bottom: 20px;
            }
            .info{
                color: #bbb;
                margin-top: 2px;
            }
            --'''
        </style>
        <!-- script
        ================================================== -->
        <script src="js/modernizr.js"></script>

    </head>

    <body>
        <c:if test="${sessionScope.LOGIN_USER.roleID eq 'AD'}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
        
        <c:if test="${empty requestScope.LIST_RANDOM || requestScope.LIST_RANDOM.size() <= 0}">
            <c:redirect url="/HomeController"></c:redirect>
        </c:if>
        
        
        <div class="preloader-wrapper">
            <div class="preloader">
            </div>
        </div>

        <div class="search-popup">
            <div class="search-popup-container">
                <form role="search" method="POST" class="search-form" action="MainController">
                    <input type="search" id="search-form" class="search-field" placeholder="Type and press enter" value="${param.search}" name="search" />
                    <span class="info">You can search name's product, category or color</span>
                    <input type="hidden" name="searchType" value="searchProduct"/>
                    <button type="submit" name="action" value="Search" class="search-submit"></button>
                </form>
            </div>
        </div>
        <header id="header">
            <div id="header-wrap">
                <nav class="secondary-nav border-bottom" style="background: #f5f5f5">
                    <div class="container">
                        <div class="row d-flex align-items-center">
                            <div class="col-md-6 header-contact">
                                <p>Let's talk! <strong>+84999999999</strong>
                                </p>
                            </div>
                            <div class="col-md-6 col-sm-12 user-items">
                                <ul class="d-flex justify-content-end list-unstyled">
                                    <c:if test="${empty sessionScope.LOGIN_USER}">
                                        <li style="padding-right: 7px;">
                                            <label class="popup">
                                                <input type="checkbox" style="display: none" onclick="redirect()"/>
                                                <div tabindex="0" class="burger" style="
                                                     width: 22px;
                                                     height: 18px;
                                                     background-color: none;">
                                                    <img src="images/png-transparent-computer-icons-login-login-button-miscellaneous-angle-text.png" style="height: 18px;width: 22px;"/>
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

                <nav class="primary-nav padding-small">
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
                                            <li><a href="#AboutContent" class="item-anchor" data-effect="About">About</a></li>
                                            <li class="menu-item has-sub">
                                                <a href="#" class="item-anchor d-flex align-item-center" data-effect="Shop">Shop<i class="icon icon-chevron-down"></i></a>
                                                <ul class="submenu" style="width: 200px;">
                                                    <li><a href="MainController?action=Search&searchType=searchProduct&search=${param.all}" class="item-anchor">All Products</a></li>
                                                    <li><a href="MainController?action=Search&searchType=searchProduct&search=bestSelling" class="item-anchor">Best Selling Products</span></a></li>
                                                    <li><a href="MainController?action=Search&searchType=searchProduct&search=newArrivals" class="item-anchor">New Arrivals</span></a></li>
                                                </ul>
                                            </li>
                                            <li><a href="MainController?action=Contact_Page" class="item-anchor" data-effect="Contact">Contact</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </nav>
            </div>
        </header>


        <section id="billboard" class="overflow-hidden">

            <button class="button-prev">
                <i class="icon icon-chevron-left"></i>
            </button>
            <button class="button-next">
                <i class="icon icon-chevron-right"></i>
            </button>
            <div class="swiper main-swiper">
                <div class="swiper-wrapper">
                    <div class="swiper-slide" style="background-image: url('images/banner1.jpg');background-repeat: no-repeat;background-size: cover;background-position: center;">
                        <div class="banner-content">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-6">
                                        <h2 class="banner-title">Line Up Collection</h2>
                                        <p>CITY CYCLE's famous collection of jeans</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="swiper-slide" style="background-image: url('images/banner2.jpg');background-repeat: no-repeat;background-size: cover;background-position: center;">
                        <div class="banner-content">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-6">
                                        <h2 class="banner-title">STILL PAIN Collection</h2>
                                        <p>CITY CYCLE's famous collection of jackets</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section id="featured-products" class="product-store padding-large">
            <div class="container">
                <div class="section-header d-flex flex-wrap align-items-center justify-content-between">
                    <h2 class="section-title">New Arrivals</h2>
                </div>
                <div class="swiper product-swiper overflow-hidden">
                    <div class="swiper-wrapper">

                        <c:forEach var="product" items="${requestScope.LIST_NEW_ARRIVALS}">
                            <div class="swiper-slide">
                                <div class="product-item">
                                    <div class="image-holder" style="height: 395px; background: var(--light-color);
                                         border: 1px solid var(--grey-color);
                                         border-radius: 6px;
                                         padding: 10px;
                                         cursor: pointer;
                                         margin-bottom: 20px;
                                         width: 100%;
                                         display: flex;
                                         justify-content:center;
                                         align-items:center;">
                                        <img src="${product.images}" alt="Books" class="product-image" style="max-height: 350px; border: none">
                                    </div>
                                    <c:url var="viewProduct" value="MainController">
                                        <c:param name="action" value="View_Product"></c:param>
                                        <c:param name="productID" value="${product.productID}"></c:param>
                                    </c:url>
                                    <div class="product-detail">
                                        <h4 class="product-title">
                                            <a href="${viewProduct}">${product.name}</a>
                                        </h4>
                                        <span class="item-price text-primary">${product.price}$</span>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="swiper-pagination"></div>
            </div>
        </section>




        <section id="featured-products" class="product-store padding-large">
            <div class="container">
                <div class="section-header d-flex flex-wrap align-items-center justify-content-between">
                    <h2 class="section-title">Best Selling Product</h2>
                </div>
                <div class="swiper product-swiper overflow-hidden">
                    <div class="swiper-wrapper">
                        <c:forEach var="product" items="${requestScope.LIST_BESTSELLING}">
                            <div class="swiper-slide">
                                <div class="product-item">
                                    <div class="image-holder" style="height: 395px; background: var(--light-color);
                                         border: 1px solid var(--grey-color);
                                         border-radius: 6px;
                                         padding: 10px;
                                         cursor: pointer;
                                         margin-bottom: 20px;
                                         width: 100%;
                                         display: flex;
                                         justify-content:center;
                                         align-items:center;
                                         }">
                                        <img src="${product.images}" alt="Books" class="product-image" style="max-height: 350px; border: none">
                                    </div>
                                    <c:url var="viewProduct" value="MainController">
                                        <c:param name="action" value="View_Product"></c:param>
                                        <c:param name="productID" value="${product.productID}"></c:param>
                                    </c:url>
                                    <div class="product-detail">
                                        <h4 class="product-title">
                                            <a href="${viewProduct}">${product.name}</a>
                                        </h4>
                                        <span class="item-price text-primary">${product.price}$</span>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="swiper-pagination"></div>
            </div>
        </section>



        <section id="featured-products" class="product-store padding-large">
            <div class="container">
                <div class="section-header d-flex flex-wrap align-items-center justify-content-between">
                    <h2 class="section-title">Maybe you like</h2>
                </div>
                <div class="swiper product-swiper overflow-hidden">
                    <div class="swiper-wrapper">
                        <c:forEach var="product" items="${requestScope.LIST_RANDOM}">
                            <div class="swiper-slide">
                                <div class="product-item">
                                    <div class="image-holder" style="height: 395px; background: var(--light-color);
                                         border: 1px solid var(--grey-color);
                                         border-radius: 6px;
                                         padding: 10px;
                                         cursor: pointer;
                                         margin-bottom: 20px;
                                         width: 100%;
                                         display: flex;
                                         justify-content:center;
                                         align-items:center;
                                         }">
                                        <img src="${product.images}" alt="Books" class="product-image" style="max-height: 350px; border: none">
                                    </div>
                                    <c:url var="viewProduct" value="MainController">
                                        <c:param name="action" value="View_Product"></c:param>
                                        <c:param name="productID" value="${product.productID}"></c:param>
                                    </c:url>
                                    <div class="product-detail">
                                        <h4 class="product-title">
                                            <a href="${viewProduct}">${product.name}</a>
                                        </h4>
                                        <span class="item-price text-primary">${product.price}$</span>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="swiper-pagination"></div>
            </div>
        </section>

        <section class="AboutContent" id="AboutContent">
            <div style="display: flex; justify-content: center; align-items: center;">
                <div class="About" style="width: 50%; background-color: teal; text-align: center; padding: 98.4px 0;margin: 20px 0; position: relative">
                    <h2 style="color: #eeeeee;">About</h2>
                    <h2 style="color: #eeeeee; position: absolute; left: 320px; top: 115px">______</h2>
                </div>
                <div class="About About-content" style="width: 50%; text-align: justify; padding: 80px 0;margin: 20px 0;">
                    <p>
                        Welcome to our website. We are glad that you have visited this sales website. 
                        This is a website selling clothes, as well as a project that I created for PRJ301. We are committed to the quality and origin of the goods we sell.
                    </p>
                </div>
            </div>
        </section>

        <section class="shoppify-section-banner">
            <div class="container">
                <div class="product-collection">
                    <div class="left-content collection-item">
                        <div class="products-thumb">
                            <img src="images/model.jpg" alt="collection item" class="large-image image-rounded">
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6 product-entry">
                            <div class="categories">RAGLAN EMBOSSED collection</div>
                            <h3 class="item-title">The casual selection</h3>
                            <p>CITY CYCLE's famous collection of Sweater</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        


        <footer id="footer">
            <div class="container">
                <div class="footer-menu-list">
                    <div style="display: flex ;justify-content: center; align-items: center">
                        <div class="col-lg-3 col-md-6 col-sm-6" style="width: 530px;">
                            <div class="footer-menu"  style="text-align: center">
                                <h5 class="widget-title">Contact Us</h5>
                                <p>Do you have any questions or suggestions? <a href="#" class="email">Sang101174@gmail.com</a>
                                </p>
                                <p>Do you need assistance? Give us a call. <br>
                                    <strong>+84 999999999</strong>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <hr>
        </footer>

    </div>

    <script src="js/jquery-1.11.0.min.js"></script>
    <script src="js/plugins.js"></script>
    <script src="js/script.js"></script>
    <script>
                                                    function redirect() {
                                                        window.location.href = 'login.jsp';
                                                    }




    </script>
</script>
</body>

</html>
