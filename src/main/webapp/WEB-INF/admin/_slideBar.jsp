<%--
  Created by IntelliJ IDEA.
  User: thanh
  Date: 30/11/2021
  Time: 5:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/admin-home">
        <div class="sidebar-brand-icon">
            <i class="fa fa-user-plus"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Admin Page</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item ${requestScope.pageName.contains("Dashboard") ? "active" : ""}">
        <a class="nav-link" href="${pageContext.request.contextPath}/admin-home">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Manage Shop
    </div>

    <!-- Nav Item - Product -->
    <li class="nav-item ${requestScope.pageName.contains("Product") ? "active" : ""}">
        <a class="nav-link" href="${pageContext.request.contextPath}/manage-product">
            <i class="fa fa-shopping-cart"></i>
            <span>Product</span></a>
    </li>

    <!-- Nav Item - Brand -->
    <li class="nav-item ${requestScope.pageName.contains("Brand") ? "active" : ""}">
        <a class="nav-link" href="${pageContext.request.contextPath}/manage-brand">
            <i class="fa fa-certificate"></i>
            <span>Brand</span></a>
    </li>

    <!-- Nav Item - Comment -->
    <li class="nav-item ${requestScope.pageName.contains("Comment") ? "active" : ""}">
        <a class="nav-link" href="${pageContext.request.contextPath}/manage-comment">
            <i class="fa fa-comments"></i>
            <span>Comment</span></a>
    </li>

    <!-- Nav Item - Account -->
    <li class="nav-item ${requestScope.pageName.contains("Account") ? "active" : ""}">
        <a class="nav-link" href="${pageContext.request.contextPath}/manage-account">
            <i class="fa fa-user"></i>
            <span>Account</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Profile
    </div>

    <!-- Nav Item - Log out -->
    <li class="nav-item ${requestScope.pageName.contains("Profile") ? "active" : ""}">
        <a class="nav-link" href="${pageContext.request.contextPath}/admin-profile">
            <i class="fa fa-address-card"></i>
            <span>My profile</span></a>
    </li>

    <!-- Nav Item - Log out -->
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/logout">
            <i class="fa fa-sign-out-alt"></i>
            <span>Logout</span></a>
    </li>

</ul>
