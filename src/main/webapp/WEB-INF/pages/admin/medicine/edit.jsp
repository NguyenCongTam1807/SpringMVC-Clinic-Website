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
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Tables</title>

    <!-- Custom fonts for this template -->
    <link href="<%=request.getContextPath()%>/template/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath()%>/template/admin/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="<%=request.getContextPath()%>/template/admin/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <style>
        .error{
            padding-left: 5px;
            font-size: 20px;
            color: red;
        }
        .gallery img{
            width: 300px;
        }
    </style>
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
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-7 col-lg-8">
                    <h4 class="mb-3">S???a thu???c</h4>

                    <s:form method="POST" id="edit-medicine" modelAttribute="medicine" action="${pageContext.request.contextPath}/manager/medicine/edit" enctype="multipart/form-data">
                        <!-- input text code -->
                        <s:hidden path="id" class="form-control" />
                        <!-- input text code-->
                        <div class="table-responsive">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="" name="name">T??n thu???c</span>
                                </div>
                                <s:input path="name" class="form-control" />
                            </div>
                        </div>
                        <div class="table-responsive">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="" name="price">Gi??</span>
                                </div>
                                <s:input path="price" class="form-control" />
                            </div>
                        </div>
                        <div class="table-responsive">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="" name="description">M?? t???</span>
                                </div>
                                <s:textarea path="description" class="form-control"/>
                            </div>
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">???nh</span>
                            </div>
                            <div class="custom-file">
                                <!-- id="inputGroupFile01 -->
                                <input type="file" id="gallery-photo-add" multiple="multiple" name="file" class="custom-file-input"/>
                                <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
                            </div>
                        </div>

                        <div class="table-responsive">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="">Lo???i thu???c</span>
                                </div>
                                <s:select path="medicine_type.id" class="custom-select" id="inputGroupSelect01">
                                    <c:forEach items="${listMedicineType}" var="medicineType">
                                        <s:option value="${medicineType.id}">${medicineType.name}</s:option>
                                    </c:forEach>
                                </s:select>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary" onclick="addMedicine()">S???a</button>
                    </s:form>
                </div>
                    <div class="col-5 col-md-5 col-lg-4">
                        <div class="gallery">
                            <c:if test="${not empty listImg}">
                                <c:forEach items="${listImg}" var="img">
                                    <img src="${pageContext.request.contextPath}/img/${img.name}" alt="">
                                </c:forEach>
                            </c:if>

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
                    <span>Copyright &copy; C??ng T??m CS92 - 2022</span>
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
                    <span aria-hidden="true">??</span>
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

<!-- Bootstrap core JavaScript-->
<script src="<%=request.getContextPath()%>/template/admin/vendor/jquery/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/template/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="<%=request.getContextPath()%>/template/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="<%=request.getContextPath()%>/template/admin/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="<%=request.getContextPath()%>/template/admin/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/template/admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="<%=request.getContextPath()%>/template/admin/js/demo/datatables-demo.js"></script>
<script type="text/javascript">
    $(function() {

        // Multiple images preview in browser
        var imagesPreview = function(input, placeToInsertImagePreview) {

            if (input.files) {
                var filesAmount = input.files.length;

                for (i = 0; i < filesAmount; i++) {
                    var reader = new FileReader();

                    reader.onload = function(event) {
                        $($.parseHTML('<img>')).attr('src', event.target.result).appendTo(placeToInsertImagePreview);
                    }

                    reader.readAsDataURL(input.files[i]);
                }
                jQuery('.gallery').html('');
            }

        };

        $('#gallery-photo-add').on('change', function() {
            imagesPreview(this, 'div.gallery');
        });
    });
</script>
<script type="text/javascript">

    function addMedicine()
    {
        $("#edit-medicine").validate({
            rules:
                {
                    "name": {
                        required: true,
                        normalizer: function(value) {
                            return $.trim(value);
                        }
                    },
                    "price":{
                        required:true,
                        number: true,
                    },
                    "description":{
                        required:true,
                        maxlength: 255,
                    },
                },
            messages:
                {
                    "name": {
                        required: "Vui l??ng nh???p t??n thu???c"
                    },
                    "price":{
                        required: "Vui l??ng nh???p gi?? ti???n",
                        number: "Nh???p sai ?????nh d???ng"
                    },
                    "description":{
                        required:"Vui l??ng nh???p m?? t???",
                        maxlength: "T???i ??a 255 k?? t???",
                    },
                }
        });
    }
</script>
</body>

</html>