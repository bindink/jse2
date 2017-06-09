package by.gsu.epamlab.readers;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.handlers.ResultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by Home on 07.06.2017.
 */
public class ResultImplXML implements IResultDAO{
    int i;
    public List<Result> results;
    private String markType;

    public ResultImplXML(String fileName) {
        markType = "DECIMAL_MARK";
        results =  getResultsFromHandler(fileName, markType );
    }

    public ResultImplXML(String fileName, String markType) {
        this.markType = markType;
        results = getResultsFromHandler(fileName, markType );
    }

    private List<Result> getResultsFromHandler(String fileName, String markType){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        ResultHandler handler = null;
        try {
            saxParser = factory.newSAXParser();
            handler = new ResultHandler(markType);
            saxParser.parse(fileName, handler);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.err.println("File not found." + e);
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        results = ((ResultHandler) handler).getResults();
        return results;
    }

    public String getMarkType(){
        return markType;
    }

    @Override
    public Result nextResult() {
        Result res = null;
        if(i<results.size()) {
            res = results.get(i);
            i++;
        }
        return res;
    }

    @Override
    public boolean hasResult(){
        return i < results.size();
    }

    @Override
    public void closeReader() {

    }
}
