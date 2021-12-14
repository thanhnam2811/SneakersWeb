<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: thanh
  Date: 21/10/2021
  Time: 7:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

<!-- Start Header Area -->
<header class="header_area sticky-header">
    <div class="main_menu">
        <nav class="navbar navbar-expand-lg navbar-light main_box">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <a class="navbar-brand logo_h" href="${pageContext.request.contextPath}/home">
                    <img src="img/logo.png" alt="home"></a>
                <%--                <button class="navbar-toggler" type="button" data-toggle="collapse"--%>
                <%--                        data-target="#navbarSupportedContent"--%>
                <%--                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">--%>
                <%--                    <span class="icon-bar"></span>--%>
                <%--                    <span class="icon-bar"></span>--%>
                <%--                    <span class="icon-bar"></span>--%>
                <%--                </button>--%>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                    <ul class="nav navbar-nav menu_nav ml-auto">
                        <li class="nav-item ${pageName == "home" ? "active" : ""}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/home">Home</a>
                        </li>
                        <li class="nav-item ${pageName == "product" ? "active" : ""}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/product">Product</a>
                        </li>
                        <c:if test="${sessionScope.loginedUser == null}">
                            <li class="nav-item ${pageName == "login-register" ? "active" : ""}">
                                <a class="nav-link" href="${pageContext.request.contextPath}/login-register">Login or
                                    Register</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.loginedUser != null}">
                            <li class="nav-item ${pageName == "my-account" ? "active" : ""}">
                                <a class="nav-link" href="${pageContext.request.contextPath}/my-account">My
                                    account</a>
                            </li>
                            <c:if test="${sessionScope.loginedUser.isAdmin()}">
                                <li class="nav-item">
                                    <a class="nav-link" href="${pageContext.request.contextPath}/admin-home">Admin
                                        page</a>
                                </li>
                            </c:if>
                        </c:if>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <c:if test="${sessionScope.loginedUser != null}">
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/cart" class="cart">
                                    <span class="ti-bag">
                                        <sup><b>${sessionScope.userCart.size()}</b></sup>
                                    </span>
                                </a>
                            </li>
                        </c:if>

                        <%--                        <li class="nav-item">--%>
                        <%--                            <button class="search"><span class="lnr lnr-magnifier" id="search"></span></button>--%>
                        <%--                        </li>--%>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <!--
    <div class="search_input" id="search_input_box">
        <div class="container">
            <form class="d-flex justify-content-between">
                <input type="text" class="form-control" id="search_input" placeholder="Search Here">
                <button type="submit" class="btn"></button>
                <span class="lnr lnr-cross" id="close_search" title="Close Search"></span>
            </form>
        </div>
    </div>
    -->
</header>
<!-- End Header Area -->