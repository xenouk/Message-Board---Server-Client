/*
 * copyright Grant Malcolm
 *
 *   This source code may be freely used, modified, or distributed
 *   provided due credit is given.
 *
 * Version 1.0
 */


/**
 *  List of topics for a simple Message Board server.
 *  This class represents a list of {@link Topic Topics} that might
 *  be stored by a Message Board server.  Topics are stored in the order
 *  of their most-recent update, the most-recently updated topics being
 *  at the start of the list.
 *  Topics are {@link #addNewTopic(Message,String) created}
 *  by providing a title and a {@link Message Message} to initiate the topic;
 *  the topic is given a unique identifier (the length of the list),
 *  and stored at the start of the list.
 *  Topics may be updated by {@link #addPost(Message,int) adding}
 *  a message to a given topic
 *  (specified by its {@link Topic#getID() identifier});
 *  the updated topic is moved to the start of the list.
 *  </p><p>
 *  Note: this class extends List<Topic>
 *  in order to add the functionality of adding topics,
 *  and adding messages to topics with the updated topic moving to the
 *  start of the list;
 *  this uses the protected scope of class BiNode in class List,
 *  and the protected scope of field theList in class List.
 *
 *  @author <a href="mailto:grant@liverpool.ac.uk">Grant Malcolm</a>
 *  @version 1.1
 *
 */
public class SortedTopicList extends List<Topic>
{
    /**
     *  The number of topics in the list.
     *
     */
    private int length = 0;

    /**
     * Creates an empty list of Topics.
     * Implements <code>empty : -&gt; SortedTopicList</code>.
     *
     */
    public SortedTopicList()
    {
    }

    /**
     *  The length of the list; i.e., the number of {@link Topic topics}
     *  in the list.
     *  Implements <code>length : SortedTopicList -&gt; Int</code>.
     *  </p><p>
     *  Note: this isn't used, so shouldn't be here.
     *
     *  @return the number of topics in the list
     */
    public int length()
    {
        return length;
    }

    /**
     *  Add a new Topic to the list.  The {@link Topic Topic} contains
     *  the given {@link Message message}, and is assigned a unique identifier
     *  (the {@link #length() length} of the list).  The new topic is
     *  placed at thestart of the list.
     *
     *  @param m the first message in the topic
     *  @param t the title of the topic
     *
     */
    public void addNewTopic(Message m, String t)
    {
        this.add(new Topic(m,t,length));

        // list is one topic longer
        length++;
    }

    /**
     *  Add a message to a topic.  The given {@link Message message} will be
     *  added to the {@link Topic topic} with the given ID;
     *  this topic will be moved to the start of the list,
     *  as it is the most-recently updated.
     *  If there is no topic in the list with the given ID,
     *  this method has no effect.
     *
     *  @param m the message to be added
     *  @param i the id of the topic to add the message to
     *
     */
    public void addPost(Message m, int i)
    {
        /*
         * We could do this with
         *
         *   Topic t = getTopic(i);
         *   removeTopic(i);
         *   t.addMessage(m);
         *   add(t);
         *
         * But getTopic() and removeTopic() both loop through the list
         * in a similar way; the following code combines these loops into
         * one loop that has the same effect as the code above,
         * but is more efficient.
         *
         */

        // if list is empty, do nothing
        if (theList == null)
        {
            return;
        }
      /*
       *  else:
       *  if the first element has the requested id, add the message
       */
        if (theList.value.getID() == i)
        {
            theList.value.addMessage(m);
            return;
        }
      /*
       *  else:
       *  (the list is not empty and the first topic is not the one we want):
       *  traverse through the list to find the topic with ID i
       */
        BiNode<Topic> t = theList.next;  // the next topic to look at

        while (t != null) // stop at end of list
        {
            if (t.value.getID() == i)
            {
            /*
             * found topic with ID i:
             * add the message and move to start of list
             */

                // add message
                t.value.addMessage(m);

                // remove t from list;
                t.prev.next = t.next;
                if (t.next != null) // there is a topic after t
                {
                    t.next.prev = t.prev;
                }

                // move t to start of list
                t.next = theList;  // apres moi, all the other topics
                theList = t;       // now t is at start of list
                // and adjust the prev pointers
                if (theList.next != null)
                {
                    theList.next.prev = theList;
                }
                return;  // done
            }
            // else (haven't found the topic): move on to tail of list
            t = t.next;
        }
        // got through without finding i: do nothing
    }

    /**
     *  Get the topic with the given ID.  This method is only required
     *  for pedagogic reasons; the code that implements this method is
     *  incorporated in the implementation of
     *  {@link #addPost addPost}.
     *
     *  @param id the ID of the topic to find
     *  @return the topic with the given ID
     *
     */
    public Topic getTopic(int id)
    {
        // traverse through this copy of the list
        BiNode<Topic> tl = theList;

        // stop when found, or at end of list
        while (tl != null)
        {
            if (tl.value.getID() == id)
            {
                // got it
                return tl.value;
            }
            // else: move on to next topic
            tl = tl.next;
        }
        // if we're here, the topic isn't in the list
        return null;
    }

    /**
     *  Remove the topic with the given ID.  This method is only required
     *  for paedogogic reasons; the code that implements this method is
     *  incorporated in the implementation of
     *  {@link #addPost addPost}.
     *
     *  @param id the ID of the topic to remove
     *
     */
    private void removeTopic(int id)
    {
        // if list is empty, do nothing
        if (length == 0) return;

        // if the first element has the requested id, remove it
        if (theList.value.getID() == id)
        {
            /*
             * remove it by setting the pointer to the start of the list
             * to the next node
             */
            theList = theList.next;
            return;
        }
        /*
         *  else:
         *  (the list is not empty and the first topic is not the one we want):
         *  traverse through the list to find the topic with ID i
         */
        BiNode<Topic> tl = theList.next; // the next topic to look at
        BiNode<Topic> tlPrev = theList;  // the previous node; when we find the
        // node to remove, we'll set the next
        // field of this node to the node
        // after tl
        while (tl != null) // stop at end of list
        {
            if (tl.value.getID() == id)
            {
              /*
               * found topic with ID i: remove it
               *
               */

                // tlPrev should point to node after tl (can be null):
                tlPrev.next = tl.next;

                return;  // done
            }
            // else: (haven't found the topic) move on to tail of list
            tlPrev = tl;
            tl = tl.next;  // tlPrev is the node before tl as required
        }
        // got through without finding i: do nothing
    }

    /**
     *  Formats the list of topics as a string.
     *
     */
    public String toString()
    {
        String topics = "";
        BiNode<Topic> node = theList;
        while (node != null)
        {
            topics += "Topic Name: "+node.value.getTitle()+
                        "\nTopic ID: "+node.value.getID()+"\n";
            node = node.next;
        }

        return topics;
    }
}
