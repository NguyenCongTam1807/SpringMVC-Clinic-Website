<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <title>SB Admin 2 - Login</title>

    <!-- Custom fonts for this template-->
    <link href="<%=request.getContextPath()%>/template/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<%=request.getContextPath()%>/template/admin/css/sb-admin-2.min.css" rel="stylesheet">
    <style>
        .error{
            padding-top: 5px;
            font-size: 20px;
            color: red;
        }
        input{
            display: block;
        }
    </style>
</head>

<body class="bg-gradient-primary">

<div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

        <div class="col-xl-10 col-lg-12 col-md-9">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                        <div class="col-lg-7">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Đăng ký tài khoản</h1>
                                </div>
                                <form id="register" action="${pageContext.request.contextPath}/dang-ky" method="post" class="user">

                                    <div class="form-group row">
                                        <div class="col-sm-6 mb-3 mb-sm-0">
                                            <input type="text" name="name" class="form-control form-control-user"
                                                   id="exampleInputUserName" placeholder="Họ và tên">
                                        </div>
                                        <div class="col-sm-6">
                                            <input type="text" name="email" class="form-control form-control-user"
                                                   id="exampleEmail" placeholder="Email">
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <div class="col-sm-6 mb-3 mb-sm-0">
                                            <input type="password" name="password" class="form-control form-control-user"
                                                   id="exampleInputPassword" placeholder="Mật khẩu">
                                        </div>
                                        <div class="col-sm-6">
                                            <input type="password" name="repassword" class="form-control form-control-user"
                                                   id="exampleRepeatPassword" placeholder="Nhập lại mật khẩu">
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <div class="col-sm-12 mb-6 mb-sm-0">
                                            <input type="text" name="address" class="form-control form-control-user"
                                                   id="exampleAdress" placeholder="Địa chỉ">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-sm-6 mb-3 mb-sm-0">
                                            <input type="tel" name="phone" class="form-control form-control-user"
                                                   id="examplePhoneNumber" placeholder="Số điện thoại">
                                        </div>
                                    </div>
                                    <button type="submit" onclick="submitForm();" class="btn btn-primary btn-user btn-block">
                                        Đăng ký
                                    </button>
                                </form>
                                <hr>
                                <div class="text-center">
                                    <a class="small" href="${pageContext.request.contextPath}/dang-nhap">Bạn đã có tài khoản</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<!-- Bootstrap core JavaScript-->
<script src="<%=request.getContextPath()%>/template/admin/vendor/jquery/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
<script src="<%=request.getContextPath()%>/template/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="<%=request.getContextPath()%>/template/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="<%=request.getContextPath()%>/template/admin/js/sb-admin-2.min.js"></script>
</body>



<script type="text/javascript">

    function submitForm()
    {
        $("#register").validate({
            rules:
                {
                    "name":
                        {
                            required: true
                        },
                    "email":{
                        required: true,
                        email: true
                    },
                    "password":{
                        required:true,
                        minlength: 6,
                        maxlength: 30,
                    },
                    "repassword":{
                        required:true,
                        minlength: 6,
                        maxlength: 30,
                        equalTo: '[name="password"]'
                    },
                    "address":{
                      required:true,
                      maxlength:255
                    },
                    "phone":{
                        required:true,
                        exactlength:10
                    }
                },
            messages:
                {
                    "name":
                        {
                            required: "Vui lòng nhập họ tên"
                        },
                    "email":{
                        required: "Vui lòng nhập email",
                        email: "Nhập sai định dạng"
                    },
                    "password":{
                        required:"Vui lòng nhập mật khẩu",
                        minlength: "Tối thiểu 6 kí tự",
                        maxlength: "Tối đa 30 kí tự",
                    },
                    "repassword":{
                        required: "Vui lòng nhập lại mật khẩu",
                        minlength: "Tối thiểu 6 kí tự",
                        maxlength: "Tối đa 30 kí tự",
                        equalTo: "Mật khẩu không khớp"
                    },
                    "address":{
                        required:"Vui lòng nhập địa chỉ",
                        maxlength:"Tối đa 255 ký tự",
                    },
                    "phone":{
                        required:"Vui lòng nhập số điện thoại",
                        exactlength:"Vui lòng nhập đúng 10 chữ số"
                    }
                }
        });
    }
</script>

</html>