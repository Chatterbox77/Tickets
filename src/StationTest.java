import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StationTest {

    @Test
    void getName() {
        Station st = new Station("City1");
        assertEquals(st.getName(),"City1");
    }

    @Test
    void setAdjacencies() {
        Station st1 = new Station("City1");
        Station st2 = new Station("City2");
        Edge e = new Edge(st2,10);
        st1.setAdjacencies(new Edge[]{e});
        assertEquals(st1.getAdjacencies().length,1);
    }

    @Test
    void getAdjacencies() {
        Station st1 = new Station("City1");
        Station st2 = new Station("City2");
        Edge e = new Edge(st2,10);
        st1.setAdjacencies(new Edge[]{e});
        assertEquals(st1.getAdjacencies().length,1);
    }

    @Test
    void getMinDistance() {
        Station st1 = new Station("City1");
        Station st2 = new Station("City2");
        Edge e = new Edge(st2,10);
        Edge e2 = new Edge(st1,10);
        st1.setAdjacencies(new Edge[]{e});
        st2.setAdjacencies(new Edge[]{e2});
        Dijkstra.computePaths(st1);
        assertEquals(st2.getMinDistance(),10);
    }



    @Test
    void toString1() {
        Station st1 = new Station("City1");
        assertEquals(st1.toString(),"City1");
    }
}