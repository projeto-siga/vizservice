package br.jus.trf2.jf.segsap;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.io.IOUtils;

public class JFRJGraphViz {
	private static final String[] DOT_COMMAND_LINUX = {"/usr/bin/dot", "-Tsvg"};
	private static final String[] DOT_COMMAND_MAC = {"/opt/local/bin/dot", "-Tsvg"};
	private static final String[] DOT_COMMAND_WINDOWS = {"C:/Program Files (x86)/Graphviz2.38/bin/dot.exe", "-Tsvg"};

	public static byte[] generateGraph(String dotFormatGraph) throws IOException {
        Runtime rt = Runtime.getRuntime();

        String[] dotArgs = isWindows() ? DOT_COMMAND_WINDOWS : isMac() ? DOT_COMMAND_MAC : DOT_COMMAND_LINUX;

        Process p = rt.exec(dotArgs);

        OutputStream stdin = p.getOutputStream();
        InputStream stdout = p.getInputStream();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));

        writer.write(dotFormatGraph);
        writer.flush();
        writer.close();
        
        byte[] bytes = IOUtils.toString(stdout, "UTF-8").getBytes();
        
        try {
			p.waitFor();
		} catch (InterruptedException e) {
			// No problem...
		}
        
        return bytes;
	}
	
	private static boolean isWindows() {
		return System.getProperty("os.name").contains("Windows");
	}

	private static boolean isMac() {
		return System.getProperty("os.name").contains("Mac");
	}
}
