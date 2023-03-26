package com.server.server;
import java.io.*;
import java.net.*;
public class UDPServer {
    public static void main(String args[]) throws Exception
    {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        while(true)
        {
            int flag=0;
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String answer = sentence.toUpperCase();
            try {
                FileReader fileReader = new FileReader("C:\\Users\\Msys\\Desktop\\Networks1-HW1\\Server\\src\\main\\java\\com\\server\\server\\database.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                String sen="";
                for(int i=0;i<sentence.length();i++){
                    char in=sentence.charAt(i);
                    if(in>='a'&&in<='z' || in>='A'&&in<='Z' || in>='0'&&in<='9' || in==','||in==' ')
                        sen+=in;
                }
                while ((line = bufferedReader.readLine()) != null) {
                    String []input = line.split(" ");
                    if(sen.equals(input[0])){
                        answer = input[1] + ", " + input[2] + ", " + input[3] + ", " + input[4] + '\n';
                        sendData = answer.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                        serverSocket.send(sendPacket);
                        flag=1;
                        break;
                    }
                }
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(flag==0) {
                answer = "Vehicle is not found" + '\n';
                sendData = answer.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }
        }
    }
}