<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--===== header =====-->

<jsp:include page="./_header.jsp"></jsp:include>

<section class="page-title bg-overlay-black parallax page-title-bg">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h1>Thông báo</h1>
            </div>
        </div>
    </div>
</section>

<section class="page-section-ptb">
    <div class="container">
        <div class="align-content-center">
            <p class="text-center"><img  width="100px" id="logo_img" src="<%=request.getContextPath()%>/teamplate/public/images/checked.png" alt="logo"></p>
            <p class="text-success text-center">Bạn đã đặt lịch thành công</p>
        </div>
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