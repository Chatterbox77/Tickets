class Station implements Comparable<Station>
{
    private String name;
    private Edge[] adjacencies;
    private double minDistance = Double.POSITIVE_INFINITY;
    private Station previous;

    //Constructor
    public Station(String argName) { name = argName; }

    //Returns the value stored in String name
    public String getName() {
        return name;
    }

    //Stores the given values into Edge[] adjacencies
    public void setAdjacencies(Edge[] adjacencies) {
        this.adjacencies = adjacencies;
    }

    //Returns the value stored in Edge[] adjacencies
    public Edge[] getAdjacencies() {
        return adjacencies;
    }

    //Returns the value stored in double minDistance
    public double getMinDistance() {
        return minDistance;
    }


    //Returns the value stored in Station previous
    public Station getPrevious() {
        return previous;
    }

    //Stores the given value into double minDistance
    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    //Stores the given value into Station previous
    public void setPrevious(Station previous) {
        this.previous = previous;
    }


    //Override for toString
    public String toString() { return name; }

    //Override for compareTo
    public int compareTo(Station other)
    {
        return Double.compare(minDistance, other.minDistance);
    }

}
