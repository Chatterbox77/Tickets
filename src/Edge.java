class Edge
{
    private  Station target;
    private double weight;
    //    Return the value stored in double weight
    public double getWeight(){ return weight; }
    //    Return the value stored in Station target
    public Station getTarget() { return target; }
    //    Override for toString
    public String toString(){ return target + " " + weight; }
    //  Constructor
    public Edge(Station argTarget, double argWeight) { target = argTarget; weight = argWeight; }
}