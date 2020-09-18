

/*

 * copyright @ maxim 2020
 */

/**
 *
 *
 */

import java.io.PrintWriter;
import java.util.ArrayList;

public class BinTree <T extends Comparable<T>>
{

    public Node <T> root; // pointer to the root
    public String deletedNodeInfo; // holds toString of deleted node

    public BinTree() // no arg default constructor
    {
            this.root = null;
    }

    private Node addRecursive(Node <T> current, T value) // recursive method to add a node to a existing list. Abstracted from user.
    {
        // reuturn null if empty tree
        if (current==null)                               // take current node and value to add in the node as a paramemter
        {
            return new Node(value);                     // returns a pointer to a node
        }
        // if value less then current node go left
        if (value.compareTo(current.value)<0)
        {
            current.left=addRecursive(current.left,value);
        }
        // if value more than current node go right
        else if (value.compareTo(current.value)>0)
        {
            current.right=addRecursive(current.right,value);
        }
        else // value already exists
        {
            return current;
        }
        return current;

    }

    public void insert(T value) // insert function available to user takes the value in T type
    {
        root=addRecursive(root,value);
    }

    private String[] containsNodeRecursive(Node <T> current, T value, String s) // performs seach for a node in an existing tree and is a abstarcted to the user
    {
        String  sar []={"0","0"};                               // Takes a node, the value to search as T value and A string to store string searched string as parameter.

        //sar.add("start");
        if (current==null)  // when list is empty or end of tree is reached
        {
            sar[0]="0";
            return sar;
            //return false;
        }
        if (value.compareTo(current.value)==0) // when the node being looked for is found
        {
            //System.out.println("binTree 63");
            sar[0]="1";
            s=current.toString();  // save the toString of the found node to the string s
            System.out.println(s);
            sar[1]=s;               // save the to string value in an string arry to return to calling function
            return sar;
        }
        return (value.compareTo(current.value)<0)?       // if value is less then current value go to left node else go to right node
            containsNodeRecursive(current.left,value,s):
            containsNodeRecursive(current.right,value,s);


    }

    public String[] search(T value, String s)       // search function available to user takes the value to seach for and a string as parameter and returns a string array
    {                                               // the first index of string array tells whether the value exists and second value contains the toString of seached node
        return containsNodeRecursive(root,value,s);
    }

    private Node<T> deleteRecursive(Node <T> current, T value) // abstracted delete node function that takes a node and value of node to delete as parameters
    {
        //System.out.println("1");
        //System.out.println("now in:"+current.toString());
        if (current==null) // when tree is empty or end of tree has been reached
        {
            //System.out.println("2");
            return null;
        }
        if (value.compareTo(current.value)==0) // // when finds the node to delete  save the to string and checks number of children
        {
            //System.out.println("3");

            String ss=current.toString();
            //System.out.println("tree 100");
           // System.out.println("====================");
            //System.out.println(this.deletedNodeInfo);
            //System.out.println("====================");

            this.deletedNodeInfo=ss;
            //System.out.println("====================");
            //System.out.println(this.deletedNodeInfo);
            //System.out.println("====================");
           // System.out.println("tree 102");
            //System.out.println(this.deletedNodeInfo);
            //System.out.println("tree 104");
            if (current.left == null && current.right == null)  // if leaf node just deletes it
            {
                //System.out.println("4");
                return null;
            }
            if (current.right == null)  // if only left children return left children
            {
               // System.out.println("5");
                return current.left;
            }

            if (current.left == null)  // if only right children return right child
            {
               // System.out.println("6");
                return current.right;
            }

            // if it has two child replace the node with smallest of the right subtree and then delete it there
            //System.out.println("7");
            T smallestValue = findSmallestValue(current.right);
           // System.out.println("8");
            System.out.println(smallestValue.toString());
            current.value = smallestValue;
           // System.out.println("9");
            current.right = deleteRightChild(current.right, smallestValue);
           // System.out.println("10");
            return current;


        }
        if (value.compareTo(current.value)<0)
        {
            //System.out.println("11");
            current.left=deleteRecursive(current.left, value);
            return current;
        }
        /////////////////////////
        // doubt with this else
        ////////////////////////

            //System.out.println("12");
            current.right=deleteRecursive(current.right,value);
            return current;




    }

    private Node<T> deleteRightChild(Node <T> current, T value) // this function deletes smallest node from the right subtree
    {

        if (current==null)
        {

            return null;
        }
        if (value.compareTo(current.value)==0)
        {

            if (current.left == null && current.right == null)
            {
                return null;
            }
            if (current.right == null)
            {
                return current.left;
            }

            if (current.left == null)
            {
                return current.right;
            }

            T smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }
        if (value.compareTo(current.value)<0)
        {
            current.left=deleteRecursive(current.left, value);
            return current;
        }
            current.right=deleteRecursive(current.right,value);
            return current;

    }



    private T findSmallestValue(Node <T> root)  // function to find smallest value on right subtree
    {
        /*
        if  (root.left != null )
        {
            findSmallestValue(root.left);

        }

            return root.value;
         */
       // System.out.println("13");
        return root.left == null ? root.value : findSmallestValue(root.left);

    }



    public void delete (T value) // delete function available to user that takes the value of node as T type as aparameter
    {
        System.out.println("in delete function");
        root=deleteRecursive(root,value);
    }

    public void traverseInOrder(Node <T> node, PrintWriter scribe)  // function to traverse the tree in traverese order
    {

        if (node != null)
        {
            traverseInOrder(node.left,scribe);
            //System.out.print(" " + node.value);
            scribe.println(node.toString());

            traverseInOrder(node.right,scribe);
        }

    }

    public void inOrder(PrintWriter scribe) // function available to user to traverse tree in order
    {
        traverseInOrder(root,scribe);
    }


    private Node<T> containsAndUpdate(Node <T> current, T value) // function to update node
                                                                 // searches the node and when found updates the node value with given value
    {
        Node <T> nwNd=new Node<>();

        String  sar []={"0","0"};

        //sar.add("start");
        if (current==null)
        {
            sar[0]="0";
            //return sar;
            //return false;
            return nwNd;
        }
        if (value.compareTo(current.value)==0)
        {

            nwNd=current;
            return nwNd;
        }
        return (value.compareTo(current.value)<0)?
            containsAndUpdate(current.left,value):
            containsAndUpdate(current.right,value);


    }

    public Node<T> update(T value) // function available to user to update a node
    {
        return containsAndUpdate(root,value);
    }

    public void traverseDes(Node <T> node, PrintWriter scribe) // function to traverse tree in descending order
    {

        if (node != null)
        {
            traverseDes(node.right,scribe);
            //System.out.print(" " + node.value);
            scribe.println(node.toString());

            traverseDes(node.left,scribe);
        }

    }

    public void DescendOrder(PrintWriter scribe)  // function available to user to
    {
        traverseDes(root,scribe);
    }




}
