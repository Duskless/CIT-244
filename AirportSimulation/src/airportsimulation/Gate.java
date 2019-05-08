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
public enum Gate {
    A1, A2, A3, A4, A5, A6, A7, A8, A9,
    B16, B17, B18, B19, B20, B21, B22, B23, B24, B25, B26, B27, B28, B29, B30, B31, B32, B33, B34, B35, B36, B37, B38, B39,
    C40, C41, C42, C43, C44, C45, C46, C47, C48, C49, C50, C51, C52, C53, C54, C55, C56, C57, C58, C59, C60, C61, C62, C63, C64, C65,
    D66, D67, D68, D69, D70, D71, D72, D73, D74, D75, D76, D77, D78, D79, D80, D81, D82, D83, D84, D85, D86, D87, D88, D89,
    M11, M12, M13, M14, M15,
    TaxiIn, TaxiOut, Runway, Elsewhere;
}

enum ActionID {
    Land,
    TaxiIn,
    Disembark,
    Board,
    TaxiOut,
    TakeOff,
    Maintence;
}

enum FlightType {
    Inbound,
    Outbound;
}

enum Type {
    Airbus_A220(141),
    Airbus_A320(206),
    Airbus_A330(287),
    Airbus_A350(366),
    Airbus_A380(544),
    Boeing_737(188),
    Boeing_747(410),
    Boeing_777(400),
    Boeing_787(330);

    int seats;

    Type(int seats) {
        this.seats = seats;
    }
}

enum Meridiem {
    AM(0),
    PM(1);

    int index = 0;

    private Meridiem(int index) {
        this.index = index;
    }
}
