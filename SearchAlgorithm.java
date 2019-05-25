import java.util.*;

public class SearchAlgorithm {

    public static void UCS(Maze maze){
        System.out.println("This is the Uniform Cost Search Algorithm.");
        System.out.println();
        ArrayList<Coordinate> queue = new ArrayList<Coordinate>();
        Coordinate Start = maze.getStart();
        Start.setDepth(0);
        Coordinate G1 = maze.getG1();
        Coordinate G2 = maze.getG2();
        int NumberofNodesExpanded=0;

        Coordinate solution=null;
        ArrayList<Coordinate> path=new ArrayList<Coordinate>();
        Start.setParent(null);
        queue.add(Start);
        boolean foundSolution=false;
        /*
         * search the coordinate with << cost ,delete ,examine it
         */

        while(!queue.isEmpty()){
            //System.out.println("mphka while");
            Coordinate toSearch =null;
            boolean hasChild=false;
            for (Coordinate i :queue ) {
                int min_cost=i.getCostfromstart();
                if (i.getCostfromstart()<=min_cost) {
                    toSearch=i;
                    min_cost=i.getCostfromstart();
                }
            }
            if(!maze.getVisited()[toSearch.getX()][toSearch.getY()]){
                maze.getVisited()[toSearch.getX()][toSearch.getY()]=true;

                //check if is solution
                if((toSearch.getX()==G1.getX() && toSearch.getY()==G1.getY()) || (toSearch.getX()==G2.getX() && toSearch.getY()==G2.getY())){
                    System.out.println("Solution found"+"X:"+toSearch.getX()+"Y:"+toSearch.getY());
                    System.out.println("Number of Nodes Expanded: " + NumberofNodesExpanded );
                    System.out.println("Max tree depth searched: " + toSearch.getDepth());
                    solution = toSearch;
                    foundSolution=true;
                    break;
                }
                if(maze.canMoveLeft(toSearch)) {
                    Coordinate child=maze.getMaze()[toSearch.getX()][toSearch.getY()-1];
                    child.setCostfromstart(toSearch.getCostfromstart());
                    child.incrementCostfromstart(child.getCostfromstart());
                    child.setParent(toSearch);
                    queue.add(child);
                    child.setDepth(toSearch.getDepth()+1);
                    hasChild=true;

                }
                if(maze.canMoveRight(toSearch)) {
                    Coordinate child=maze.getMaze()[toSearch.getX()][toSearch.getY()+1];
                    child.setCostfromstart(toSearch.getCostfromstart());
                    child.incrementCostfromstart(child.getCostfromstart());
                    child.setParent(toSearch);
                    queue.add(child);
                    child.setDepth(toSearch.getDepth()+1);
                    hasChild=true;

                }
                if(maze.canMoveUp(toSearch)) {
                    Coordinate child=maze.getMaze()[toSearch.getX()-1][toSearch.getY()];
                    child.setCostfromstart(toSearch.getCostfromstart());
                    child.incrementCostfromstart(child.getCostfromstart());
                    child.setParent(toSearch);
                    queue.add(child);
                    child.setDepth(toSearch.getDepth()+1);
                    hasChild=true;
                }
                if(maze.canMoveDown(toSearch)) {
                    Coordinate child=maze.getMaze()[toSearch.getX()+1][toSearch.getY()];
                    child.setCostfromstart(toSearch.getCostfromstart());
                    child.incrementCostfromstart(child.getCostfromstart());
                    child.setParent(toSearch);
                    queue.add(child);
                    child.setDepth(toSearch.getDepth()+1);
                    hasChild=true;
                }
                if(maze.canMoveUpLeft(toSearch)) {
                    Coordinate child=maze.getMaze()[toSearch.getX()-1][toSearch.getY()-1];
                    child.setCostfromstart(toSearch.getCostfromstart());
                    child.incrementCostfromstart(child.getCostfromstart());
                    child.setParent(toSearch);
                    queue.add(child);
                    child.setDepth(toSearch.getDepth()+1);
                    hasChild=true;
                }
                if(maze.canMoveUpRight(toSearch)) {
                    Coordinate child=maze.getMaze()[toSearch.getX()-1][toSearch.getY()+1];
                    child.setCostfromstart(toSearch.getCostfromstart());
                    child.incrementCostfromstart(child.getCostfromstart());
                    child.setParent(toSearch);
                    queue.add(child);
                    child.setDepth(toSearch.getDepth()+1);
                    hasChild=true;
                }
                if(maze.canMoveDownLeft(toSearch)) {
                    Coordinate child=maze.getMaze()[toSearch.getX()+1][toSearch.getY()-1];
                    child.setCostfromstart(toSearch.getCostfromstart());
                    child.incrementCostfromstart(child.getCostfromstart());
                    child.setParent(toSearch);
                    queue.add(child);
                    child.setDepth(toSearch.getDepth()+1);
                    hasChild=true;
                }
                if(maze.canMoveDownRight(toSearch)) {
                    Coordinate child=maze.getMaze()[toSearch.getX()+1][toSearch.getY()+1];
                    child.setCostfromstart(toSearch.getCostfromstart());
                    child.incrementCostfromstart(child.getCostfromstart());
                    child.setParent(toSearch);
                    queue.add(child);
                    child.setDepth(toSearch.getDepth()+1);
                    hasChild=true;
                }

            }
            if(hasChild) {
                NumberofNodesExpanded++;

            }
            queue.remove(toSearch);

        }

        if(foundSolution) {

            while(solution.getParent()!=null) {
                path.add(solution);
                solution=solution.getParent();
            }

            Iterator<Coordinate>iter=path.iterator();
            iter.next();
            while(iter.hasNext()) {
                Coordinate current = iter.next();
                maze.getMaze()[current.getX()][current.getY()].setCellValue(5);
                System.out.print(current.getX()+","+current.getY()+" ");

            }
            System.out.println(" ");
            System.out.println(maze.toString());
        }
        else {
            System.out.println("Maze unsolvable ,sorry matey :/");
        }

    }
    public static void Astar(Maze maze) {
        System.out.println("This is the Astar Search Algorithm.");
        System.out.println();
        ArrayList<Coordinate> queue = new ArrayList<Coordinate>();
        Coordinate Start = maze.getStart();
        Start.setDepth(0);
        Coordinate G1 = maze.getG1();
        Coordinate G2 = maze.getG2();
        int NumberofNodesExpanded=0;

        Coordinate solution=null;
        ArrayList<Coordinate> path=new ArrayList<Coordinate>();
        Start.setParent(null);
        queue.add(Start);
        boolean foundSolution=false;
        /*
         * Using euclideian distance to find h(n)
         */
        Coordinate toSearch =Start;
        while(!queue.isEmpty()){

            boolean hasChild=false;
            double minCost=maze.calculateDistance(Start.getX(), Start.getY(),G1.getX(),G1.getY());
            for (Coordinate i :queue ) {
                double distance1=maze.calculateDistance(i.getX(), i.getY(),G1.getX(),G1.getY());
                double distance2=maze.calculateDistance(i.getX(), i.getY(),G2.getX(),G2.getY());
                System.out.println("Distance from G1:  "+distance1);
                System.out.println("Distance from G2:  "+distance2);
                if(distance1<=distance2) {
                    i.setDistance(distance1);
                }
                else {
                    i.setDistance(distance2);
                }
                if (i.getDistance()+i.getCostfromstart()<=minCost) {
                    minCost=i.getDistance()+i.getCostfromstart();
                    toSearch=i;

                }
            }

            if(!maze.getVisited()[toSearch.getX()][toSearch.getY()]){
                maze.getVisited()[toSearch.getX()][toSearch.getY()]=true;

                //check if is solution
                if((toSearch.getX()==G1.getX() && toSearch.getY()==G1.getY()) || (toSearch.getX()==G2.getX() && toSearch.getY()==G2.getY())){
                    System.out.println("Solution found"+"X:"+toSearch.getX()+"Y:"+toSearch.getY());
                    System.out.println("Number of Nodes Expanded: " + NumberofNodesExpanded );
                    System.out.println("Max tree depth searched: " + toSearch.getDepth());
                    solution = toSearch;
                    foundSolution=true;
                    break;
                }
                if(maze.canMoveLeft(toSearch)) {
                    Coordinate child=maze.getMaze()[toSearch.getX()][toSearch.getY()-1];
                    child.setCostfromstart(toSearch.getCostfromstart());
                    child.incrementCostfromstart(child.getCostfromstart());
                    child.setParent(toSearch);
                    queue.add(child);
                    child.setDepth(toSearch.getDepth()+1);
                    hasChild=true;

                }
                if(maze.canMoveRight(toSearch)) {
                    Coordinate child=maze.getMaze()[toSearch.getX()][toSearch.getY()+1];
                    child.setCostfromstart(toSearch.getCostfromstart());
                    child.incrementCostfromstart(child.getCostfromstart());
                    child.setParent(toSearch);
                    queue.add(child);
                    child.setDepth(toSearch.getDepth()+1);
                    hasChild=true;

                }
                if(maze.canMoveUp(toSearch)) {
                    Coordinate child=maze.getMaze()[toSearch.getX()-1][toSearch.getY()];
                    child.setCostfromstart(toSearch.getCostfromstart());
                    child.incrementCostfromstart(child.getCostfromstart());
                    child.setParent(toSearch);
                    queue.add(child);
                    child.setDepth(toSearch.getDepth()+1);
                    hasChild=true;
                }
                if(maze.canMoveDown(toSearch)) {
                    Coordinate child=maze.getMaze()[toSearch.getX()+1][toSearch.getY()];
                    child.setCostfromstart(toSearch.getCostfromstart());
                    child.incrementCostfromstart(child.getCostfromstart());
                    child.setParent(toSearch);
                    queue.add(child);
                    child.setDepth(toSearch.getDepth()+1);
                    hasChild=true;
                }
                if(maze.canMoveUpLeft(toSearch)) {
                    Coordinate child=maze.getMaze()[toSearch.getX()-1][toSearch.getY()-1];
                    child.setCostfromstart(toSearch.getCostfromstart());
                    child.incrementCostfromstart(child.getCostfromstart());
                    child.setParent(toSearch);
                    queue.add(child);
                    child.setDepth(toSearch.getDepth()+1);
                    hasChild=true;
                }
                if(maze.canMoveUpRight(toSearch)) {
                    Coordinate child=maze.getMaze()[toSearch.getX()-1][toSearch.getY()+1];
                    child.setCostfromstart(toSearch.getCostfromstart());
                    child.incrementCostfromstart(child.getCostfromstart());
                    child.setParent(toSearch);
                    queue.add(child);
                    child.setDepth(toSearch.getDepth()+1);
                    hasChild=true;
                }
                if(maze.canMoveDownLeft(toSearch)) {
                    Coordinate child=maze.getMaze()[toSearch.getX()+1][toSearch.getY()-1];
                    child.setCostfromstart(toSearch.getCostfromstart());
                    child.incrementCostfromstart(child.getCostfromstart());
                    child.setParent(toSearch);
                    queue.add(child);
                    child.setDepth(toSearch.getDepth()+1);
                    hasChild=true;
                }
                if(maze.canMoveDownRight(toSearch)) {
                    Coordinate child=maze.getMaze()[toSearch.getX()+1][toSearch.getY()+1];
                    child.setCostfromstart(toSearch.getCostfromstart());
                    child.incrementCostfromstart(child.getCostfromstart());
                    child.setParent(toSearch);
                    queue.add(child);
                    child.setDepth(toSearch.getDepth()+1);
                    hasChild=true;
                }

            }
            if(hasChild) {
                NumberofNodesExpanded++;

            }
            queue.remove(toSearch);

        }

        if(foundSolution) {
            while(solution.getParent()!=null) {
                path.add(solution);
                solution=solution.getParent();
            }
            Iterator<Coordinate>iter=path.iterator();
            iter.next();
            while(iter.hasNext()) {
                Coordinate current = iter.next();
                maze.getMaze()[current.getX()][current.getY()].setCellValue(5);
                //System.out.print(current.getX()+","+current.getY()+" ");

            }
            System.out.println(" ");
            System.out.println(maze.toString());
        }
        else {
            System.out.println("Maze unsolvable.");
        }

    }

}
