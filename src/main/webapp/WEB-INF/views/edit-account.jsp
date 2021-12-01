<%--
  Created by IntelliJ IDEA.
  User: thanh
  Date: 23/10/2021
  Time: 9:08 AM
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
    <link rel="stylesheet" href="css/themify-icons.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/nice-select.css">
    <link rel="stylesheet" href="css/nouislider.min.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/main.css">

    <script>
        function checkInput() {
            // Check Fullname
            var _fullname = document.forms["form-edit-account"]["_fullname"].value;
            if (_fullname == "") {
                alert("Fullname invalid!");
                return false;
            }

            // Check Phonenumber
            var _phoneNumber = document.forms["form-edit-account"]["_phoneNumber"].value;
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
            var _dateOfBirth = new Date(document.forms["form-edit-account"]["_dateOfBirth"].value);
            if (_calculateAge(_dateOfBirth) < 18) {
                alert("Check your Date of Birth! You must be over than 18 years old!");
                return false;
            }
        }

        function _calculateAge(DoB) { // DoB is a date
            return ~~((Date.now() - DoB) / (31557600000)); // 365.25 (day) * 24 (hours) * 60 (minutes) * 60 (seconds) * 1000 (miliseconds)
        }

        function checkPassword(){
            // Check password
            var _newpassword = document.forms["form-change-password"]["_newpassword"].value;
            var _cpassword = document.forms["form-change-password"]["_cpassword"].value;
            if (_newpassword.length < 8) {
                alert("Password invalid! Password must be at least 8 characters");
                return false;
            } else if (_newpassword != _cpassword) {
                alert("Confirm password invalid!");
                return false;
            }
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
                <h1>Edit Account</h1>
                <nav class="d-flex align-items-center">
                    <a href="${pageContext.request.contextPath}/home">Home<span class="lnr lnr-arrow-right"></span></a>
                    <a href="${pageContext.request.contextPath}/my-account">My Account<span
                            class="lnr lnr-arrow-right"></span></a>
                    <a href="${pageContext.request.contextPath}/edit-account">Edit Account</a>
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
                    <h3>Change Your Information</h3>
                    <h3><b>${edit_account_message}</b></h3>
                    <form class="row login_form" action="edit-account?type=edit-account" method="post"
                          id="form-edit-account" name="form-edit-account"
                          novalidate="novalidate" onsubmit="return checkInput()">
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" name="_username" id="_username"
                                   value="${user.username}" required readonly>
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" name="_fullname" id="_fullname"
                                   placeholder="Full name"
                                   onblur="this.placeholder = 'Full name'"
                                   value="${user.fullname}" required>
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" name="_avatar" id="_avatar"
                                   placeholder="Avatar URL"
                                   onblur="this.placeholder = 'Avatar URL'"
                                   value="${user.avatar}">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" name="_phoneNumber" id="_phoneNumber"
                                   placeholder="Phone number"
                                   onblur="this.placeholder = 'Phone number'"
                                   value="${user.phoneNumber}" required>
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" name="_address" id="_address"
                                   placeholder="Address"
                                   onblur="this.placeholder = 'Address'"
                                   value="${user.address}" required>
                        </div>
                        <div class="col-md-12 form-group">
                            <div class="default-select">
                                <select name="_sex">
                                    <option value="Male" ${user.sex == "Male" ? "selected" : ""}>Male</option>
                                    <option value="Female" ${user.sex == "Female" ? "selected" : ""}>Female</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-12 form-group">
                            <span>Date of Birth:</span>
                            <input type="date" class="form-control" name="_dateOfBirth" id="_dateOfBirth" required
                                   min="1900-01-01" value="${user.dateOfBirth}">
                        </div>
                        <div class="col-md-12 form-group">
                            <button type="submit" value="submit" class="primary-btn">Save</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="login_form_inner">
                    <h3>Change your password</h3>
                    <h3><b>${change_password_message}</b></h3>
                    <form class="row login_form" action="edit-account?type=change-password" method="post"
                          id="form-change-password" name="form-change-password"
                          novalidate="novalidate" onsubmit="return checkPassword()">
                        <div class="col-md-12 form-group">
                            <input type="password" class="form-control" id="_oldpassword" name="_oldpassword"
                                   placeholder="Old Password"
                                   onfocus="this.placeholder = ''" onblur="this.placeholder = 'Old Password'">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="password" class="form-control" id="_newpassword" name="_newpassword"
                                   placeholder="New Password"
                                   onfocus="this.placeholder = ''" onblur="this.placeholder = 'New Password'">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="password" class="form-control" id="_cpassword" name="_cpassword"
                                   placeholder="Confirm Password"
                                   onfocus="this.placeholder = ''" onblur="this.placeholder = 'Confirm Password'">
                        </div>
                        <div class="col-md-12 form-group">
                            <button type="submit" value="submit" class="primary-btn">Save</button>
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
