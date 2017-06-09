package by.gsu.epamlab.handlers;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.factories.MarksFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 07.06.2017.
 */
public class ResultHandler extends DefaultHandler{
    private String login;
    private List<Result> results = new ArrayList<>();
    private String markType;

    public ResultHandler(String markType){
        this.markType = markType;
    }
    private static enum Tags{
        STUDENT,RESULTS,TESTS,LOGIN,TEST;
    }

    private Tags tag;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        String tagName = null;
        if("tns:results".equals(qName)){
            tagName = qName.substring(4);
        }
        else{
            tagName = qName;
        }

        tag = Tags.valueOf(tagName.toUpperCase());

        if(tag == Tags.TEST){
            final int TEST_INDEX = 0, DATE_INDEX = 1, MARK_INDEX = 2;
            results.add(new Result(login, attributes.getValue(TEST_INDEX), attributes.getValue(DATE_INDEX), MarksFactory.getMarkFromFactory(markType,attributes.getValue(MARK_INDEX))));
        }

    }

    // Метод вызывается когда SAXParser считывает текст между тэгами
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (tag == Tags.LOGIN) {
            String str = new String(ch, start, length).trim();
            if(!str.isEmpty()) {
                login = str;
            }

        }
    }

    public List<Result> getResults(){
        return results;
    }
}
