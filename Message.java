/*
 *
 * copyright Grant Malcolm
 *
 *   This source code may be freely used, modified, or distributed
 *   provided due credit is given.
 *
 */


/**
 *  This class represents messages for a simple Message Board server,
 *  implementing sort Message in
 *  <a href="MessageBoard.maude">MESSAGE_BOARD</a>.
 *  Messages consist of two strings: one representing the name of the poster;
 *  the other being the text of the message.
 *
 *
 *  @author <a href="mailto:grant@liverpool.ac.uk">Grant Malcolm</a>
 *  @version 1.0
 *
 */
public class Message
{

    /**
     *  The name of the poster who sent the message.
     *
     */
    private final String sender;

    /**
     *  The text of the message.
     *
     */
    private final String text;

    /**
     *  Creates a new <code>Message</code> instance with the given data.
     *  Implements newMessage : String String -&gt; Message.
     *
     *  @param s the name of the poster
     *  @param t the text of the message
     *
     */
    public Message(String s, String t)
    {
        sender = s;
        text = t;
    }

    /**
     *  Return the name of the sender of the message.
     *  Implements getSender : Message -&gt; String.
     *
     *  @return the name of the poster who sent the message
     *
     */
    public String getSender()
    {
        return sender;
    }

    /**
     *  Return the text of the message.
     *  Implements getText : Message -&gt; String.
     *
     *  @return the text of the message
     *
     */
    public String getText()
    {
        return text;
    }

    /**
     *  Give a string showing the sender and the message text.
     *  <p>To agree with the Maude tests, it might be better to have
     *      <code>return "newMessage(" + sender + ", " + text + ")";</code>,
     *  but the implementation here tries to give a slightly more readable
     *  format.</p>
     *
     *  @return a string in the format
     *   <code>From: <i>sender</i></code> followed by the text on the next line,
     *   and ended by a newline.
     *
     */
    public String toString()
    {
        return "From: " + sender + "\nMessage: " + text + "\n";
    }
}
