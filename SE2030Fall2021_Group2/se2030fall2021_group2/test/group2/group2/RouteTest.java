package group2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouteTest {

    Route route = new Route("12", "MCTS",
            "12", "Teutonia-Hampton", "",
            3, "", "00835", "");

    @BeforeEach
    void setUp() {
        route = new Route("12", "MCTS",
                "12", "Teutonia-Hampton", "",
                3, "", "00835", "");
    }

    @Test
    void getId() {
        assertEquals(3, route.getId());
    }

    @Test
    void getRoute_id() {
        assertEquals("12", route.getRoute_id());
    }

    @Test
    void getAgency_id() {
        assertEquals("MCTS", route.getAgency_id());
    }

    @Test
    void setAgency_id() {
        route.setAgency_id("MCTS-2");
        assertEquals("MCTS-2", route.getAgency_id());
    }

    @Test
    void getRoute_short_name() {
        assertEquals("12", route.getRoute_short_name());
    }

    @Test
    void setRoute_short_name() {
        route.setRoute_short_name("13");
        assertEquals("13", route.getRoute_short_name());
    }

    @Test
    void getRoute_long_name() {
        assertEquals("Teutonia-Hampton", route.getRoute_long_name());
    }

    @Test
    void setRoute_long_name() {
        route.setRoute_long_name("Humboldt - Forest Home");
        assertEquals("Humboldt - Forest Home", route.getRoute_long_name());
    }

    @Test
    void getRoute_desc() {
        assertEquals("", route.getRoute_desc());
    }

    @Test
    void setRoute_desc() {
        route.setRoute_desc("The route from Teutonia to Hampton");
        assertEquals("The route from Teutonia to Hampton", route.getRoute_desc());
    }

    @Test
    void getRoute_type() {
        assertEquals(3, route.getRoute_type());
    }

    @Test
    void setRoute_type() {
        route.setRoute_type(4);
        assertEquals(4, route.getRoute_type());
    }

    @Test
    void getRoute_color() {
        assertEquals("00835", route.getRoute_color());
    }

    @Test
    void setRoute_color() {
        route.setRoute_color("00836");
        assertEquals("00836", route.getRoute_color());
    }

    @Test
    void setRoute_id() {
        route.setRoute_id("137");
        assertEquals("137", route.getRoute_id());
    }

    @Test
    void testFinalize() {
    }

    @Test
    void testToString() {
        assertEquals("12,MCTS,12,Teutonia-Hampton,,3,,00835,\n", route.toString());
    }
}