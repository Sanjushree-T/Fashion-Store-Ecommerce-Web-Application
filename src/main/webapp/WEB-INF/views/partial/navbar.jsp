<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<header class="navbar">
    
    <div class="container navbar-container">

        <div class="logo">
            <a href="${pageContext.request.contextPath}/home">
                Fashion Store
            </a>
        </div>

        <form class="search-bar"
              action="${pageContext.request.contextPath}/products"
              method="get">

            <input type="text"
                   name="keyword"
                   placeholder="Search for fashion products...">

            <button type="submit">
                Search
            </button>
        </form>

        <nav class="nav-links">
            <a href="${pageContext.request.contextPath}/home">
                Home
            </a>

            <a href="${pageContext.request.contextPath}/products">
                Products
            </a>

            <a href="${pageContext.request.contextPath}/cart">
                Cart
            </a>

            <a href="${pageContext.request.contextPath}/login">
                Login
            </a>
        </nav>

    </div>

</header>