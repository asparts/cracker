package cracker;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class PortScanner {


	public String Scan(String host, int range1, int range2) throws UnknownHostException {
	
		//TODO: Make port range and stop printing bcuz this is slow af...
		
		String text = "";

        InetAddress inetAddress = InetAddress.getByName(host);
        
        String hostName = inetAddress.getHostName();
        for (int port = range1; port <= range2; port++) {
            try {
            	System.out.println("port " + port);
                Socket socket = new Socket(hostName, port);
                text += "" + hostName + " is listening on port " + port +"\n";
                //System.out.println(text);
                socket.close();
            } catch (IOException e) {
                // empty catch block
            }
        }
		return text;
    }
	
	
}
