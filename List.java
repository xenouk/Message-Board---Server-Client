/*
 * /home/grant/Teaching/COMP213/Assignments/MBs/Solution/List.java
 *
 * Created: Sun Oct 13 22:53:30 2013
 *
 * copyright Grant Malcolm
 *
 *   This source code may be freely used, modified, or distributed
 *   provided due credit is given.
 *
 */

/**
 *  Basic linked lists, with a method to add an element at the start.
 *
 *
 * @author <a href="mailto:grant@liverpool.ac.uk">Grant Malcolm</a>
 * @version 1.0
 *
 */
public class List<E>
{
    /**
     *  The list of elements.
     *  Set to <code>null</code> if the list is empty.
     *  </p><p>
     *  Scope is protected to allow functionality to be added;
     *  see {@link List.BiNode class BiNode}.</p>
     *
     */
    protected BiNode<E> theList;

    /**
     *  Creates an empty <code>List</code>.
     *
     */
    public List()
    {
    }

    /**
     *  Add an element to the start of the list.
     *
     *  @param elt the element to add to the list
     *
     */
    public void add(E elt)
    {
        theList = new BiNode<E>(elt, theList);
    }

    /**
     *  Nodes in the linked list.
     *  Each node stores an {@link #value element},
     *  and has pointers to the {@link #prev preceding}
     *  and {@link #next succeding} nodes in the list.
     *  </p><p>
     *  Paedogogic note: no very good reason for using doubly-linked lists,
     *  bit wasteful of memory with the unnecessary prev field;
     *  this inner class has protected scope so that extra functionality
     *  can be added by subclassing the List class,
     *  as we do in class {@link SortedTopicList SortedTopicList}.</p>
     *
     */
    protected static class BiNode<F>
    {
        /**
         *  The element in the list.
         *
         */
        protected final F value;

        /**
         *  The next node in the linked list.
         *  Set to null to indicate that the current node is last in the list.
         *
         */
        protected BiNode<F> next;

        /**
         *  The previous node in the linked list.
         *  Set to null to indicate that the current node is first in the list.
         *
         */
        protected BiNode<F> prev;

        /**
         *  Construct a node with given element and successor node.
         *
         *  @param elt the element to be put at the start of the list
         *  @param tail the succeding node
         *
         */
        BiNode(F elt, BiNode<F> tail)
        {
            value = elt;
            next = tail;

            // the succeding node should point back to this node
            if (tail != null)
            {
                tail.prev = this;
            }
        }
    }
}
