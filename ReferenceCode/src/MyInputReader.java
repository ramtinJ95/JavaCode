import java.io.*;
import java.util.Scanner;

/**
 * Created by Ramtin on 2017-06-16.
 *
 * This Class is where I will store my reference code for reading input to a program
 * from either console or a file
 */
public class MyInputReader {

    private void readFromStandardInput(){
        String data;

        try(BufferedReader in = new BufferedReader(new InputStreamReader(System.in))){
            while((data = in.readLine()) != null){
                System.out.println(data); // this is just a dummy operation
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void readFromStandardInputScanner(){
        Scanner scanner = new Scanner(System.in);
        int data = 0; // Does not have to be a int can be any primitive + string
        while(scanner.hasNextInt()){
            data = scanner.nextInt();
        }
        System.out.println(data);

    }

    private void readFromFile(){
        String inFileStr = "someFile.txt";
        String outFileStr = "someFile-out.txt";
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFileStr));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFileStr))) {

            byte[] byteBuf = new byte[4000];
            int numBytesRead;
            while ((numBytesRead = in.read(byteBuf)) != -1) {
                out.write(byteBuf, 0, numBytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
