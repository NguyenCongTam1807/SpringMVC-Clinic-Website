<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Phòng khám tư nhân - Công Tâm - CS92</title>

    <!-- Favicon -->
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/template/public/images/favicon.ico">

    <!-- font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

    <!-- Plugins -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/template/public/css/plugins-css.css">

    <!-- Typography -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/template/public/css/typography.css">

    <!-- Style -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/template/public/css/style.css">

    <!-- Responsive -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/template/public/css/responsive.css">
</head>

<body>

<!--===== header start =====-->

<body class="wrapper">

    <!--===== header =====-->

<header id="header" class="header default fullWidth">
    <!--===== mega menu =====-->
    <div class="menu">
        <!-- menu start -->
        <nav id="menu" class="mega-menu">
            <!-- menu list items container -->
            <section class="menu-list-items">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 col-md-12">
                            <!-- menu logo -->
                            <ul class="menu-logo">
                                <li> <a href="${pageContext.request.contextPath}/trang-chu"><img id="logo_img" src="<%=request.getContextPath()%>/template/public/images/logo.png" alt="logo"> </a> </li>
                            </ul>
                            <!-- menu links -->
                            <div class="menu-bar">
                                <ul class="menu-links">
                                    <li><a href="${pageContext.request.contextPath}/trang-chu" data-hover="TRANG&nbsp;CHỦ">Trang chủ<i class="fa fa-angle-down d-sm-none set-icon"></i></a> </li>
                                    <c:choose>
                                        <c:when test="${employee == null}">
                                            <li><a href="${pageContext.request.contextPath}/dat-lich" data-hover="Đặt&nbsp;lịch">Đặt&nbsp;lịch<i class="fa fa-angle-down d-lg-none set-icon"></i></a></li>
                                            <li><a href="${pageContext.request.contextPath}/huy-lich" data-hover="Hủy&nbsp;lịch">Hủy lịch</a></li>
                                            <li><a href="${pageContext.request.contextPath}/dang-nhap" data-hover="Đăng&nbsp;nhập">Đăng nhập</a></li>
                                            <li><a href="${pageContext.request.contextPath}/dang-ky" data-hover="Đăng&nbsp;ký">Đăng ký</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <c:if test="${employee.userType == 0}">
                                                <li><a href="${pageContext.request.contextPath}/manager/medicinetype" data-hover="Quản&nbsp;Lý">Quản Lý</a></li>
                                            </c:if>
                                            <c:if test="${employee.userType == 1}">
                                                <li>
                                                    <a href="javascript:void(0)" data-hover="Loại&nbsp;thuốc">Loại&nbsp;thuốc<i class="fa fa-angle-down d-lg-none set-icon"></i></a>
                                                    <!-- drop down multilevel  -->
                                                    <ul class="drop-down-multilevel">
                                                        <c:forEach items="${listCategory}" var="category">
                                                            <li><a href="${pageContext.request.contextPath}/danh-muc/${category.id}">${category.name}</a></li>
                                                        </c:forEach>
                                                    </ul>
                                                </li>
                                                <li><a href="${pageContext.request.contextPath}/benh-nhan" data-hover="Bệnh&nbsp;nhân">Bệnh&nbsp;nhân<i class="fa fa-angle-down d-lg-none set-icon"></i></a></li>
                                            </c:if>
                                            <li><a href="${pageContext.request.contextPath}/thong-tin" data-hover="Đổi&nbsp;mật&nbsp;khẩu">Đổi mật khẩu</a></li>
                                            <li><a href="${pageContext.request.contextPath}/dang-xuat" data-hover="Đăng&nbsp;xuất">Đăng&nbsp;xuất</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </ul>

                                <!-- Shopping cart -->
                                <div class="search-cart">

                                    <c:if test="${employee.userType == 1}">
                                        <div class="search"> <a class="search-btn" href="#search"></a> </div>
                                        <c:choose>
                                            <c:when test="${not empty listCart}">
                                                <div class="shpping-cart"> <a class="cart-btn txt-white" href="${pageContext.request.contextPath}/gio-hang">Kê toa<strong class="item">${listCart.size()}</strong></a>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="shpping-cart"> <a class="cart-btn txt-white" href="${pageContext.request.contextPath}/gio-hang">Kê toa<strong class="item">0</strong></a>
                                            </c:otherwise>
                                        </c:choose>
                                        <div class="cart">
                                            <c:choose>
                                                <c:when test="${not empty listCart}">
                                                    <c:forEach items="${listCart}" var="cart">
                                                        <div class="cart-item">
                                                            <div class="cart-image"> <img class="img-fluid" src="${pageContext.request.contextPath}/img/${cart.medicine.imgs[0].name}" alt=""> </div>
                                                            <div class="cart-name clearfix"> <a href="${pageContext.request.contextPath}/san-pham/${cart.medicine.id}">${cart.medicine.name} </a>
                                                                <div class="cart-name"></div>
                                                                <div class="cart-name"><small>x ${cart.quantity}</small></div>
                                                                <div class="cart-price"> <ins>${cart.unitPrice} đ </ins> </div>
                                                            </div>
                                                        </div>
                                                        <%--                                                        <div class="cart-close"> <a href="javascript:void(0)"> <i class="fa fa-times-circle"></i> </a> </div>--%>
                                                    </c:forEach>

                                                    <div class="cart-total">
                                                        <h6 class="mb-15"> Tổng tiền: ${order.total} đ</h6>
                                                        <div class="d-flex justify-content-center mb-10"> <a class="btn theme-button" href="${pageContext.request.contextPath}/gio-hang">Xem giỏ hàng</a> </div>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="cart-total">
                                                        <h6 class="mb-15"> Chưa có toa thuốc</h6>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>

                                        </div>
                                        </div>

                                        <div id="search">
                                        <button type="button" class="close">×</button>
                                        <form action="${pageContext.request.contextPath}/tim-kiem" method="post">
                                            <input type="search" name="search" placeholder="Search for products">
                                            <button type="submit" class="btn btn-primary"><span class="ti-search"></span></button>
                                        </form>
                                    </div>
                                    </c:if>

<%--                                    <div class="more"> <a class="more-btn txt-white" href="javascript:void(0)"> <i class="fas fa-align-left"></i></a>--%>
<%--                                        <div class="sub-more">--%>
<%--                                            <ul class="my-account">--%>
<%--                                                <c:choose>--%>
<%--                                                    <c:when test="${employee == null}">--%>
<%--                                                        <li><a href="${pageContext.request.contextPath}/huy-lich">Hủy lịch khám</a></li>--%>
<%--                                                        <li><a href="${pageContext.request.contextPath}/dang-nhap">Đăng&nbsp;nhập</a></li>--%>
<%--                                                        <li><a href="${pageContext.request.contextPath}/dang-ky">Đăng&nbsp;ký</a></li>--%>
<%--                                                    </c:when>--%>
<%--                                                    <c:otherwise>--%>
<%--                                                        <c:if test="${employee.userType == 0}">--%>
<%--                                                            <li><a href="${pageContext.request.contextPath}/manager/medicinetype">Quyền Admin</a></li>--%>
<%--                                                        </c:if>--%>
<%--&lt;%&ndash;                                                        <c:if test="${employee.userType == 1}">&ndash;%&gt;--%>

<%--&lt;%&ndash;                                                        </c:if>&ndash;%&gt;--%>
<%--                                                        <li><a href="${pageContext.request.contextPath}/thong-tin">Đổi mật khẩu</a></li>--%>
<%--                                                        <li><a href="${pageContext.request.contextPath}/dang-xuat">Đăng&nbsp;xuất</a></li>--%>
<%--                                                    </c:otherwise>--%>
<%--                                                </c:choose>--%>
<%--                                            </ul>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </div>
            </section>
        </nav>
        <!-- menu end -->
    </div>
</header>
</body>