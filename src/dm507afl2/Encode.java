package dm507afl2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Encode {
    public static void main(String[] args) {
        String fileNameIn = args[0];
        String fileNameOut = args[1];
        writeCompressedFile(fileNameIn,fileNameOut);
    }
    private static void writeCompressedFile(String fromPath, String toPath) {
        int[] map = getFrequency(fromPath);
        System.out.println(Arrays.toString(map));
        Tree huffmanTree = Utils.getHuffmanTree(map).data;
        String p = getPatternString(getPatternMap(huffmanTree), fromPath);
        System.out.println(p);

        writeCompressionToFile(p, map, toPath);
    }
    private static int[] getFrequency(String path) {
        int[] map = new int[256];
        try(ByteStream in = new ByteStream(new FileInputStream(path))){
            for (Integer i : in) {
                map[i]++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    private static Map<Integer,String> getPatternMap(Tree huffmanTree){
        Map<Integer,String> map = new HashMap<>();
        huffmanTree.buildMap("", map);
        return map;
    }

    private static String getPatternString(Map<Integer, String> patternMap, String path) {
        String p = ""; //todo use StringBuilder
        try(ByteStream in = new ByteStream(new FileInputStream(path))){
            for (Integer i : in) {
                p+= patternMap.get(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
    private static void writeCompressionToFile(String p, int[] map, String path) {
        try(BitOutputStream out = new BitOutputStream(new FileOutputStream(path))){
            for (int i:map){
                out.writeInt(i);
            }
            for (char c : p.toCharArray()) {
                int g = (c=='0')?0:1;
                out.writeBit(g);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
