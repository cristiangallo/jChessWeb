<%@page import="appExceptions.*"%>
<%@page import="entidades.*" %>
<%@page import="java.lang.Exception" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Iniciar partida|jAjedrez</title>
	</head>
	<body onload="document.getElementById(dniJugadorBlanco).focus();">
		<form action="iniciar-partida" method="POST">
			<table align="center">
				<% if (request.getAttribute("errorCampos")!=null){
					Exception error = (Exception)request.getAttribute("errorCampos");
						out.println("<tr><td colspan='3'><font color='red'>"+error.getMessage()+"</font></tr>");}%>
				<% if (request.getAttribute("error")!=null){
					appException error = (appException)request.getAttribute("error");
						out.println("<tr><td colspan='3'><font color='red'>"+error.getMessage()+"</font></tr>");}%>
				<tr>
					<td colspan="3"><h1>Hay una partida pendiente, <br>¿desean continuarla o juegan una nueva?</h1></td>
				</tr>
				<tr>							
					<% Jugador jugadorBlanco = (Jugador)session.getAttribute("jugadorBlanco");
						if (jugadorBlanco!=null) { 
							out.println("<input type='hidden' name='dniJugadorBlanco' id='dniJugadorBlanco' value='"+request.getParameter("dniJugadorBlanco")+"'>");
							out.println("<td>Jugador blanco:</td>");					
							out.println("<td>" + jugadorBlanco.getNombre()+" "+jugadorBlanco.getApellido()+"</td>");
							out.println("<td></td>");						
						}else {
							out.println("<td>DNI Jugador blanco:</td>");
							out.println("<td><input type='text' name='dniJugadorBlanco' id='dniJugadorBlanco' value='"+request.getParameter("dniJugadorBlanco")+"'></td>");
							if (request.getParameter("dniJugadorBlanco")!=null) {
								out.println("<td><input type='button' name='buscarJugBlanco' id='agregarJugBlanco' value='Agregar'></td>");
							} else {
								out.println("<td></td>");
							}
						} %>					
				</tr>				
				<tr>							
					<% Jugador jugadorNegro = (Jugador)session.getAttribute("jugadorNegro");
						if (jugadorNegro!=null) { 
							out.println("<input type='hidden' name='dniJugadorNegro' id='dniJugadorNegro' value='"+request.getParameter("dniJugadorNegro")+"'>");
							out.println("<td>Jugador Negro:</td>");					
							out.println("<td>" + jugadorNegro.getNombre()+" "+jugadorNegro.getApellido()+"</td>");
							out.println("<td></td>");						
						}else {
							out.println("<td>DNI Jugador Negro:</td>");
							out.println("<td><input type='text' name='dniJugadorNegro' id='dniJugadorNegro' value='"+request.getParameter("dniJugadorNegro")+"'></td>");
							if (request.getParameter("dniJugadorNegro")!=null) {
								out.println("<td><input type='button' name='buscarJugNegro' id='agregarJugNegro' value='Agregar'></td>");
							} else {
								out.println("<td></td>");
							}
						} %>					
				</tr>
				<% Partida partida = (Partida)session.getAttribute("partida");
					if (partida!=null){ 
						out.println("<td><input type='submit' name='action' value='Continuar'></td>");
						out.println("<td><input type='submit' name='action' value='Nueva'></td>");
					}
				%>
			</table>
		</form>
	</body>
	</body>
</html>