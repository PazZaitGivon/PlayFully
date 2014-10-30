
/**
 * Write a description of class Team here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Team
{
    private String name;
    //private Account leader;//unecessary you can have a team of one to lead?
    private Team leaders;//can also be null needs to also be on other way?
    private Team [] subordinates;//again can be null
    private Account [] members;

    /**
     * Constructor for objects of class Team
     */
    public Team(String name, Account creator, Account [] members)//This is really really terrible; super recursive
    {
        this.name=name;
        //this.leaders=new Team(name+" leaders", null, creator);
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */

}
