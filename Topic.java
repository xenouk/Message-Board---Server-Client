/*
 *
 * copyright Grant Malcolm
 *
 *   This source code may be freely used, modified, or distributed
 *   provided due credit is given.
 *
 */



/**
 *  A class of Topics for a simple Message Board server,
 *  implementing sort Topic in <a href="MessageBoard.maude">MESSAGE_BOARD</a>.
 *  Each topic consists of a list of {@link Message Messages}
 *  that have been posted to the server.  Each topic also has an ID,
 *  which should be assigned by the server in such a way that it
 *  uniquely identifies the topic
 *  (this is done in class {@link SortedTopicList SortedTopicList}).
 *  Topics also have a title, which should be provided by the 
 *  Message Board user, along with an initial message to start
 *  the topic (see {@link #Topic(Message,String,int) the constructor}).
 *
 *
 *  @author <a href="mailto:grant@liverpool.ac.uk">Grant Malcolm</a>
 *  @version 1.0
 *
 */
public class Topic
{
    /**
     *  The list of {@link Message messages} in the topic.
     *  Messages are stored in reverse order:
     *  the first message is at the end of the list;
     *  the most-recently added message is at the start.
     *
     */
    private final List<Message> messages = new List<Message>();

    /**
     *  The unique identifier for this topic.
     *
     */
    private final int id;

    /**
     *  The title of this topic.
     *
     */
    private final String title;

    /**
     *  Creates a new <code>Topic</code> instance with a given ID, title,
     *  and {@link Message message} to initiate the topic.
     *  Implements newTopic : Message Int String -&gt; Topic.
     *
     *  @param m the {@link Message Message} that initiates the topic
     *  @param t the title of the topic
     *  @param i the unique identifier for this topic
     *
     */
    public Topic(Message m, String t, int i)
    {
        id = i;
        title = t;

        // add the given message to the list of messages
        messages.add(m);
    }

    /**
     *  Return the unique identifier for the topic.
     *  Implements getID : Topic -&gt; Int.
     *
     *  @return the topic's identifier
     *
     */
    public int getID()
    {
        return id;
    }

    /**
     *  Return the title of the topic.
     *  Implements getTitle : Topic -&gt; String.
     *
     *  @return the topic's title
     *
     */
    public String getTitle()
    {
        return title;
    }

    /**
     *  Add a {@link Message Message} to the topic}.
     *  Implements addMsg : Message Topic -&gt; Topic.
     *
     *  @param m the message to be added.
     *
     */
    public void addMessage(Message m)
    {
        messages.add(m);
    }

    /**
     *  Formats the topic as a string.
     *  <p>Gives the title an ID of the topic,
     *  and all the messages in the topic in a readable format.
     *
     *  @return a string consisting of the topic title and ID,
     *    followed by all the messages in the topic
     *
     *  @see Message#toString Message#toString
     *
     */
    public String toString()
    {
        // title and ID #Edited by Guang Peng Li
        //String topicInfo = "Topic: " + title + "\nID: " + id + "\n";

        //  variable msgs stores the string of all messages in the topic.
        String msgs = "";
        // start at first msg
        List.BiNode<Message> currentNode = messages.theList;
      /*
       *  msgs contains all the messages posted *after* the message
       *  in currentNode
       */
        while (currentNode != null)
        {
         /*
          *  the message in currentNode was posted *before*
          *  the messages already in msgs,
          *  so put that message at the start of the string
          */
            msgs = currentNode.value + msgs;
            currentNode = currentNode.next;  // move on to the next node
        }

        // return title + ID + all messages #Edited by Guang Peng Li
        return msgs;
    }
}
