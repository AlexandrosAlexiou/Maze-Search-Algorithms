import java.util.Random;

public class Coordinate {

    private int x;
    private int y;
    private int cellvalue;
    private int costfromstart;
    private Coordinate parent;
    private int depth;
    private double distance;
    private double totalCost=0; //g(n)+h(n),where g(n) is costfromstart
    public Coordinate(int f, int s,int p) {
        this.x =f;
        this.y = s;
        int costfromstart=0;
        this.cellvalue =CalculateAccess(p);
    }
    public void setDepth(int depth) {
        this.depth=depth;
    }
    public int getDepth() {
        return this.depth;
    }

    public void setDistance(double distance1) {
        this.distance=distance1;
    }
    public double getDistance() {
        return this.distance;
    }
    public void setTotalCost(double cost) {
        this.totalCost=cost;
    }
    public double getTotalCost() {
        return this.totalCost;
    }

    private int CalculateAccess(int p) {

        Random gen=new Random();
        int generated=gen.nextInt(100);
        if(generated<p) {
            return 1;
        }else{
            return 0;
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getCellValue() {
        return this.cellvalue;

    }

    public int getCostfromstart() {
        return costfromstart;
    }

    public Coordinate getParent() {
        return this.parent;
    }
    public void setParent(Coordinate parent) {
        this.parent=parent;
    }
    public void incrementCostfromstart(int costfromstart) {
        this.costfromstart = costfromstart + 1 ;
    }
    public void setCostfromstart(int cost) {
        this.costfromstart=cost;
    }
    public void setCellValue(int cellvalue) {
        this.cellvalue = cellvalue;
    }
}
