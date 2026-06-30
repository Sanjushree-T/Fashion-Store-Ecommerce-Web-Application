<%@ page language="java" %>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Fashion Store Register</title>

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/register.css?v=2">

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
rel="stylesheet">

</head>

<body>

<div class="container">

    <!-- LEFT FORM SECTION -->

    <div class="form-section">

        <h2>Create Account</h2>

        <form action="${pageContext.request.contextPath}/register"
              method="post">

            <div class="input-group">

                <input type="text"
                       name="fullName"
                       placeholder="Full Name"
                       required>

            </div>

            <div class="input-group">

                <input type="email"
                       name="email"
                       placeholder="Email Address"
                       required>

            </div>

            <div class="input-group">

                <input type="text"
                       name="phone"
                       placeholder="Phone Number"
                       required>

            </div>

            <div class="input-group">

                <input type="password"
                       name="password"
                       placeholder="Password"
                       required>

            </div>

            <div class="input-group">

                <input type="text"
                       name="address1"
                       placeholder="Address Line 1">

            </div>

            <div class="input-group">

                <input type="text"
                       name="address2"
                       placeholder="Address Line 2">

            </div>

            <div class="input-group">

                <input type="text"
                       name="city"
                       placeholder="City">

            </div>

            <div class="input-group">

                <input type="text"
                       name="state"
                       placeholder="State">

            </div>

            <div class="input-group">

                <input type="text"
                       name="pincode"
                       placeholder="Pincode">

            </div>

            <div class="input-group">

                <input type="text"
                       name="country"
                       placeholder="Country">

            </div>

            <button type="submit">

                Create Account

            </button>

            <div class="login-link">

                Already have an account?

                <a href="${pageContext.request.contextPath}/login">

                    Sign In

                </a>

            </div>

        </form>

    </div>

    <!-- RIGHT SIDE -->

    <div class="side-section">

        <div class="side-content">

            <h1>Fashion Store</h1>

            <p>
                Discover premium styles,
                luxury collections and
                modern fashion trends
                designed for your perfect look.
            </p>

        </div>

    </div>

</div>

</body>
</html>