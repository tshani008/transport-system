/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transportsystem;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author Theja
 */
public class SetOfJourneys extends Vector<Journey> implements Serializable {

    /**
     *
     */
    public SetOfJourneys() {

        super();
    }

    /**
     *
     * @param j
     */
    public void addJourney(Journey j) {
        super.add(j);
    }
    
    /**
     *
     * @param j2
     */
    public void removeJournry(Journey j2) {
        super.remove(j2);
    }

}
