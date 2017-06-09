package by.gsu.epamlab.beans;

/**
 * Created by Home on 07.06.2017.
 */
public class HalfMark extends Mark {
    private int markValue;

    public HalfMark(String mark){
        super(mark);
    }

    public HalfMark(int mark){
        super(mark);
    }

    public void setMarkValue(String markValue) {
        this.markValue = (int)(Double.parseDouble(markValue)*2);
    }

    public String getStringMark() {
        String strMark;
        if (markValue %2==0){
            strMark = markValue /2+"." + 0;
        }else strMark = markValue /2+"." + 5;
        return strMark;
    }

    @Override
    public int getMarkValue() {
        return markValue;
    }
}
