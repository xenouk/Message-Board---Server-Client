/**
 * Author: Guangpeng Li
 * ID: 200876363
 * Date: 06/12/13
 * University of Liverpool
 * Comp 213 Assignment 2
 */

/**
 * For purpose of testing
 */
import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException{
        String line;

        // Should gives error
        Socket client = new Socket("localhost", 12056);
        BufferedReader in = new BufferedReader
                (new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter
                (new OutputStreamWriter(client.getOutputStream()));
        out.flush();
        System.out.println("\nAdd Topic Test 1, Send 3: ");
        out.println("3");
        out.println("Topic 0");
        out.println("Tester 3");
        out.println("1Hello, New Board!\nEither that background goes\n1or I do.");
        out.println(".");
        out.flush();
        System.out.println(in.readLine());
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        // Should be error
        client = new Socket("localhost", 12056);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        System.out.println("\nAdd Topic Test 2, Send 3: ");
        out.println("3");
        out.println("Topic 0");
        out.println("Tester 3");
        out.println("");
        out.println(".");
        out.flush();
        System.out.println(in.readLine());
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        // Should be error
        client = new Socket("localhost", 12056);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        System.out.println("\nAdd Topic Test 3, Send 3: ");
        out.println("3");
        out.println("Topic 0");
        out.println("Tester 3");
        out.println();
        out.println(".");
        out.flush();
        System.out.println(in.readLine());
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        // Should be error
        client = new Socket("localhost", 12056);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        System.out.println("\nAdd Topic Test 4, Send 3: ");
        out.println("3");
        out.println("");
        out.println("Tester 3");
        out.println("1Hello, New Board!\n1Either that background goes\n1or I do.");
        out.println(".");
        out.flush();
        System.out.println(in.readLine());
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        // Should be error
        client = new Socket("localhost", 12056);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        System.out.println("\nAdd Topic Test 5, Send 3: ");
        out.println("3");
        out.println();
        out.println("Tester 3");
        out.println("1Hello, New Board!\n1Either that background goes\n1or I do.");
        out.println(".");
        out.flush();
        System.out.println(in.readLine());
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        // Should be error
        client = new Socket("localhost", 12056);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        System.out.println("\nAdd Topic Test 6, Send 3: ");
        out.println("3");
        out.println("Topic 0");
        out.println("");
        out.println("1Hello, New Board!\n1Either that background goes\n1or I do.");
        out.println(".");
        out.flush();
        System.out.println(in.readLine());
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        // Should be error
        client = new Socket("localhost", 12056);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        System.out.println("\nAdd Topic Test 7, Send 3: ");
        out.println("3");
        out.println("Topic 0");
        out.println();
        out.println("1Hello, New Board!\n1Either that background goes\n1or I do.");
        out.println(".");
        out.flush();
        System.out.println(in.readLine());
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        // Should gives error
        client = new Socket("localhost", 12056);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        System.out.println("\nAdd Message Test 1, Send 2: ");
        out.println("2");
        out.println("0");
        out.println("Tester 2");
        out.println("1Working~\n1really working?\n1I guess so.");
        out.println(".");
        out.flush();
        System.out.println(in.readLine());
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        // Should gives error
        client = new Socket("localhost", 12056);
        System.out.println("\nGet Topic Test 1, Send 1: ");
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        out.println("1");
        out.println("5");
        out.flush();
        while ((line = in.readLine()) != null) {
           System.out.println(line);
        }
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        // Should gives error
        client = new Socket("localhost", 12056);
        System.out.println("\nGet Topic Test 2, Send 1: ");
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        out.println("1");
        out.println("");
        out.flush();
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        // Should gives error
        client = new Socket("localhost", 12056);
        System.out.println("\nGet Topic Test 3, Send 1: ");
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        out.println("1");
        out.println();
        out.flush();
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        // Should be success
        client = new Socket("localhost", 12056);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        System.out.println("\nAdd Topic Test 2, Send 3: ");
        out.println("3");
        out.println("Topic 0");
        out.println("Tester 2");
        out.println("1Hello, New Board!\n1Either that background goes\n1or I do.");
        out.println(".");
        out.flush();
        System.out.println(in.readLine());
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        // Should be error
        client = new Socket("localhost", 12056);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        System.out.println("\nAdd Message Test 2, Send 2: ");
        out.println("2");
        out.println("0");
        out.println("Tester 2");
        out.println("Working~\n1really working?\n1I guess so.");
        out.println(".");
        out.flush();
        System.out.println(in.readLine());
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        // Should be error
        client = new Socket("localhost", 12056);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        System.out.println("\nAdd Message Test 3, Send 2: ");
        out.println("2");
        out.println("0");
        out.println("Tester 2");
        out.println();
        out.println(".");
        out.flush();
        System.out.println(in.readLine());
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        // Should be error
        client = new Socket("localhost", 12056);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        System.out.println("\nAdd Message Test 4, Send 2: ");
        out.println("2");
        out.println("0");
        out.println();
        out.println("1Working~\n1really working?\n1I guess so.");
        out.println(".");
        out.flush();
        System.out.println(in.readLine());
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        // Should be error
        client = new Socket("localhost", 12056);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        System.out.println("\nAdd Message Test 5, Send 2: ");
        out.println("2");
        out.println("0");
        out.println("Tester 2");
        out.println("");
        out.println(".");
        out.flush();
        System.out.println(in.readLine());
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        // Should be error
        client = new Socket("localhost", 12056);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        System.out.println("\nAdd Message Test 6, Send 2: ");
        out.println("2");
        out.println("0");
        out.println("");
        out.println("1Working~\n1really working?\n1I guess so.");
        out.println(".");
        out.flush();
        System.out.println(in.readLine());
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        // Should be error
        client = new Socket("localhost", 12056);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        System.out.println("\nAdd Message Test 7, Send 2: ");
        out.println("2");
        out.println("");
        out.println("Tester 2");
        out.println("1Working~\n1really working?\n1I guess so.");
        out.println(".");
        out.flush();
        System.out.println(in.readLine());
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        // Should be error
        client = new Socket("localhost", 12056);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        System.out.println("\nAdd Message Test 8, Send 2: ");
        out.println("2");
        out.println();
        out.println("Tester 2");
        out.println("1Working~\n1really working?\n1I guess so.");
        out.println(".");
        out.flush();
        System.out.println(in.readLine());
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }


        // Should be success
        client = new Socket("localhost", 12056);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        System.out.println("\nAdd Message Test 9, Send 2: ");
        out.println("2");
        out.println("0");
        out.println("Tester 4");
        out.println("1Working~\n1really working?\n1I guess so.");
        out.println(".");
        out.flush();
        System.out.println(in.readLine());
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        // Should be success
        client = new Socket("localhost", 12056);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        System.out.println("\nAdd Topic Test 8, Send 3: ");
        out.println("3");
        out.println("Topic 1");
        out.println("Tester 3B");
        out.println("1You are Fun!\n1Yesssss!");
        out.println(".");
        out.flush();
        System.out.println(in.readLine());
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        // Should be success
        client = new Socket("localhost", 12056);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        System.out.println("\nAdd Message Test 10, Send 2: ");
        out.println("2");
        out.println("0");
        out.println("Tester 1");
        out.println("1Haha~\n1You don't say?!\n1loooool.");
        out.println(".");
        out.flush();
        System.out.println(in.readLine());
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        // Should be success
        client = new Socket("localhost", 12056);
        System.out.println("\nGet Topic Test 1, Send 1: ");
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        out.println("1");
        out.println("0");
        out.flush();
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        client = new Socket("localhost", 12056);
        System.out.println("\nGet Topic IDs, Send 0: ");
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        out.println("0");
        out.flush();
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        try{
            in.close();
            out.close();
            client.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
