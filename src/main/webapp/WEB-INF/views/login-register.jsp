<%--
  Created by IntelliJ IDEA.
  User: thanh
  Date: 22/10/2021
  Time: 12:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="beans.Account" %>
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

    <script>
        function checkInput() {
            // Check username
            var _username = document.forms["formRegister"]["_username"].value;
            if (_username == "") {
                alert("Username invalid!");
                return false;
            }

            // Check password
            var _password = document.forms["formRegister"]["_password"].value;
            var _cpassword = document.forms["formRegister"]["_cpassword"].value;
            if (_password.length < 8) {
                alert("Password invalid! Password must be at least 8 characters");
                return false;
            } else if (_password != _cpassword) {
                alert("Confirm password invalid!");
                return false;
            }

            // Check Fullname
            var _fullname = document.forms["formRegister"]["_fullname"].value;
            if (_fullname == "") {
                alert("Fullname invalid!");
                return false;
            }

            // Check Phonenumber
            var _phoneNumber = document.forms["formRegister"]["_phoneNumber"].value;
            if (_phoneNumber.length != 10) {
                alert("Phonenumber invalid! 10 number!");
                return false;
            }
            for (var i = 0; i < _phoneNumber.length; i++) {
                if (_phoneNumber.charAt(i) > '9' || _phoneNumber.charAt(i) < '0') {
                    alert("Phonenumber invalid!");
                    return false;
                }
            }

            // Check Date
            var _dateOfBirth = new Date(document.forms["formRegister"]["_dateOfBirth"].value);
            if (_calculateAge(_dateOfBirth) < 18) {
                alert("Check your Date of Birth! You must be over than 18 years old!");
                return false;
            }
        }

        function _calculateAge(DoB) { // DoB is a date
            return ~~((Date.now() - DoB) / (31557600000)); // 365.25 (day) * 24 (hours) * 60 (minutes) * 60 (seconds) * 1000 (miliseconds)
        }
    </script>

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
                <h1>Login or Register</h1>
                <nav class="d-flex align-items-center">
                    <a href="index.html">Home<span class="lnr lnr-arrow-right"></span></a>
                    <a href="category.html">Login or Register</a>
                </nav>
            </div>
        </div>
    </div>
</section>
<!-- End Banner Area -->

<!--================Login Box Area =================-->
<section class="login_box_area section_gap">
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <div class="login_form_inner">
                    <h3>Log in to enter</h3>
                    <h3>
                        <b>${login_message}</b>
                    </h3>
                    <form class="row login_form" action="login-register?type=login" method="post">
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" name="username" placeholder="Username"
                                   value="${username}"
                                   onfocus="this.placeholder = ''" onblur="this.placeholder = 'Username'">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="password" class="form-control" name="password" placeholder="Password"
                                   onfocus="this.placeholder = ''" onblur="this.placeholder = 'Password'">
                        </div>
                        <div class="col-md-12 form-group">
                            <div class="creat_account">
                                <input type="checkbox" name="rememberMe" value="Y" id="rememberMe">
                                <label for="rememberMe">Keep me logged in</label>
                            </div>
                        </div>
                        <div class="col-md-12 form-group">
                            <button type="submit" value="submit" class="primary-btn">Log In</button>
                            <!--
                            <a href="#">Forgot Password?</a>
                            -->
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="login_form_inner">
                    <h3>Register new Account</h3>
                    <h3><b>${register_message}</b></h3>
                    <form class="row login_form" action="login-register?type=register" method="post"
					name="formRegister" onsubmit="return checkInput()">
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" name="_username" placeholder="Username" required
                                   id="_username"
                                   onfocus="this.placeholder = ''" onblur="this.placeholder = 'Username'">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="password" class="form-control" name="_password" placeholder="Password" required
                                   id="_password"
                                   onfocus="this.placeholder = ''" onblur="this.placeholder = 'Password'">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="password" class="form-control" name="_cpassword" placeholder="Confirm Password"
                                   required id="_cpassword"
                                   onfocus="this.placeholder = ''" onblur="this.placeholder = 'Confirm Password'">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" name="_fullname" placeholder="Full Name" required
                                   id="_fullname"
                                   onfocus="this.placeholder = ''" onblur="this.placeholder = 'Full Name'">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" name="_avatar" placeholder="Avatar URL"
                                   onfocus="this.placeholder = ''" onblur="this.placeholder = 'Avatar URL'">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" name="_phoneNumber"
                                   placeholder="0xxxxxxxxx (10 number)" required id="_phoneNumber"
                                   onfocus="this.placeholder = ''" onblur="this.placeholder = '0xxxxxxxxx (10 number)'">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" name="_address" placeholder="Address" required
                                   id="_address"
                                   onfocus="this.placeholder = ''" onblur="this.placeholder = 'Address'">
                        </div>
                        <div class="col-md-12 form-group">
                            <div class="default-select" id="default-select">
                                <select name="_sex">
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-12 form-group">
                            <span>Date of Birth:</span>
                            <input type="date" class="form-control" name="_dateOfBirth" id="_dateOfBirth" required min="1900-01-01">
                        </div>
                        <div class="col-md-12 form-group">
                            <button type="submit" value="submit" class="primary-btn">Register</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<!--================End Login Box Area =================-->

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
