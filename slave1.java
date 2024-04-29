import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class slave1
{
	public static void main(String[] args) throws Exception
	{
		Scanner sc= new Scanner(System.in);
		Socket socket=new Socket("localhost",9001);
		BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
		System.out.print("Enter your name: ");
		String name =sc.nextLine();
		while (true)
		{
			String msg="";
			String line=in.readLine();
			//System.out.print("\nread at slave1: "+line);
			if(line.startsWith("SUBMITNAME")) 
				out.println(name);
			else if (line.startsWith("MESSAGE"))
			System.out.println("\n"+line.substring(8));
			if(name.startsWith("slave1"))
			{ 
				System.out.print("Enter a message: ");
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