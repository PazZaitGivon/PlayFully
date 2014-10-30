
/**
 * Write a description of class Phase here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Phase
{

    private String name;
    private String desc;
    private String reward;
    private String title;
    private Quest [] quests;
    private int [] counters; //counting any quests that need to be repeated
    private int [] goals;
    private Event [] events;
    private boolean [] completed;
    private StatChange [] bonuses;
    private Phase next;
    
   // private Missions [] missions;
    
    /**
     * Constructor for objects of class Phase
     */
    public Phase(String name, String desc, String reward, String title, Quest [] quests, int [] times, 
    Event [] events, boolean [] completed, StatChange[] bonuses,  Phase unlockable )
    {
      this.name=name;//cute name for this tier
      this.desc=desc;//what oull be doing, may be irrelevant given name
      this.reward=reward;//go buy yourself a.... make yourself a ... relax with a...
      this.title=title;//title you receive once completeing this phase
      this.quests=quests;
      this.counters=new int [times.length]; 
        for(int i=0; i<times.length; i++)
            {this.counters[i]=0;}
      this.goals=times;
      this.events=events;
      this.completed=completed;
      this.bonuses=bonuses;
      this.next=unlockable;
    }
    //we can make default instances where there is no unlockable phase (ie grand finale)
    //where there are no events
    //where there are no quest counters
    public Phase (String name, String desc, String reward, String title, Quest[] quests, int[]times,
        StatChange [] bonuses, Phase unlockable)
    {//quest only, no events
        this(name, desc, reward, title, quests, times,null, null, bonuses, unlockable);//null to save space
    }
    
    public Phase (String name, String desc, String reward, String title,Event [] events, boolean [] completed,
      StatChange [] bonuses, Phase unlockable)
    {//events only, no quests
        this(name, desc, reward, title, null, null , events, completed, bonuses, unlockable);//null to save space
    }
    //end constructors
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //basic accessors and mutators
   public String getName()
   {return this.name;}
   
   public void setName(String name2)
   {this.name=name2;}
   
    public String getDesc()
   {return this.desc;}
   
   public void setDesc(String desc2)
   {this.desc=desc2;}
   
    public String getReward()
   {return this.reward;}
   
   public void setReward(String reword)
   {this.reward=reword;}
   
    public String getTitle()
   {return this.title;}
   
   public void setTitle(String title2)
   {this.title=title2;}
   
   public Quest [] getQuests()//add a tostring with goals and counters
   {return this.quests;}
   
   public int [] getCounters()
   {return this.counters;}
   
   public int [] getGoals()
   {return this.goals;}
   
     public void addQuest(Quest newQuest, int goal)//reusing add statChange from quest
    {
        Quest [] newQuests = new Quest [this.quests.length+1];
        int [] newGoals = new int [this.goals.length+1];
        for(int i=0; i<this.quests.length; i++)
        {
            newQuests[i]=this.quests[i];
            newGoals[i]=this.goals[i];
        }
        newQuests[this.quests.length]=newQuest;
        newGoals[this.goals.length]=goal;
        this.quests=newQuests;
        this.goals=newGoals;
    }// done!
    
       public void deleteQuest(String name)
    {
        Quest [] newQuests = new Quest [quests.length-1];
        int oldCount=0;
        int newCount=0;
        while(oldCount< quests.length)
        {
            if( quests[oldCount].getName().equals(name) )
            {
                oldCount++;
            }
            else 
            {
                if(newCount<newQuests.length)
                newQuests[newCount]=quests[oldCount];
                newCount++;
                oldCount++;
            }
        }
        if(oldCount==newCount)
       { System.out.println("sorry we didnt recognise the stat you wanted to delete, if you had it you dont anymore though :D");}
       this.quests=newQuests;
    }
    
    
    public void changeReps(String name, int newReps)
    {
        int index=0;
        boolean found=false;
        while(index< quests.length && !found)
        {
            if( quests[index].getName().equals(name) )
            {
                counters[index]=newReps;
                found=true;
            }
            index++;
        }
        if(!found)
        { System.out.println("sorry we didnt recognise the stat whose value you wanted to play with");}
    }
  
    public StatChange [] getBonuses()
   {return this.bonuses;}
   
    public void addBonus(StatChange extra)//reusing add statChange from quest
    {
        StatChange [] newChanges = new StatChange [this.bonuses.length+1];
        for(int i=0; i<this.bonuses.length; i++)
        {newChanges[i]=this.bonuses[i];}
        newChanges[newChanges.length-1]=extra;
        this.bonuses=newChanges;
    }
    
       public void deleteBonus(String name)
    {
        StatChange [] newChange = new StatChange [bonuses.length-1];
        int oldCount=0;
        int newCount=0;
        while(oldCount< bonuses.length)
        {
            if( bonuses[oldCount].getStat().getName().equals(name) )
            {
                oldCount++;
            }
            else 
            {
                if(newCount<newChange.length)
                newChange[newCount]=bonuses[oldCount];
            }
        }
        if(oldCount==newCount)
       { System.out.println("sorry we didnt recognise the stat you wanted to delete, if you had it you dont anymore though :D");}
       this.bonuses=newChange;
    }
    
    
    public void changeValue(String name, int newValue)
    {
        int index=0;
        boolean found=false;
        while(index< bonuses.length && !found)
        {
            if( bonuses[index].getStat().getName().equals(name) )
            {
                bonuses[index].setValue(newValue);
                found=true;
            }
            index++;
        }
        if(!found)
        { System.out.println("sorry we didnt recognise the stat whose value you wanted to play with");}
    }
   
    public Event [] getIncompleteEvents()
    {
        int counter=0;
        for(int i=0; i<events.length;i++)//double loop, is there a better way? arraylist?
        {
            if(!completed[i])
            {counter++;}
        }
        
        Event [] result=new Event [counter];
        int newCount=0;
        for(int i=0; i<counter;i++)
        {
            if(!completed[i])
            {result[newCount]=events[i];}
        }
        return result;
    }
    
    public Event [] getCompleteEvents()
    {
        int counter=0;
        for(int i=0; i<events.length;i++)//double loop, is there a better way? arraylist?
        {
            if(completed[i])
            {counter++;}
        }
        
        Event [] result=new Event [counter];
        int newCount=0;
        for(int i=0; i<counter;i++)
        {
            if(completed[i])
            {result[newCount]=events[i];}
        }
        return result;
    }
   
    public Event [] getEvents()
    {return this.events;}
    
    public Phase getNext()
    {
     if(this.next==null)
        {return null;}
     else
       { return this.next;}
    }
       
   public void setNext( Phase extra)
   {this.next=extra;}
   
   public Phase getFinal()
   { 
       if(this.next==null)
        {return this;}
       Phase pointer=this.next;
       while(pointer.next!=null)
       {pointer=pointer.next;}
       return this;
    }
    
    public int howManyLeft()//look! magic!
    {   if (this.next==null)
        return 1;
        else
        return 1+this.next.howManyLeft();
    }
   
    public void doQuest(Quest todo)
    {
        todo.activate();
        int index=-1;
        for (int i=0; i<quests.length; i++)
        {
            if(todo==quests[i])
            {index=i;}
        }
        if(index==-1)
        {System.out.println("error, couldnt find quest");}// have a way for quests to find which phases tehy effect
        else
        {counters[index]++;}
    }
    
    public boolean isComplete()
    {
        boolean result=true;
        int index=0;
        while( index<quests.length)
        {
            if(counters[index]!=goals[index])
                {return false;}
            index++;
        }
        
        index=0;
        while(index<events.length)
        {
            if(!completed[index])
                {return false;}
            index++;
        }
        return true;
    }
    
    public Phase complete()//error checking time, protected?
    {
        for(int i=0; i<bonuses.length; i++)
        {bonuses[i].apply();}
        System.out.println("congrats on finishing this phase! your reward is "+reward);
        return this.next;
    }
    
}
