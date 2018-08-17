package lesson4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Monitor {


    private int index = 0;
    private static final List<String> list =
            new ArrayList<>(Arrays.asList("A", "B", "C"));
    int lastIndex = list.size() - 1;

    public String getListElement() {
        return list.get(index);
    }

    public void nextIndex() {
        index = (index == lastIndex) ? 0 : index + 1;
    }
}
