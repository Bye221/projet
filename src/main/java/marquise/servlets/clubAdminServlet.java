package marquise.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import marquise.projos.Article;
import marquise.projos.Utilisateur;
import marquise.services.InformationLibrary;

@WebServlet("/clubAdmin")
public class clubAdminServlet extends AbstractGenericServlet {
	
	private int idRecherche;
	private int idInformation;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4835451077585731550L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		resp.setCharacterEncoding("UTF-8");

		templateEngine.process("admin/clubAdmin", context, resp.getWriter());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String text=" Hello le test";
		String auteur ="L-C";
		
		String releaseDateAsString = req.getParameter("releaseDate");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate releaseDate = LocalDate.parse(releaseDateAsString, formatter);
		String title = req.getParameter("title");
		Article newArticle = new Article(null, title, null, null, null);
		Article addedArticle = InformationLibrary.getInstance().addArticle(title, text , releaseDate, title);
		
		
		
		resp.sendRedirect("clubAdmin");
	
	}
	
	


	
	

}
