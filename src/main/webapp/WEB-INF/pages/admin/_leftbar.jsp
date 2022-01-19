<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11/19/21
  Time: 10:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Shop Giay <sup>2</sup></div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/trang-chu">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Interface
    </div>



    <li id="categoryActive" class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/manager/medicinetype">
            <i class="fas fa-fw fa-cog"></i>
            <span>Loại thuốc</span></a>
    </li>

    <li id="productActive" class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/manager/medicine">
            <i class="fas fa-fw fa-cog"></i>
            <span>Thuốc</span></a>
    </li>

    <!-- tài khoản -->
    <li id="userActive" class="nav-item ">
        <a class="nav-link" href="${pageContext.request.contextPath}/manager/employee">
            <i class="fas fa-fw fa-table"></i>
            <span>Tài khoản</span></a>
    </li>


    <li id="oderActive" class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/manager/checkup">
            <i class="fas fa-fw fa-table"></i>
            <span>Thanh toán</span></a>
    </li>

    <li id="oderActive" class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/manager/shift">
            <i class="fas fa-fw fa-table"></i>
            <span>Kê toa</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">


    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>
</ul>
