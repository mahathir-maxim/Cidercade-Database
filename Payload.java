


/*

 * copyright @ maxim 2020
 */

/**
 *
 * @author maxim
 */
import java.util.Objects;
public class Payload implements Comparable<Payload>
{
    // properties
    String Name;
    String HighScore;
    String Initials;
    String Plays;
    String revenue;

    public Payload(String Name, String HighScore, String Initials, String Plays, String rev) // defined constructor
    {
        this.Name = Name;
        this.HighScore = HighScore;
        this.Initials = Initials;
        this.Plays = Plays;
        this.revenue=rev;
    }

    public String getRevenue() // getter
    {
        return revenue;
    }

    public void setRevenue(String revenue) //setter
    {
        this.revenue = revenue;
    }

    public Payload(String Name) //defined constructor
    {
        this.Name = Name;
    }

    public Payload()// no arg default constructor
    {

    }

    public String getName() //getter
    {
        return Name;
    }

    public void setName(String Name) //setter
    {
        this.Name = Name;
    }

    public String getHighScore() //getter
    {
        return HighScore;
    }

    public void setHighScore(String HighScore) //setter
    {
        this.HighScore = HighScore;
    }

    public String getInitials() //getter
    {
        return Initials;
    }

    public void setInitials(String Initials)// setter
    {
        this.Initials = Initials;
    }

    public String getPlays() //getter
    {
        return Plays;
    }

    public void setPlays(String Plays) //setter
    {
        this.Plays = Plays;
    }

    @Override
    public int compareTo(Payload o) //compares two payload objects by the name and returns -1,0 or 1
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools
        //| Templates.
        String x=this.Name.toLowerCase();
        String y=o.Name.toLowerCase();
        int z;
        z=x.compareTo(y);
        return z;

    }


    ///////doubtfull//////////
    /////////////////////////
    @Override
    public boolean equals(Object obj) // checks to see if two objects are equal based on their class and name
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Payload other = (Payload) obj;
        if (!Objects.equals(this.Name, other.Name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() // returns the payload object presented in string
    {

       String s=this.Name+", "+this.HighScore+", "+this.Initials+", "+this.Plays+", "+this.revenue;
       return s;
    }

    public String nameString() // returns the name of the payload object in string
    {
        String a="Name: "+this.Name+"\n";
        return a;
    }


}
