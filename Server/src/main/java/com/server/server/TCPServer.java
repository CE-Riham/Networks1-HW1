package com.server.server;
import java.io.*;
import java.net.*;
public class TCPServer {
    public static void main(String argv[]) throws Exception
    {
        String clientSentence;
        String answer;
        ServerSocket welcomeSocket = new ServerSocket(6789);
        while(true) {
            int flag=0;
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();

            try {
                FileReader fileReader = new FileReader("C:\\Users\\Msys\\Desktop\\Networks1-HW1\\Server\\src\\main\\java\\com\\server\\server\\database.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String []input = line.split(" ");
                    if(clientSentence.equals(input[0])){
                        answer = input[1] + ", " + input[2] + ", " + input[3] + ", " + input[4] + '\n';
                        outToClient.writeBytes(answer);
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
                outToClient.writeBytes(answer);
            }
        }
    }
}

