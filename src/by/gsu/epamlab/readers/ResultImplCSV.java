package by.gsu.epamlab.readers;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.factories.MarksFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Home on 07.06.2017.
 */
public class ResultImplCSV implements IResultDAO{
    private final String DELIMETER = ";";
    private Scanner sc;
    private String markType;

    public ResultImplCSV(String fileName) {
       markType = "MARK";
    }

    public ResultImplCSV(String fileName, String markType){
        try {
            sc = new Scanner(new FileReader(fileName));
            sc.useLocale(Locale.ENGLISH);
            this.markType =  markType;
        } catch (FileNotFoundException e) {
            System.err.println("File not found." + e);
            System.exit(0);
        }
    }

    public String getMarkType(){
        return markType;
    }

    @Override
    public Result nextResult() {
        String line;
        String[] parts;
        Result result = null;
        line = sc.nextLine();
        parts = line.split(DELIMETER);
        result = new Result(parts[0], parts[1], parts[2], MarksFactory.getMarkFromFactory(markType, parts[3]));
        return result;
    }

    @Override
    public boolean hasResult() {
        return sc.hasNextLine();
    }

    @Override
    public void closeReader() {
        sc.close();
    }
}
