import java.util.*;
/**
 * this is how you write a comment that might take several lines start with a /** 
 * then do a * at the begining of each line, many programs will write the * for you when you enter
 * 
 * Comments are useful to explain what you are trying to do to anyone who reads your code, 
 * including yourself
 * 
 *public Stat(String name, String desc, int decayRate, int decayConst, boolean staticDecay, int lvlUpConstant, Stat overStat, Stat [] miniStats)
 *public Stat(String name, String desc)
 *public String getName()//tested
 *public void setName(String newName)//tested
 *public String getDesc()//tested
 *public void setDesc(String newDesc)//tesdted
 *public int getPoints()//tested
 *public int getLvl()//tested
 *public int getTotalPointsToNextLevel()//tested
 *public int getTodayCounter()
 *public int getDayCounter()
 *public int getDecayRate()//tested
 *public void setDecayRate(int newRate)
 *public int getDaysToDecay()//tested
 *public int getDecayConst()// if 0 wont decay
 *public void setDecayConst(int newDecay)
 *public int getDecaysInARow()
 *public boolean getStaticDecay()
 *public void switchStaticDecay()
 *public int getLvlUpConst()
 *public void setLevlUpConst(int newConst)  
 *public void newDay()
 *protected void addValue(int modifier)
 *public int getTotalPoints()
 *protected void decay()     
 *public void delete() What about when we need to delete the stat? if ober and understats are implemented will be complex; else just set to null?
 */

//if you want to write quick comments that take up only one line start tehm with a "//"
public class Stat//a class is a way of organising data so you can acess it more easily, 
//in Java it is polite to name a class with a capital letter
{
    // this is my variable list, every Stat I make from now on will include all these variables
    //private means you cant change it from outside the class unless you use a public class method
    private String name;//string is a string of characters in order, a sequence of charachters if you will
    private String desc;
    private int points;//int is an integer, whole number, both positive and negative
    private int level;//might be this is wasting space, need to fuigure out how lvl ups will work
    //some sort of history, string array? but I also want links to said quests and stats?
    private int todayCounter;//how many points today?
    private int dayCounter;//how many days in a row have you done something
    private int daysToDecay;
    private int decayRate;//if 0 wont decay, also if decay const is 0 and static wont decay
    private int decayConst;// if 0 wont decay
    private int decaysInARow;// we dont need this; will fix later
    private boolean staticDecay;//true means decays in a row doesnt matter except for statistics, and decayConst doesnt change, doesnt decay more each time
    private int lvlUpConst; //(static?)
    Stat overStat; //Math is a part of Academics NOT IMPLEMENTED
    Stat [] underStats; //Calc is a part of Math NOT IMPLEMENTED!
    
    //private String [] titles;// problem, how long is array? size level? ridiculous
   // private static int statCount;//static means this will be the same in all variables of Stat type 
   //one thing I think will be useful will be to know how many of each stat is used and how many stats each person has statisticaly
   //this wont work though since this Stat class is for each person's stat. I still need to come up with where
   //to implament the counters for different stats, the how many stats per person can be done more easily in a person class
   
//this is my beautiful test function! isnt it lovely?
    public static void test()
    {
        Scanner in=new Scanner(System.in);
        System.out.println("enter the name of your new stat");
        String name=in.nextLine();
        System.out.println("please write a short description of what "+name+" encompasses");
        String desc=in.nextLine();
        Stat test1=new Stat(name,desc);
        System.out.println("you are currently level "+test1.getLvl()+" in "+test1.getName()+" which is "+test1.getDesc());//not finished
        System.out.println("you have been improving "+test1.getName()+" for "+test1.getDayCounter()+" days in a row");
        System.out.println("you have "+test1.getDaysToDecay()+"/"+test1.getDecayRate()+" days left to do something before you lose exp! ");
        System.out.println(); 
        System.out.println("rename your stat");
        test1.setName(in.nextLine());
        System.out.println("redescribe it");
        test1.setDesc(in.nextLine());
        System.out.println("you are currently level "+test1.getLvl()+" in "+test1.getName()+" which is "+test1.getDesc());//not finished
        System.out.println("you have been improving "+test1.getName()+" for "+test1.getDayCounter()+" days in a row");
        System.out.println("you have "+test1.getDaysToDecay()+"/"+test1.getDecayRate()+" days left to do something before you lose exp! ");
        System.out.println();
        System.out.println("enter '!' to  end, enter '+' to add exp, enter anything else to start a new day");
        String command=in.nextLine();
        while(!command.equals("!"))
        {
           if(command.equals("+"))
           {
               System.out.println("enter a number");
               if(in.hasNextInt())
              { test1.addValue(in.nextInt());}
              else
              {System.out.println("sorry couldnt understand you, only whole numbers are allowed");}
               in.nextLine();
               System.out.println("new exp is:"+test1.getPoints()+"/"+test1.getTotalPointsToNextLevel()+" to get to level "+(test1.getLvl()+1));
           }
           
           else
           {
               test1.newDay();
               System.out.println("you have been improving "+test1.getName()+" for "+test1.getDayCounter()+" days in a row");
               System.out.println("you have "+test1.getDaysToDecay()+"/"+test1.getDecayRate()+" days left to do something before you lose exp! ");

           }
           
           System.out.println("enter '!' to  end, enter '+' to add a number, enter anything else to make a new day");
           command=in.nextLine();
        }
        System.out.println("Thanks for playing!");
    }
    
   /**
     * This is a constructor, the default constructor, 
     * if you want to make a new stat object this is what you will be using behind the scenes
     * To make a new Stat object called newStat you need to write: 
     * newStat=new Stat("this is my name","this is my description");
     */
        
    
    
    public Stat(String name, String desc, int decayRate, int decayConst, boolean staticDecay, int lvlUpConstant, Stat overStat, Stat [] miniStats)
    {
        this.name=name; 
        this.desc=desc; 
        points=0;//when we start a new stat we dont have any points in it yet
        level=0;
        dayCounter=0;
        todayCounter=0;
        daysToDecay=decayRate;
        this.decayRate=decayRate;
        this.decayConst=decayConst;
        this.staticDecay=staticDecay;
        this.lvlUpConst=lvlUpConstant;
        this.overStat=overStat;
        this.underStats=miniStats;
    }//end constructor, its polite and helpful for debugging to know where which things start and end
    
    public Stat(String name, String desc, int decayRate, int decayConst, boolean staticDecay, int lvlUpConstant)
    {
        this.name=name; 
        this.desc=desc; 
        points=0;//when we start a new stat we dont have any points in it yet
        level=0;
        dayCounter=0;
        todayCounter=0;
        daysToDecay=decayRate;
        this.decayRate=decayRate;
        this.decayConst=decayConst;
        this.staticDecay=staticDecay;
        this.lvlUpConst=lvlUpConstant;
        this.overStat=null;//temp fix
        this.underStats=null;//temp fix
    }
    
       public Stat(String name, String desc)
    {
       this(name, desc, 0, 0, false, 100,null,null);
    }  
     
    public String getName()//tested
    {  return this.name;}
    
    public void setName(String newName)//tested
    {name=newName;}
    
    public String getDesc()//tested
    {return desc;}
    
    public void setDesc(String newDesc)//tesdted
    {desc=newDesc;}
   
    public int getPoints()//tested
    {return this.points;}
     
    public int getLvl()//tested
    {return this.level;}
    
    public int getTotalPointsToNextLevel()//tested
    {return (level+1)*lvlUpConst;}
    
    public int getTodayCounter()
    {return todayCounter;}
    
    public int getDayCounter()
    {return dayCounter;}
        
    public int getDecayRate()//tested
    {return decayRate;}
    
    public void setDecayRate(int newRate)
    {decayRate=newRate;}
    
    public int getDaysToDecay()//tested
    {return daysToDecay;}
    
    public int getDecayConst()// if 0 wont decay
    {return decayConst;}
   
    public void setDecayConst(int newDecay)
    {this.decayConst=newDecay;}
   
    public int getDecaysInARow()
    {return decaysInARow;}
   
    public boolean getStaticDecay()
    {return staticDecay;}
  
    public void switchStaticDecay()
    {this.staticDecay=!this.staticDecay;}
    
    public int getLvlUpConst()
    {return lvlUpConst;}
    
    public void setLevlUpConst(int newConst)
    {this.lvlUpConst=newConst;}
    
    /*protected void levelUp()
    {
        this.level++; 
        this.points=0; 
        System.out.println("Level up! You are now level "+level+" in "+name);
    }now unneeded due to updated add function?
    */ 
   
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
        //somethign about history, days to decay/ daycounter
    }
    
    protected void addValue(int modifier)
    {
        todayCounter=todayCounter+modifier;
        decaysInARow=0;
        if(points+modifier>=this.getTotalPointsToNextLevel())
            {   points=points+modifier-this.getTotalPointsToNextLevel();
                level++;
                System.out.println("Level up! You are now level "+level+" in "+name);
                while(points>=this.getTotalPointsToNextLevel())
                {
                    points=points-this.getTotalPointsToNextLevel();
                    level++;
                    System.out.println("Level up! You are now level "+level+" in "+name);
                }
            }
       else
       {this.points=this.points+modifier;}
       while(points+modifier<0 && level>0)//level down
       {
           points=points+lvlUpConst*level;
           level--;
    
           System.out.println("You lost one level in "+name+" you now have "+points+"/"+this.getTotalPointsToNextLevel()+" to get back to to level "+(this.getLvl()+1)+" please try not to neglect "+name+" so much in the future");

        }
    }
    
    public int getTotalPoints()
    {
        int TotalPoints=points;
        for(int tempLvl=level; tempLvl>0; tempLvl--)
        {
            points=points+(tempLvl*lvlUpConst);
        }
        return points;
    }
      
    protected void decay()
    {
        if(this.daysToDecay==1)
        {
            int lost;
            if(this.staticDecay)
            {this.points=this.points-this.decayConst;
            lost=decayConst;}
            else
            {this.points=this.points-this.decayConst*decaysInARow;
            lost=decayConst*decaysInARow;}// 1,2,3 or 2,4,6 etc 
            this.daysToDecay=this.decayRate;
            this.decaysInARow++;
            
            if(points<0 && level > 0)
            {points=points+level*lvlUpConst;
            level--;}
            
            System.out.println("You lost "+lost+" point(s) in "+name+" you now have "+points+"/"+this.getTotalPointsToNextLevel()+" to get to level "+(this.getLvl()+1)+" please try not to neglect "+name+" so much in the future");
            System.out.println("you have decayed "+decaysInARow+" times in a row!");
        }//or should decaying speed up?
        else {
        if(this.daysToDecay!=0)
        {this.daysToDecay--;}}
    }
    
}//end Stat class
