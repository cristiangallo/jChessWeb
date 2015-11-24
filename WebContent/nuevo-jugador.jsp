<%@page import="appExceptions.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Nuevo jugador  | jAjedrez</title>
		<script type="text/javascript" language="JavaScript"></script>		
	</head>
	<body onload="document.getElementById(dniJugadorBlanco).focus();">
		<form action="nuevo-jugador" method="POST">
			<table align="center">				
				<% if (request.getAttribute("errorCampos")!=null){
						out.println("<tr><td colspan='3'><font color='red'>"+request.getAttribute("errorCampos")+"</font></tr>");}%>
				<% if (request.getAttribute("error")!=null){
					appException error = (appException)request.getAttribute("error");
						out.println("<tr><td colspan='3'><font color='red'>"+error.getMessage()+"</font></tr>");}%>
				<tr>
					<td>DNI jugador:</td>
					<td>
						<input type="text" name="dniJugador" id="dniJugador"
							<% if (request.getParameter("dniJugador")!=null){out.println("value="+request.getParameter("dniJugador"));} %>
						>
					</td>
				</tr>
				<tr>
					<td>Nombre jugador:</td>
					<td>
						<input type="text" name="nombreJugador" id="nombreJugador"
							<% out.println(request.getParameter("nombreJugador")); 
							if (request.getParameter("nombreJugador")!=null){out.println("value="+request.getParameter("nombreJugador"));} %>
						>
					</td>
				</tr>
				<tr>
					<td>Apellido jugador:</td>
					<td>
						<input type="text" name="apellidoJugador" id="apellidoJugador"
							<% if (request.getParameter("apellidoJugador")!=null){out.println("value="+request.getParameter("apellidoJugador"));} %>
						>
					</td>
				</tr>
				
				<tr align="center">
					<td>&nbsp;</td><td><input type="submit" value="Agregar"></td>
				</tr>
			</table>
		</form>
	</body>
</html>