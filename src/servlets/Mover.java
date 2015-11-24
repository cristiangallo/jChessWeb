package servlets;

import entidades.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appExceptions.appException;
import appExceptions.appGameOver;
import ui.JAjedrez;
import negocio.ControladorJugarPartida;
import entidades.Partida;

/**
 * Servlet implementation class Mover
 */
@WebServlet("/Mover")
public class Mover extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mover() {
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
		String desde = request.getParameter("desde");
		String hasta = request.getParameter("hasta");
		Partida partida = (Partida)request.getSession().getAttribute("partida");
		ControladorJugarPartida ctrJugarPartida = new ControladorJugarPartida(partida);
		
		char desdeX = desde.toLowerCase().charAt(0);
		int desdeY = Integer.parseInt(desde.substring(1, 2));
		char hastaX = hasta.toLowerCase().charAt(0);
		int hastaY = Integer.parseInt(hasta.substring(1, 2)); 	
		try {
			ctrJugarPartida.moverPieza(desdeX, desdeY, hastaX, hastaY);
			
		} catch (appException | appGameOver e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", e);
		}
		request.getRequestDispatcher("jugar.jsp").forward(request, response);			
	}

}
