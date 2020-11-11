import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {

        String clientSentence;
        String capitalizedStr;
        boolean listening=true;
        final int PORTNR = 65000;

        System.out.println("------------------------");
        System.out.println("|  S E R V E R   T C P |");
        System.out.println("------------------------");
        System.out.println(" port :"+ PORTNR);

        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("adres serwera :"+inetAddress.getHostAddress());

        try {
            ServerSocket welcomeSocket = new ServerSocket(PORTNR);
            System.out.println("chyba dziala ..." + welcomeSocket.getLocalSocketAddress());
            System.out.println("reciv buff size : " + welcomeSocket.getReceiveBufferSize());
            System.out.println("gniazdo" + welcomeSocket.getInetAddress() + "\n adres:" + inetAddress);

            while(listening){
                Socket connectionSocket = welcomeSocket.accept();
                System.out.println(".....listening");
                BufferedReader inFromClient = new BufferedReader(
                        new InputStreamReader(connectionSocket.getInputStream()));
                DataOutputStream outputStream = new DataOutputStream(connectionSocket.getOutputStream());
                clientSentence = inFromClient.readLine();
                if (clientSentence.trim().equals("exit")) listening=false;
                capitalizedStr = clientSentence.toUpperCase()+'\n';
                System.out.println("przetworzono "+capitalizedStr);

                outputStream.writeBytes(capitalizedStr);

            }
            System.out.println("server stopped");
        }catch (IOException e){
            System.out.println("nie mogę nasluchiwac na gniezezie "+PORTNR);
        }
    }
}
