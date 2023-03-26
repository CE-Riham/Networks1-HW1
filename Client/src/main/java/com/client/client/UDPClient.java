package com.client.client;
import java.io.*;
import java.net.*;
class UDPClient {
    public static void main(String args[]) throws Exception
    {

        while(true) {
            System.out.println("Enter 0 to exit");
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("192.168.1.104");
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
            String sentence = inFromUser.readLine();
            if(sentence.equals("0")){
                clientSocket.close();
                break;
            }
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String result = new String(receivePacket.getData());
            System.out.println("FROM SERVER:" );
            for(int i=0;i<result.length();i++){
                char in=result.charAt(i);
                if(in>='a'&&in<='z' || in>='A'&&in<='Z' || in>='0'&&in<='9' || in==','||in==' ')
                    System.out.print(in);
            }
            System.out.println();
            clientSocket.close();
        }
    }
}