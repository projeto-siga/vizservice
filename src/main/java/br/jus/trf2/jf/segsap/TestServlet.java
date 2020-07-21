package br.jus.trf2.jf.segsap;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TestServlet", urlPatterns = "/test")
public class TestServlet extends HttpServlet {

	private static final String DEFAULT_GRAPH = "digraph \"\"{ graph[tooltip=\"Tramitação\"]  \"T2SITI\"[shape=\"rectangle\"][label=\"SITI\"][tooltip=\"SUBSECRETARIA DE INFRAESTRUTURA DE TI\"]; \"T2DIVEC\"[shape=\"rectangle\"][label=\"DIVEC\"][tooltip=\"DIVISÃO DE EDUCAÇÃO CORPORATIVA\"]; \"T2STI\"[shape=\"rectangle\"][label=\"STI\"][tooltip=\"SECRETARIA DE TECNOLOGIA DA INFORMAÇÃO\"]; \"T2COABDA\" [shape=\"oval\"] [label=\"COABDA\"][color=\"red\"][tooltip=\"COORDENADORIA DE APLICAÇÕES E BANCO DE DADOS\"]; \"T2COABDA\" -> \"T2SITI\"[tooltip=\"Transferido em 19/04/18\"][label=\"1\"]; \"T2SITI\" -> \"T2STI\"[tooltip=\"Transferido em 19/04/18\"][label=\"2\"]; \"T2STI\" -> \"T2DIVEC\"[tooltip=\"Transferido em 24/04/18\"][label=\"3\"]; \"T2DIVEC\" -> \"T2STI\"[tooltip=\"Transferido em 24/04/18\"][label=\"4\"]; \"T2STI\" -> \"T2COABDA\"[tooltip=\"Transferido em 25/04/18\"][label=\"5\"]; }";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SvgServlet.corsHeaders(response);
		byte[] image = JFRJGraphViz.generateGraph(DEFAULT_GRAPH);
		response.setContentType("image/svg+xml");
		response.setContentLength(image.length);
		response.getOutputStream().write(image);
	}
}