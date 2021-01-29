import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client implements Runnable {
    final private BufferedReader reader;
    final private BufferedWriter writer;
    ArrayList<Client> clients;
    String clientname;

    public Client(Socket socket, ArrayList<Client> clients) throws IOException {
        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        reader = new BufferedReader(isr);

        OutputStreamWriter o = new OutputStreamWriter(socket.getOutputStream());
        writer = new BufferedWriter(o);

        clientname = reader.readLine();

        this.clients = clients;
    }


    @Override
    public void run() {
        String clientData = null;
        String condition = null;
        try {

            condition = reader.readLine()+"\n";
            System.out.println(condition);

            clientData = reader.readLine()+"\n";

            if (condition.contains("AllMsg")|| condition.contains("chat"))
                clientData = clientname + " : "+clientData;

            while (clientData != null) {
                for (Client client : clients){
                    synchronized (client.writer) {
                        if (condition.contains("AllMsg")){
                            client.writer.write("AllMsg\n");
                            client.writer.flush();
                        }
                        else if (condition.contains("notice")){
                            client.writer.write("notice\n");
                            client.writer.flush();
                        }
                        else if (condition.contains("assign1")){
                            client.writer.write("assign1\n");
                            client.writer.flush();
                        }
                        else if (condition.contains("assign2")){
                            client.writer.write("assign2\n");
                            client.writer.flush();
                        }
                        else if (condition.contains("assign3")){
                            client.writer.write("assign3\n");
                            client.writer.flush();
                        }
                        else if (condition.contains("chat1")){
                            client.writer.write("chat1\n");
                            client.writer.flush();
                        }
                        else if (condition.contains("chat2")){
                            client.writer.write("chat2\n");
                            client.writer.flush();
                        }
                        else if (condition.contains("chat3")){
                            client.writer.write("chat3\n");
                            client.writer.flush();
                        }

                        client.writer.write(clientData);
                        client.writer.flush();
                    }
                }
                condition = reader.readLine()+"\n";
                clientData = reader.readLine()+"\n";
                if (condition.contains("AllMsg") || condition.contains("chat"))
                    clientData = clientname + " : "+clientData;

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}