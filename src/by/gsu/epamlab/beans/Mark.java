package by.gsu.epamlab.beans;

/**
 * Created by Home on 07.06.2017.
 */
public class Mark {
    private int markValue;
    public Mark(){
    }

    public Mark(String mark){
        setMarkValue(mark);
    }

    public Mark(int mark){
        this.markValue = mark;
    }

    public void setMarkValue(int markValue){
        this.markValue = markValue;
    }

    public int getMarkValue(){
        return markValue;
    }

    public void setMarkValue(String markValue){
        this.markValue = Integer.parseInt(markValue);
    }

    public String getStringMark(){ return String.valueOf(markValue);}
}
