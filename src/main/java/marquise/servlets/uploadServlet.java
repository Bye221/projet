package marquise.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

// servlet pour l'upload des documents
@WebServlet("/upload")
public class uploadServlet extends AbstractGenericServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		HttpSession session=req.getSession(false);
		
		if(session != null){}
		else{
			resp.sendRedirect("connexion");
			out.println("Veuillez entre un mot de passe correct");
		}
		
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		
		
		
		WebContext context = new WebContext(req, resp, req.getServletContext());

		templateEngine.process("connectedUsers/upload", context, resp.getWriter());
	}
	
	

}
