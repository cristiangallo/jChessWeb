<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Jugamos ajedrez</title>
	</head>
	<body onload="document.getElementById(dniJugadorBlanco).focus();">
		<form action="buscar-jugadores" method="POST">
			<table align="center">				
					<tr>
						<td colspan="2"><h1>Para comenzar a jugar, <br>ingresá los dni de los jugadores</h1></td>
					</tr>
				
				<tr>
					<td>DNI Jugador Blanco:</td>
					<td><input type="text" name="dniJugadorBlanco" id="dniJugadorBlanco" value="<% if (request.getParameter("dniJugadorBlanco")!=null) out.println(request.getParameter("dniJugadorBlanco"));%>"></td>
				</tr>
				
					<tr>
						<td>DNI Jugador Negro:</td>
						<td><input type="text" name="dniJugadorNegro" id="dniJugadorNegro" value="<% if (request.getParameter("dniJugadorNegro")!=null) out.println(request.getParameter("dniJugadorNegro"));%>"></td>
					</tr>
				
				<tr align="center">
					<td>&nbsp;</td><td><input type="submit" value="Buscar"></td>
				</tr>
			</table>
		</form>
	</body>
</html>