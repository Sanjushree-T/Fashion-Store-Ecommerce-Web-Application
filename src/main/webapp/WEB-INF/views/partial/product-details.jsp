<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ page import="com.fashionstore.model.Products" %>

<%
    Products product =
        (Products) request.getAttribute("product");
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Product Details</title>

    <!-- CSS -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/product-details.css">

</head>

<body>

    <div class="product-details-container">

        <!-- PRODUCT IMAGE -->
        <div class="product-image">

            <img src="<%= product.getImageUrl() %>"
                 alt="Product Image">

        </div>

        <!-- PRODUCT INFO -->
        <div class="product-info">

            <h1>
                <%= product.getProductName() %>
            </h1>

            <h3>
                Brand :
                <%= product.getBrand() %>
            </h3>

            <h2>
                ₹ <%= product.getPrice() %>
            </h2>

            <p>
                <%= product.getDescription() %>
            </p>

            <!-- BUTTONS -->
            <div class="button-group">

                <a href="#"
                   class="cart-btn">

                    Add To Cart

                </a>

                <a href="#"
                   class="buy-btn">

                    Buy Now

                </a>

            </div>

        </div>

    </div>

</body>

</html>