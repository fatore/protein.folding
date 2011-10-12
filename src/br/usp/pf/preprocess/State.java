package br.usp.pf.preprocess;

/**
 *
 * @author fm
 */
public class State implements Comparable {

    public int id;
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

    public int getId() {
        return id;
    }

    public void setId(int key) {
        this.id = key;
    }

    public int compareTo(Object o) throws ClassCastException {
        if (!(o instanceof State)) {
            //throw new ClassCastException("A Node object expected.");
        }
        int c = ((State) o).getContacts();
        return c - this.contacts;
    }
}
