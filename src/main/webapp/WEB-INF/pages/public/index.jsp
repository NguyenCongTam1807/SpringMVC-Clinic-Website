<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="./_header.jsp"></jsp:include>

<!--===== End header =====-->

<!--===== Banner Slider =====-->

<section id="main-slider">
  <div id="demo" class="carousel slide" data-ride="carousel">

    <div class="carousel-inner">

      <div class="carousel-inner">
        <div class="carousel-item active"> <img class="img-fluid" src="<%=request.getContextPath()%>/teamplate/public/images/banner/14.jpg" alt="Banner 1">
          <div class="slider-content">
            <div class="container">
              <div class="row">
                <div class="col-xl-6 col-md-12 col-sm-12 offset-xl-6 bottom-15">
                  <div class="p-5 xx-p-15 text-right">
                    <h1 class="text-uppercase animated lightSpeedIn"><span>Phòng Khám</span> Tư Nhân </h1>
                    <p class="mt-20 mb-30 animated slideInLeft">Sức khỏe là vàng</p>
                    <a class="btn theme-button animated slideInRight" href="${pageContext.request.contextPath}/dat-lich">Đặt lịch hẹn </a> </div>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>
    </div>
  </div>

</section>
<!--===== Best Selling Section =====-->
<br><br><br>
<!--=====End Best Selling Section =====-->

<!--===== Our Service Section =====-->
<section class="page-section-pt">
  <div class="container">
    <div class="row">
      <div class="col-lg-4 col-md-4 text-center hvr-wobble-horizontal"> <img class="img-fluid service-img" src="<%=request.getContextPath()%>/teamplate/public/images/fast-delivery.png" alt="">
        <h4>Fast Shipping</h4>
        <p>Lorem ipsum dolor sit amet</p>
      </div>
      <div class="col-lg-4 col-md-4 text-center hvr-wobble-horizontal"> <img class="img-fluid service-img" src="<%=request.getContextPath()%>/teamplate/public/images/credit-card.png" alt="">
        <h4>Secure Payment</h4>
        <p>Lorem ipsum dolor sit amet</p>
      </div>
      <div class="col-lg-4 col-md-4 text-center hvr-wobble-horizontal"> <img class="img-fluid service-img" src="<%=request.getContextPath()%>/teamplate/public/images/customer-service.png" alt="">
        <h4>Customer Support</h4>
        <p>Lorem ipsum dolor sit amet</p>
      </div>
    </div>
  </div>
</section>
<!--=====End Our Section =====-->

<!--===== Subscribe Section =====-->
<section class="page-section-ptb">
  <div class="parallax-subscribe">
    <div class="container">
      <div class="row">
        <div class="col-lg-8 offset-lg-2 text-center mb-50 pt-50 xs-mb-0">
          <h2 class="slick-title text-white">Subscribe Us</h2>
          <p class="text-white">Lorem ipsum dolor sit amet adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magn a aliqua. Ut enim ad minim veniam quis nostrud acuad.</p>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-6 offset-lg-3 text-center pb-50">
          <div class="subscribe-form">
            <form>
              <input class="form-control" type="email" value="" name="EMAIL" placeholder="Enter your email">
              <button class="btn btn-sub" type="submit" value="Subscribe" name="subscribe">Subscribe</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<!--=====End Subscribe Section =====-->

<!--=====Blog Section =====-->

<!--=====End Blog Section =====-->

<!--=====Our Brands Section =====-->
<section class="page-section-ptb">
  <div class="container">
    <div class="row">
      <div class="col-lg-2 col-md-2 col-sm-4 col-4"> <img class="img-fluid brand" src="<%=request.getContextPath()%>/teamplate/public/images/brands/01.png" alt="">
        <div class="blog-effect"></div>
      </div>
      <div class="col-lg-2 col-md-2 col-sm-4 col-4"> <img class="img-fluid brand" src="<%=request.getContextPath()%>/teamplate/public/images/brands/02.png" alt=""> </div>
      <div class="col-lg-2 col-md-2 col-sm-4 col-4"> <img class="img-fluid brand" src="<%=request.getContextPath()%>/teamplate/public/images/brands/03.png" alt=""> </div>
      <div class="col-lg-2 col-md-2 col-sm-4 col-4"> <img class="img-fluid brand" src="<%=request.getContextPath()%>/teamplate/public/images/brands/04.png" alt=""> </div>
      <div class="col-lg-2 col-md-2 col-sm-4 col-4"> <img class="img-fluid brand" src="<%=request.getContextPath()%>/teamplate/public/images/brands/05.png" alt=""> </div>
      <div class="col-lg-2 col-md-2 col-sm-4 col-4"> <img class="img-fluid brand" src="<%=request.getContextPath()%>/teamplate/public/images/brands/06.png" alt=""> </div>
    </div>
  </div>
</section>
<!--=====End Our Brands Section =====-->

<!--===== Footer =====-->
<jsp:include page="./_footer.jsp"></jsp:include>
<!--=====End Footer =====-->