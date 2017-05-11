package marquise.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;


import marquise.services.InformationLibrary;

@WebServlet("/detail")
public class CityDetailServlet extends AbstractGenericServlet2 {

	private static final long serialVersionUID = 8559083626521311046L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(req);

		WebContext context = new WebContext(req, resp, getServletContext());
		
		Integer idImage = Integer.parseInt(req.getParameter("id"));
		context.setVariable("image", InformationLibrary.getInstance().getImage(idImage));		
		//context.setVariable("comments", InformationLibrary.getInstance().listCommentsByCity(idCity));
		context.setVariable("comments", InformationLibrary.getInstance().listAllImages());
		
		/*if(req.getSession().getAttribute("commentCreationError") != null) {
			context.setVariable("commentCreationErrorMessage", req.getSession().getAttribute("commentCreationError"));
			context.setVariable("commentCreation", req.getSession().getAttribute("commentCreationData"));

			req.getSession().removeAttribute("commentCreationError");
			req.getSession().removeAttribute("commentCreationData");
		} else {
			context.setVariable("commentCreation", new Comment());
		}*/
		
		templateEngine.process("imagedetail", context, resp.getWriter());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//try {
			Integer cityId = Integer.parseInt(req.getParameter("id"));
			
			//String pseudo = req.getParameter("pseudo");
			//String message = req.getParameter("comment");			
			//Comment newComment = new Comment(null, pseudo, LocalDate.now(), message);
			
			//try {				
				//CityService.getInstance().addComment(cityId, newComment);
			//} catch (IllegalArgumentException e) {
			//	req.getSession().setAttribute("commentCreationError", e.getMessage());
			//	req.getSession().setAttribute("commentCreationData", newComment);
			//}
			
			resp.sendRedirect(String.format("detail?id=%d", cityId));
		//} catch (NumberFormatException e) {
			resp.sendRedirect("home2");
		} 
		
		
	}
	
	

//}
