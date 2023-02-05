package group2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TripTest {
    Trip trip;

    @BeforeEach
    void setUp(){
         trip = new Trip("64","17-SEP-SUN", "21736632-908","UJI",
                 "0", "67557", "13-SEP_78_2_23");
    }

    @Test
    void routeId(){
        assertEquals("64", trip.getRoute_id());
    }

    @Test
    void gettingServiceId(){
        assertEquals("17-SEP-SUN", trip.getService_id());
    }

    @Test
    void getTripId(){
        assertEquals("13466-2899", trip.getTrip_id());
    }

    @Test
    void getTripHeadSign(){
        assertEquals("UJI", trip.getTrip_headsign());
    }

    @Test
    void getDirectionId(){
        assertEquals("0", trip.getDirection_id());
    }

    @Test
    void gettingBlockId(){
        assertEquals("67557", trip.getBlock_id());
    }

    @Test
    void gettingShapeId(){
        assertEquals("13-SEP_78_2_23", trip.getShape_id());
    }

    @Test
    void setTripHeadSign(){
        trip.setTrip_headsign("GPL");
        assertEquals("GPL", trip.getTrip_headsign());
    }



}
