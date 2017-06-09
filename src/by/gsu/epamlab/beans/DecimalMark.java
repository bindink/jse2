package by.gsu.epamlab.beans;

/**
 * Created by Home on 07.06.2017.
 */
public class DecimalMark extends Mark{

    private int markValue;

    public DecimalMark(String mark){
        super(mark);
    }

    public DecimalMark(int mark){
        super(mark);
    }

    public void setMarkValue(String markValue) {
        this.markValue = (int)(Double.parseDouble(markValue)*10);
    }

    public String getStringMark() {
        return markValue /10 + "." + markValue %10;
    }

    @Override
    public int getMarkValue() {
        return markValue;
    }
}
