package dm507afl2;

/**
 *
 * @author Eger
 */
public class Element {
    public int key;
    public Tree data;
    public Element(int i, Tree o){
        this.key = i;
        this.data = o;
    }

    @Override
    public String toString() {
        return "Element{" +
                "key=" + key +
                ", data=" + data +
                '}';
    }
}
