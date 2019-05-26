import java.util.Random;

public class Coordinate {

    private int x;
    private int y;
    private int cellValue;
    private int costFromStart =0;
    private Coordinate parent;
    //====== extra fields for A* ===================//
    private double distanceFromNearestSolution; // H(n).
    private int parentCostFromStart; // Parent's G(n).
    private double totalCost=0; //g(n)+h(n),where g(n) is the cost from the Start Coordinate


    public Coordinate(int f, int s,int p) {
        this.x =f;
        this.y = s;
        this.cellValue = CalculateAccess(p);
    }
    public Coordinate(int f,int s){
        this.x=f;
        this.y=s;
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

    //==============================Calculate the Minimum Distance from the two solution coordinates=============//
    public double calculateMinimumDistanceFromTheTwoSolutions(Coordinate G1,Coordinate G2) {
        double distanceFromG1=Math.sqrt((this.getX() - G1.getX()) * (this.getX() - G1.getX()) + (this.getY() - G1.getY()) * (this.getY() - G1.getY()));
        double distanceFromG2=Math.sqrt((this.getX() - G2.getX()) * (this.getX()- G2.getX()) + (this.getY() - G2.getY()) * (this.getY() - G2.getY()));
        if(distanceFromG1>distanceFromG2){
            this.distanceFromNearestSolution=distanceFromG2;
            return distanceFromG2;
        }
        this.distanceFromNearestSolution=distanceFromG1;
        return distanceFromG1;
    }
    //==============================Calculate the Total Cost to the solution =============//
    public double calculateTotalCostFromTheSolution(){
        this.totalCost=parent.costFromStart+1+this.distanceFromNearestSolution;
        return this.totalCost;
    }

    public double getDistanceFromNearestSolution() {
        return this.distanceFromNearestSolution;
    }
    public double getTotalCost() {
        return this.totalCost;
    }

    public void setStartCoordinateTotalCost() {
        this.totalCost = this.distanceFromNearestSolution;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCellValue() {
        return this.cellValue;

    }

    public int getCostFromStart() {
        return this.costFromStart;
    }

    public Coordinate getParent() {
        return this.parent;
    }

    public void setParent(Coordinate parent) {
        this.parent=parent;
    }

    public void setParentCostFromStart(int parentCostFromStart) {
        this.parentCostFromStart = parentCostFromStart;
    }


    public void incrementCostFromStart() {
        this.costFromStart++;
    }

    public void setCostFromStart(int cost) { this.costFromStart =cost; }

    public void setCellValue(int cellValue) {
        this.cellValue = cellValue;
    }
}
