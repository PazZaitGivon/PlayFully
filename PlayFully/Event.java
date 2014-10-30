
/**
 * one time thing, possibly a project, possibly an event
 */
public class Event
{
    private String name;
    private String desc;
    private StatChange [] bonus;
    //private time and or date
    private String comment;//?

       public Event(String name, StatChange [] bonus, String comment)
    {
        this.name=name;
        this.bonus=bonus;
        this.comment=comment;
    }
    
    public Event(String name, StatChange [] bonus)
    {
        this(name,bonus,"");//or should it be null?
    }
    
    public String getName()
    {return this.name;}
    
    public void setName(String name)
    {this.name=name;}
    
    public String getDesc()
    {return this.desc;}
    
    private void setDesc(String desc2)
    {this.desc=desc2;}
    
    public String getComment()
    {return this.comment;}
    
    public void setComment(String comment)
    {this.comment=comment;}
    
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
    
   // public void complete
}
