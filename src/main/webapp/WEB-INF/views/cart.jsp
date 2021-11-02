<%@ page import="utils.MyUtils" %>
<%@ page import="beans.Cart" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: thanh
  Date: 23/10/2021
  Time: 10:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="vi" class="no-js">

<head>
    <!-- Mobile Specific Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Favicon-->
    <link rel="shortcut icon" href="img/fav.png">
    <!-- Author Meta -->
    <meta name="author" content="CodePixar">
    <!-- Meta Description -->
    <meta name="description" content="">
    <!-- Meta Keyword -->
    <meta name="keywords" content="">
    <!-- meta character set -->
    <meta charset="UTF-8">
    <!-- Site Title -->
    <title>Karma Shop</title>

    <!--
            CSS
            ============================================= -->
    <link rel="stylesheet" href="css/linearicons.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/themify-icons.css">
    <link rel="stylesheet" href="css/nice-select.css">
    <link rel="stylesheet" href="css/nouislider.min.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/main.css">

    <style>
        /* Chrome, Safari, Edge, Opera */
        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }

        /* Firefox */
        input[type=number] {
            -moz-appearance: textfield;
        }
    </style>
</head>

<body>

<!-- Start Header Area -->
<jsp:include page="_header.jsp"/>
<!-- End Header Area -->

<!-- Start Banner Area -->
<section class="banner-area organic-breadcrumb">
    <div class="container">
        <div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
            <div class="col-first">
                <h1>Cart</h1>
                <nav class="d-flex align-items-center">
                    <a href="${pageContext.request.contextPath}/home">Home<span class="lnr lnr-arrow-right"></span></a>
                    <a href="${pageContext.request.contextPath}/cart">Cart</a>
                </nav>
            </div>
        </div>
    </div>
</section>
<!-- End Banner Area -->

<!--================Cart Area =================-->
<section class="cart_area">
    <div class="container">
        <div class="cart_inner">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Product</th>
                        <th scope="col">Price</th>
                        <th scope="col">Quantity</th>
                        <th scope="col"></th>
                        <th scope="col">Total</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="total" value="0"></c:set>
                    <c:forEach items="${sessionScope.userCart}" var="c">
                        <c:set var="total" value="${total+c.cost}"></c:set>
                        <form action="cart" method="get">
                            <tr>
                                <td>
                                    <div class="media">
                                        <div class="d-flex">
                                            <a href="${pageContext.request.contextPath}/product-detail?id=${c.product.id}">
                                                <img src="${c.product.image}" alt="${c.product.name}"
                                                     width="200px"
                                                     height="200px">
                                            </a>
                                        </div>
                                        <div class="media-body">
                                            <a href="${pageContext.request.contextPath}/product-detail?id=${c.product.id}">
                                                <h6>${c.product.name}</h6></a>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <h5><fmt:formatNumber maxFractionDigits="0" value="${c.product.cost}"/>
                                        vnđ</h5>
                                </td>
                                <td>
                                    <div class="product_count">
                                        <input type="number" name="new_quantity" id="product-${c.product.id}"
                                               maxlength="12"
                                               value="${c.quantity}"
                                               title="Quantity:" class="input-text qty" required
                                               onkeyup="
                                                       var result = document.getElementById('product-${c.product.id}');
                                                       var sst = result.value;
                                                       var btn = document.getElementById('btn-save-${c.product.id}');
                                                       var message = document.getElementById('errorMessage');
                                                       if (sst > ${c.product.quantity}) {
                                                           alert('Must be lower than available quantity: ${c.product.quantity}');
                                                           result.value = ${c.quantity};
                                                       }
                                                       if( !isNaN( sst ) &amp;&amp; sst < ${c.product.quantity} )
                                                       result.value;
                                                       if ( result.value != ${c.quantity} && btn.classList.contains('disable')) {
                                                       document.getElementById('btn-save-${c.product.id}').classList.remove('disable');
                                                       message.innerText = 'Save your changes first!';
                                                       }
                                                       if ( result.value == ${c.quantity} && !btn.classList.contains('disable')) {
                                                       document.getElementById('btn-save-${c.product.id}').classList.add('disable');
                                                       message.innerText = '';
                                                       }
                                                       return false;">
                                        <button
                                                onclick="
                                                        var result = document.getElementById('product-${c.product.id}');
                                                        var sst = result.value;
                                                        var btn = document.getElementById('btn-save-${c.product.id}');
                                                        var message = document.getElementById('errorMessage');
                                                        if( !isNaN( sst ) &amp;&amp; sst < ${c.product.quantity} )
                                                        result.value++;
                                                        if ( result.value != ${c.quantity} && btn.classList.contains('disable')) {
                                                        document.getElementById('btn-save-${c.product.id}').classList.remove('disable');
                                                        message.innerText = 'Save your changes first!';
                                                        }
                                                        if ( result.value == ${c.quantity} && !btn.classList.contains('disable')) {
                                                        document.getElementById('btn-save-${c.product.id}').classList.add('disable');
                                                        message.innerText = '';
                                                        }
                                                        return false;"
                                                class="increase items-count" type="button"><i
                                                class="lnr lnr-chevron-up"></i></button>
                                        <button
                                                onclick="
                                                        var result = document.getElementById('product-${c.product.id}');
                                                        var sst = result.value;
                                                        var btn = document.getElementById('btn-save-${c.product.id}');
                                                        var message = document.getElementById('errorMessage');
                                                        if( !isNaN( sst ) &amp;&amp; sst > 1 )
                                                        result.value--;
                                                        if ( result.value != ${c.quantity} && btn.classList.contains('disable')) {
                                                        document.getElementById('btn-save-${c.product.id}').classList.remove('disable');
                                                        message.innerText = 'Save your changes first!';
                                                        }
                                                        if ( result.value == ${c.quantity} && !btn.classList.contains('disable')) {
                                                        document.getElementById('btn-save-${c.product.id}').classList.add('disable');
                                                        message.innerText = '';
                                                        }
                                                        return false;"
                                                class="reduced items-count" type="button"><i
                                                class="lnr lnr-chevron-down"></i></button>
                                    </div>
                                </td>
                                <td>
                                    <input type="hidden" name="type" value="Update">
                                    <input type="hidden" name="idProduct" value="${c.product.id}">
                                    <button type="submit" class="genric-btn success disable" style="width: 100px"
                                            id="btn-save-${c.product.id}">Save
                                    </button>
                                    <div style="height: 50px"></div>
                                    <a href="${pageContext.request.contextPath}/cart?type=Delete&idProduct=${c.product.id}"
                                       class="genric-btn danger" style="width: 100px">Delete</a>
                                </td>
                                <td>
                                    <h5><fmt:formatNumber maxFractionDigits="0" value="${c.cost}"/> vnđ</h5>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                    <tr>
                        <td>
                            <h4><b style="color: #d62828;" id="errorMessage"></b></h4>
                        </td>
                        <td>

                        </td>
                        <td>

                        </td>
                        <td>
                            <h5>Subtotal</h5>
                        </td>
                        <td>
                            <h5><fmt:formatNumber maxFractionDigits="0" value="${total}"/> vnđ</h5>
                        </td>
                    </tr>
                    <tr class="out_button_area">
                        <td>

                        </td>
                        <td>

                        </td>
                        <td>

                        </td>
                        <td>

                        </td>
                        <td>
                            <form action="check-out" method="post">
                                <button class="primary-btn" type="submit">Checkout</button>
                            </form>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>
<!--================End Cart Area =================-->

<!-- start footer Area -->
<jsp:include page="_footer.jsp"/>
<!-- End footer Area -->

<script src="js/vendor/jquery-2.2.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
        integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
        crossorigin="anonymous"></script>
<script src="js/vendor/bootstrap.min.js"></script>
<script src="js/jquery.ajaxchimp.min.js"></script>
<script src="js/jquery.nice-select.min.js"></script>
<script src="js/jquery.sticky.js"></script>
<script src="js/nouislider.min.js"></script>
<script src="js/jquery.magnific-popup.min.js"></script>
<script src="js/owl.carousel.min.js"></script>
<!--gmaps Js-->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCjCGmQ0Uq4exrzdcL6rvxywDDOvfAu6eE"></script>
<script src="js/gmaps.min.js"></script>
<script src="js/main.js"></script>
</body>

</html>