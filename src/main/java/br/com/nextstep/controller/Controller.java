package main.java.br.com.nextstep.controller;

import main.java.br.com.nextstep.beans.Evento;
import main.java.br.com.nextstep.beans.Personagem;
import main.java.br.com.nextstep.bo.EventoBO;
import main.java.br.com.nextstep.bo.PersonagemBO;
import main.java.br.com.nextstep.excecao.Excecao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@WebServlet(urlPatterns = {"/personagens", "/timeline","/portugues","/ingles"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public Controller() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {			
			switch (request.getRequestURI()) {
			case "/portugues":
				trocarlingua(request,response);
			case "/ingles":
				trocarlingua(request,response);
			case "/personagens":
				mostraPersonagens(request, response);
			case "/timeline":
				mostraTimeline(request, response);
			default:
				request.getRequestDispatcher("./pages/index.jsp");
			} 


		} catch (Exception e) {
			Excecao.tratarExcecao(e);
			e.printStackTrace();
		}
	}

	private void mostraPersonagens(HttpServletRequest request, HttpServletResponse response) throws Exception {
			List<Personagem> listaPersonagens = PersonagemBO.mostraPersonagem();
			request.setAttribute("listaPersonagens", listaPersonagens);
			request.getRequestDispatcher("characters.jsp").forward(request, response);
	}

	private void mostraTimeline(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Evento> listaEventos = EventoBO.mostraEvento();
		request.setAttribute("listaEventos", listaEventos);
		request.getRequestDispatcher("timeline.jsp").forward(request, response);
	}

	private void trocarlingua(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String language = request.getParameter("lingua");
		Locale locale = new Locale(language);

		Config.set(request.getSession(), Config.FMT_LOCALE,locale);
		Config.set(request.getSession(), Config.FMT_FALLBACK_LOCALE, locale);

		response.sendRedirect("index.jsp?lingua="+locale);
	}


}
