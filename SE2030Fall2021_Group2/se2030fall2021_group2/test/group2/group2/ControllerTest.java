package group2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ControllerTest {

    Controller cont;
    DataLists data;

    @BeforeEach
    void init() {
        data = new DataLists();
        cont = new Controller();
    }

    @Test
    void testFinalize() {
    }

    @Test
    void test_isStringDataValid() {
        String strA = null;
        String strB = "";
        String strC = "tacos";
        String strD = "1234";
        assertFalse(cont.isStringDataValid(strA));
        assertFalse(cont.isStringDataValid(strB));
        assertTrue(cont.isStringDataValid(strC));
        assertTrue(cont.isStringDataValid(strD));
    }

    @Test
    void test_isIntDataValid() {
        String strA = null;
        String strB = "";
        String strC = "tacos";
        String strD = "1234";
        String strE = "-1";
        assertFalse(cont.isIntDataValid(strA));
        assertFalse(cont.isIntDataValid(strB));
        assertFalse(cont.isIntDataValid(strC));
        assertTrue(cont.isIntDataValid(strD));
        assertTrue(cont.isIntDataValid(strE));
    }

    @Test
    void test_isDoubleDataValid() {
        String strA = null;
        String strB = "";
        String strC = "tacos";
        String strD = "1234";
        String strE = "-1";
        assertFalse(cont.isIntDataValid(strA));
        assertFalse(cont.isIntDataValid(strB));
        assertFalse(cont.isIntDataValid(strC));
        assertTrue(cont.isIntDataValid(strD));
        assertTrue(cont.isIntDataValid(strE));
    }

    @Test
    void test_isLineValid() {
        String lineA = null;
        String lineB = "i, like, waffles";
        String lineC = "tacos, , , , , , ,";
        String lineD = "hey,you,are,cool,";
        String lineE = ",,,,,,,,,,,";
        assertFalse(cont.isLineValid(lineA,0));
        assertTrue(cont.isLineValid(lineB,2));
        assertTrue(cont.isLineValid(lineC, 7));
        assertTrue(cont.isLineValid(lineD, 4));
        assertTrue(cont.isLineValid(lineE, 11));

    }

    @Test
    void test_isPosIntDataValid() {
        String strA = null;
        String strB = "";
        String strC = "tacos";
        String strD = "1234";
        String strE = "-1";
        assertFalse(cont.isPosIntDataValid(strA));
        assertFalse(cont.isPosIntDataValid(strB));
        assertFalse(cont.isPosIntDataValid(strC));
        assertTrue(cont.isPosIntDataValid(strD));
        assertFalse(cont.isPosIntDataValid(strE));
    }

    @Test
    void test_isHeaderValid() {
        String strA = "stop_id,stop_name,stop_desc,stop_lat,stop_lon";
        String strB = "trip_id,arrival_time,departure_time,stop_id,stop_sequence," +
                "stop_headsign,pickup_type,drop_off_type";
        String strC = "route_id,service_id,trip_id,trip_headsign,direction_id,block_id,shape_id";
        String strD = "route_id,agency_id,route_short_name,route_long_name,route_desc,route_type," +
                "route_url,route_color,route_text_color";
        String strE = "-1";
        String strF = null;
        assertTrue(cont.isHeaderValid(strA));
        assertTrue(cont.isHeaderValid(strB));
        assertTrue(cont.isHeaderValid(strC));
        assertTrue(cont.isHeaderValid(strD));
        assertFalse(cont.isHeaderValid(strE));
        assertFalse(cont.isHeaderValid(strF));

    }

    @Test
    void export() {

    }

    @Test
    void importFiles() {
    }

    @Test
    void alterStopLocation() {
    }

    @Test
    void alterTripStopTimes() {
    }

    @Test
    void calculateDistance() {
    }

    @Test
    void calculateNumTripsPerStops() {
    }

    @Test
    void calculateSpeed() {
    }

    @Test
    void createBusPlot() {
    }

    @Test
    void createStopsPlot() {
    }

    @Test
    void searchByRoute() {
    }

    @Test
    void searchByStop() {
    }

    @Test
    void update() {
    }
}