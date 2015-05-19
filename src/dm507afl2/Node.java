package dm507afl2;

import java.util.Map;

public class Node extends Tree {
    public final Tree left;
    public final Tree right;

    public Node(Tree left, Tree right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void buildMap(String pattern, Map<Integer, String> map) {
//        System.out.println(pattern);
        left.buildMap(pattern + "0", map);
        right.buildMap(pattern + "1", map);
    }

    @Override
    public String toString() {
        return "Node{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
