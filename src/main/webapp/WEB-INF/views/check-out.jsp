<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: thanh
  Date: 27/10/2021
  Time: 10:56 PM
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
    `<title>NT Sneaker Shop</title>

    <!--
            CSS
            ============================================= -->
    <link rel="stylesheet" href="css/linearicons.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/themify-icons.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
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
                <h1>Checkout</h1>
                <nav class="d-flex align-items-center">
                    <a href="${pageContext.request.contextPath}/home">Home<span class="lnr lnr-arrow-right"></span></a>
                    <a href="${pageContext.request.contextPath}/check-out">Checkout</a>
                </nav>
            </div>
        </div>
    </div>
</section>
<!-- End Banner Area -->

<!--================Checkout Area =================-->
<section class="checkout_area section_gap">
    <div class="container">
        <div class="billing_details">
            <div class="row">
                <div class="col-lg-12">
                    <form class="row contact_form" action="check-out" method="post">
                        <div class="order_box">
                            <h2>Your Order</h2>
                            <ul class="list">
                                <c:set var="total" value="0"></c:set>
                                <li><a>Product <span>Total</span></a></li>
                                <c:forEach items="${sessionScope.userCart}" var="c">
                                    <c:set var="total" value="${total+c.cost}"></c:set>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/product-detail?id=${c.product.id}">${c.product.name} ( x${c.quantity} )
                                            <span class="last">
                                                <fmt:formatNumber maxFractionDigits="0" value="${c.cost}"/> vnđ
                                            </span>
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                            <ul class="list list_2">
                                <li><a>Total <span><fmt:formatNumber maxFractionDigits="0" value="${total}"/> vnđ</span></a>
                                </li>
                            </ul>
                            <h2>Information of Receiver</h2>
                            <div class="col-md-12 form-group p_star">
                                <input type="text" class="form-control" id="fullName" name="name"
                                       value="${sessionScope.loginedUser.fullname}" onblur="placeholder= 'Full name'">
                            </div>
                            <div class="col-md-12 form-group p_star">
                                <input type="number" class="form-control" id="phoneNumber" name="number" required
                                       value="${sessionScope.loginedUser.phoneNumber}"
                                       onblur="placeholder= 'Phone number'">
                            </div>
                            <div class="col-md-12 form-group p_star">
                                <input type="text" class="form-control" id="address" name="name"
                                       value="${sessionScope.loginedUser.address}" onblur="placeholder='Address'">
                            </div>
                            <button class="primary-btn" type="submit">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<!--================End Checkout Area =================-->

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
