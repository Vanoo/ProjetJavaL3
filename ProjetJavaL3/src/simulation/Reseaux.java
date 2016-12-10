package simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;

public class Reseaux {
	
	
	Socket socket;
	InetAddress server_address;
	int port;
    BufferedReader in;
    PrintWriter out;
	
	public Reseaux(String adress, int port)
	{
		// Avoir l'adresse grace a l'ip fournie 
		try
		{
			server_address = InetAddress.getByName(adress);
			this.port = port;
			System.out.println("Tentative de connection a"+server_address+":"+port);
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		
		// Ouverture de la connexion
		try
		{
			
			socket = new Socket(server_address, port);
			System.out.println("pllop");
			
		}
		catch (IOException e)
		{	
			e.printStackTrace();
		}
		
		// Creation des entrees/sorties
		try
		{
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			System.out.println("plllop");
			//socket.close();
		}
		catch (IOException e)
		{	
			e.printStackTrace();
		}
		
	}	
	
	public int send(String message)
	{
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
	
	public void close() 
	{
		try
		{
			this.socket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}