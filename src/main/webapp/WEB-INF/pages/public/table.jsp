<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--===== header =====-->

<jsp:include page="./_header.jsp"></jsp:include>

<section class="page-title bg-overlay-black parallax page-title-bg">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <%--                <h1>Thông báo</h1>--%>
            </div>
        </div>
    </div>
</section>

<section class="page-section-pt ">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 offset-lg-2 text-center title-line">
                <h2 class="slick-title">Tìm kiếm lịch khám</h2>
                <p>Hủy lịch khám</p>
            </div>
        </div>
    </div>
</section>

<section class="page-section-ptb">
    <div class="container">
        <div class="main-block bg-light">
            <div class="row">
                <div class="col-lg-12">
                    <div class="table-responsive cart-table border-radius bg-white mb-20">
                        <table class="table mb-0">
                            <thead>
                            <c:if test="${not empty msg}">
                                <div>
                                    <p class="text-success">
                                        <span class="text">${msg}</span>
                                    </p>
                                </div>
                            </c:if>
                            <c:choose>
                            <c:when test="${not empty listShift}">
                            <tr>
                                <th>Tên</th>
                                <th>Email</th>
                                <th>Số điện thoại</th>
                                <th>Hủy lịch</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listShift}" var="shift">
                            <tr>
                                <td>${shift.name}</td>
                                <td>${shift.email}</td>
                                <td>${shift.phone}</td>
                                <td class="remove-product"><a href="${pageContext.request.contextPath}/huy-lich/del?id=${shift.id}"><i class="fas fa-trash-alt"></i></a></td>
                            </tr>
                            </c:forEach>
                            </c:when>
                            <c:otherwise>
                            </thead>
                            <tbody>
                            <tr><p>Bạn chưa đặt lịch </p></tr>

                            </c:otherwise>
                            </c:choose>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mt-30">
            <div class="col-lg-4 col-md-6 offset-lg-4">


            </div>

            <c:if test="${not empty oder}">
                <div class="col-lg-4 col-md-6">
                    <div class="main-block">
                        <div class="filter-title">
                            <h5>In hóa đơn nhận thuốc</h5>
                        </div>
                        <table class="table table-borderless mb-0">
                            <tr class="border-theme">
                                <td>Tổng tiền</td>
                                <td class="float-right">${oder.total} đ</td>
                            </tr>
                            </tbody>
                        </table>
                        <div class="text-center"> <a class="btn black-button animated slideInRight" href="${pageContext.request.contextPath}/pay?id=${oder.id}">Xử lý tóa thuốc <i class="fas fa-arrow-alt-circle-right"></i></a> </div>
                    </div>
                </div>
            </c:if>

        </div>
    </div>
</section>

<jsp:include page="./_footer.jsp"></jsp:include>
<!--=====End Footer =====-->