package readerandwriter;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import java.io.*;

public class ClientReaderAndWriter {


    String path = null;

    public ClientReaderAndWriter(String path){
        this.path = path;
    }


    public void fileWrite(String msg) {
  //      File file = new File("src/main/resources/lib/test1.txt");
        File file = new File(path);
        try(FileWriter writer = new FileWriter(file, true)) {
            writer.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read(String filename){
        try (BufferedReader reader = new BufferedReader( new FileReader(filename))) {
            String str;
            while ((str = reader.readLine()) != null ) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int countLines(){
        int count = 0;
        try (BufferedReader reader = new BufferedReader( new FileReader(path))) {
            String str;
            while ((str = reader.readLine()) != null) {
                if (!str.equals("")){
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public String readLine(int expectedLineNumber){
        LineIterator it = null;
        try {
            it = IOUtils.lineIterator(
                    new BufferedReader(new FileReader(path)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int lineNumber = 0; it.hasNext(); lineNumber++) {
            String line = (String) it.next();
            if (lineNumber == expectedLineNumber) {
                return line;
            }
        }
        return null;
    }

    public String readLastLine(int Lline){
        int  expectedLineNumber = countLines() - Lline;
        LineIterator it = null;
        try {
            it = IOUtils.lineIterator(
                    new BufferedReader(new FileReader(path)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int lineNumber = 0; it.hasNext(); lineNumber++) {
            String line = (String) it.next();
            if (lineNumber == expectedLineNumber) {
                return line;
            }
        }
        return null;
    }

    public void readLastStrings(int n){
        if (countLines() >= n){
            for (int i = 0; i < n; i++) {
                System.out.println(readLine(countLines() - i));
            }
        }else{
            for (int i = 0; i < countLines(); i++) {
                System.out.println(readLine(countLines() - i));
            }
        }
    }

}
