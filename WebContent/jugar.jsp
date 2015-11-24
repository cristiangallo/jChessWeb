<%@page import="entidades.*" %>
<%@page import="appExceptions.*" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.Map" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Partida iniciada!</title>
	</head>
	<body>
		<%
			Jugador jugadorBlanco = (Jugador)session.getAttribute("jugadorBlanco");
			Jugador jugadorNegro = (Jugador)session.getAttribute("jugadorNegro");
			Partida partida = (Partida) session.getAttribute("partida");
		%>
		<table>
			<% if (request.getAttribute("error")!=null){
				appException error = (appException)request.getAttribute("error");
				out.println("<tr><td colspan='3'><font color='red'>"+error.getMessage()+"</font></tr>");}%>
		
			<tr>
				<th colspan="2">Jugador blanco</th>
				<th>&nbsp;</th>
				<th colspan="2">Jugador negro</th>
			</tr>
			<tr>	
				<td colspan="2"><%="("+jugadorBlanco.getDni()+") "+jugadorBlanco.getNombre()+" "+jugadorBlanco.getApellido() %></td>
				<td>&nbsp;</td>
				<td colspan="2"><%="("+jugadorNegro.getDni()+") "+jugadorNegro.getNombre()+" "+jugadorNegro.getApellido() %></td>
			</tr>
			<tr>
				<td colspan="3">Turno:</td>
				<td colspan="2"><strong><%= partida.getTurno()%></strong></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">Desde</td><td>&nbsp;</td><td colspan="2" align="center">Hasta</td>
			</tr>
			<form action="Mover" method="post">
				<tr>
					<td colspan="2" align="center"><input type="text" name="desde" id="desde"></td>
					<td>&nbsp;</td>
					<td colspan="2" align="center"><input type="text" name="hasta" id="hasta"></td>
				</tr>
				<tr>
					<td colspan="5" align="center"><input type='submit' value='Mover'></td>
				</tr>
				<% HashMap<Posicion, Pieza> tablero = partida.getTablero();
				for (Map.Entry<Posicion, Pieza> entry : tablero.entrySet()) {
					 Posicion posicion = entry.getKey();
					 Pieza pieza = entry.getValue();
					 out.println("<tr><td colspan='5'>"+pieza.getNombre()+" "+pieza.getColor()+" en "+posicion.getX()+posicion.getY()+"</tr>");}
				%>
			</form>
		</table>
	</body>
</html>