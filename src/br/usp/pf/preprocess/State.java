package br.usp.pf.preprocess;

/**
 *
 * @author fm
 */
public class State implements Comparable {

    /**
	 */
    public int id;
    /**
	 */
    public int incidence;
    /**
	 */
    public int energy;
    /**
	 */
    public int contacts;

    /**
	 * @return
	 */
    public int getContacts() {
        return contacts;
    }

    /**
	 * @param contacts
	 */
    public void setContacts(int contacts) {
        this.contacts = contacts;
    }

    /**
	 * @return
	 */
    public int getEnergy() {
        return energy;
    }

    /**
	 * @param energy
	 */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
	 * @return
	 */
    public int getIncidence() {
        return incidence;
    }

    /**
	 * @param incidence
	 */
    public void setIncidence(int incidence) {
        this.incidence = incidence;
    }

    /**
	 * @return
	 */
    public int getId() {
        return id;
    }

    /**
	 * @param key
	 */
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
