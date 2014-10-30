import java.util.*;//for scanner, for test
/**
 * we need to weigh potential savings of combining events and Quests (savings in complexity
 * vs Potential savings in space (less variables in events) by sepperating them
 */
public class Quest
{
    // instance variables - replace the example below with your own
    private String name;//past tense name for toString as well?
    private String desc;//should I put in another desc?
    //private string ingredients?
    //private String comment, if I decide to make this the history instance
    //private Date dueDate;
    private StatChange [] changes;
    private boolean isEvent;//true for delete after one time and submit in history, false for recurring or still useful
    private int todayCounter;//how many times today?
    private boolean ratio;
    private int dayCounter;//how many days in a row have you done something
    private int daysToDecay;
    private int decayRate;//if 0, wont decay
    private int decayConst;
    //Quest [] included: These quests are included in this one, make sure I dont lose exp from not doing them if I did this one?
    
    
    
    public static void testQuest()
    {
        Scanner in=new Scanner(System.in);
        System.out.println("enter the name of your new quest");
        String nameQ=in.nextLine();
        System.out.println("enter a description of what "+nameQ+" entails");
        String descQ=in.nextLine();
        System.out.println("enter how many stats you want this to effect");
        int size=in.nextInt();
        
        StatChange [] changes= new StatChange [size];
        for(int i=0; i<size; i++)
        {
            in.nextLine();
            System.out.println("enter the name of your new stat");
            String nameS=in.nextLine();
            System.out.println("please write a short description of what "+nameS+" encompasses");
            String descS=in.nextLine();
            Stat tempStat=new Stat(nameS,descS);
            System.out.println("please enter the amount of exp completing "+nameQ+" would give you in "+nameS+" if it is negative write a - sign before it");
            int value=in.nextInt();
            changes[i]=new StatChange(tempStat, value);

        }
        Quest test = new Quest(nameQ, descQ, changes);
        
        System.out.println("enter '+' to do this quest, '!' to quit and anything else to start a new day");
        String command=in.nextLine();
        while(!command.equals("!"))
        {
            if (command.equals("+"))
            {
                test.activate();
                System.out.println("You have done this "+test.getTodayCounter()+" times today");
            }
            
            else
            {
                test.newDay();
                System.out.println("you have been improving "+test.getName()+" for "+test.getDayCounter()+" days in a row");
               System.out.println("you have "+test.getDaysToDecay()+"/"+test.getDecayRate()+" days left to do something before you lose exp! ");
            }
            command=in.nextLine();

        }
        
        
     //   System.out.println
        
        
    }
    

    /**
     * Constructor for objects of class Quest
     */
    public Quest(String name, String desc, int decayRate, boolean ratio, int decayConst, StatChange [] changes)
    {
        this.name=name;
        this.desc=desc;       
        this.changes=changes;  
        todayCounter=0;
        dayCounter=0;
        this.isEvent=false;
        this.decayRate=decayRate;//doesnt decay by default fixed later?
        this.ratio=ratio;
        this.decayConst=decayConst;
        daysToDecay=decayRate;
    }

    public Quest(String name, String desc, StatChange [] changes)//default decay rate of 0
    {
        this.name=name;
        this.desc=desc;       
        this.changes=changes; 
        todayCounter=0;
        dayCounter=0;
        this.isEvent=true;
        this.decayRate=0;//doesnt decay by default fixed later?
        this.ratio=true;
        this.decayConst=0;
        daysToDecay=decayRate;
    }
    
    public void makeRecurring()// includes no decay
    {this.isEvent=false;}
    //check to see if something is an event in later funcs?
    
    public String getName()
    {return this.name;}
    
    public void setName(String newName)
    {this.name=newName;}
    
    public String getdesc()
    {return this.desc;}
    
    public void setDesc(String newDesc)
    {this.desc=newDesc;}
    
    public int getDecayRate()
    {return this.decayRate;}
    
    public void setDecayRate(int newDecay)
    {this.decayRate=newDecay;}
    
    public int getDaysToDecay()
    {return this.daysToDecay;}
    
    public int getDayCounter()
    {return this.dayCounter;}
    
    public int getTodayCounter()
    {return this.todayCounter;}
    
    public void switchRatio()
    {this.ratio=!this.ratio;}
    
    public boolean getRatio()
    {return this.ratio;}
    
    public int getDecayConst()
    {return this.decayConst;}
    
    public void setDecayConst(int newDecayConst)
    {this.decayConst=newDecayConst;}
    
    //lets fix things easier!
    
    
    public void deleteChange(String name)
    {
        StatChange [] newChange = new StatChange [changes.length-1];
        int oldCount=0;
        int newCount=0;
        while(oldCount< changes.length)
        {
            if( changes[oldCount].getStat().getName().equals(name) )
            {
                oldCount++;
            }
            else 
            {
                if(newCount<newChange.length)
                newChange[newCount]=changes[oldCount];
            }
        }
        if(oldCount==newCount)
       { System.out.println("sorry we didnt recognise the stat you wanted to delete, if you had it you dont anymore though :D");}
       this.changes=newChange;
    }
    
    public void changeValue(String name, int newValue)
    {
        int index=0;
        boolean found=false;
        while(index< changes.length && !found)
        {
            if( changes[index].getStat().getName().equals(name) && !found )
            {
                changes[index].setValue(newValue);
                System.out.println("new value ="+changes[index].getValue());
                found=true;
            }
            index++;
        }
        if(!found)
        { System.out.println("sorry we didnt recognise the stat whose value you wanted to play with");}
       
    }
    
    public void addStatChange(StatChange extra)
    {
        StatChange [] newChanges = new StatChange [this.changes.length+1];
        for(int i=0; i<this.changes.length; i++)
        {newChanges[i]=this.changes[i];        }
        newChanges[newChanges.length-1]=extra;
        this.changes=newChanges;
    }
    
    public Stat [] getStats()
    {
        Stat [] result=new Stat [changes.length];
        for(int i=0; i<changes.length; i++)
        {
            result[i]=changes[i].getStat();
        }
        return result;
    }
    
    public void activate()
    {
         for(int i=0; i<changes.length; i++)
        {
            changes[i].apply();//add changes to value
        }
        todayCounter++;
    }
    
    public void activate(String comment)
    {//something extra with history?
        this.activate();
    }
    
    public void activate( String comment, int [] bonus)//we need an account to do history, if history will be sortable we should make history string plus array of stats?
    {
        //assume bonus is same lenghth array as changes array
        //history thing with comment?
        for(int i=0; i<changes.length; i++)
        {
            changes[i].apply(bonus[i]);
        }
        todayCounter++;
    }
    
    public void newDay()
    {
         if(todayCounter<=0)
        {
            System.out.println("yesterdayCounter : "+todayCounter);
            decay();
            dayCounter=0;
        }
        else
        {
            daysToDecay=decayRate;
            dayCounter++;
        }
        
        todayCounter=0;
       // for(int i=0; i< changes.length; i++)
        //{changes[i].getStat.newDay();}// when account be sure not to double count, should we be rid of this?
    }
    
    public void decay()
    {
        if(this.ratio)
        {
            for(int i=0; i<changes.length; i++)
            {changes[i].getStat().addValue(changes[i].getValue()*(-1)*this.decayConst);}// fix the output of please try not to neglect so much in future?
        }
        else
        {
            for(int i=0; i<changes.length; i++)
            {changes[i].getStat().addValue(this.decayConst*(-1));}
        }
    }
   /* String toString(String comment, int [] changes)

    */
    
}//end class Quest
