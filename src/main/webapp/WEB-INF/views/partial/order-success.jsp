<%
String deliveryName =
(String) request.getAttribute("deliveryName");

String paymentMethod =
(String) request.getAttribute("paymentMethod");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Order Success</title>

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
rel="stylesheet">

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:'Poppins',sans-serif;
}

body{

    min-height:100vh;

    display:flex;

    justify-content:center;

    align-items:center;

    background:
    radial-gradient(circle at top left,#7c3aed 0,transparent 35%),
    radial-gradient(circle at bottom right,#06b6d4 0,transparent 30%),
    linear-gradient(135deg,#17111f,#2b2038,#15111c);

    color:white;
}

/* ===== CARD ===== */

.success-card{

    width:650px;

    padding:55px;

    text-align:center;

    border-radius:35px;

    background:
    rgba(255,255,255,0.06);

    backdrop-filter:blur(16px);

    border:
    1px solid rgba(255,255,255,0.08);

    box-shadow:
    0 25px 60px rgba(0,0,0,0.35);
}

/* ===== ICON ===== */

.success-icon{

    font-size:60px;

    font-weight:700;

    color:#67e8f9;

    margin-bottom:20px;
}

/* ===== TITLE ===== */

.success-card h1{

    font-size:48px;

    line-height:1.3;

    color:#67e8f9;

    margin-bottom:25px;
}

/* ===== TEXT ===== */

.success-card p{

    font-size:20px;

    color:#d1d5db;

    margin-bottom:18px;

    line-height:1.7;
}

.success-card b{

    color:white;
}

/* ===== BUTTON ===== */

.home-btn{

    display:inline-block;

    margin-top:35px;

    padding:16px 38px;

    border-radius:35px;

    text-decoration:none;

    font-weight:700;

    font-size:16px;

    background:
    linear-gradient(135deg,#67e8f9,#a78bfa);

    color:#111827;

    transition:0.3s;
}

.home-btn:hover{

    transform:translateY(-3px);

    box-shadow:
    0 12px 25px rgba(103,232,249,0.25);
}

</style>

</head>

<body>

<div class="success-card">

    <!-- ICON -->

    <div class="success-icon">

    	&#10004;

	</div>

    <!-- TITLE -->

    <h1>

        Order Placed Successfully

    </h1>

    <!-- DETAILS -->

    <p>

        Thank you
        <b><%= deliveryName %></b>

    </p>

    <p>

        Payment Method :
        <b><%= paymentMethod %></b>

    </p>

    <p>

        Your fashion order is confirmed
        and will be delivered soon.

    </p>

    <!-- BUTTON -->

    <a href="home"
       class="home-btn">

        Continue Shopping

    </a>

</div>

</body>

</html>