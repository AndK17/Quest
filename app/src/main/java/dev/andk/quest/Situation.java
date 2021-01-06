package dev.andk.quest;

public class Situation {
    Situation[] direction;
    String subject,text;
    int dF,dM,dR;
    public Situation (String subject, String text, int variants, int df,int dm,int dr) {
        this.subject=subject;
        this.text=text;
        dF=df;
        dM=dm;
        dR=dr;
        direction=new Situation[variants];
    }
}
