package br.com.caelum.vraptor.controller;

import java.io.IOException;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.observer.download.ByteArrayDownload;
import br.com.caelum.vraptor.observer.download.Download;

@Controller
public class JFRJVizService {
		
	@Get
	@Path("/viz")
	public Download viz() throws IOException {
		byte[] image = generateImage();
		return new ByteArrayDownload(image, "image/svg+xml", "graphic.jpg");
	}
	
	@Post
	@Path("/vizPost")
	public Download postViz(String dot) throws IOException {
		byte[] image = generateImage(dot);
		return new ByteArrayDownload(image, "image/svg+xml", "graphic.jpg");
	}
	
	private byte[] generateImage() throws IOException {
		return JFRJGraphViz.generateGraph();
	}
	
	private byte[] generateImage(String dot) throws IOException {
		return JFRJGraphViz.generateGraph(dot);
	}
}