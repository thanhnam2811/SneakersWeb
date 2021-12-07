<%--
  Created by IntelliJ IDEA.
  User: thanh
  Date: 30/11/2021
  Time: 6:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin - Manage Brand</title>

    <!-- Custom fonts for this template -->
    <link href="admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="admin/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="admin/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    <!-- Select 2 -->
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />

    <!-- Icon -->
    <link rel="icon" href="img/fav.png">
</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <jsp:include page="_slideBar.jsp"/>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <jsp:include page="_topNav.jsp"/>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800">My Profile</h1>

                <!-- Row Content -->
                <div class="row">
                    <!-- My Profile Area-->
                    <div class="col-xl-8 col-lg-7">
                        <div class="shadow card">
                            <div class="py-3 text-center">
                                <h3 class="m-0 font-weight-bold text-primary">My Profile</h3>
                            </div>
                            <div class="row px-3">
                                <!-- Avatar -->
                                <div class="col-xl-3">
                                    <img src="img/avatar-demo.png" style="width: 200px; height: 225px;" />
                                </div>
                                <form class="user col-xl-9">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="_username" name="_username" placeholder="username" readonly>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="_fullname" name="_fullname" placeholder="Full Name" readonly>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-sm-4">
                                            <select class="select2 form-control" id="_sex" name="_sex" readonly>
                                                <option value="Male">Male</option>
                                                <option value="Female">Female</option>
                                            </select>
                                        </div>
                                        <div class="col-sm-8">
                                            <input type="date" class="form-control" id="_dateOfBirth" name="_dateOfBirth" readonly>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <input type="tel" class="form-control" id="_phoneNumber" name="_phoneNumber" placeholder="Phone number (10 numbers)" pattern="[0-9]{10}" readonly>
                                    </div>
                                    <div class="form-group">
                                        <!-- <input type="text" class="form-control form-control-user" id="_describe" placeholder="Describe"> -->
                                        <textarea class="form-control" id="_address" name="_address" placeholder="Address" readonly></textarea>
                                    </div>
                                    <hr>
                                    <div class="form-group row">
                                        <div class="col-sm-4">
                                            <button type="submit" class="btn btn-primary btn-block">
                                                Edit
                                            </button>
                                        </div>
                                        <div class="col-sm-4">
                                            <button type="submit" class="btn btn-success btn-block" disabled>
                                                Save
                                            </button>
                                        </div>
                                        <div class="col-sm-4">
                                            <button type="reset" class="btn btn-secondary btn-block" disabled>
                                                Reset
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <!-- My profile area -->
                    <div class="col-lg-7 col-xl-4">
                        <div class="shadow card">
                            <div class="py-3 text-center">
                                <h3 class="m-0 font-weight-bold text-primary">Change Password</h3>
                            </div>
                            <form class="user px-3">
                                <div class="form-group">
                                    <input type="password" class="form-control" id="_oldPassword" name="_oldPassword" placeholder="Old Password">
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control" id="_newPassword" name="_newPassword" placeholder="New Password">
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control" id="_confirmPassword" name="_confirmPassword" placeholder="Repeat New Password">
                                </div>
                                <hr>
                                <div class="form-group">
                                    <button type="reset" class="btn btn-primary btn-block">
                                        Submit
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Your Website 2020</span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Bootstrap core JavaScript-->
<script src="admin/vendor/jquery/jquery.min.js"></script>
<script src="admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="admin/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="admin/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="admin/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="admin/js/demo/datatables-demo.js"></script>

<!-- Select 2 -->
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>

</body>

</html>
