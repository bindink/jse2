package by.gsu.epamlab.factories;

import by.gsu.epamlab.beans.DecimalMark;
import by.gsu.epamlab.beans.HalfMark;
import by.gsu.epamlab.beans.Mark;
import by.gsu.epamlab.beans.Result;

/**
 * Created by Home on 07.06.2017.
 */
public class MarksFactory {
    static enum MarkKind {
        MARK {
            Mark getMark(String mark) {
                return new Mark(mark);
            }
        },
        HALF_MARK {
            Mark getMark(String mark) {
                return new HalfMark(mark);
            }
        },
        DECIMAL_MARK {
            Mark getMark(String mark) {
                return new DecimalMark(mark);
            }
        };
        abstract Mark getMark(String mark);
    }

    public static Mark getMarkFromFactory(String markType, String mark) {
        return MarkKind.valueOf(markType).getMark(mark);
    }
}
