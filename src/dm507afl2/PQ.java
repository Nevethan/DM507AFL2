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
public interface PQ {
    Element extractMin();
    void insert(Element e);
    int size();
}
