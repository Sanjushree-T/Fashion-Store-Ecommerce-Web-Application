<%@ page import="java.util.List" %>
<%@ page import="com.fashionstore.model.Products" %>

<%
List<Products> productList =
(List<Products>) request.getAttribute("productList");

String keyword = request.getParameter("keyword");
String categoryId = request.getParameter("categoryId");
String minPrice = request.getParameter("minPrice");
String maxPrice = request.getParameter("maxPrice");
String sortBy = request.getParameter("sortBy");

if(keyword == null) keyword = "";
if(categoryId == null) categoryId = "0";
if(minPrice == null) minPrice = "";
if(maxPrice == null) maxPrice = "";
if(sortBy == null) sortBy = "";
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Fashion Store - Products</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/products.css?v=7">

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
          class="top-search">

        <input type="text"
               name="keyword"
               placeholder="Search products..."
               value="<%= keyword %>">

        <button type="submit">

            Search

        </button>

    </form>

    <!-- NAV LINKS -->

    <div class="nav-links">

        <a href="home">Home</a>

        <a href="products">Products</a>

        <a href="cart">Cart</a>

        <a href="login">Login</a>

    </div>

</div>

<!-- ===== HERO ===== -->

<div class="hero">

    <h1>

        Discover Your Style

    </h1>

    <p>

        Explore premium fashion collections
        with trendy and modern styles.

    </p>

</div>

<!-- ===== MAIN ===== -->

<div class="main">

    <!-- ===== FILTER ===== -->

    <div class="filter-box">

        <h2>

            Filters

        </h2>

        <form action="products"
              method="get">

            <!-- SEARCH -->

            <label>

                Search

            </label>

            <input type="text"
                   name="keyword"
                   placeholder="Search..."
                   value="<%= keyword %>">

            <!-- CATEGORY -->

            <label>

                Category

            </label>

            <select name="categoryId">

                <option value="0">

                    All Categories

                </option>

                <option value="1"
                <%= categoryId.equals("1")
                ? "selected" : "" %>>

                    Men

                </option>

                <option value="2"
                <%= categoryId.equals("2")
                ? "selected" : "" %>>

                    Women

                </option>

                <option value="3"
                <%= categoryId.equals("3")
                ? "selected" : "" %>>

                    Kids

                </option>

                <option value="4"
                <%= categoryId.equals("4")
                ? "selected" : "" %>>

                    Footwear

                </option>

                <option value="5"
                <%= categoryId.equals("5")
                ? "selected" : "" %>>

                    Accessories

                </option>

            </select>

            <!-- PRICE -->

            <label>

                Price Range

            </label>

            <div class="price-row">

                <input type="number"
                       name="minPrice"
                       placeholder="Min"
                       value="<%= minPrice %>">

                <input type="number"
                       name="maxPrice"
                       placeholder="Max"
                       value="<%= maxPrice %>">

            </div>

            <!-- SORT -->

            <label>

                Sort By

            </label>

            <select name="sortBy">

                <option value="">

                    Default

                </option>

                <option value="lowToHigh"
                <%= sortBy.equals("lowToHigh")
                ? "selected" : "" %>>

                    Price Low To High

                </option>

                <option value="highToLow"
                <%= sortBy.equals("highToLow")
                ? "selected" : "" %>>

                    Price High To Low

                </option>

            </select>

            <!-- BUTTONS -->

            <div class="btn-row">

                <button type="submit"
                        class="apply-btn">

                    Apply

                </button>

                <button type="button"
                        class="clear-btn"
                        onclick="window.location.href='products'">

                    Clear

                </button>

            </div>

        </form>

    </div>

    <!-- ===== PRODUCTS ===== -->

    <div class="products-area">

        <div class="products-header">

            <h2>

                All Products

            </h2>

            <p>

                <%= productList != null
                ? productList.size() : 0 %>

                Products

            </p>

        </div>

        <!-- ===== GRID ===== -->

        <div class="product-grid">

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

                        <button type="submit"
                                class="cart-btn">

                            Add To Cart

                        </button>

                    </form>

                </div>

            </div>

<%

    }

} else {

%>

            <h2 class="no-products">

                No Products Found

            </h2>

<%

}

%>

        </div>

    </div>

</div>

</body>

</html>