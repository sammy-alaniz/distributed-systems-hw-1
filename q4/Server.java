import java.io.*;

public class Server {
  public static void main (String[] args) throws FileNotFoundException, IOException{
    int tcpPort;
    int udpPort;
    if (args.length != 3) {
      System.out.println("ERROR: Provide 3 arguments");
      System.out.println("\t(1) <tcpPort>: the port number for TCP connection");
      System.out.println("\t(2) <udpPort>: the port number for UDP connection");
      System.out.println("\t(3) <file>: the file of inventory");

      System.exit(-1);
    }
    tcpPort = Integer.parseInt(args[0]);
    udpPort = Integer.parseInt(args[1]);
    String fileName = args[2];



    // parse the inventory file
    // Geeks for Geeks : https://www.geeksforgeeks.org/bufferedreader-readline-method-in-java-with-examples/
    FileReader fileReader = new FileReader(fileName);
    BufferedReader buffReader = new BufferedReader(fileReader);
    while (buffReader.ready())
    {
      System.out.println(buffReader.readLine());
    }

    // TODO: handle request from clients
  }
}
