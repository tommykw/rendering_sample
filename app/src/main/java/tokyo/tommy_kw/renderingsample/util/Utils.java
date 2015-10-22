package tokyo.tommy_kw.renderingsample.util;

import android.support.v4.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tommy on 15/10/23.
 */
public class Utils {
    public static <T> T circular(List<T> list, int i) {
        int x = i % list.size();
        return list.get(x);
    }

    public static <T> List<Pair<T,T>> pairs(List<T> vs) {
        List<Pair<T,T>> result = new ArrayList<Pair<T,T>>();

        for (int i = 0; i < vs.size()-1; i++) {
            result.add(Pair.create(vs.get(i), vs.get(i+1)));
        }
        return result;
    }
}
