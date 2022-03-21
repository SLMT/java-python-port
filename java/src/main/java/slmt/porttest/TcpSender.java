package slmt.porttest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TcpSender implements Sender {
	
	private Socket socket;
	private BufferedWriter output;
	private BufferedReader input;
	
	public TcpSender(String ip, int port) throws IOException {
		socket = new Socket(ip, port);
		output = new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream(), "UTF-8"));
		input = new BufferedReader(new InputStreamReader(
				socket.getInputStream(), "UTF-8"));
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
		socket.close();
	}

}
