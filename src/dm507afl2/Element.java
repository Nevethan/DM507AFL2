/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm507afl2;

/**
 *
 * @author Eger
 */
public class Element {
    public int key;
    public Object data;
    public Element(int i, Object o){
        this.key = i;
        this.data = o;
    }

    @Override
    public String toString() {
        return String.valueOf(key);
    }
}
