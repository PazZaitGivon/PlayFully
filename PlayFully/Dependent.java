
/**
 * Write a description of class Dependent here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dependent
{
    // instance variables - replace the example below with your own
    private String name;
    private String title;
    private Account [] providers;
    private Stat [] stats;
    private int [] values;
    private String [] history;
    /**
     * Constructor for objects of class Dependent
     */
    public Dependent(String name, Account [] providers, Stat [] stats, int [] values)
    {
     this.name=name;
     title="";
     this.providers=providers;
     this.stats=stats;
     this.values=values;
     this.history=new String[0];
     
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
  
}
