
public class StatChange
{

    // instance variables - replace the example below with your own
    private int myValue;
    private Stat myStat;
    /**
     * Constructor for objects of class statChange
     */
    public StatChange(Stat yourStat, int yourValue)
    {
        myValue=yourValue;
        myStat=yourStat;
    }

    public int getValue()
    {return this.myValue;}
    public void setValue(int value)
    {this.myValue=value;}
    public Stat getStat()
    {return this.myStat;}
    public void setStat(Stat yourStat)
    {this.myStat=yourStat;}
    
    public void apply()
    {myStat.addValue(myValue);}
    
    public void apply(int bonus)
    {myStat.addValue(myValue+bonus);}
    
    public String toString()
    {
        String result="";
        if(myValue>=0)
            {result=result+": +"+myValue;}
        else
            {result=result+": "+myValue;}
        return result;
    }
    
        
}//end class

