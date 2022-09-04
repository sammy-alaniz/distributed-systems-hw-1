import java.io.*;
import java.net.*;

public class Server {

  public static void parseBuffer(byte[] msgData) {
    String msg = new String(msgData);
    String[] tokens = msg.split(" ");

    if (tokens[0].equals("setmode")) {
      System.out.println("setmode hit");
      // TODO: set the mode of communication for sending commands to the server 
      // and display the name of the protocol that will be used in future
    }
    else if (tokens[0].equals("purchase")) {
      System.out.println("purchase hit");
      System.out.println(msg);
      // TODO: send appropriate command to the server and display the
      // appropriate responses form the server
    } else if (tokens[0].equals("cancel")) {
      // TODO: send appropriate command to the server and display the
      // appropriate responses form the server
    } else if (tokens[0].equals("search")) {
      // TODO: send appropriate command to the server and display the
      // appropriate responses form the server
    } else if (tokens[0].equals("list")) {
      // TODO: send appropriate command to the server and display the
      // appropriate responses form the server
    } else {
      System.out.println("ERROR: No such command");
    }
  }

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

          parseBuffer(dataPacket.getData());

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
