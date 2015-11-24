package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import appExceptions.appException;
import ui.JAjedrez;
import negocio.ControladorJugarPartida;
import entidades.Jugador;
import entidades.Partida;

/**
 * Servlet implementation class IniciarPartida
 */
@WebServlet("/iniciar-partida")
public class IniciarPartida extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IniciarPartida() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		Partida partida;
		
		String action = request.getParameter("action");
		
		System.out.println("que hacemos con la partida");
		if ("Continuar".equals(action)) {
			System.out.println("continuar");			
			request.getRequestDispatcher("jugar.jsp").forward(request, response);
			return;
		} else if ("Nueva".equals(action)) {
			System.out.println("Nueva");
			
			try {
				Jugador jugadorBlanco = (Jugador)request.getSession().getAttribute("jugadorBlanco");
				Jugador jugadorNegro = (Jugador)request.getSession().getAttribute("jugadorNegro");
				ControladorJugarPartida ctrJugarPartida = new ControladorJugarPartida(jugadorBlanco, jugadorNegro);
				partida = ctrJugarPartida.iniciarPartida();
				request.getSession().setAttribute("partida", partida);
				request.getRequestDispatcher("jugar.jsp").forward(request, response);
				return;
			} catch (appException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		/*
		int dniJugadorBlanco = Integer.parseInt(request.getParameter("dniJugadorBlanco"));
		int dniJugadorNegro = Integer.parseInt(request.getParameter("dniJugadorNegro"));
		
		ControladorJugarPartida ctrJugarPartida = new ControladorJugarPartida();
		Jugador jugadorBlanco = ctrJugarPartida.buscarJugadorBlanco(dniJugadorBlanco);
		Jugador jugadorNegro = ctrJugarPartida.buscarJugadorNegro(dniJugadorNegro);
		request.getSession().setAttribute("jugadorBlanco", jugadorBlanco);
		request.getSession().setAttribute("jugadorNegro", jugadorNegro);
		try {			
			Partida partida = ctrJugarPartida.hayPartidaPendiente();
			if (partida != null) {
				request.getSession().setAttribute("partida", partida);
				request.getRequestDispatcher("iniciar-partida.jsp").forward(request, response);	
			} else {
				partida = ctrJugarPartida.iniciarPartida();
				request.getSession().setAttribute("partida", partida);
				request.getRequestDispatcher("jugar.jsp").forward(request, response);		
			}			
		} catch (appException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", e);
			request.getRequestDispatcher("iniciar-partida.jsp").forward(request, response);	
		}
		*/
	}
}
