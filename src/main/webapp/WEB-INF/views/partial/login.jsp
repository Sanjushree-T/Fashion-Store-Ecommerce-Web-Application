<%@ page language="java" %>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Fashion Store Login</title>

<!-- CSS FILE -->

<link rel="stylesheet"
href="${pageContext.request.contextPath}/assets/css/login.css?v=10">

<!-- GOOGLE FONT -->

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
rel="stylesheet">

</head>

<body>

<div class="container" id="container">

    <!-- SIGN UP -->

    <div class="form-container sign-up-container">

        <form action="${pageContext.request.contextPath}/register"
              method="post">

            <h1>Create Account</h1>

            <input type="text"
                   name="fullName"
                   placeholder="Full Name"
                   required>

            <input type="email"
                   name="email"
                   placeholder="Email"
                   required>

            <input type="password"
                   name="password"
                   placeholder="Password"
                   required>

            <button type="submit">
                Sign Up
            </button>

        </form>

    </div>

    <!-- SIGN IN -->

    <div class="form-container sign-in-container">

        <form action="${pageContext.request.contextPath}/login"
              method="post">

            <h1>Sign In</h1>

            <input type="email"
                   name="email"
                   placeholder="Email"
                   required>

            <input type="password"
                   name="password"
                   placeholder="Password"
                   required>

            <a href="#">
                Forgot Your Password?
            </a>

            <button type="submit">
                Sign In
            </button>

        </form>

    </div>

    <!-- OVERLAY -->

    <div class="overlay-container">

        <div class="overlay">

            <!-- LEFT -->

            <div class="overlay-panel overlay-left">

                <h1>Welcome Back!</h1>

                <p>
                    Login with your personal details
                    and continue your luxury
                    fashion journey.
                </p>

                <button class="ghost" id="signIn">
                    Sign In
                </button>

            </div>

            <!-- RIGHT -->

            <div class="overlay-panel overlay-right">

                <h1>Fashion Store</h1>

                <p>
                    Discover premium styles,
                    trendy outfits and modern
                    fashion collections.
                </p>

                <button class="ghost" id="signUp">
                    Sign Up
                </button>

            </div>

        </div>

    </div>

</div>

<!-- JAVASCRIPT -->

<script>

const signUpButton =
document.getElementById("signUp");

const signInButton =
document.getElementById("signIn");

const container =
document.getElementById("container");

signUpButton.addEventListener("click", () => {

    container.classList.add("right-panel-active");

});

signInButton.addEventListener("click", () => {

    container.classList.remove("right-panel-active");

});

</script>

</body>
</html>