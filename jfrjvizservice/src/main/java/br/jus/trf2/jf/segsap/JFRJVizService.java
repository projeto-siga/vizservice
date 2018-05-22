package br.jus.trf2.jf.segsap;

import java.io.IOException;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.interceptor.download.ByteArrayDownload;
import br.com.caelum.vraptor.interceptor.download.Download;

@Resource
public class JFRJVizService {
	
	private static final String DEFAULT_GRAPH = "digraph \"\"{ graph[tooltip=\"Tramitação\"]  \"T2SITI\"[shape=\"rectangle\"][label=\"SITI\"][tooltip=\"SUBSECRETARIA DE INFRAESTRUTURA DE TI\"]; \"T2DIVEC\"[shape=\"rectangle\"][label=\"DIVEC\"][tooltip=\"DIVISÃO DE EDUCAÇÃO CORPORATIVA\"]; \"T2STI\"[shape=\"rectangle\"][label=\"STI\"][tooltip=\"SECRETARIA DE TECNOLOGIA DA INFORMAÇÃO\"]; \"T2COABDA\" [shape=\"oval\"] [label=\"COABDA\"][color=\"red\"][tooltip=\"COORDENADORIA DE APLICAÇÕES E BANCO DE DADOS\"]; \"T2COABDA\" -> \"T2SITI\"[tooltip=\"Transferido em 19/04/18\"][label=\"1\"]; \"T2SITI\" -> \"T2STI\"[tooltip=\"Transferido em 19/04/18\"][label=\"2\"]; \"T2STI\" -> \"T2DIVEC\"[tooltip=\"Transferido em 24/04/18\"][label=\"3\"]; \"T2DIVEC\" -> \"T2STI\"[tooltip=\"Transferido em 24/04/18\"][label=\"4\"]; \"T2STI\" -> \"T2COABDA\"[tooltip=\"Transferido em 25/04/18\"][label=\"5\"]; }";
	
	@Get
	@Path("/viz")
	public Download viz() throws IOException {
		return postViz(DEFAULT_GRAPH);
	}
	
	@Post
	@Path("/vizPost")
	public Download postViz(String dot) throws IOException {
		byte[] image = generateImage(dot);
		return new ByteArrayDownload(image, "image/svg+xml", "graphic.jpg");
	}
	
	private byte[] generateImage(String dot) throws IOException {
		return JFRJGraphViz.generateGraph(dot);
	}
}