/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airportsimulation;

import java.util.Random;

/**
 *
 * @author mfaux02
 */
public class Flight extends Event {

    String flightID;
    AirportID id;
    FlightType type;

    Flight(String planeID, AirportID airportID, FlightType type, Gate gate, Time time) {
        
        this.planeID = planeID;
        this.id = airportID;
        this.type = type;
        this.gate = gate;
        this.time = new Time(time.toString());

        Random rng = new Random();
        
        if(type == FlightType.Inbound){
            acID = ActionID.Land;
        }else{
            acID = ActionID.Board;
        }

        flightID = Integer.toHexString(rng.nextInt(10000)).toLowerCase();
    }

    @Override
    public String toString() {
        String rtn = type + " Flight : " + this.flightID + " Scheduled for " + this.time + " on Plane " + this.planeID + ".";

        return rtn;
    }

}
