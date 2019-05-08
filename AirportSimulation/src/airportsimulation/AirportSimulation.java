/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airportsimulation;

import static java.lang.Thread.sleep;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mfaux02
 */
public class AirportSimulation {

    static final int TOTAL_PLANES_TO_LAND = 25; //Planes to schedule for landing
    static final int TIME_PASSAGE_AMOUNT = 250; //Amount of time equal to 1 minute | 1000 = 1 second
    static Time syncedTime;                           //timekeeper

    static boolean run = true;                  //true while keep running
    static boolean schedulerRunning = false;    //true while scheduler is running
    static SynchronizedSchedule schedule = new SynchronizedSchedule();
    static SynchronizedGates gates = new SynchronizedGates();
    static LinkedList<Event> eventList = new LinkedList<>();//NOT IMPLEMENTED YET
    static LinkedList<Plane> planesForLanding = new LinkedList<>();//planes waiting to land
    static Random rng = new Random();//Random Number Generator

    static int planesLanded = 0;
    static int planesTakenOff = 0;
    static int passengersArrived = 0;
    static int passengersDeparted = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        gates.put(Gate.Runway, null);

        syncedTime = new Time();
        Runnable timer = new Timer();
        Thread timerThread = new Thread(timer);

        timerThread.start();
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        run = false;

        printSchedule();

        printOut(getStatus());
    }

    /**
     * Retrieves the status of current planes and passenger throughput
     *
     * @return plane and passenger throughput status
     */
    public static String getStatus() {
        String status = "";
        status += "Planes landed       : " + planesLanded;
        status += "Planes takenOff     : " + planesTakenOff;
        status += "Passengers Arrived  : " + passengersArrived;
        status += "Passengers Departed : " + passengersDeparted;

        return status;
    }

    /**
     *
     * @return the synchronized current Time
     */
    public synchronized static Time getTime() {
        return syncedTime;
    }

    /**
     * passes 1 minute of Time
     */
    public synchronized static void doTick() {
        syncedTime.doTick();
    }

    /**
     * passes (amount) of Time in minutes
     *
     * @param amount the number of minutes to pass
     */
    public synchronized static void doTick(int amount) {
        syncedTime.doTick(amount);
    }

    /**
     * receives the synchronized flight at the index from the schedule
     *
     * @param index
     * @return the flight at the index
     */
    public static Event getFlight(int index) {
        return schedule.getFlight(index);
    }

    /**
     * removes and receives the synchronized flight at the index from the
     * schedule
     *
     * @param index
     * @return the flight at the index
     */
    public static Event removeFlight(int index) {
        return schedule.removeFlight(index);
    }

    /**
     * adds flight f to the synchronized schedule at the index
     *
     * @param index
     * @param f the flight to add
     */
    public static void addFlight(int index, Flight f) {
        schedule.addFlight(index, f);
    }

    /**
     * receives the size of the synchronized schedule
     *
     * @return the size of the schedule
     */
    public static int getSize() {
        return schedule.getSize();
    }

    /**
     *
     * @return whether or not the synchronized schedule is empty
     */
    public static boolean isEmpty() {
        return schedule.isEmpty();
    }

    /**
     * sends the schedule to printOut();
     */
    public static void printSchedule() {
        for (int i = 0; i < getSize(); i++) {
            printOut(getFlight(i).toString());
        }
    }

    /**
     * sends String text to an output
     *
     * @param text the text to print
     */
    public static void printOut(String text) {
        System.out.println("[" + getTime() + "]" + text);
    }

    /**
     * gets the index where the Time would be located
     *
     * @param t the time to locate an index for
     * @return the index for Time t
     */
    public static int getIndex(Time t) {
        Time time = new Time(t.toString());

        if (isEmpty()) {
            return 0;
        }

        for (int i = 0; i < getSize(); i++) {

            if (time.compareTo(getFlight(i).time) < 0) {
                return i;
            } else if (time.compareTo(getFlight(i).time) > 0) {
                if (getSize() <= i) {
                    return getSize();
                }
                if (time.compareTo(getFlight(i).time) < 0) {
                    return i + 1;
                }
            }
        }

        return getSize();
    }

    /**
     * finds a free time on the schedule for a new flight
     *
     * @param t the Time to start at
     * @return the Time that is available
     */
    public static Time getFreeTime(Time t) {

        Time time = new Time(t.toString());

        int min = time.getMinute() % 4;
        if (min != 0) {
            time.doTick(4 - min);
        }

        if (isEmpty()) {
            return time;
        }

        int difference;

        int i = 0;
        difference = time.compareTo(getFlight(i).time);

        if (difference < 0) {
            return time;
        }

        for (i = 0; i < getSize(); i++) {
            difference = time.compareTo(getFlight(i).time);

            if (difference > 0) {
                if (getSize() <= i + 1) {
                    return time;
                }

                difference = time.compareTo(getFlight(i + 1).time);
                if (difference < 0) {
                    return time;
                }
            } else if (difference == 0) {
                time.doTick(4);
                i--;
            }
        }

        difference = time.compareTo(getFlight(getSize() - 1).time);
        if (difference > 0) {
            return time;
        } else if (difference == 0) {
            time.doTick(4);
        }
        return time;
    }
}

/**
 * A class to run when an Event occurs
 *
 * @author mfaux02
 */
class Action implements Runnable {

    Event e;
    ActionID id;

    Action(Event e) {
        this.e = e;
        this.id = e.acID;
    }

    @Override
    public void run() {
        switch (id) {
            case Land:
                land();
                break;
            case TaxiIn:
                //taxiIn();
                //NOT FULLY IMPLEMENTED YET
                break;
            case Disembark:
                //disembark();
                //NOT FULLY IMPLEMENTED YET
                break;
            case Board:
                board();
                break;
            case TaxiOut:
                //taxiOut();
                //NOT FULLY IMPLEMENTED YET
                break;
            case TakeOff:
                //takeOff();
                //NOT FULLY IMPLEMENTED YET
                break;
            case Maintence:
                doMaintence();
                //NOT FULLY IMPLEMENTED YET
                break;
        }
    }

    private void land() {
        Plane plane = null;

        for (int i = 0; i < AirportSimulation.planesForLanding.size(); i++) {
            if (e.planeID.equals(AirportSimulation.planesForLanding.get(i).id)) {
                plane = AirportSimulation.planesForLanding.remove(i);
                break;
            }
        }

        boolean run = true;

        if (plane == null) {
            try {
                throw new Exception();
            } catch (Exception ex) {
                Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        while (run) {
            if (AirportSimulation.gates.get(Gate.Runway) == null) {
                AirportSimulation.gates.put(Gate.Runway, plane);

                try {
                    sleep((int) (1.5 * AirportSimulation.TIME_PASSAGE_AMOUNT + AirportSimulation.rng.nextInt((int) (2.5 * AirportSimulation.TIME_PASSAGE_AMOUNT))));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
                }

                AirportSimulation.printOut("[RUNWAY]:Plane " + plane.id + " for Flight " + ((Flight) e).flightID + " has landed on the runway!");
                AirportSimulation.planesLanded++;

                AirportSimulation.gates.put(Gate.Runway, null);

                taxiIn(plane);
                run = false;
            }
            try {
                sleep(AirportSimulation.TIME_PASSAGE_AMOUNT);
            } catch (InterruptedException ex) {
                Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void taxiIn(Plane plane) {

        try {
            sleep((int) (5.5 * AirportSimulation.TIME_PASSAGE_AMOUNT + AirportSimulation.rng.nextInt((int) (1.5 * AirportSimulation.TIME_PASSAGE_AMOUNT))));
        } catch (InterruptedException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }

        AirportSimulation.gates.put(((Flight) e).gate, plane);
        AirportSimulation.printOut("[TAXIWAY]:Plane " + plane.id + " for Flight " + ((Flight) e).flightID + " has arrived at Gate " + ((Flight) e).gate);

        disembark(plane);
    }

    private void disembark(Plane plane) {
        try {
            sleep(30 * AirportSimulation.TIME_PASSAGE_AMOUNT + AirportSimulation.rng.nextInt(15) * AirportSimulation.TIME_PASSAGE_AMOUNT);
        } catch (InterruptedException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }

        AirportSimulation.passengersArrived += plane.passengers;
        plane.passengers = 0;
        AirportSimulation.gates.put(((Flight) e).gate, plane);
        AirportSimulation.printOut("[Gate - " + ((Flight) e).gate + "]:Plane " + plane.id + " for Flight " + ((Flight) e).flightID + " has fully Disembarked");

    }

    private void board() {
        Plane plane = null;

        if (e.planeID.equals(AirportSimulation.gates.get(e.gate).id)) {
            plane = AirportSimulation.gates.get(e.gate);

            AirportSimulation.printOut("[Gate - " + ((Flight) e).gate + "]:Plane " + plane.id + " for Flight " + ((Flight) e).flightID + " has begun Boarding");
            try {
                sleep(25 * AirportSimulation.TIME_PASSAGE_AMOUNT + AirportSimulation.rng.nextInt(10) * AirportSimulation.TIME_PASSAGE_AMOUNT);
            } catch (InterruptedException ex) {
                Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
            }

            plane.passengers = AirportSimulation.rng.nextInt(plane.type.seats / 10) + plane.type.seats / 10 * 9;

            AirportSimulation.printOut("[Gate - " + ((Flight) e).gate + "]:Plane " + plane.id + " for Flight " + ((Flight) e).flightID + " has finished Boarding with " + plane.passengers + " Passengers");

            AirportSimulation.gates.remove(e.gate);

            taxiOut(plane);

        }

    }

    private void taxiOut(Plane plane) {
        try {
            sleep((int) (11.5 * AirportSimulation.TIME_PASSAGE_AMOUNT + AirportSimulation.rng.nextInt((int) (2.5 * AirportSimulation.TIME_PASSAGE_AMOUNT))));
        } catch (InterruptedException ex) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
        }

        AirportSimulation.gates.put(((Flight) e).gate, plane);
        AirportSimulation.printOut("[TAXIWAY]:Plane " + plane.id + " for Flight " + ((Flight) e).flightID + " has arrived at Runway and is prepared for TakeOff");

        takeOff(plane);
    }

    private void takeOff(Plane plane) {

        boolean run = true;

        while (run) {
            if (AirportSimulation.gates.get(Gate.Runway) == null) {
                AirportSimulation.gates.put(Gate.Runway, plane);

                try {
                    sleep((int) (1.5 * AirportSimulation.TIME_PASSAGE_AMOUNT + AirportSimulation.rng.nextInt((int) (2.5 * AirportSimulation.TIME_PASSAGE_AMOUNT))));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
                }

                AirportSimulation.printOut("[RUNWAY]:Plane " + plane.id + " for Flight " + ((Flight) e).flightID + " has taken off!");
                AirportSimulation.planesTakenOff++;

                AirportSimulation.gates.put(Gate.Runway, null);

                run = false;
            }
            try {
                sleep(AirportSimulation.TIME_PASSAGE_AMOUNT / 2);
            } catch (InterruptedException ex) {
                Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void doMaintence() {//NOT IMPLEMENTED YET

    }
}

/**
 * A class to run to add additional flights to the schedule
 *
 * @author mfaux02
 */
class Scheduler implements Runnable {

    final int MIN_MINUTE_LANDED_TIME = 160;//The minimum time in minutes for a plane to be landed
    final int TIME_RANDOMIZER = 15;//a randomizer added to the minimum time

    @Override
    public void run() {
        AirportSimulation.printOut("[SCHEDULER]:Started!");

        Time time = null;

        while (AirportSimulation.getSize() < AirportSimulation.TOTAL_PLANES_TO_LAND * 1.85) {
            time = new Time(AirportSimulation.getTime().toString());
            time.doTick(16);

            Plane plane = createPlane();
            AirportSimulation.planesForLanding.add(plane);
            AirportSimulation.printOut("[SCHEDULER]:Plane " + plane.id + " Registered!");

            Time flightInTime = AirportSimulation.getFreeTime(time);
            Flight flightIn = createInboundFlight(plane, flightInTime);
            AirportSimulation.addFlight(AirportSimulation.getIndex(flightIn.time), flightIn);
            AirportSimulation.printOut("[SCHEDULER]:" + flightIn);
            AirportSimulation.gates.put(flightIn.gate, null);

            time = new Time(flightInTime.toString());
            time.doTick(MIN_MINUTE_LANDED_TIME + (AirportSimulation.rng.nextInt(TIME_RANDOMIZER) * 4));

            Time flightOutTime = AirportSimulation.getFreeTime(time);
            Flight flightOut = createOutboundFlight(plane, flightIn.gate, flightOutTime);
            AirportSimulation.addFlight(AirportSimulation.getIndex(flightOut.time), flightOut);
            AirportSimulation.printOut("[SCHEDULER]:" + flightOut);

        }
        AirportSimulation.schedulerRunning = false;
        AirportSimulation.printOut("[SCHEDULER]:Ended!");

    }

    public Flight createInboundFlight(Plane plane, Time time) {
        Gate gate = getGate();
        AirportID id = getAirportID();

        Flight flight = new Flight(plane.id, id, FlightType.Inbound, gate, time);
        return flight;
    }

    public Flight createOutboundFlight(Plane plane, Gate gate, Time time) {
        AirportID id = getAirportID();

        Flight flight = new Flight(plane.id, id, FlightType.Outbound, gate, time);
        return flight;
    }

    public AirportID getAirportID() {
        AirportID[] ids = AirportID.values();

        int id = AirportSimulation.rng.nextInt(ids.length);

        return ids[id];
    }

    public Gate getGate() {

        String gates = AirportSimulation.gates.keySet().toString();
        String gateID;

        gateID = "A";
        for (int i = 1; i < 10; i++) {
            if (!gates.contains(gateID + i)) {
                return Gate.valueOf(gateID + i);
            }
        }
        gateID = "B";
        for (int i = 16; i < 40; i++) {
            if (!gates.contains(gateID + i)) {
                return Gate.valueOf(gateID + i);
            }
        }
        gateID = "C";
        for (int i = 40; i < 66; i++) {
            if (!gates.contains(gateID + i)) {
                return Gate.valueOf(gateID + i);
            }
        }
        gateID = "D";
        for (int i = 66; i < 90; i++) {
            if (!gates.contains(gateID + i)) {
                return Gate.valueOf(gateID + i);
            }
        }

        return Gate.Elsewhere;
    }

    public Gate getMaintenceGate() {//NOT IMPLEMENTED YET
        String gates = AirportSimulation.gates.keySet().toString();
        if (!gates.contains("M11")) {
            return Gate.M11;
        } else if (!gates.contains("M12")) {
            return Gate.M12;
        } else if (!gates.contains("M13")) {
            return Gate.M13;
        } else if (!gates.contains("M14")) {
            return Gate.M14;
        } else if (!gates.contains("M15")) {
            return Gate.M15;
        } else {
            return Gate.Elsewhere;
        }
    }

    public Plane createPlane() {
        Type[] types = Type.values();
        int id = AirportSimulation.rng.nextInt(types.length);
        Type type = types[id];
        return new Plane(type);
    }

}

class Timer implements Runnable {

    @Override
    public void run() {
        while (AirportSimulation.run) {
            AirportSimulation.doTick();

            if (!AirportSimulation.isEmpty()) {
                if (AirportSimulation.getTime().compareTo(AirportSimulation.getFlight(0).time) == 0) {

                    Event f = AirportSimulation.removeFlight(0);
                    Runnable flight = new Action(f);
                    Thread flightThread = new Thread(flight);
                    flightThread.start();
                }
            }
            if (!AirportSimulation.eventList.isEmpty()) {
                for (int i = 0; i < 6; i++) {
                    if (AirportSimulation.getTime().compareTo(AirportSimulation.eventList.get(0).time) == 0) {

                        Runnable e = new Action(AirportSimulation.eventList.remove(i));
                        Thread eventThread = new Thread(e);
                        eventThread.start();
                    }

                }
            }

            if (AirportSimulation.getSize() < AirportSimulation.TOTAL_PLANES_TO_LAND * 1.5 & !AirportSimulation.schedulerRunning) {
                Runnable schedule = new Scheduler();
                Thread scheduler = new Thread(schedule);
                scheduler.start();
                AirportSimulation.schedulerRunning = true;
            }

            try {
                sleep(AirportSimulation.TIME_PASSAGE_AMOUNT);
            } catch (InterruptedException ex) {
                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
