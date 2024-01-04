<!DOCTYPE html >
<%@ page contentType="text/html; charset=iso-8859-1"
	pageEncoding="iso-8859-1" language="java"
	import="java.util.*, com.miw.model.Book,com.miw.presentation.book.*, com.miw.util.BuyConstants"
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
		<article>
			<table>
				<caption>Our catalog:</caption>
				<thead>
					<tr>
						<th>Direccion</th>
						<th>Precio</th>
						<th>Estado</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.buys" var="buy">
							<tr>
								<td><s:property value="#buy.direccion" /></td>
								<td><s:property value="#buy.price" /> &euro;</td>
								<td>
    								<s:if test="#request.admin">
							            <s:form action="actualizar-estado-buy.action">
							                <s:hidden name="buyId" value="%{#buy.id}" /> 
							                <s:select name="estado" list="#request.estados"
	      										 required="true" value="%{#buy.estado}"  emptyOption="false" />
							                <s:submit value="Actualizar Estado" />
							            </s:form>
							        </s:if>
    								<s:else>
					            		<s:property value="#buy.estado" />
					            	</s:else>
					            </td>
							</tr>
					</s:iterator>
				</tbody>
			</table>
			<a href="show-books.action">Go Back</a><br /> 
		</article>
	</section>
	<footer>
		<strong> Master in Web Engineering (miw.uniovi.es).</strong><br /> <em>University
			of Oviedo </em>
	</footer>
</body>