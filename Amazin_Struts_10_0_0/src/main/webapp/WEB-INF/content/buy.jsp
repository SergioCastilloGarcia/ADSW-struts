<!DOCTYPE html >
<%@ page contentType="text/html; charset=iso-8859-1"
	pageEncoding="iso-8859-1" language="java"
	import="java.util.*, com.miw.model.Book,com.miw.presentation.book.*"
	errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<head>
<title>Amazin</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<header>
		<h1 class="header">Amazin.com</h1>
		<h2 class="centered">
			Welcome to the <em>smallest</em> online shop in the world!!
		</h2>
	</header>
	<nav>
		<ul>
			<li><a href="index.action">Start</a></li>
			<li><a href="http://miw.uniovi.es">About</a></li>
			<li><a href="mailto:dd@email.com">Contact</a></li>
			<li><a href="logout.action">Logout</a></li>
		</ul>
	</nav>
	<section>
			<a href="show-books.action">Go Back</a><br /> 
		<article>
			<h2>Completar compra</<h2>>
			<s:form action="buy">
				<s:textfield name="direccion" label="Direccion" />
				<s:submit />
			</s:form>
			<h3>Price: <s:property value="%{#session.shoppingcart.price}" /> &euro;</h3>
			<a href="buy-form.action">Comprar</a><br /> 
		</article>
	</section>
	<footer>
		<strong> Master in Web Engineering (miw.uniovi.es).</strong><br /> <em>University
			of Oviedo </em>
	</footer>
</body>