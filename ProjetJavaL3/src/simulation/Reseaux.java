package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Reseaux {
	
	Socket socket;
	InetAddress server_address;
	int port;
    BufferedReader in;
    PrintWriter out;
	
	public Reseaux(String adress, int port)
	{
		try
		{
			server_address = InetAddress.getByName(adress);
			this.port = port;
			socket = new Socket(server_address, port);
			System.out.println("Tentative de connection � "+server_address);
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
			System.out.println("Tentative de connection � "+server_address);
			//socket.close();
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{	
			e.printStackTrace();
		}
	}
	
	public int send(String message)
	{
		System.out.println("Envoi Message : "+message+"\n");
        out.println(message);
		return 0;
	}
	
	
	public String receive ()
	{
		String message = "";
		try
		{
			message = in.readLine();
		}
		catch (IOException e)
		{	
			e.printStackTrace();
		}
		return message;
	}
	

}
