<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--===== header =====-->

<jsp:include page="./_header.jsp"></jsp:include>

<section class="page-title bg-overlay-black parallax page-title-bg">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h1>Đặt lịch khám</h1>
            </div>
        </div>
    </div>
</section>

<section class="page-section-ptb">
    <div class="container">
        <section class="page-section-pt mb-50">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 offset-lg-2">
                        <div class="row">
                            <div class="col-lg-4 col-md-4 text-center hvr-wobble-horizontal"> <img class="img-fluid service-img" src="<%=request.getContextPath()%>/teamplate/public/images/address.png" alt="">
                                <h4>Address</h4>
                                <p>xyz, Đà Nẵng</p>
                            </div>
                            <div class="col-lg-4 col-md-4 text-center hvr-wobble-horizontal"> <img class="img-fluid service-img" src="<%=request.getContextPath()%>/teamplate/public/images/phone.png" alt="">
                                <h4>Phone</h4>
                                <p>+(012) 456 789</p>
                            </div>
                            <div class="col-lg-4 col-md-4 text-center hvr-wobble-horizontal"> <img class="img-fluid service-img" src="<%=request.getContextPath()%>/teamplate/public/images/mail.png" alt="">
                                <h4>Email</h4>
                                <p>info@demo.com</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--===== Contact Section =====-->

        <!--===== Contact form Section =====-->
        <section class="page-section-pb">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 offset-lg-2">
                        <div class="review-form">
                            <form action="<%=request.getContextPath()%>/dat-lich" method="post">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <input type="text" name="name" class="form-control" placeholder="Họ và tên">
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <input type="email" name="email" class="form-control" placeholder="Email">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <div class="form-group">
                                            <input type="text" name="phone" class="form-control" placeholder="Số điện thoại">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <select class="custom-select form-group" style="background-color: #F2F2F2;border: none" name="time" id="">
                                            <option value="">--- Chọn lịch hẹn ---</option>
                                            <c:forEach items="${listatime}" var="atime">
                                                <c:if test="${atime.active == 0}">
                                                    <option value="${atime.id}">${atime.timeName}</option>
                                                </c:if>
                                                <c:if test="${atime.active == 1}">
                                                    <option value="${atime.id}" disabled>${atime.timeName}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
<%--                                        <div class="form-group">--%>
<%--                                            <input type="text" class="form-control" placeholder="Số điện thoại">--%>
<%--                                        </div>--%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <textarea name="message" class="form-control" rows="5" placeholder="Chú thích (nếu có)"></textarea>
                                </div>
                                <button type="submit" class="btn theme-button animated slideInRight" href="javascript:void(0)">Đặt lịch </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</section>

<script>
    function myFunction() {
        // document.getElementById("demo").innerHTML = "Hello World";
        confirm("Bạn chắc chọn phòng này!!!");
    }
</script>

<jsp:include page="./_footer.jsp"></jsp:include>
<!--=====End Footer =====-->