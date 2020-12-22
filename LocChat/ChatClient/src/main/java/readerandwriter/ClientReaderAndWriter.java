package chat.readerandwriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ClientReaderAndWriter {

    public void write(String msg){

      //  String txtPath = "src/main/resources/lib/hp.txt";
        String txtPath = "hp.txt";
        try (BufferedWriter writer = new BufferedWriter( new FileWriter( txtPath))) {
                writer.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void read(){

    }


}
