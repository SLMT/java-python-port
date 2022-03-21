package slmt.porttest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ForkSender implements Sender {
	
	private Process process;
	private BufferedWriter output;
	private BufferedReader input;
	
	public ForkSender(File workingDir, String pythonPath, String scriptName) throws IOException {
		List<String> cmd = new ArrayList<String>();
		cmd.add(pythonPath);
		cmd.add(scriptName);
		
		ProcessBuilder builder = new ProcessBuilder();
		builder.command(cmd).directory(workingDir);
		process = builder.start();
		
		output = new BufferedWriter(new OutputStreamWriter(
				process.getOutputStream(), "UTF-8"));
		input = new BufferedReader(new InputStreamReader(
				process.getInputStream(), "UTF-8"));
	}

	@Override
	public String send(String request) throws IOException {
		
		// Send the request
		output.append(request);
		output.newLine();
		output.flush();
		
		// Read the response
		String response = input.readLine();
		
		return response;
	}

	@Override
	public void close() throws Exception {
		process.destroy();
	}

}

