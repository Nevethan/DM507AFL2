package dm507afl2;

public class Utils {

    public static Element getHuffmanTree(int[] map) {
        PQ pq = new PQHeap(256);
        for (int i = 0; i < map.length; i++) {
            if(map[i]!=0) {
                pq.insert(new Element(map[i], new Leaf(i)));
            }
        }
        while (pq.size()>1 ){
            Element e1 = pq.extractMin();
            Element e2 = pq.extractMin();
            pq.insert(new Element(e1.key+e2.key,new Node(e1.data,e2.data)));
        }
        return pq.extractMin();
    }

}
