package slmt.porttest;

import java.io.IOException;

public interface Sender extends AutoCloseable {

	String send(String request) throws IOException;
	
}
