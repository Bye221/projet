package marquise.servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import marquise.projos.Image;
import marquise.services.InformationLibrary;

@WebServlet("/addimage")
@MultipartConfig
public class ImageAddServlet extends AbstractGenericServlet2 {

	private static final long serialVersionUID = -3497793006266174453L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		
		WebContext context = new WebContext(req, resp, getServletContext());
		if(req.getSession().getAttribute("imageCreationError") != null) {
			context.setVariable("errorMessage", req.getSession().getAttribute("imageCreationError"));
			context.setVariable("image", (Image) req.getSession().getAttribute("imageCreationData"));

			req.getSession().removeAttribute("imageCreationError");
			req.getSession().removeAttribute("imageCreationData");
		} else {
			context.setVariable("image", new Image(null, null, null));
		}
		context.setVariable("countries", context);
		templateEngine.process("connectedUsers/imageadd", context, resp.getWriter());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String summary = req.getParameter("summary");
		
		
		
		Part imagePicture = req.getPart("picture");
		Image newImage = new Image(null, name, summary);
		InputStream is  = imagePicture.getInputStream();
		
		try {
			InformationLibrary.getInstance().addImage(newImage, is);
			resp.sendRedirect("certificatsAdmin");
		} catch (IllegalArgumentException|IOException e) {
			req.getSession().setAttribute("imageCreationError", e.getMessage());
			req.getSession().setAttribute("imageCreationData", newImage);
			resp.sendRedirect("addimage");
		} 

	}

	
}
