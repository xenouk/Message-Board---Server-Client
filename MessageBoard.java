/**
 * Author: Guangpeng Li
 * ID: 200876363
 * Date: 06/12/13
 * University of Liverpool
 * Comp 213 Assignment 2
 */

/**
 * A Message Board server stores messages organised by topic.
 * Each topic consists of a number of messages received across
 * a network connection from client programs {@link EchoHandler}.
 * Your server will maintain a list of topics, and accept
 * connections from clients across the network.
 *
 * This server will allow multiple clients to connect with, as
 * threat is used. Therefore, clients can access to the server
 * simultaneously to {@link EchoHandler#addNewTopic()},
 * {@link EchoHandler#addMessage()} {@link EchoHandler#getTopic()}
 * and to get all topic IDs and names.
 *
 * @author <a href="mailto:sggli@liverpool.ac.uk">Guangpeng Li</a>
 * @version 1.0
 */

import java.io.*;
import java.net.*;

public class MessageBoard {
    /**
     * Port number for the server
     */
    private static final int PORT_NUM = 12056;

    /**
     * The server socket
     */
    private static ServerSocket ss;

    /**
     * Set when shutDown() is called to stop the server:
     */
    private static boolean shutDownCalled = false;

    /**
     * Shut the server down by closing the server socket
     */
    public static void shutDown() {
        // flag that the server socket has been closed
        shutDownCalled = true;

        try {
         /*
          *  close the server socket;
          *  call of accept() in main will throw a SocketException
          */
            ss.close();
        }
        catch (Exception e) {
            // something went wrong; give data:
            System.err.println("problem shutting down:");
            System.err.println(e.getMessage());
            // and trust the JVM to clean up:
            System.exit(1);
        }
    }

    public static void main(String[] args ) {
        // for client connections
        Socket incoming;

        // session-handling thread
        Thread t;

        try {
            // set up server socket
            ss = new ServerSocket(PORT_NUM);

            while (true) {
                incoming = ss.accept();

                // start session-handler in new thread
                t = new Thread(new EchoHandler(incoming));
                t.start();
            }
        }
        catch (SocketException se) {
         /*
          * will be thrown when accept() is called after closing
          * the server socket, in method shutDown().
          * If shutDownCalled, then simply exit; otherwise,
          * something else has happened:
          */
            if (!shutDownCalled) {
                System.err.println("socket problem:");
                System.err.println(se.getMessage());
                // trust the JVM to clean up
                System.exit(1);
            }
        }
        catch (IOException ioe) {
            System.err.println("I/O error:");
            System.err.println(ioe.getMessage());
            System.exit(1);
        }
        finally {
            if (ss != null) {
                try {
                    ss.close(); // Shut down the Server
                    System.out.println("Server Closed.");
                }
                catch (Exception e) {
                    System.err.println("closing: " + e.getMessage());
                }
            }
        }
    }
}



/**
 *  Session-handler class to handle one remote client
 *  in a separate thread. This class contains
 *  {@link SortedTopicList sorted topic list} which will store the
 *  message board in the server {@link MessageBoard Message Board}. The server
 *  will use socket system to connect clients remotely.
 *
 *  This class has 4 main functionality, which sends all {@link Topic#getID()}
 *  and {@link Topic#getTitle()} to the client when it receives the input
 *  string "0"
 *  It will send all senders {@link Message#getSender()} and
 *  {@link Message#getText()} to the client with the given {@link Topic#getID()}
 *  when it receives the input string "1"
 *  It will add input string from the client to the server when the input is "2"
 *  {@link SortedTopicList#addPost(Message, int)} and input "3"
 *  {@link SortedTopicList#addNewTopic(Message, String)}.
 *
 *  The threat will be handled synchronized with a lock, as the lock will keep
 *  the shared item and it will not unlock until the threat get its job done.
 *  Therefore, other threats will not be able to access the item while other
 *  threat is having it. Basically, application is threat-safe.
 *
 *  Any errors have caught by the exception will cause the clients
 *  lose connection from the server. The clients will also lose connections
 *  from the server when the job is done.
 *
 *  Any strings with empty space into front or back will be eliminated.
 *  e.g. "    .    " will become "."
 */
class EchoHandler implements Runnable {

    /**
     *  Instantiate a message board for this server
     *
     */
    private static SortedTopicList forum = new SortedTopicList();

    /**
     *  A lock for threat synchronize
     *
     */
    private static Integer lock = new Integer(0);

    /**
     *  To find whenever the client has done its job.
     *
     */
    private static boolean done;

    /**
     *  A string for handling I/O
     *
     */
    private String line;

    /**
     *  Input Reader for this echo handler
     *
     */
    BufferedReader in = null;

    /**
     *  Output writer for this echo handler
     *
     */
    PrintWriter out = null;

    /**
     *  The socket to connection to the remote client
     *
     */
    private Socket client;

    /**
     *  Creates a new <code>EchoHandler</code> instance with a given socket
     *
     *  @param s the socket for the client
     *
     */
    EchoHandler(Socket s) {
        client = s;
    }

    /**
     *  Handle session for one remote client:
     *  set up I/O, then repeat all input till
     *  client sends "close"
     */
    public void run() {
        System.out.println("Connected to a client.\n");
        try {
            // set up I/O
            in = new BufferedReader
                    (new InputStreamReader(client.getInputStream()));
            out = new PrintWriter
                    (new OutputStreamWriter(client.getOutputStream()));

            done = false;
            while (!done) {
                line = in.readLine().trim();
                System.out.println("Input From Client: " + line);

                  // if the input is null or "close"
                  // the client will lose connection
                if ((line == null) || (line.equals("close"))){
                    out.println("close");
                    out.flush();
                    done = true;
                } // if the input is "shutdown
                  // the server will close
                else if (line.equals("shutdown")) {
                    MessageBoard.shutDown();
                    return;
                } // else if the input is "0"
                  // send all topics to the client
                else if(line.equals("0")){
                    out.println("Number of Topics: "+forum.length());
                    out.println(forum);
                    out.flush();
                    done = true;
                } // else if the input is "1"
                  // send a topic to the client
                else if(line.equals("1")){
                    getTopic();
                } // else if the input is "2"
                  // add message to a topic
                else if(line.equals("2")){
                    synchronized (lock){
                        addMessage();
                    }
                } // else if the input is "3"
                  // add a new topic
                else if(line.equals("3")){
                    synchronized (lock){
                        addNewTopic();
                    }
                } // else the input is invalid
                else {
                    System.out.println("Invalid Command: " + line);
                    System.out.println();
                    out.println("Invalid Command: " + line);
                    out.println("Please try again");
                    out.flush();
                }
            }
        } catch (IOException ioe) {
            // Add post pointer null error
            System.err.println(ioe.getMessage());
        } finally {  // close connections
            try {
                in.close(); // Close input
            }
            catch(IOException ioe) {
            }
            if (out != null) {
                out.close(); // Close output
            }
            if (client != null) {
                try {
                    client.close(); // Close Client Socket
                    System.out.println("Client Disconnected.\n");
                }
                catch (IOException ioe) {
                }
            }
        }
    }

    /**
     *  Add string inputs into {@link SortedTopicList sorted topic list}
     *  The code that implements this method is
     *  incorporated in the implementation of {@link #run run}.
     *  When successfully added the topic into the message board, this
     *  will send "3" back to the client.
     *
     *  When the input format is wrong, this will send "6" back
     *  to the client. The Message Board will change nothing.
     */
    private void addNewTopic() {
        try{
            String topicName = in.readLine().trim();
            System.out.println("Topic: " + topicName);

            String sender = in.readLine().trim();
            System.out.println("From: " + sender);
            // To get the checked message protocol
            String message = isMessage();
            // To check if the topic name and sender
            // is valid
            if(topicName.equals("") || topicName == null ||
                    sender.equals("") || sender == null){
                throw new IOException();
            }
            // Add the topic into the message board
            forum.addNewTopic(new Message(sender,message),topicName);
            System.out.println("Message:\n" + message);
            out.println("3");
            out.flush();
            done = true;
        }catch (IOException ioe){
            // Message format error caught. eg. without prefix 1
            System.err.println(ioe.getMessage());
            System.out.println("Add topic error: 6");
            out.println("6");
            out.flush();
            done = true;
        }
    }

    /**
     *  Add string inputs into {@link SortedTopicList sorted topic list}
     *  with the given ID.
     *  The code that implements this method is
     *  incorporated in the implementation of {@link #run run}.
     *  When successfully added the message into the topic, this
     *  will send "2" back to the client.
     *
     *  When the ID is not found, this will send "5" back to the client
     *  When the input format is wrong, this will send "6\n" back
     *  to the client. The Message Board will change nothing.
     */
    private void addMessage() {
        try{
            String topicID = in.readLine().trim();
            System.out.println("Insert to Topic ID: " + topicID);

            String sender = in.readLine().trim();
            System.out.println("From:" + sender);
            // To get the checked message protocol
            String message = isMessage();
            // Throw an error when ID is not found
            if(forum.getTopic(Integer.parseInt(topicID)) == null){
                throw new NullPointerException();
            } // to check if the sender is valid
            else if(sender.equals("") || sender == null){
                throw new IOException();
            }
            // The input is added to the Message board
            forum.addPost(new Message(sender,message),Integer.parseInt(topicID));
            System.out.println("Message:\n" + message);
            out.println("2\n");
            out.flush();
            done = true;
        }catch (IOException ioe){
            // Message format error caught. eg. without prefix 1
            System.err.println(ioe.getMessage());
            System.out.println("Add post error: 6\n");
            out.println("6\n");
            out.flush();
            done = true;
        }catch (NullPointerException npe){
            // ID is not found error caught
            System.err.println(npe.getMessage());
            System.out.println("Add post ID error: 5");
            out.println("5");
            out.flush();
            done = true;
        }catch (NumberFormatException nfe){
            // Catch any number format error. eg. ID is not a number
            System.err.println(nfe.getMessage());
            System.out.println("Add post ID error: 5");
            out.println("5");
            out.flush();
            done = true;
        }
    }

    /**
     *  Send all senders and messages in topic
     *  with the ID from client back to the client.
     *  The code that implements this method is
     *  incorporated in the implementation of
     *  {@link #run run}.
     *
     *  When the ID is not found, this will send "4\n" back to the client
     */
    private void getTopic() {
        try{
            String topicID = in.readLine().trim();
            System.out.println("Insert to Topic ID: " + topicID);
            // Throw an io exception if the received ID is null or empty
            if(forum.getTopic(Integer.parseInt(topicID)) == null || topicID.equals("")){
                throw new IOException();
            }
            // Output the topic to the client
            out.println(forum.getTopic(Integer.parseInt(topicID)));
            out.flush();
            done = true;
        }catch(IOException ioe){
            // Catch any I/O errors
            System.err.println(ioe.getMessage());
            System.out.println("View post ID error: 4\n");
            out.println("4\n");
            out.flush();
            done = true;
        }catch (NumberFormatException nfe){
            // Catch any number format error. eg. ID is not a number
            System.err.println(nfe.getMessage());
            System.out.println("View post ID error: 4\n");
            out.println("4\n");
            out.flush();
            done = true;
        }
    }

    /**
     *  To check if the input message meets the protocol
     *  The code that implements this method is
     *  incorporated in the implementation of
     *  {@link #addNewTopic()}  add new topic} and
     *  {@link #addMessage() add message}.
     *
     *  If the message meets the protocol, it will return the message.
     *  else throws an i/o exception.
     */
    private String isMessage() throws IOException{
        // define the protocol has met or not
        boolean met = true;
        // store the message
        String message = "";
        do{
            line = in.readLine().trim();
            // if the line of string is prefix with 1
            // or only is ".", will be added to the topic
            if(line.startsWith("1") || (line.equals("."))) {
                message += line + "\n";
            }
            // else the server will disconnect the client
            else met = false;
            // Loop will stop when line is equal to "."
        }while(!line.equals("."));
        // when the message does not meet the protocol
        if(!met) throw new IOException();
        // the message will return if met is true
        return message;
    }
}