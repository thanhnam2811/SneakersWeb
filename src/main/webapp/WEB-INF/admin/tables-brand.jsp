<%--
  Created by IntelliJ IDEA.
  User: thanh
  Date: 30/11/2021
  Time: 6:28 PM
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
                <h1 class="h3 mb-2 text-gray-800">Manage Brands</h1>

                <!-- Row Content -->
                <div class="row">
                    <!-- DataTales Product Area-->
                    <div class="card shadow mb-4 col-xl-8 col-lg-7">
                        <div class="py-3 text-center">
                            <h3 class="m-0 font-weight-bold text-primary">DataTables Brand</h3>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Logo</th>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Action</th>
                                    </tr>
                                    </thead>
                                    <tfoot>
                                    <tr>
                                        <th>ID</th>
                                        <th>Logo</th>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Action</th>
                                    </tr>
                                    </tfoot>
                                    <tbody>
                                    <tr>
                                        <!-- ID -->
                                        <td style="text-align: left; vertical-align: middle;">
                                            1
                                        </td>

                                        <!-- Image -->
                                        <td>
                                            <img src="img/logo-demo.png" style="width: 120px; height: 150px;" />
                                        </td>

                                        <!-- Info -->
                                        <td style="text-align: left; vertical-align: middle;">
                                            [Tên brand]
                                        </td>

                                        <!-- Quantity -->
                                        <td style="text-align: left; vertical-align: middle;">
                                            brand@gamil.com
                                        </td>

                                        <!-- Action -->
                                        <td style="text-align: center; vertical-align: middle;">
                                            <a href="# " class="btn btn-info btn-icon-split btn-sm ">
                                                        <span class="icon text-white-50 ">
                                                        <i class="fas fa-info-circle "></i>
                                                    </span>
                                                <span class="text ">Edit</span>
                                            </a>
                                            <a href="# " class="btn btn-danger btn-icon-split btn-sm ">
                                                        <span class="icon text-white-50 ">
                                                        <i class="fas fa-trash "></i>
                                                    </span>
                                                <span class="text ">Delete</span>
                                            </a>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- Edit & Create Form Area -->
                    <div class="col-lg-7 col-xl-4">
                        <div class="shadow card">
                            <div class="py-3 text-center">
                                <h3 class="m-0 font-weight-bold text-primary">Create new Brand</h3>
                            </div>
                            <form class="user px-3">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="_id" name="_id" placeholder="ID" readonly>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="_name" name="_name" placeholder="Name">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="_logo" name="_logo" placeholder="Logo (URL)">
                                </div>
                                <div class="form-group">
                                    <input type="email" class="form-control" id="_email" name="_email" placeholder="Email">
                                </div>
                                <hr>
                                <div class="form-group row">
                                    <div class="col-sm-6">
                                        <button type="submit" class="btn btn-primary btn-block">
                                            Submit
                                        </button>
                                    </div>
                                    <div class="col-sm-6">
                                        <button type="reset" class="btn btn-secondary btn-block">
                                            Reset
                                        </button>
                                    </div>
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
