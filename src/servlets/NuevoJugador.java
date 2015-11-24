package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appExceptions.appException;
import negocio.ControladorJugarPartida;

/**
 * Servlet implementation class NuevoJugador
 */
@WebServlet("/nuevo-jugador")
public class NuevoJugador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NuevoJugador() {
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
		System.out.println("intentado agregar jugador");
		ControladorJugarPartida controlador = new ControladorJugarPartida();
		int dni = 0;
		try {
			dni = Integer.parseInt(request.getParameter("dniJugador"));
		} catch (NullPointerException|NumberFormatException e) {
			request.setAttribute("errorCampos", "Ingresa el dni");
			request.getRequestDispatcher("nuevo-jugador.jsp").forward(request, response);
			return;
		}
		String nombre = request.getParameter("nombreJugador");
		String apellido = request.getParameter("apellidoJugador");
		try {
			controlador.addJugador(dni, nombre, apellido);
		} catch (appException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", e);
			request.getRequestDispatcher("nuevo-jugador.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("jAjedrez.jsp").forward(request, response);	
		
	}

}
