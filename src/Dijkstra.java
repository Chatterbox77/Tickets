
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Dijkstra
{
//    Compute the shortest path from given station to all other station
//    Using Dijkstra algorithm
    public static void computePaths(Station source)
    {
        source.setMinDistance(0);
        PriorityQueue<Station> vertexQueue = new PriorityQueue<Station>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Station u = vertexQueue.poll();


            for (Edge e : u.getAdjacencies())
            {
                Station v = e.getTarget();
                double weight = e.getWeight();
                double distanceThroughU = u.getMinDistance() + weight;
                if (distanceThroughU < v.getMinDistance()) {
                    vertexQueue.remove(v);

                    v.setMinDistance(distanceThroughU);
                    v.setPrevious(u);
                    vertexQueue.add(v);
                }
            }
        }
    }




}