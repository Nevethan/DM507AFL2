package dm507afl2;

import java.util.Map;

public class Leaf extends Tree{
    private int i;

    public Leaf(int i) {
        this.i = i;
    }

    @Override
    public void buildMap(String pattern, Map<Integer, String> map) {
//        System.out.println(i + " : " + pattern);
        map.put(i,pattern);
    }
}
