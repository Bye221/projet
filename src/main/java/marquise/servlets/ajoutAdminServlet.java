package marquise.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import marquise.services.InformationLibrary;

@WebServlet("/ajoutAdmin")
public class ajoutAdminServlet extends AbstractGenericServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4835451077585731550L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		HttpSession session=req.getSession(false);
		resp.setCharacterEncoding("UTF-8");
		if(session != null){}
		else{
			resp.sendRedirect("connexion");
			out.println("Veuillez entre un mot de passe correct");
		}
		resp.setCharacterEncoding("UTF-8");
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		resp.setCharacterEncoding("UTF-8");

		templateEngine.process("admin/ajoutAdmin", context, resp.getWriter());
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		Integer tarif = 10;
		String releaseDateAsString = req.getParameter("releaseDate");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate releaseDate = LocalDate.parse(releaseDateAsString, formatter);
		String nom = req.getParameter("nom");
		InformationLibrary.getInstance().addInformationUtilisateur(nom, nom, nom, releaseDate, tarif, nom, nom);
		
		resp.setCharacterEncoding("UTF-8");
		resp.sendRedirect("admin");
	
	}
	
	


	
	

}
