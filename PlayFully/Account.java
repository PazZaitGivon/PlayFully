
/**
 * Write a description of class Account here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Account
{
    // instance variables - replace the example below with your own
    private Stat [] stats;
    private Stat lvl;
    private Stat contributor;
    private int contribution;//this is spendable contribution points! you get one each time your contribution level increases
    private Questline [] questlines;
    private Quest [] quests;
    private Event [] events; //might become obsolete
    private String [] history;//history should also include links to the quests?
    private String username;
    private String password;

    public Account(String username, String password)//default account
    {
        this.username=username;
        this.password=password;
        int lvlUpConst=100;
        String desc="This is your overall level, improve by using the capabilities of the game and unlock new functionalities!";
        //setting desc up there for readability
        //public Stat(String name, String desc, int decayRate, int decayConst, boolean staticDecay, int lvlUpConstant)
        this.lvl=new Stat("Level", desc , 0, 0, false, lvlUpConst);//no decaying for the start, they are just newbies
        desc="Thank you for helping PLAYFULLY grow! we need people like you, built by you for you";
        this.contributor=new Stat("Contribution",desc, 0,0, false, 100);//again your contributions are forever!
        this.contribution=0;
    }

}
