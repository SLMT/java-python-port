package slmt.porttest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.ProcessBuilder.Redirect;

public class QuickTest {

	public static void main(String[] args) throws IOException {
		ProcessBuilder builder = new ProcessBuilder();
		builder.command("C:\\Users\\SLMT\\anaconda3\\python.exe", "fork-estimate.py");
		builder.directory(new File("R:\\Research\\2021-Hermes-Control\\python-workspace"));
		builder.redirectOutput(Redirect.INHERIT);
		builder.redirectError(Redirect.INHERIT);
		Process process = builder.start();

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				process.getOutputStream(), "UTF-8"));
		writer.append("hahaha");
		writer.newLine();
		writer.flush();
	}

}
