
/*

 * copyright @ maxim 2020
 */

import java.util.Objects;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


/**
 *
 * @author maxim
 */
public class Main
{
    private static final DecimalFormat  DF2 = new DecimalFormat("#0.00"); // it formats the output of double number to two decimal places
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner input= new Scanner(System.in); // create scanner
        System.out.println("Enter database file name: "); // prompt for file name
        String DatafileName= input.nextLine(); // save file name
        File dataFile=new File(DatafileName); // pass filename and open file to read
        Scanner inData=new Scanner(dataFile); // declare scanner

        System.out.println("Enter batch file name: ");
        String BatchFileName=input.nextLine();
        File batchFile=new File(BatchFileName);
        Scanner inBatch=new Scanner(batchFile);

        //FileWriter outfile= new FileWriter("pilot_areas.txt");
        PrintWriter write= new PrintWriter("cidercade.dat"); // open file to write
        PrintWriter print=new PrintWriter("cidercade.log"); // open file to write

        BinTree <Payload> tree= new BinTree<>(); // create a binary search tree object
        //////////
        // change later
        /////////
        while(inData.hasNextLine()) // read from the database file till the end of the file
        {
            //System.out.println("start");
            //System.out.println("the database file is empty\n");
            String temp1=inData.nextLine();
            String[] arr1= temp1.split(",");
            /*
            for (int i=0; i<arr.length; i++)
            {
                System.out.println(arr[i]);

            }
            System.out.println("=========================");*/
            //String sz1=arr1[0];

            String Name1=arr1[0].strip(); // get rid of whitespaces in front and back

            String score1= arr1[1].strip();
            int scr=Integer.parseInt(score1);
            score1=Integer.toString(scr);

            String initial1= arr1[2].strip();


            String plays1= arr1[3].strip();
            int plys=Integer.parseInt(plays1);
            plays1=Integer.toString(plys);

            String revenue1= arr1[4].strip();
            int sizeRev=revenue1.length();
            if (revenue1.charAt(sizeRev-1)==10)
            {
                revenue1=revenue1.substring(0, sizeRev-1);
            }
            revenue1=removeZero(revenue1);
            //int sizeRev=revenue1.length();
            //revenue1=revenue1.substring(0, sizeRev-1);

            Payload payld1= new Payload(Name1,score1,initial1,plays1,revenue1); // create payload object with the values
            Node <Payload> node1= new Node<>(payld1); // create node

            tree.insert(payld1); // insert the node into tree
            //System.out.println("end");
        }

        PrintWriter lekh= new PrintWriter("fromDatabase.txt");
        tree.inOrder(lekh);
        lekh.println();
        lekh.close();
        while(inBatch.hasNext()) // read from batch file and perform operations according to commands
        {

            String temp=inBatch.nextLine();
            String[] arr= temp.split("\\s+"); // split by white spaces
            /*
            for (int i=0; i<arr.length; i++)
            {
                System.out.println(arr[i]);

            }
            System.out.println("=========================");*/
            String sz=arr[0];
            if (sz.equals("1")) // when command is to add node
            {
                int first=temp.indexOf("\"");
                String temp2=temp.substring(first+1);
                String tempi=temp.substring(first+1);
                int second=temp2.indexOf("\"");
                temp2=temp2.substring(0,second);
                String temp3= tempi.substring(second+1);
                //System.out.println("added line: "+temp3 );
                temp3=temp3.strip();
                String arr2[]= temp3.split("\\s+");



                //System.out.println("here 50");
                //String tempName= arr[1];
                //int size=tempName.length();
                temp2=temp2.strip();
                String Name=temp2;

                String score= arr2[0];
                int s1=Integer.parseInt(score);
                score=Integer.toString(s1);

                String initial= arr2[1];


                String plays= arr2[2];
                int p1=Integer.parseInt(plays);
                plays=Integer.toString(p1);

                String revenue= arr2[3];
                revenue=removeZero(revenue);

                 ////
                 /*for (int i=0; i<arr2.length; i++)
                 {
                     System.out.println(i+": "+arr2[i]);
                 }*/

                Payload payld= new Payload(Name,score,initial,plays,revenue);
                Node <Payload> node= new Node<>(payld);

                tree.insert(payld);

                print.println("RECORD ADDED");
                print.println("Name: "+Name);
                print.println("High Score: "+score);
                print.println("Initials: "+initial);
                print.println("Plays: "+plays);
                print.println("Revenue: "+revenue);
                print.println();
            }

            else if (sz.equals("2")) // when command is to search node
            {
                //System.out.println("here 88");
                System.out.println("SEARCH START");
                String term=arr[1];
                for (int i=2; i<arr.length; i++)
                {
                    term +=" "+arr[i];
                }
                System.out.println("search term:"+term);
                Payload Pname=new Payload(term);
                String str=null;
                String[] Alist=tree.search(Pname, str);
                //System.out.println(Alist);
                String a=Alist[0];
                str=Alist[1];
                str=str.strip();
                System.out.println(str);
                if (a.equals("0"))
                {

                    print.println(term+" NOT FOUND");
                    print.println();
                }
                else if (a.equals("1"))
                {
                    print.println(term+" FOUND");
                    String[] sArr=parseSearch(str);
                    for (int i=0; i<sArr.length;i++)
                    {
                        System.out.println(i+": "+sArr[i]);
                    }
                    print.println("High Score: "+sArr[0]);
                    print.println("Initials: "+sArr[1]);
                    print.println("Plays: "+sArr[2]);
                    print.println("Revenue: "+sArr[3]);
                    print.println();

                }
                System.out.println("SEARCH END");
            }
            else  if (sz.equals("3")) // when command is to update a node
            {
               // System.out.println("UPDATE");

                //System.out.println(temp);

                int ind1=temp.indexOf("\"");
                String tempn=temp.substring(ind1+1);
                //System.out.println(tempn);

                String nwStr=temp.substring(ind1+1);

                int ind2= tempn.indexOf("\"");
                //System.out.println(ind2);
                tempn=tempn.substring(0,ind2);
                //System.out.println(tempn);

                int x1=ind2+1;
                nwStr=nwStr.substring(x1);
                nwStr=nwStr.strip();
                //System.out.println(nwStr);

                String ary[]=nwStr.split("\\s+");

                String fldNo=ary[0];
                //System.out.println(fldNo);

                String nwVal=ary[1];
                //System.out.println(nwVal);
                String field=null;

                Payload upName=new Payload(tempn);
                Node <Payload> nd;
                nd=tree.update(upName);
                //System.out.println("score: "+nd.value.HighScore);
                //System.out.println("initials: "+nd.value.Initials);
                //System.out.println("plays: "+nd.value.Plays);
               // System.out.println("revenue: "+nd.value.revenue);
                if (fldNo.equals("1"))
                {

                    field="high score";
                    int try1=Integer.parseInt(nwVal);
                    nwVal=Integer.toString(try1);
                    nd.value.HighScore=nwVal;
                }
                else if(fldNo.equals("2"))
                {

                    field="initials";
                    nd.value.Initials=nwVal;
                }
                else if(fldNo.equals("3"))
                {
                    int try2=Integer.parseInt(nwVal);
                    nwVal=Integer.toString(try2);
                    //System.out.println("plays: "+nd.value.Initials);
                    field="plays";
                    nd.value.Plays=nwVal;
                    int d=Integer.parseInt(nwVal);
                    double dd=d*.25;

                    nd.value.revenue="$"+DF2.format(dd);
                }

                print.println(tempn+" UPDATED");
                print.println("UPDATE TO "+field+" - VALUE "+nwVal);
                print.println("High Score: "+nd.value.HighScore);
                print.println("Initials: "+nd.value.Initials);
                print.println("Plays: "+nd.value.Plays);
                print.println("Revenue: "+nd.value.revenue);
                print.println();

                //System.out.println("UPDATE DONE");
            }

            else if (sz.equals("4")) // when command is to delete a node in a tree

            {
                String dTerm=arr[1];
                for (int i=2; i<arr.length; i++)
                {
                    dTerm +=" "+arr[i];
                }
                System.out.println(dTerm);
                Payload dltNm= new Payload(dTerm);
                tree.delete(dltNm);
                String info=tree.deletedNodeInfo;
                System.out.println("********************");
                System.out.println(tree.deletedNodeInfo);
                System.out.println("********************");
                if (info.isEmpty())
                {
                    System.out.println("info is empty!");
                }
                else
                {
                    String infoArr[]=info.split("\\s*,\\s*");
                    print.println("RECORD DELETED");
                    print.println("Name: "+infoArr[0]);
                    print.println("High Score: "+infoArr[1]);
                    print.println("Initials: "+infoArr[2]);
                    print.println("Plays: "+infoArr[3]);
                    print.println("Revenue: "+infoArr[4]);
                    print.println();
                }

                //String infoArr[]=info.split("\\s*,\\s*");
                /*
                print.println("RECORD DELETED");
                print.println("Name: "+infoArr[0]);
                print.println("High Score: "+infoArr[1]);
                print.println("Initials: "+infoArr[2]);
                print.println("Plays: "+infoArr[3]);
                print.println("Revenue: "+infoArr[4]);
                print.println();*/
            }

            else if(sz.equals("5")) // when command is to sort the nodes in a ascending or descending order and print them out
            {
                System.out.println("here 123");
                String m=arr[1];
                if (m.equals("asc"))
                {
                    System.out.println("here 125");
                    print.println("RECORDS SORTED ASCENDING");
                    tree.inOrder(print);
                    print.println();
                }
                else
                {
                    print.println("RECORDS SORTED DESCENDING");
                    tree.DescendOrder(print);
                    print.println();
                }
            }


        }

        traverseLevelOrder(tree, write);  // print the tree by traversing in level order

        inData.close();
        inBatch.close();
        write.close();
        print.close();

    }

    public static String[] parseSearch(String s) // this function takes a string and returns an array split by comma and whitespace
    {
        s=s.strip();
        int x=s.indexOf(',');
        String temp= s.substring(x+2);
        String[] arr=temp.split("\\s*,\\s*");
        return arr;

    }

    public static void printTree(BinTree<Payload>tree, PrintWriter print)
    {

    }

    public static void traverseLevelOrder(BinTree<Payload>tree, PrintWriter write)  // this function traverses the tree in level order
    {
        if (tree.root == null)
        {
            return;
        }

        Queue<Node<Payload>> nodes = new LinkedList<>();
        nodes.add(tree.root);

        while (!nodes.isEmpty())
        {

            Node<Payload> node = nodes.remove();
            String s=node.toString();
            write.println(s);
            System.out.println(s);

            if (node.left != null)
            {
            nodes.add(node.left);
            }

            if (node.right!= null)
            {
                nodes.add(node.right);
            }
        }
    }


    public static int DatabaseName(String s) // unnecessary function
    {
        int temp= s.indexOf("\"");
        return temp;
    }



    public static String removeZero(String s) // this functions removes front zeros from a string
    {
        String temp=s.substring(1);
        double d=Double.parseDouble(temp);
        temp=DF2.format(d);
        //temp=Double.toString(d);
        //temp=DF2.format(temp);
        temp="$"+temp;
        return temp;
    }
}
