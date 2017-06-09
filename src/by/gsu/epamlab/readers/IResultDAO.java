package by.gsu.epamlab.readers;

import by.gsu.epamlab.beans.Result;

/**
 * Created by Home on 07.06.2017.
 */
public interface IResultDAO {
    Result nextResult();
    boolean hasResult();
    void closeReader();
    String getMarkType();
}
