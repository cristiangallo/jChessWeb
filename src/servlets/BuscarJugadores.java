package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.ControladorJugarPartida;
import appExceptions.appException;
import entidades.Jugador;
import entidades.Partida;

/**
 * Servlet implementation class BuscarJugadores
 */
@WebServlet("/buscar-jugadores")
public class BuscarJugadores extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarJugadores() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Jugador jugadorBlanco = null;
		Jugador jugadorNegro = null;
	
		ControladorJugarPartida ctrJugarPartida = new ControladorJugarPartida();
		try {
			int dniJugadorBlanco = Integer.parseInt(request.getParameter("dniJugadorBlanco"));
			jugadorBlanco = ctrJugarPartida.buscarJugadorBlanco(dniJugadorBlanco);
			// request.getSession().setAttribute("dniJugadorBlanco", dniJugadorBlanco);
		} catch (NullPointerException e){
			request.setAttribute("error", "Corregí el dni del jugador blanco");
			request.getRequestDispatcher("jAjedrez.jsp").forward(request, response);	
		}
		
		try {
			int dniJugadorNegro = Integer.parseInt(request.getParameter("dniJugadorNegro"));
			jugadorNegro = ctrJugarPartida.buscarJugadorNegro(dniJugadorNegro);
			// request.getSession().setAttribute("dniJugadorNegro", dniJugadorNegro);
		} catch (NullPointerException e){
			request.setAttribute("error", "Corregí el dni del jugador negro");
			request.getRequestDispatcher("jAjedrez.jsp").forward(request, response);	
		}
		if (jugadorBlanco==null || jugadorNegro==null){
			request.getRequestDispatcher("nuevo-jugador.jsp").forward(request, response);
			return;
		}
		System.out.println(jugadorBlanco.getApellido());
		System.out.println(jugadorNegro.getApellido());
		request.getSession().setAttribute("jugadorBlanco", jugadorBlanco);
		request.getSession().setAttribute("jugadorNegro", jugadorNegro);
		//request.getRequestDispatcher("jAjedrez.jsp").forward(request, response);	
		
		try {			
			Partida partida = ctrJugarPartida.hayPartidaPendiente();
			if (partida != null) {
				request.getSession().setAttribute("partida", partida);
				request.getRequestDispatcher("iniciar-partida.jsp").forward(request, response);	
				return;
			} else {
				partida = ctrJugarPartida.iniciarPartida();
				request.getSession().setAttribute("partida", partida);
				request.getRequestDispatcher("jugar.jsp").forward(request, response);
				return;
			}			
		} catch (appException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", e);
			request.getRequestDispatcher("iniciar-partida.jsp").forward(request, response);	
		}
		
	}

}
