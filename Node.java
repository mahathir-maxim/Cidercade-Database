/*

 * copyright @ maxim 2020
 */


/**
 *
 * @author maxim
 */
public class Node<T extends Comparable<T>> implements Comparable<Node<T>>  // extends comparable and implements comparable
{

    public T value;
    /*********************/
    public Node<T> right;
    public Node<T> left;


    @Override
    public int compareTo(Node<T> o) //overrides the compareTo class that compares between two obejcts
    {
         int val= value.compareTo(o.value);
         return val;

    }
    /*********************/
    public Node() //default constructor
    {

    }
    public Node (T object) // constuctor
    {
        value=object;
    }

    public void setData(T object) //setter
    {
        this.value=object;
    }

    public void setRight(Node<T> object) //setter
    {
        this.right=object;
    }


    public void setLeft(Node<T> object) //setter
    {
        this.left=object;
    }

    public T getData () //getter
    {
        return value;
    }

    public Node<T> getRight () //getter
    {
        return right;
    }

    public Node<T> getLeft () //getter
    {
        return left;
    }

    @Override
    public String toString()  //This overrides the toString method which transfers the contents of the node class into a string
    {
        String str= value.toString();
        return str;
    }



}
