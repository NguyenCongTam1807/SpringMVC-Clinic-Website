<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11/13/21
  Time: 9:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <title>Thống kê</title>

    <!-- Custom fonts for this template -->
    <link href="<%=request.getContextPath()%>/template/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath()%>/template/admin/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="<%=request.getContextPath()%>/template/admin/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/stats.js"></script>
</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <jsp:include page="../_leftbar.jsp"></jsp:include>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <jsp:include page="../_search.jsp"></jsp:include>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="row justify-content-md-around">
                <div class="col-md-3 text-center">
                    <canvas id="myCateStatsChart" ></canvas>
                </div>
                <table class="table col-md-8 text-center">
                    <tr>
                        <th>Mã loại thuốc</th>
                        <th>Tên loại thuốc</th>
                        <th>Số lượng thuốc</th>
                    </tr>
                    <c:forEach items="${medsByCate}" var="c">
                        <tr>
                            <td>${c[0]}</td>
                            <td>${c[1]}</td>
                            <td>${c[2]}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <hr style="height:10px;color:black;background-color:black">
            <div class="row">
                <div class="col-12 text-center">
                    <h3>Thống Kê Doanh Thu Theo Tháng Trong Năm</h3>
                    <br>
                    <form action="" class="form-inline justify-content-center">
                            <input type="text" placeholder="Nhập năm cần thống kê" name="year" class="form-control col-3" />
                            <input type="submit" value="Hiển thị" class="btn btn-success" />
                    </form>
                    <br>
                    <canvas id="myRevenueStatsChart" ></canvas>
                </div>
            </div>

        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Công Tâm CS92 - 2022</span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="login.html">Logout</a>
            </div>
        </div>
    </div>
</div>

<script>
    document.getElementById("statsActive").classList.add('active');
</script>
    <script>
        let cateLabels=[], cateInfo=[];
        <c:forEach items="${medsByCate}" var="c">
        cateLabels.push('${c[1]}')
        cateInfo.push(${c[2]})
        </c:forEach>
        drawCateChart("myCateStatsChart",cateLabels,cateInfo)

        let revenueInfo = [];
        <c:forEach items="${revenueByMonth}" var="c">
            revenueInfo.push(${c})
        </c:forEach>
        drawRevenueChart("myRevenueStatsChart",revenueInfo)

    </script>

<!-- Bootstrap core JavaScript-->
<script src="<%=request.getContextPath()%>/template/admin/vendor/jquery/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/template/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="<%=request.getContextPath()%>/template/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="<%=request.getContextPath()%>/template/admin/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="<%=request.getContextPath()%>/template/admin/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/template/admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="<%=request.getContextPath()%>/template/admin/js/demo/datatables-demo.js"></script>
</body>

</html>