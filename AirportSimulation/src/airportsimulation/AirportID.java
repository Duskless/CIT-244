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
public enum AirportID {
    ATL("ATL", "KATL", "Hartsfield-Jackson Atlanta International Airport"),
    PEK("PEK", "ZBAA", "Beijing Capital International Airport"),
    LHR("LHR", "EGLL", "London Heathrow Airport"),
    HND("HND", "RJTT", "Tokyo International Airport"),
    ORD("ORD", "KORD", "O'Hare International Airport"),
    LAX("LAX", "KLAX", "Los Angeles International Airport"),
    CDG("CDG", "LFPG", "Charles de Gaulle International Airport"),
    DFW("DFW", "KDFW", "Dallas-Fort Worth International Airport"),
    CGK("CGK", "WIII", "Soekarno-Hatta International Airport"),
    DXB("DXB", "OMDB", "Dubai International Airport"),
    FRA("FRA", "EDDF", "Frankfurt International Airport"),
    HKG("HKG", "VHHH", "Hong Kong International Airport"),
    DEN("DEN", "KDEN", "Denver International Airport"),
    BKK("BKK", "VTBS", "Suvarnabhumi Airport"),
    SIN("SIN", "WSSS", "Singapore Changi Airport"),
    AMS("AMS", "EHAM", "Amsterdam Airport Schiphol"),
    JFK("JFK", "KJFK", "John F. Kennedy International Airport"),
    CAN("CAN", "ZGGG", "Guangzhou Baiyun International Airport"),
    MAD("MAD", "LEMD", "Barajas International Airport"),
    IST("IST", "LTBA", "Atatürk International Airport"),
    PVG("PVG", "ZSPD", "Shanghai Pudong International Airport"),
    SFO("SFO", "KSFO", "San Francisco International Airport"),
    LAS("LAS", "KLAS", "McCarran International Airport"),
    CLT("CLT", "KCLT", "Charlotte/Douglas International Airport"),
    PHX("PHX", "KPHX", "Sky Harbor International Airport"),
    IAH("IAH", "KIAH", "George Bush Intercontinental Airport"),
    KUL("KUL", "WMKK", "Kuala Lumpur International Airport"),
    MIA("MIA", "KMIA", "Miami International Airport"),
    ICN("ICN", "RKSI", "Incheon International Airport"),
    MUC("MUC", "EDDM", "Franz Josef Strauss International Airport"),
    PIT("PIT", "KPIT", "Pittsburgh International Airport");

    String IATA;
    String ICAO;
    String name;

    AirportID(String IATA, String ICAO, String name) {
        this.IATA = IATA;
        this.ICAO = ICAO;
        this.name = name;
    }
}
