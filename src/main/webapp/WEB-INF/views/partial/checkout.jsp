<%@ page import="com.fashionstore.model.Users" %>

<%
Users user =
(Users) session.getAttribute("loggedUser");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Checkout</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/checkout.css?v=1">

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

<div class="checkout-container">

    <div class="checkout-card">

        <h1>Checkout</h1>

        <p class="subtitle">
            Confirm your delivery details and place your order.
        </p>

        <form action="place-order" method="post">

            <div class="input-row">

                <div class="input-group">
                    <label>Full Name</label>
                    <input type="text"
                           name="deliveryName"
                           value="<%= user != null ? user.getFullName() : "" %>"
                           required>
                </div>

                <div class="input-group">
                    <label>Phone</label>
                    <input type="text"
                           name="deliveryPhone"
                           value="<%= user != null ? user.getPhone() : "" %>"
                           required>
                </div>

            </div>

            <div class="input-group">
                <label>Address Line 1</label>
                <input type="text"
                       name="deliveryAddress1"
                       value="<%= user != null ? user.getAddress1() : "" %>"
                       required>
            </div>

            <div class="input-group">
                <label>Address Line 2</label>
                <input type="text"
                       name="deliveryAddress2"
                       value="<%= user != null ? user.getAddress2() : "" %>"
                       required>
            </div>

            <div class="input-row">

                <div class="input-group">
                    <label>City</label>
                    <input type="text"
                           name="deliveryCity"
                           value="<%= user != null ? user.getCity() : "" %>"
                           required>
                </div>

                <div class="input-group">
                    <label>Pincode</label>
                    <input type="text"
                           name="deliveryPincode"
                           value="<%= user != null ? user.getPincode() : "" %>"
                           required>
                </div>

            </div>

            <div class="input-group">
                <label>Country</label>
                <input type="text"
                       name="deliveryCountry"
                       value="<%= user != null ? user.getCountry() : "" %>"
                       required>
            </div>

            <div class="input-group">
                <label>Payment Method</label>
                <select name="paymentMethod" required>
                    <option value="Cash On Delivery">Cash On Delivery</option>
                    <option value="UPI">UPI</option>
                    <option value="Card">Card</option>
                </select>
            </div>

            <button type="submit" class="place-btn">
                Place Order
            </button>

        </form>

    </div>

</div>

</body>
</html>