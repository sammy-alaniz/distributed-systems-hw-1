import java.io.*;
import java.net.*;

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
    // Chapter 6 : Distributed Programming
    int len = 1024;
    DatagramPacket dataPacket, returnPacket;
    try
    {
      DatagramSocket dataSocket = new DatagramSocket(udpPort);
      byte[] buf = new byte[len];
      while(true)
      {
        try
        {
          dataPacket = new DatagramPacket(buf, buf.length);
          dataSocket.receive(dataPacket);
          String tmp = new String(dataPacket.getData());
          System.out.println(tmp);
          returnPacket = new DatagramPacket(dataPacket.getData(),
                                            dataPacket.getLength(),
                                            dataPacket.getAddress(),
                                            dataPacket.getPort());
          dataSocket.send(returnPacket);
        }
        catch (IOException e)
        {
          System.err.println(e);
        }
      }
    }
    catch (SocketException se)
    {
      System.err.println((se));
    }
  }
}
