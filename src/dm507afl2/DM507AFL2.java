package dm507afl2;

import java.io.FileInputStream;
import java.util.Arrays;

/**
 *
 * @author Eger
 */
public class DM507AFL2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] map = getFrequency();
//        System.out.println(in.readBit());
        System.out.println(Arrays.toString(map));

        Tree huffmanTree = getHuffmanTree(map);
        System.out.println(huffmanTree);

//        System.out.println(e2);
//        in.close();
    }

    private static Tree getHuffmanTree(int[] map) {
        PQ pq = new PQHeap(256);
        for (int i = 0; i < map.length; i++) {
            if(map[i]!=0) {
                pq.insert(new Element(map[i], new Leaf(i)));
            }
        }
        System.out.println(pq.getHeapSize());
        while (pq.getHeapSize()>1 ){
            Element e1 = pq.extractMin();
            Element e2 = pq.extractMin();
            System.out.println(e2);
            pq.insert(new Element(e1.key+e2.key,new Node(e1.data,e1.data)));
        }
        return pq.extractMin().data;
    }

    private static int[] getFrequency() {
        int[] map = new int[256];
        try(ByteStream in = new ByteStream(new FileInputStream("/home/krizzmp/afl 1.md"))){
            for (Integer i : in) {
                map[i]++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
