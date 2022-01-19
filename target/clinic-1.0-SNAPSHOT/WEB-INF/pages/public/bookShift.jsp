<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--===== header =====-->

<jsp:include page="./_header.jsp"></jsp:include>

<section class="page-title bg-overlay-black parallax page-title-bg">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h1>Danh sách bệnh nhân</h1>
            </div>
        </div>
    </div>
</section>

<section class="page-section-ptb">
    <div class="container">
        <c:if test="${not empty roomActive}">
            <h3 class="text-danger">${roomActive}</h3>
        </c:if>
        <div class="row">
            <c:forEach items="${listRoom}" var="room">
                <c:choose>
                    <c:when test="${room.active == 0}">
                        <div class="col-4">
                            <form action="${pageContext.request.contextPath}/benh-nhan?id=${room.id}" method="post">
                                <input class="btn btn-block btn-dark  pt-4 pb-4 mb-4" onclick="return myFunction()" type="submit" value="${room.name}">
                            </form>
                        </div>
                    </c:when>
                    <c:when test="${room.active == 1}">
                        <div class="col-4">
                            <form action="${pageContext.request.contextPath}/benh-nhan?id=${room.id}" method="post">
                                <input class="btn btn-block btn-dark pt-4 pb-4 mb-4" type="submit" value="${room.name}" disabled>
                            </form>
                        </div>
                    </c:when>
                    <c:otherwise>

                    </c:otherwise>
                </c:choose>

            </c:forEach>

        </div>
    </div>
</section>

<script>
    function myFunction() {
        // document.getElementById("demo").innerHTML = "Hello World";
        confirm("Bạn sẻ kê toa cho bệnh nhân này!!!");
    }
</script>

<jsp:include page="./_footer.jsp"></jsp:include>
<!--=====End Footer =====-->