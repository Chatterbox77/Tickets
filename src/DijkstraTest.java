import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    @Test
    void computePaths() {
        Station st1 = new Station("City1");
        Station st2 = new Station("City2");
        Edge e = new Edge(st2,10);
        Edge e2 = new Edge(st1,10);
        st1.setAdjacencies(new Edge[]{e});
        st2.setAdjacencies(new Edge[]{e2});
        Dijkstra.computePaths(st1);
        assertEquals(st2.getMinDistance(),10);
    }


}