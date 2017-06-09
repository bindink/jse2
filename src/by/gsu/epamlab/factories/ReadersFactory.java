package by.gsu.epamlab.factories;

import by.gsu.epamlab.readers.IResultDAO;
import by.gsu.epamlab.readers.ResultImplCSV;
import by.gsu.epamlab.readers.ResultImplXML;

/**
 * Created by Home on 07.06.2017.
 */
public class ReadersFactory {
    static enum ReaderKinds{
        XML{
            IResultDAO getReader(String fileName, String markType){
                return new ResultImplXML(fileName, markType);
            }
        },
        CSV{
            IResultDAO getReader(String fileName, String markType) {
                return new ResultImplCSV(fileName, markType);
            }
        };
        abstract IResultDAO getReader(String fileName, String markType);

    }

    public static IResultDAO getReaderFromFactory(String fileName, String markType){
        String [] fileNameArray = fileName.split("\\.");

        String extension = fileNameArray[fileNameArray.length - 1];
        return ReaderKinds.valueOf(extension.toUpperCase()).getReader(fileName, markType);
    }
}
