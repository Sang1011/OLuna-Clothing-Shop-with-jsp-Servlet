<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="user.UserDTO"%>
<%@page import="shopping.ProductDTO"%>
<%@page import="shopping.ProductDTO"%>
<%@page import="shopping.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Shopping Cart</title>
        <link rel="stylesheet" href="css/viewcartCustom.css">
        <link rel="icon" href="images/OLuna_FULL.png" type="image/x-icon" />
        <link rel="stylesheet" href="https://code.jquery.com/jquery-3.3.1.slim.min.js">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
    </head>
    <body>
        <section class="pt-5 pb-5">
            <div class="container">
                <div class="row w-100">
                    <div class="col-lg-12 col-md-12 col-12">
                        <h3 class="display-5 mb-2 text-center">Shopping Cart</h3>
                        <p class="mb-5 text-center">
                            <c:set var="count" value="0"/>
                            <i class="text-info font-weight-bold"> <span id="totalItems">0</span> </i> items in your cart</p>
                            <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.roleID eq 'AD'}">
                                <c:redirect url="login.jsp"></c:redirect>
                            </c:if>
                            <c:if test="${sessionScope.CART != null}">

                            <table id="shoppingCart" class="table table-condensed table-responsive">
                                <thead>
                                    <tr>
                                        <th style="width:55%">Product</th>
                                        <th style="width:8%">Price</th>
                                        <th style="width:10%">Quantity</th>
                                        <th style="width:20%">Total</th>
                                        <th style="width:16%"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="total" value="0"/>
                                    <c:set var="subtotal" value="0"/>
                                    <c:forEach var="product" varStatus="counter" items="${sessionScope.CART.cart}">
                                        <c:set var="subtotal" value="${product.value.price * product.value.quantity}" />
                                        <c:set var="total" value="${total + subtotal}" />
                                    <form action="MainController" method="POST"> 
                                        <c:set var="count" value="${count + 1}" />
                                        <tr>
                                            <td data-th="Product">
                                                <div class="row">
                                                    <div class="col-md-3 text-left" style>
                                                        <img src="${product.value.images}" alt="" class="img-fluid d-none d-md-block rounded mb-2 shadow" style="width: 250px; height: 150px;">
                                                    </div>
                                                    <div class="col-md-9 text-left mt-sm-2">
                                                        <h4>${product.value.name}</h4>
                                                        <h6>Collection: ${product.value.collection}</h6>
                                                        <h6>Brand:${product.value.brand}</h6>
                                                    </div>
                                                </div>
                                            </td>
                                            <td data-th="Price">${product.value.price}$</td>
                                            <td data-th="Quantity">
                                                <div class="number-control">
                                                    <div class="number-left"></div>
                                                    <input class="number-quantity" type="number" name="quantity" min="1" value="${product.value.quantity}" onkeydown="return event.key !== 'Enter';">
                                                    <div class="number-right"></div>
                                                </div>
                                            </td>
                                            <td data-th="Total">
                                                ${subtotal}
                                            </td>
                                            <td class="actions" data-th="">
                                                <div class="text-right">
                                                    <button class="btn btn-white border-secondary bg-white btn-md mb-2" name="action" value="Edit">
                                                        <input type="hidden" name="productID" value="${product.value.productID}"/>
                                                        <i class="fas fa-sync"></i>
                                                    </button>
                                                    <button class="btn btn-white border-secondary bg-white btn-md mb-2" name="action" value="Remove">
                                                        <input type="hidden" name="id" value="${product.value.productID}"/>
                                                        <i class="fas fa-trash"></i>
                                                    </button>
                                                </div>
                                            </td>
                                        </tr>
                                    </form>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div class="float-right text-right">
                                <h4>Total:</h4>
                                <h1>${total}$</h1>
                            </div>
                        </div>
                    </div>

                    <div class="row mt-4 d-flex align-items-center">
                        <div class="col-sm-6 order-md-2 text-right">
                            <c:url var="checkoutLink" value="MainController">
                                <c:param name="action" value="Checkout"></c:param>
                                <c:param name="total" value="${total}"></c:param>
                            </c:url>
                            <a href="${checkoutLink}" class="btn btn-primary mb-4 btn-lg pl-5 pr-5">Check out</a
                        </div>
                    </div>
                </c:if>
                <c:if test="${requestScope.INFO eq 'Payment successfully'}">
                    <h3 style="color: green; text-align: center">${requestScope.INFO}</h3>
                </c:if>
                <c:if test="${requestScope.INFO ne 'Payment successfully'}">
                    <h3 style="color: crimson; text-align: center">${requestScope.INFO}</h3>
                </c:if>
                <c:if test="${requestScope.LIST_OUTOFSTOCK != null}">
                    <c:if test="${not empty requestScope.LIST_OUTOFSTOCK}">
                        <c:forEach var="message" items="${requestScope.LIST_OUTOFSTOCK}">
                            <center>
                                <label style="color: crimson">${message}</label> </br>
                            </center>
                        </c:forEach>
                    </c:if>
                </c:if>
                <c:url var="homelink" value="MainController">
                    <c:param name="action" value="Home"></c:param>
                </c:url>
                <div class="col-sm-6 mb-3 mb-m-1 order-md-1 text-md-left">
                    <a href="${homelink}">
                        <i class="fas fa-arrow-left mr-2"></i> Continue Shopping</a>
                </div>
            </div>
        </section>


        <script>
            var count = ${count}
            document.getElementById("totalItems").textContent = count;

            // Lấy tất cả các phần tử "number-left" và "number-right"
            const numberControls = document.querySelectorAll('.number-control');

            // Biến danh sách phần tử thành mảng
            const numberArray = Array.from(numberControls);

            // Xử lý sự kiện cho từng phần tử
            numberArray.forEach((numberControl) => {
                const numberInput = numberControl.querySelector('.number-quantity');
                const numberLeft = numberControl.querySelector('.number-left');
                const numberRight = numberControl.querySelector('.number-right');

                let quantity = parseInt(numberInput.value);

                numberLeft.addEventListener('click', () => {
                    if (quantity > parseInt(numberInput.min)) {
                        quantity--;
                        updateNumber();
                    }
                });

                numberRight.addEventListener('click', () => {
                    quantity++;
                    updateNumber();
                });

                function updateNumber() {
                    numberInput.value = quantity;
                }
                updateNumber();
            });

        </script>

    </body>
</html>
