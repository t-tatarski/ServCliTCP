import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        /*
        -------------------------
        client TCP - SKJ int 2020
        -------------------------
         */
        final int SOCKETNR = 65000;
        String stringToServ;
        String stringFronSrv;

        System.out.println("*************************");
        System.out.println("** C L I E N T  SOCKET **");
        System.out.println("*************************");
        System.out.println("otwieram polaczenie na porcie "+ SOCKETNR);
        System.out.println("adress :"+ InetAddress.getLocalHost());
        System.out.println("wyslij tekst na serwer :");

        BufferedReader inString = new BufferedReader(new InputStreamReader(System.in));
        try {
            Socket clientSocket = new Socket("127.0.0.1", SOCKETNR); /// tu może trzeba bedzie zmienic adres
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            stringToServ = inString.readLine();
            outputStream.writeBytes(stringToServ + '\n');
            stringFronSrv = bufferedReader.readLine();
            System.out.println("OTRZYMANO Z SERWERA : \n" + stringFronSrv);
            clientSocket.close();
            if (clientSocket == null)  System.out.println("zakonczono poloczenie");
        }catch(IOException e){
            System.out.println("nie udalo sie nawiazac poloczenia z serwerem "+SOCKETNR);
        }
    }
}
