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

import marquise.projos.Article;
import marquise.projos.Utilisateur;
import marquise.services.InformationLibrary;

@WebServlet("/contactAdmin")
public class contactAdminServlet extends AbstractGenericServlet {

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
		
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		
		WebContext context = new WebContext(req, resp, req.getServletContext());
				
		context.setVariable("contacts", InformationLibrary.getInstance().listCommentaires());
		
		resp.setCharacterEncoding("UTF-8");
		
		
		

		templateEngine.process("admin/contactAdmin", context, resp.getWriter());
		
	}
	
	
	
	


	
	

}
