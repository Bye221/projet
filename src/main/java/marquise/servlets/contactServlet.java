package marquise.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;


import marquise.projos.Commentaire;
import marquise.services.InformationLibrary;


@WebServlet("/contact")
public class contactServlet extends AbstractGenericServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		
		resp.setCharacterEncoding("UTF-8");
		
		WebContext context = new WebContext(req, resp, req.getServletContext());

		templateEngine.process("users/contact", context, resp.getWriter());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String commentaire =req.getParameter("commentaire");
		
		Commentaire newCommentaire = new Commentaire(null, email, commentaire);
		Commentaire addedCommentaire = InformationLibrary.getInstance().addCommentaire(email, commentaire);
		
		
		
		resp.sendRedirect("contact");
	}
	
	

}
