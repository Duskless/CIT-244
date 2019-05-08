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
public class Plane {

    String id;
    String status;
    Type type;
    int passengers;

    Plane(Type type) {
        Random rng = new Random();
        this.type = type;
        passengers = rng.nextInt(type.seats/10) + type.seats/10*9;
        status = "On Time";


        id = Integer.toHexString(rng.nextInt(10000)).toUpperCase();
    }
}


