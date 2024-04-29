import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class master
{
	 public static void main(String args[]) throws Exception
	{
		Scanner sc=new Scanner(System.in);
		Socket socket=new Socket("localhost",9001);

		//READ DATA FROM SOCKET
		BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
		System.out.print("Enter your name:");
		String name=sc.nextLine();
		while(true)
		{
			// READ MESSAGE OF A SERVER WHICH IS SUBMITNAME IN SERVER
			String line=in.readLine();
			String msg="";
			//System.out.print("read at master: "+line);
			if(line.startsWith("SUBMITNAME")) 
				// SEND NAME TO SERVER
				out.println(name);

				// MESSAGE COMING FROM SERVER FOR LOOP
			else if (line.startsWith("MESSAGE"))
				System.out.println(line.substring(8));

			// MASTER GIVEN ON INPUT I.E ENTER YOUR NAME
			if(name.startsWith("master"))
			{
				System.out.print("Enter a message:");
				msg=sc.nextLine();
				out.println(msg);
			}
			if (msg.equals("stop"))
			{
					System.exit(0);
			}
		}
	}
}