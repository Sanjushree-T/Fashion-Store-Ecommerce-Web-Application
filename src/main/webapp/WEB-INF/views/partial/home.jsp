<%@ page import="java.util.List" %>
<%@ page import="com.fashionstore.model.Products" %>

<%
List<Products> productList =
(List<Products>) request.getAttribute("productList");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Fashion Store</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/home.css?v=5">

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
rel="stylesheet">

</head>

<body>

<!-- ===== NAVBAR ===== -->

<div class="navbar">

    <div class="logo">

        Fashion Store

    </div>

    <!-- SEARCH -->

    <form action="products"
          method="get"
          class="search-box">

        <input type="text"
               name="keyword"
               placeholder="Search fashion products...">

        <button type="submit">

            Search

        </button>

    </form>

    <!-- LINKS -->

    <div class="nav-links">

        <a href="home">Home</a>

        <a href="products">Products</a>

        <a href="cart">Cart</a>

        <a href="login">Login</a>

    </div>

</div>

<!-- ===== HERO ===== -->

<div class="hero">

    <div class="hero-content">

        <h1>

            Upgrade Your Fashion Style

        </h1>

        <p>

            Discover premium collections,
            trending outfits and modern fashion.

        </p>

        <a href="products"
           class="shop-btn">

            Shop Now

        </a>

    </div>

</div>

<!-- ===== CATEGORY SECTION ===== -->

<div class="section-title">

    Shop By Categories

</div>

<div class="category-container">

    <a href="products?categoryId=1"
       class="category-card">

        <h2>Men</h2>

        <p>

            Premium mens fashion collection

        </p>

    </a>

    <a href="products?categoryId=2"
       class="category-card">

        <h2>Women</h2>

        <p>

            Luxury and trending outfits

        </p>

    </a>

    <a href="products?categoryId=3"
       class="category-card">

        <h2>Kids</h2>

        <p>

            Stylish kids fashion wear

        </p>

    </a>

    <a href="products?categoryId=4"
       class="category-card">

        <h2>Footwear</h2>

        <p>

            Modern shoes and sneakers

        </p>

    </a>

    <a href="products?categoryId=5"
       class="category-card">

        <h2>Accessories</h2>

        <p>

            Luxury fashion accessories

        </p>

    </a>

</div>

<!-- ===== FEATURED PRODUCTS ===== -->

<div class="section-title">

    Featured Products

</div>

<div class="products-grid">

<%

if(productList != null &&
   !productList.isEmpty()) {

    for(Products product : productList) {

%>

    <!-- PRODUCT CARD -->

    <div class="product-card">

        <!-- IMAGE -->

        <div class="product-image">

            <img
            src="${pageContext.request.contextPath}/assets/images/<%= product.getImageUrl() %>"

            alt="<%= product.getProductName() %>">

        </div>

        <!-- INFO -->

        <div class="product-info">

            <h3>

                <%= product.getProductName() %>

            </h3>

            <p>

                <%= product.getBrand() %>

            </p>

            <div class="price">

                &#8377; <%= product.getPrice() %>

            </div>

            <!-- ADD TO CART -->

            <form action="add-to-cart"
                  method="post">

                <input type="hidden"
                       name="productId"
                       value="<%= product.getProductId() %>">

                <button type="submit">

                    Add To Cart

                </button>

            </form>

        </div>

    </div>

<%

    }

} else {

%>

    <h2 style="text-align:center;
               color:white;
               margin-top:50px;">

        No Products Available

    </h2>

<%

}

%>

</div>

</body>

</html>