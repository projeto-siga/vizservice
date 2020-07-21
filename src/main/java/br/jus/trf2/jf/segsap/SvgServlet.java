package br.jus.trf2.jf.segsap;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crivano.swaggerservlet.SwaggerUtils;

@WebServlet(name = "SvgServlet", urlPatterns = "/svg")
public class SvgServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dot = SwaggerUtils.convertStreamToString(request.getInputStream());
		corsHeaders(response);
		byte[] image = JFRJGraphViz.generateGraph(dot);
		response.setContentType("image/svg+xml");
		response.setContentLength(image.length);
		response.getOutputStream().write(image);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dot = request.getParameter("dot");
		corsHeaders(response);
		byte[] image = JFRJGraphViz.generateGraph(dot);
		response.setContentType("image/svg+xml");
		response.setContentLength(image.length);
		response.getOutputStream().write(image);
	}

	public static void corsHeaders(HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type,Authorization");
	}
}