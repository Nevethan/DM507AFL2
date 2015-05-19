package dm507afl2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Decode {
    public static void main(String[] args) {
        String fileNameIn = args[0];
        String fileNameOut = args[1];
        writeDecompressedFile(fileNameIn, fileNameOut);
    }
    private static void writeDecompressedFile(String fromPath, String toPath) {
        try(BitInputStream in = new BitInputStream(new FileInputStream(fromPath))){
            int[] freq = new int[256];
            for (int i = 0; i < 256; i++) {
                freq[i] = in.readInt();
            }
            Element element = Utils.getHuffmanTree(freq);
            Tree root = element.data;

            int n = element.key;
            Tree currTree = root;

            try(FileOutputStream out = new FileOutputStream(toPath)) {
                while (n>0){
                    if(currTree instanceof Leaf){
                        Leaf leaf = (Leaf) currTree;
//                        System.out.println(leaf.i);
                        out.write(leaf.i);
                        currTree = root;
                        n--;
                    }else if(currTree instanceof Node){
                        Node node = (Node) currTree;
                        int bit = in.readBit();
                        if(bit==0){
                            currTree = node.left;
                        }else if(bit==1){
                            currTree = node.right;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
