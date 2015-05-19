package dm507afl2;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        System.out.println(Arrays.toString(map));
        Tree huffmanTree = getHuffmanTree(map);
        String p = getPatternString(getPatternMap(huffmanTree));
        System.out.println(p);

    }

    private static String getPatternString(Map<Integer, String> patternMap) {
        String p = "";
        try(ByteStream in = new ByteStream(new FileInputStream("/home/krizzmp/afl 1.md"))){
            for (Integer i : in) {
                p+= patternMap.get(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    private static Map<Integer,String> getPatternMap(Tree huffmanTree){
        Map<Integer,String> map = new HashMap<>();
        huffmanTree.buildMap("",map);
        return map;
    }


    private static Tree getHuffmanTree(int[] map) {
        PQ pq = new PQHeap(256);
        for (int i = 0; i < map.length; i++) {
            if(map[i]!=0) {
                pq.insert(new Element(map[i], new Leaf(i)));
            }
        }
        while (pq.getHeapSize()>1 ){
            Element e1 = pq.extractMin();
            Element e2 = pq.extractMin();
            pq.insert(new Element(e1.key+e2.key,new Node(e1.data,e2.data)));
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
