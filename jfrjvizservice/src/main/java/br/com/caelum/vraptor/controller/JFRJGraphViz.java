package br.com.caelum.vraptor.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.io.IOUtils;

public class JFRJGraphViz {
	private static final String DEFAULT_GRAPH = "digraph \"\"{ graph[tooltip=\"Tramitação\"]  \"T2SITI\"[shape=\"rectangle\"][label=\"SITI\"][tooltip=\"SUBSECRETARIA DE INFRAESTRUTURA DE TI\"]; \"T2DIVEC\"[shape=\"rectangle\"][label=\"DIVEC\"][tooltip=\"DIVISÃO DE EDUCAÇÃO CORPORATIVA\"]; \"T2STI\"[shape=\"rectangle\"][label=\"STI\"][tooltip=\"SECRETARIA DE TECNOLOGIA DA INFORMAÇÃO\"]; \"T2COABDA\" [shape=\"oval\"] [label=\"COABDA\"][color=\"red\"][tooltip=\"COORDENADORIA DE APLICAÇÕES E BANCO DE DADOS\"]; \"T2COABDA\" -> \"T2SITI\"[tooltip=\"Transferido em 19/04/18\"][label=\"1\"]; \"T2SITI\" -> \"T2STI\"[tooltip=\"Transferido em 19/04/18\"][label=\"2\"]; \"T2STI\" -> \"T2DIVEC\"[tooltip=\"Transferido em 24/04/18\"][label=\"3\"]; \"T2DIVEC\" -> \"T2STI\"[tooltip=\"Transferido em 24/04/18\"][label=\"4\"]; \"T2STI\" -> \"T2COABDA\"[tooltip=\"Transferido em 25/04/18\"][label=\"5\"]; }";
	
	public static byte[] generateGraph() throws IOException {
		return generateGraph(DEFAULT_GRAPH);
	}

	public static byte[] generateGraph(String dotFormatGraph) throws IOException {
        Runtime rt = Runtime.getRuntime();

        //converte para svg (siga usa esse formato) e joga para stdout o dotgraph passado em stdin
        //String[] dotArgs = {"/usr/bin/dot", "-Tsvg"};
        String[] dotArgs = {"C:/Program Files (x86)/Graphviz2.38/bin/dot.exe", "-Tsvg"};

        Process p = rt.exec(dotArgs);

        OutputStream stdin = p.getOutputStream();
        InputStream stdout = p.getInputStream();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));

        writer.write(dotFormatGraph);
        writer.flush();
        writer.close();
        
        byte[] bytes = IOUtils.toByteArray(stdout);
        
        try {
			p.waitFor();
		} catch (InterruptedException e) {
			// No problem...
		}
        
        return bytes;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println(new String(generateGraph(), "UTF-8"));
	}
}
