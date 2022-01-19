<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--===== header =====-->

<jsp:include page="./_header.jsp"></jsp:include>

<section class="page-title bg-overlay-black parallax page-title-bg">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
            </div>
        </div>
    </div>
</section>

<section class="page-section-pt ">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 offset-lg-2 text-center title-line">
                <h2 class="slick-title">Tìm kiếm lịch khám</h2>
                <c:if test="${not empty msg}">
                    <p class="text-success">Hủy lịch khám thành công</p>
                </c:if>

            </div>
        </div>
    </div>
</section>

<section class="page-section-ptb">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 offset-lg-2">
                <div class="review-form">
                    <form action="<%=request.getContextPath()%>/huy-lich" method="post">
                        <div class="row">

                            <div class="col-lg-12">
                                <div class="form-group">
                                    <input type="text" name="search" class="form-control" placeholder="Họ và tên">
                                </div>
                            </div>
                        </div>
                        <button type="submit" class="btn theme-button animated slideInRight" href="javascript:void(0)">Tìm kiếm </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="./_footer.jsp"></jsp:include>
<!--=====End Footer =====-->