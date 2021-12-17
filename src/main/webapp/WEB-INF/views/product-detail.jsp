<%@ page import="utils.MyUtils" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: thanh
  Date: 23/10/2021
  Time: 7:33 PM
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
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/themify-icons.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/nice-select.css">
    <link rel="stylesheet" href="css/nouislider.min.css">
    <link rel="stylesheet" href="css/ion.rangeSlider.css"/>
    <link rel="stylesheet" href="css/ion.rangeSlider.skinFlat.css"/>
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
                <h1>Product Details Page</h1>
                <nav class="d-flex align-items-center">
                    <a href="index.html">Home<span class="lnr lnr-arrow-right"></span></a>
                    <a href="#">Shop<span class="lnr lnr-arrow-right"></span></a>
                    <a href="single-product.html">Product Detail</a>
                </nav>
            </div>
        </div>
    </div>
</section>
<!-- End Banner Area -->

<!--================Single Product Area =================-->
<div class="product_image_area">
    <div class="container">
        <div class="row s_product_inner">
            <div class="col-lg-6">
                <div class="single-prd-item">
                    <img class="img-fluid" src="${product.image}" alt="">
                </div>
            </div>
            <div class="col-lg-5 offset-lg-1">
                <div class="s_product_text">
                    <h3>${product.name}</h3>
                    <h2><fmt:formatNumber maxFractionDigits="0" value="${product.cost}"/> vnđ</h2>
                    <ul class="list">
                        <li><a class="active"
                               href="${pageContext.request.contextPath}/product?brand=${product.idBrand}">
                            <span>Brand</span>: ${brandName}</a></li>
                        <li><a><span>Available</span> : ${product.quantity}</a></li>
                    </ul>
                    <p style="text-align: justify-all">${product.describe}</p>
                    <form action="cart" method="get">
                        <input type="hidden" name="idProduct" value="${product.id}">
                        <div class="product_count">
                            <label for="quantity">Quantity:</label>
                            <input type="number" name="quantity" id="sst" maxlength="12" value="1" title="Quantity:" required
                                   class="input-text qty" onkeyup="var result = document.getElementById('sst'); var sst = result.value; if (sst > ${product.quantity}) {alert('Must be lower than available quantity: ${product.quantity}'); result.value = 1;}">
                            <button
                                    onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst ) &amp;&amp; sst < ${product.quantity} ) result.value++;return false;"
                                    class="increase items-count" type="button"><i class="lnr lnr-chevron-up"></i>
                            </button>
                            <button
                                    onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst ) &amp;&amp; sst > 1 ) result.value--;return false;"
                                    class="reduced items-count" type="button"><i class="lnr lnr-chevron-down"></i>
                            </button>
                        </div>
                        <div class="card_area d-flex align-items-center">
                            <input type="hidden" name="type" value="AddToCart">
                            <button type="submit" class="genric-btn primary ${sessionScope.loginedUser != null ? "" : "disable"}">
                                ${sessionScope.loginedUser != null ? "Add to Cart" : "Login to use Cart"}
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!--================End Single Product Area =================-->

<!--================Product Description Area =================-->
<section class="product_description_area">
    <div class="container">
        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <li class="nav-item active">
                <h3>Comments</h3>
            </li>
        </ul>
        <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade show active">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="comment_list">
                            <c:forEach items="${listComment}" var="c">
                                <div class="review_item">
                                    <div class="media">
                                        <div class="d-flex">
                                            <img src="img/avatar/user.png" alt="">
                                        </div>
                                        <div class="media-body">
                                            <h4>${c.username}</h4>
                                        </div>
                                    </div>
                                    <p>${c.comment}</p>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <c:if test="${sessionScope.loginedUser == null}">
                        <div class="col-lg-6" style="padding-top: 50px;">
                            <div class="review_box">
                                <h4 style="text-align: right">Login to comment!</h4>
                                <div class="col-md-12 text-right">
                                    <a href="${pageContext.request.contextPath}/login-register" class="btn primary-btn">Login
                                        now</a>
                                </div>
                                </form>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.loginedUser != null}">
                        <div class="col-lg-6" style="padding-top: 50px;">
                            <div class="review_box">
                                <h4>New comment</h4>
                                <form class="row contact_form" action="product-detail" method="post"
                                      id="form-comment" novalidate="novalidate">
                                    <input type="text" name="idProduct" value="${product.id}" style="display: none">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="username" name="username"
                                                   value="${sessionScope.loginedUser.username}" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
											<textarea class="form-control" name="new_comment" id="comment" rows="1"
                                                      placeholder="Comment"></textarea>
                                        </div>
                                    </div>
                                    <div class="col-md-12 text-right">
                                        <button type="submit" value="submit" class="btn primary-btn">Post</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</section>
<!--================End Product Description Area =================-->

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
