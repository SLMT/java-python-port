package slmt.porttest;

import java.io.File;
import java.io.IOException;

public class MainClass {

	private static final String IP = "127.0.0.1";
	private static final int PORT = 54321;
	
	private static final String SCRIPT_NAME = "fork-estimate.py";
	
	private static final String TEST_STR = "1,0,7.0,1,\"[3]\",\"[0]\",\"[3]\",0,\"[0.044304]\",\"[0.222222]\",\"[0.43]\",\"[0]\",\"[0]\",\"[0]\",\"[0]\"";
	private static final String EXPECTED_RESPONSE = "1,\"[12.342]\",\"[1563]\",\"[452]\"";
	
	public static void main(String[] args) throws IOException {
		int type = Integer.parseInt(args[0]);
		int trialTimes = Integer.parseInt(args[1]);
		String pythonPath = args[2];
		String scriptDir = args[3];
		
		long startTime, elapsedTime, summedTime = 0;
		
		// Create a sender
		Sender sender = newSender(type, pythonPath, scriptDir);
		
		// Perform trials
		for (int i = 0; i < trialTimes; i++) {
			startTime = System.nanoTime();
			performTrial(sender);
			elapsedTime = System.nanoTime() - startTime;
			summedTime += elapsedTime;
		}
		
		// Report the results
		System.out.println(String.format("Performed Trials: %d times", trialTimes));
		System.out.println(String.format("Total Elasped Time: %d £gs", summedTime / 1000));
		System.out.println(String.format("Averaged Processing Time: %d £gs", summedTime / trialTimes / 1000));
	}
	
	public static Sender newSender(int type, String pythonPath, String scriptDir) throws IOException {
		switch (type) {
		case 1:
			return new TcpSender(IP, PORT);
		case 2:
			return new ForkSender(new File(scriptDir), pythonPath, SCRIPT_NAME);
		default:
			throw new RuntimeException("There is no type for id: " + type);
		}
	}
	
	public static void performTrial(Sender sender) throws IOException {
		String response = sender.send(TEST_STR);
		
		if (!response.equals(EXPECTED_RESPONSE)) {
			throw new RuntimeException("Expected result: " + EXPECTED_RESPONSE + ", but get: " + response);
		}
	}
}
