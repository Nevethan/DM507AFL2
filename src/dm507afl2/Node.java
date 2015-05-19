package dm507afl2;

import java.util.Map;

public class Node extends Tree {
    private final Tree data;
    private final Tree data1;

    public Node(Tree data, Tree data1) {
        this.data = data;
        this.data1 = data1;
    }

    @Override
    public void buildMap(String pattern, Map<Integer, String> map) {
//        System.out.println(pattern);
        data.buildMap(pattern+"0",map);
        data1.buildMap(pattern+"1",map);
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", data1=" + data1 +
                '}';
    }
}
