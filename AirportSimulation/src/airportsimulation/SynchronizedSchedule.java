/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airportsimulation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 * A class used to synchronize the schedule between threads
 *
 * @author mfaux02
 */
public class SynchronizedSchedule {

    LinkedList<Event> schedule = new LinkedList<>();

    public synchronized Event getFlight(int index) {
        return schedule.get(index);
    }

    public synchronized Event removeFlight(int index) {
        return schedule.remove(index);
    }

    public synchronized void addFlight(int index, Flight f) {
        schedule.add(index, f);
    }

    public synchronized int getSize() {
        return schedule.size();
    }

    public synchronized boolean isEmpty() {
        return schedule.isEmpty();
    }
}

/**
 * A class used to synchronize the gates between threads
 *
 * @author mfaux02
 */
class SynchronizedGates {

    HashMap<Gate, Plane> gates = new HashMap<>();

    public synchronized Plane get(Gate g) {
        return gates.get(g);
    }

    public synchronized Plane remove(Gate g) {
        return gates.remove(g);
    }

    public synchronized Set<Gate> keySet() {
        return gates.keySet();
    }

    public synchronized void put(Gate g, Plane p) {
        gates.put(g, p);
    }
}