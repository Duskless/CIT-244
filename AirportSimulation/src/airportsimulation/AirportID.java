/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airportsimulation;

/**
 *
 * @author Mafau
 */
public enum AirportID {//NOT FULLY IMPLEMENTED
    ATL("KATL", "Hartsfield-Jackson Atlanta International Airport"),
    PEK("ZBAA", "Beijing Capital International Airport"),
    LHR("EGLL", "London Heathrow Airport"),
    HND("RJTT", "Tokyo International Airport"),
    ORD("KORD", "O'Hare International Airport"),
    LAX("KLAX", "Los Angeles International Airport"),
    CDG("LFPG", "Charles de Gaulle International Airport"),
    DFW("KDFW", "Dallas-Fort Worth International Airport"),
    CGK("WIII", "Soekarno-Hatta International Airport"),
    DXB("OMDB", "Dubai International Airport"),
    FRA("EDDF", "Frankfurt International Airport"),
    HKG("VHHH", "Hong Kong International Airport"),
    DEN("KDEN", "Denver International Airport"),
    BKK("VTBS", "Suvarnabhumi Airport"),
    SIN("WSSS", "Singapore Changi Airport"),
    AMS("EHAM", "Amsterdam Airport Schiphol"),
    JFK("KJFK", "John F. Kennedy International Airport"),
    CAN("ZGGG", "Guangzhou Baiyun International Airport"),
    MAD("LEMD", "Barajas International Airport"),
    IST("LTBA", "Atatürk International Airport"),
    PVG("ZSPD", "Shanghai Pudong International Airport"),
    SFO("KSFO", "San Francisco International Airport"),
    LAS("KLAS", "McCarran International Airport"),
    CLT("KCLT", "Charlotte/Douglas International Airport"),
    PHX("KPHX", "Sky Harbor International Airport"),
    IAH("KIAH", "George Bush Intercontinental Airport"),
    KUL("WMKK", "Kuala Lumpur International Airport"),
    MIA("KMIA", "Miami International Airport"),
    ICN("RKSI", "Incheon International Airport"),
    MUC("EDDM", "Franz Josef Strauss International Airport"),
    PIT("KPIT", "Pittsburgh International Airport");

    String ICAO;
    String name;

    AirportID(String ICAO, String name) {
        this.ICAO = ICAO;
        this.name = name;
    }
}
