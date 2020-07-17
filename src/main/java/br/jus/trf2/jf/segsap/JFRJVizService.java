package br.jus.trf2.jf.segsap;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.observer.download.ByteArrayDownload;
import br.com.caelum.vraptor.observer.download.Download;


@Controller
public class JFRJVizService {

	private HttpServletResponse response;
	private HttpServletRequest request;

	public JFRJVizService(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	private static final String DEFAULT_GRAPH = "digraph \"\"{ graph[tooltip=\"Tramitação\"]  \"T2SITI\"[shape=\"rectangle\"][label=\"SITI\"][tooltip=\"SUBSECRETARIA DE INFRAESTRUTURA DE TI\"]; \"T2DIVEC\"[shape=\"rectangle\"][label=\"DIVEC\"][tooltip=\"DIVISÃO DE EDUCAÇÃO CORPORATIVA\"]; \"T2STI\"[shape=\"rectangle\"][label=\"STI\"][tooltip=\"SECRETARIA DE TECNOLOGIA DA INFORMAÇÃO\"]; \"T2COABDA\" [shape=\"oval\"] [label=\"COABDA\"][color=\"red\"][tooltip=\"COORDENADORIA DE APLICAÇÕES E BANCO DE DADOS\"]; \"T2COABDA\" -> \"T2SITI\"[tooltip=\"Transferido em 19/04/18\"][label=\"1\"]; \"T2SITI\" -> \"T2STI\"[tooltip=\"Transferido em 19/04/18\"][label=\"2\"]; \"T2STI\" -> \"T2DIVEC\"[tooltip=\"Transferido em 24/04/18\"][label=\"3\"]; \"T2DIVEC\" -> \"T2STI\"[tooltip=\"Transferido em 24/04/18\"][label=\"4\"]; \"T2STI\" -> \"T2COABDA\"[tooltip=\"Transferido em 25/04/18\"][label=\"5\"]; }";

	// private static final String DEFAULT_GRAPH =
	// "digraph \"\" { graph[ratio=\"0.4\" tooltip=\"Documentos Relacionados\" color=\"#e2eaee\" bgcolor=\"#e2eaee\" URL=\"javascript: bigmapRelacaoDocs();\"]; node[fillcolor=white fontsize=20 style=filled]; edge[penwidth=2];  \"OTZZ-MEM-2018/00002-A\"[shape=\"rectangle\"][label=\"MEM2-A\"][color=\"red\"][URL=\"exibir?sigla=OTZZ-MEM-2018/00002-A\" penwidth=2][tooltip=\"OTZZ-MEM-2018/00002-A\"]; \"OTZZ-RHU-2018/00001-V01\"[shape=\"rectangle\"][label=\"RHU1-V01\"][URL=\"exibir?sigla=OTZZ-RHU-2018/00001-V01\" penwidth=2][tooltip=\"OTZZ-RHU-2018/00001-V01\"]; \"OTZZ-MEM-2018/00001-A\"[shape=\"rectangle\"][label=\"MEM1-A\"][URL=\"exibir?sigla=OTZZ-MEM-2018/00001-A\" penwidth=2][tooltip=\"OTZZ-MEM-2018/00001-A\"]; \"OTZZ-MEM-2018/00002-A\" -> \"OTZZ-RHU-2018/00001-V01\"[style=\"dashed\"][tooltip=\"Vincula&ccedil;&atilde;o\"][color=\"gray\"][dir=none]; \"OTZZ-MEM-2018/00001-A\" -> \"OTZZ-MEM-2018/00002-A\"[style=\"dashed\"][tooltip=\"Juntada\"][dir=back]; }";

	@Get
	@Path("/test")
	public Download viz() throws IOException {
		corsHeaders(response);
		byte[] image = generateImage(DEFAULT_GRAPH);
		return new ByteArrayDownload(image, "image/svg+xml", "graphic.jpg");
	}

	@Post
	@Consumes("text/vnd.graphviz")
	@Path("/svg")
	public Download svg(String dot) throws IOException {
		corsHeaders(response);
		byte[] image = generateImage(dot);
		return new ByteArrayDownload(image, "image/svg+xml", "graphic.jpg");
	}

	private byte[] generateImage(String dot) throws IOException {
		return JFRJGraphViz.generateGraph(dot);
	}

	public static void corsHeaders(HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods",
				"GET,POST,DELETE,PUT,OPTIONS");
		response.addHeader("Access-Control-Allow-Headers",
				"Content-Type,Authorization");
	}

}