<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="shopping.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
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
        <!-- script
        ================================================== -->
        <script src="js/modernizr.js"></script>
        <style>
            .pagination{
                text-align: center;
            }
            .pagination>a{
                padding: 5px;
                border: 1px solid;
                background-color: #eee;
                margin: 2px;
            }

            .pagination>a:hover{
                background-color: #3B5998;
                color: #eee;
            }

            .pagination a.active{
                background-color: #bbb;
                color: #000;
            }

            .listpro>li{
                margin-left: 20px;
            }
            .info{
                color: #bbb;
                margin-top: 2px;
            }
            --'''
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER.roleID eq 'AD'}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>

        <c:if test="${requestScope.DATA == null}">
            <c:redirect url="/SearchController"></c:redirect>
        </c:if>

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
                                <p>Let's talk! <strong>+84 9999999999</strong>
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
        <section class="site-banner jarallax min-height300 padding-large" style="background: url(images/hero-image.jpg) no-repeat; background-position: top;">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-title">Shop page</h1>
                        <div class="breadcrumbs">
                            <span class="item">
                                <c:url var="homeLink" value="MainController">
                                    <c:param name="action" value="Home"></c:param>
                                </c:url>
                                <a href="${homeLink}">Home</a>
                            </span>
                            <span class="item">/ Shop</span>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <div class="shopify-grid padding-large">
            <div class="container">
                <div class="row">
                    <section id="selling-products" class="col-md-9 product-store">
                        <div class="container">
                            <ul class="tabs list-unstyled listpro">
                                <li><a href="MainController?action=Search&searchType=searchProduct&search=${param.all}">All</a></li>
                                <li><a href="MainController?action=Search&searchType=searchProduct&search=T-Shirt">T-Shirts</a></li>
                                <li><a href="MainController?action=Search&searchType=searchProduct&search=Jean">Jeans</a></li>
                                <li><a href="MainController?action=Search&searchType=searchProduct&search=Jacket">Jackets</a></li>
                                <li><a href="MainController?action=Search&searchType=searchProduct&search=Hoodie">Hoodies</a></li>
                                <li><a href="MainController?action=Search&searchType=searchProduct&search=Sweater">Sweaters</a></li>
                                <li><a href="MainController?action=Search&searchType=searchProduct&search=Hat">Hats</a></li>
                            </ul>
                        </div>

                        <div id="all">
                            <div class="row d-flex flex-wrap">
                                <c:forEach var="product" items="${requestScope.DATA}">
                                    <div class="product-item col-lg-4 col-md-6 col-sm-6">
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
                                </c:forEach>
                            </div>
                        </div>
                        <c:set var="page" value="${requestScope.PAGE}"></c:set>
                            <div class="pagination">
                            <c:forEach begin="1" end="${requestScope.NUM}" var="i">
                                <a class="${i == page?"active":""}" href="MainController?action=Search&searchType=searchProduct&search=${param.search}&page=${i}">${i}</a>
                            </c:forEach>
                        </div>

                    </section>
                    <aside class="col-md-3">
                        <div class="sidebar">
                            <div class="widgets widget-menu">
                                <div class="widget-search-bar">
                                    <form action="MainController" role="search" method="get" class="d-flex">
                                        <input class="search-field" name="search" value="${param.search}" placeholder="Search" type="text">
                                        <input name="searchType" value="searchProduct" type="hidden">
                                        <button type="submit" name="action" value="Search" class="btn btn-dark"><i class="icon icon-search"></i></button>
                                    </form>
                                </div> 
                            </div>
                            <div class="widgets widget-product-tags">
                                <h5 class="widget-title">Collections</h5>
                                <ul class="product-tags sidebar-list list-unstyled">
                                    <li class="tags-item">
                                        <a href="MainController?action=Search&searchType=searchProduct&collection=Loose Fit Printed&search=none">Loose Fit Printed</a>
                                    </li>
                                    <li class="tags-item">
                                        <a href="MainController?action=Search&searchType=searchProduct&collection=Camping in the Summer&search=none">Camping in the Summer</a>
                                    </li>
                                    <li class="tags-item">
                                        <a href="MainController?action=Search&searchType=searchProduct&collection=STILL PAIN&search=none">STILL PAIN</a>
                                    </li>
                                    <li class="tags-item">
                                        <a href="MainController?action=Search&searchType=searchProduct&collection=Line Up&search=none">Line Up</a>
                                    </li>
                                    <li class="tags-item">
                                        <a href="MainController?action=Search&searchType=searchProduct&collection=THE BOYS TV SHOW&search=none">THE BOYS TV SHOW</a>
                                    </li>
                                    <li class="tags-item">
                                        <a href="MainController?action=Search&searchType=searchProduct&collection=Slim Fit Lightweight&search=none">Slim Fit Lightweight</a>
                                    </li>
                                    <li class="tags-item">
                                        <a href="MainController?action=Search&searchType=searchProduct&collection=RAGLAN EMBOSSED&search=none">RAGLAN EMBOSSED</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="widgets widget-product-brands">
                                <h5 class="widget-title">Brands</h5>
                                <ul class="product-tags sidebar-list list-unstyled">
                                    <li class="tags-item">
                                        <a href="MainController?action=Search&searchType=searchProduct&brand=URBANFIND&search=none">URBANFIND</a>
                                    </li>
                                    <li class="tags-item">
                                        <a href="MainController?action=Search&searchType=searchProduct&brand=H&M&search=none">H&M</a>
                                    </li>
                                    <li class="tags-item">
                                        <a href="MainController?action=Search&searchType=searchProduct&brand=Be Yourself&search=none">Be Yourself</a>
                                    </li>
                                    <li class="tags-item">
                                        <a href="MainController?action=Search&searchType=searchProduct&brand=CITY CYCLE&search=none">CITY CYCLE</a>
                                    </li>
                                    <li class="tags-item">
                                        <a href="MainController?action=Search&searchType=searchProduct&brand=THE BOYS&search=none">THE BOYS</a>
                                    </li>
                                    <li class="tags-item">
                                        <a href="MainController?action=Search&searchType=searchProduct&brand=Gucci&search=none">Gucci</a>
                                    </li>
                                    <li class="tags-item">
                                        <a href="MainController?action=Search&searchType=searchProduct&brand=Leinne&search=none">Gucci</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="widgets widget-price-filter">
                                <h5 class="widget-title">Filter By Price</h5>
                                <ul class="product-tags sidebar-list list-unstyled">
                                    <li class="tags-item">
                                        <a href="MainController?action=Search&searchType=searchProduct&lower=0&upper=39&search=none">Less than $40</a>
                                    </li>
                                    <li class="tags-item">
                                        <a href="MainController?action=Search&searchType=searchProduct&lower=40&upper=50&search=none">$40- $50</a>
                                    </li>
                                    <li class="tags-item">
                                        <a href="MainController?action=Search&searchType=searchProduct&lower=60&upper=70&search=none">$60- $70</a>
                                    </li>
                                    <li class="tags-item">
                                        <a href="MainController?action=Search&searchType=searchProduct&lower=80&upper=90&search=none">$80- $90</a>
                                    </li>
                                    <li class="tags-item">
                                        <a href="MainController?action=Search&searchType=searchProduct&lower=91upper=-1&search=none">Greater than $90</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </aside>

                </div>
            </div>
        </div>
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
