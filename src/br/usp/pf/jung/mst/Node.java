/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.pf.jung.mst;

/**
 *
 * @author fm
 */
public class Node implements Comparable {

    public int key;
    public int incidence;
    public int energy;
    public int contacts;

    public int getContacts() {
        return contacts;
    }

    public void setContacts(int contacts) {
        this.contacts = contacts;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getIncidence() {
        return incidence;
    }

    public void setIncidence(int incidence) {
        this.incidence = incidence;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int compareTo(Object o) throws ClassCastException {
        if (!(o instanceof Node)) {
            //throw new ClassCastException("A Node object expected.");
        }
        int c = ((Node) o).getContacts();
        return c - this.contacts;
    }
}
