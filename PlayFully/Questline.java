
/**
 * Write a description of class Questline here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Questline
{
    // instance variables - replace the example below with your own
  //  private Phase [] steps; not necessary, we have
  //want too see percent complete?
    private String name;
    private String desc;
    private Phase pointer;
    private int totalPhases;
    private String reward;
    private StatChange [] bonus;

    /**
     * Constructor for objects of class Questline
     */
    public Questline(String name, String desc, String reward, Phase first)
    {
        this.name=name;
        this.desc=desc;
        this.reward=reward;
        this.pointer=first;
      
    }
    
    public void addNextPhase(Phase extra)//can handle interjecting several phases
    {
        if(extra.getNext()==null)
        {
            extra.getFinal().setNext(this.pointer.getNext());
            this.pointer.setNext(extra);
            this.totalPhases=totalPhases+extra.howManyLeft();
        }
    }
    
    public void addFinalPhase(Phase extra)
    {        this.pointer.getFinal().setNext(extra);    }
    
    public void skipPhase()
    {
        if(pointer.getNext()!=null)
        {
        System.out.println ("you skipped this phase, warning next phase might assume you did this phase anyway");
        pointer=pointer.getNext();
        }
        else
        {System.out.println("you finished this questline! congrats!");}//appply rewards?
    }
    
    public String getName()
    {return this.name;}
    
    public void setName(String name)
    {this.name=name;}
    
    public String getDesc()
    {return desc;}
    
    public void setDesc(String desc)
    {this.desc=desc;}
    
    public String getReward()
    {return reward;}
    
    public void setReward(String reward)
    {this.reward=reward;}
    
    public int getTotalPhases()
    {return this.totalPhases;}
    
    public int getPercentDone()
        {return (100*pointer.howManyLeft())/totalPhases;}
    
        public StatChange [] getBonus()
    {return this.bonus;}
    
     public void addBonus(Stat type, int value)//reusing add statChange from quest
    {
        StatChange extra=new StatChange(type, value);
        StatChange [] newBonus = new StatChange [this.bonus.length+1];
        for(int i=0; i<this.bonus.length; i++)
        {
            newBonus[i]=this.bonus[i];
        }
        newBonus[this.bonus.length]=extra;
    }// done!
    
       public void deleteBonus(String name)
    {
       StatChange [] newBonus = new StatChange [bonus.length-1];
        int oldCount=0;
        int newCount=0;
        while(oldCount< bonus.length)
        {
            if( bonus[oldCount].getStat().getName().equals(name) )
            {
                oldCount++;
            }
            else 
            {
                if(newCount<newBonus.length)
                newBonus[newCount]=bonus[oldCount];
                newCount++;
                oldCount++;
            }
        }
        if(oldCount==newCount)
       { System.out.println("sorry we didnt recognise the stat you wanted to delete, if you had it you dont anymore though :D");}
       this.bonus=newBonus;
    }
    
    
    public void changeValue(String name, int newValue)
    {
        int index=0;
        boolean found=false;
        while(index< bonus.length && !found)
        {
            if( bonus[index].getStat().getName().equals(name))//is checking so much chaper than 
            {
                bonus[index].setValue(newValue);
                found=true;
            }
        }
        if(!found)
        { System.out.println("sorry we didnt recognise the stat whose value you wanted to play with");}
    }
     

}
