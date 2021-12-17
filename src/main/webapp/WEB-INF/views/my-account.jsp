<%-- Created by IntelliJ IDEA. User: thanh Date: 22/10/2021 Time: 3:55 PM To change this template use File | Settings |
    File Templates. --%>
<%@ page import="beans.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
</head>

<body>
<!-- Start Header Area -->
<jsp:include page="_header.jsp" />
<!-- End Header Area -->

<!-- Start Banner Area -->
<section class="banner-area organic-breadcrumb">
    <div class="container">
        <div class="breadcrumb-banner d-flex flex-wrap align-items-center justify-content-end">
            <div class="col-first">
                <h1>My Account Page</h1>
                <nav class="d-flex align-items-center">
                    <a href="${pageContext.request.contextPath}/home">Home<span
                            class="lnr lnr-arrow-right"></span></a>
                    <a href="${pageContext.request.contextPath}/my-account">My Account</a>
                </nav>
            </div>
        </div>
    </div>
</section>
<!-- End Banner Area -->

<!-- Start Align Area -->
<div class="whole-wrap pb-100" style="padding-top: 50px;">
    <div class="container">
        <div class="section-top-border">
            <div class="row">
                <div class="col-lg-8 col-md-8">
                    <form>
                        <div class="order_box" id="form-info" style="background-color: rgba(224,243,255,0.5)">
                            <h2>Your Information</h2>
                            <ul class="list">
                                <li><a>ID: <span>${user.id}</span></a></li>
                                <li><a>Username: <span>${user.username}</span></a></li>
                                <li><a>Fullname: <span>${user.fullname}</span></a></li>
                                <li><a>Phone number: <span>${user.phoneNumber}</span></a></li>
                                <li><a>Address: <span>${user.address}</span></a></li>
                                <li><a>Sex: <span>${user.sex}</span></a></li>
                                <li><a>Date of Birth: <span>${user.dateOfBirth}</span></a></li>
                            </ul>
                            <div class="d-flex" style="padding-top: 20px">
                                <a href="${pageContext.request.contextPath}/edit-account" class="primary-btn rounded"
                                   style="width: 150px;">
                                    Edit
                                </a>
                            </div>
                            <div class="d-flex" style="padding-top: 20px">
                                <a href="${pageContext.request.contextPath}/logout" class="primary-btn rounded"
                                   style="width: 150px;">
                                    Log out
                                </a>
                            </div>
                            <div class="d-flex" style="padding-top: 20px">
                                <a href="#" class="primary-btn rounded"
                                   style="width: 150px;"
                                   onclick="
                                        var myOrders = document.getElementById('myOrders');

                                        if(myOrders.style.display == 'block')
                                            {
                                                myOrders.style.display = 'none';
                                            }
                                        else{
                                            myOrders.style.display = 'block'
                                        }
                                        ">
                                    My Orders
                                </a>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-lg-3 col-md-4 mt-sm-30" id="avatar">
                    <h3 class="mb-30" style="text-align: center;">
                        AVATAR
                    </h3>
                    <div class="single-gallery-image" style="background: url(${user.avatar}); height: 255px"></div>
                </div>
                <style>
                    .hide{
                        display: none;
                    }
                </style>
            <%--               Order--%>
                <div class="col-lg-8 col-md-8 hide" id="myOrders">
                    <c:forEach items="${requestScope.listOrder}" var="c">
                        <form class=" py-1">
                            <div class="order_box">
                                <h2>Your Order</h2>

                                <ul class="list">
                                    <li><a href="#">Product <span>Total</span></a></li>
                                    <c:forEach items="${c.getListDetailByID(conn)}" var="o">
                                    <li><a href="product-detail?id=${o.idProduct}">${o.getNameProduct(conn)}
                                        <span class="middle">x ${o.quantity}</span>
                                        <span class="last"><fmt:formatNumber type="number"
                                                                             maxFractionDigits="0" value="${o.cost}"/>vnđ</span>
                                    </a></li>
                                    </c:forEach>
<%--                                    <li><a href="#">Fresh Tomatoes <span class="middle">x 02</span> <span--%>
<%--                                            class="last">$720.00</span></a></li>--%>
<%--                                    <li><a href="#">Fresh Brocoli <span class="middle">x 02</span> <span--%>
<%--                                            class="last">$720.00</span></a></li>--%>
                                </ul>

                                <ul class="list list_2">
                                    <li><a href="#">Total <span><fmt:formatNumber type="number"
                                                                                  maxFractionDigits="0" value="${c.cost}"/>vnđ</span></a></li>
                                    <li><a href="#">Purcharse Date <span>${c.purchaseDate}</span></a></li>
                                </ul>
                            </div>
                        </form>
                    </c:forEach>
<%--                    <form>--%>
<%--                        <div class="order_box">--%>
<%--                            <h2>Your Order</h2>--%>
<%--                            <ul class="list">--%>
<%--                                <li><a href="#">Product <span>Total</span></a></li>--%>
<%--                                <li><a href="#">Fresh Blackberr--%>
<%--                                    <span class="middle">x 02</span>--%>
<%--                                    <span class="last">$720.00</span>--%>
<%--                                </a></li>--%>
<%--                                <li><a href="#">Fresh Tomatoes <span class="middle">x 02</span> <span--%>
<%--                                        class="last">$720.00</span></a></li>--%>
<%--                                <li><a href="#">Fresh Brocoli <span class="middle">x 02</span> <span--%>
<%--                                        class="last">$720.00</span></a></li>--%>
<%--                            </ul>--%>
<%--                            <ul class="list list_2">--%>
<%--                                <li><a href="#">Total <span>$2210.00</span></a></li>--%>
<%--                            </ul>--%>
<%--                        </div>--%>
<%--                    </form>--%>
                </div>
            </div>

        </div>
    </div>
</div>
<!-- End Align Area -->

<!--================Checkout Area =================-->
<section class="checkout_area section_gap">
    <div class="container">
        <div class="billing_details">
            <div class="row">
            </div>
        </div>
    </div>
</section>
<!--================End Checkout Area =================-->

<!-- start footer Area -->
<jsp:include page="_footer.jsp" />
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