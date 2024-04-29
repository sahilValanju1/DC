// READ OR WRITE DATA OVER NETWORK
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.PrintWriter;

// TO ESTABLISHED CONNECTION
import java.net.ServerSocket;
import java.net.Socket;

// FOR STORE NAMES OF CLIENT IN A VECTOR USED FOR MULTICAST
import java.util.Vector;

// ALL CLASS MUST BE PUBLIC TO COMMUNICATE ON NETWORK
public class Server
{
	// WHAT TYPE OF DATA STORING IN VECTOR <PRINT WRITER>
	private static Vector<PrintWriter> writers=new Vector<PrintWriter>();
	public static void main(String args[]) throws Exception
	{
		// SERVER SOCKET CLASS IE SERVER PUBLISH THAT LISTENING ON THIS PORT
		ServerSocket listener=new ServerSocket(9001); // PORT NO. 9001

		// DISPLAYING MESSAGE
		System.out.println("The server is running at port 9001.");

		// CONTINUOUSLY LISTENING
		while(true) // continuously accepts the cliect requests and start the thread for each client
		{
			// START() IS A METHOD OF THREAD
			//LISTENER.ACCEPT RETURN SOCKET OBJECT OF CLIENT
			// START() EXECUTE RUN METHD
			// HANDLER CREATES OBJECTS
			new Handler(listener.accept()).start(); //HANDLER CONSTRUCUTOR START THREAD
		}
	}// MAIN COMPLETE

	// INHERITING THREAD CLASS
	private static class Handler extends Thread
	{
		private Socket socket;
		public Handler(Socket socket)
		{
			this.socket=socket;
		}
		// EXECUTE PER THREAD
		public void run()
		{
			try
			{		// SOCKET.GETINPUTSTREAM-- READ DATA FROM PARTICULAR CLIENT
					BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
					// WRITE TO EACH AND EVERY CLIENT
					PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
					
					// BROADCAST MESSAGE SUBMITNAME
					out.println("SUBMITNAME");

					// SERVER READ AND STORE DATA
					String name=in.readLine();
					System.out.println(name+ "joined");

					// WRITER IS A VECTOR
					// ADD METHOD TO STORE OUT IN VECTOR
					writers.add(out);

					// CONTINUOUSLY SEND AND RECIEVE
					while(true)
					{
						String input=in.readLine();
						//System.out.println("read at server:"+input);
						System.out.println("MESSAGE " +name+ ":" +input);

						//FROM WRITER VECTOR EACH ELEMENT PRINTING I.E FOR MULTICASTING
						for (PrintWriter x:writers)
							x.println("MESSAGE " +name+ ":" +input);
						/*if (input.equals("stop"))
						{
							System.exit(0);
						}*/
					}
			}
			catch(Exception e)
			{
				System.err.println(e);
			}
		}
	}
}