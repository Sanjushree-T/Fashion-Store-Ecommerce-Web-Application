<%@ page import="java.util.List" %>
<%@ page import="com.fashionstore.model.CartItems" %>
<%@ page import="com.fashionstore.dao.impl.ProductsDAOImpl" %>
<%@ page import="com.fashionstore.model.Products" %>

<%
List<CartItems> cartList =
(List<CartItems>) request.getAttribute("cartList");

ProductsDAOImpl dao =
new ProductsDAOImpl();

double grandTotal = 0;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Cart</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/cart.css?v=3">

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
rel="stylesheet">
</head>

<body>

<div class="navbar">
    <div class="logo">Fashion Store</div>

    <div class="nav-links">
        <a href="home">Home</a>
        <a href="products">Products</a>
        <a href="cart">Cart</a>
    </div>
</div>

<div class="cart-container">

    <h1>Shopping Cart</h1>

<%
if(cartList != null && !cartList.isEmpty()) {

    for(CartItems item : cartList) {

        Products product =
        dao.getProductById(item.getVariantId());

        double total =
        product.getPrice() * item.getQuantity();

        grandTotal += total;
%>

    <div class="cart-card">

        <div class="cart-image">
            <img src="${pageContext.request.contextPath}/assets/images/<%= product.getImageUrl() %>"
                 alt="<%= product.getProductName() %>">
        </div>

        <div class="cart-info">
            <h2><%= product.getProductName() %></h2>
            <p>Brand : <%= product.getBrand() %></p>

            <div class="price">
                &#8377; <%= product.getPrice() %>
            </div>
        </div>

        <div class="quantity-section">
            <form action="update-cart" method="post">

                <input type="hidden"
                       name="cartItemId"
                       value="<%= item.getCartItemId() %>">

                <input type="number"
                       name="quantity"
                       value="<%= item.getQuantity() %>"
                       min="1">

                <button type="submit">Update</button>
            </form>
        </div>

        <div class="total-section">
            &#8377; <%= total %>
        </div>

        <div class="remove-section">
            <form action="remove-cart-item" method="post">

                <input type="hidden"
                       name="cartItemId"
                       value="<%= item.getCartItemId() %>">

                <button type="submit" class="remove-btn">
                    Remove
                </button>
            </form>
        </div>

    </div>

<%
    }
%>

    <div class="cart-summary">

        <div class="grand-total">
            Grand Total : &#8377; <%= grandTotal %>
        </div>

        <a href="checkout" class="checkout-btn">
    		Book Now
		</a>

    </div>

<%
} else {
%>

    <h2 class="empty-cart">Your Cart Is Empty</h2>

    <a href="products" class="checkout-btn">
        Continue Shopping
    </a>

<%
}
%>

</div>

</body>
</html>