import java.util.*;

public class SearchAlgorithm {

    public static void UCS(Maze maze) {
        System.out.println("|===========================================|");
        System.out.println("This is the Uniform Cost Search Algorithm.");
        System.out.println("|===========================================|");
        System.out.println();

        ArrayList<Coordinate> queue = new ArrayList<Coordinate>();
        Coordinate Start = maze.getStart();
        Coordinate G1 = maze.getG1();
        Coordinate G2 = maze.getG2();

        int NumberofNodesExpanded = 0;
        int treeDepth = 0;
        Coordinate solution = null;
        ArrayList<Coordinate> path = new ArrayList<Coordinate>();
        Start.setParent(null);
        queue.add(Start);
        Coordinate toSearch = Start;
        boolean foundSolution = false;

        while (!queue.isEmpty()) {
            boolean samecost=true;
            for (Coordinate i : queue) {

                if (i.getCostFromStart()==treeDepth-1) {
                    samecost=false;
                }
            }
            if(samecost){
                toSearch=queue.get(0);
            }else {
                for (Coordinate i : queue) {

                    if (i.getCostFromStart() < treeDepth) {
                        toSearch = i;
                    }
                }
            }

            boolean hasChild = false;
            if (!maze.getVisited()[toSearch.getX()][toSearch.getY()]) {
                maze.getVisited()[toSearch.getX()][toSearch.getY()] = true;

                //check if is solution
                if ((toSearch.getX() == G1.getX() && toSearch.getY() == G1.getY()) || (toSearch.getX() == G2.getX() && toSearch.getY() == G2.getY())) {
                    System.out.println("Solution found" + "X:" + toSearch.getX() + "Y:" + toSearch.getY());
                    System.out.println("Number of Nodes Expanded: " + NumberofNodesExpanded);
                    System.out.println("Max tree depth searched: " + treeDepth);
                    solution = toSearch;
                    foundSolution = true;
                    break;
                }
                if (maze.canMoveLeft(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX()][toSearch.getY() - 1];
                    child.setCostFromStart(toSearch.getCostFromStart()+1);
                    child.setParent(toSearch);
                    queue.add(child);
                    hasChild = true;

                }
                if (maze.canMoveRight(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX()][toSearch.getY() + 1];
                    child.setCostFromStart(toSearch.getCostFromStart()+1);
                    child.setParent(toSearch);
                    queue.add(child);
                    hasChild = true;

                }
                if (maze.canMoveUp(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX() - 1][toSearch.getY()];
                    child.setCostFromStart(toSearch.getCostFromStart()+1);
                    child.setParent(toSearch);
                    queue.add(child);

                    hasChild = true;
                }
                if (maze.canMoveDown(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX() + 1][toSearch.getY()];
                    child.setCostFromStart(toSearch.getCostFromStart()+1);
                    child.setParent(toSearch);
                    queue.add(child);

                    hasChild = true;
                }
                if (maze.canMoveUpLeft(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX() - 1][toSearch.getY() - 1];
                    child.setCostFromStart(toSearch.getCostFromStart());
                    child.incrementCostFromStart();
                    child.setParent(toSearch);
                    queue.add(child);
                    hasChild = true;
                }
                if (maze.canMoveUpRight(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX() - 1][toSearch.getY() + 1];
                    child.setCostFromStart(toSearch.getCostFromStart()+1);
                    child.setParent(toSearch);
                    queue.add(child);
                    hasChild = true;
                }
                if (maze.canMoveDownLeft(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX() + 1][toSearch.getY() - 1];
                    child.setCostFromStart(toSearch.getCostFromStart()+1);
                    child.setParent(toSearch);
                    queue.add(child);

                    hasChild = true;
                }
                if (maze.canMoveDownRight(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX() + 1][toSearch.getY() + 1];
                    child.setCostFromStart(toSearch.getCostFromStart()+1);
                    child.setParent(toSearch);
                    queue.add(child);

                    hasChild = true;
                }

            }
            if (hasChild) {
                NumberofNodesExpanded++;
                if(samecost) {
                    treeDepth++;
                }

            }
            queue.remove(toSearch);
            //queue = revArrayList(queue);

        }

        //Checking if the maze is solved
        if (foundSolution) {

            while (solution.getParent() != null) {
                path.add(solution);
                solution = solution.getParent();
            }

            System.out.println("======================= List of Coordinates that were visited =========================");
            Iterator<Coordinate> iter = path.iterator();
            iter.next();
            while (iter.hasNext()) {
                Coordinate current = iter.next();
                maze.getMaze()[current.getX()][current.getY()].setCellValue(5);
                System.out.print(current.getX() + "," + current.getY() + " ");

            }
            System.out.println("\n\n");
            System.out.println(maze.toString());
        } else {
            System.out.println("Maze unsolvable.");
        }

    }

    public static void Astar(Maze maze){
        System.out.println("|===========================================|");
        System.out.println("This is the A* Search Algorithm.");
        System.out.println("|===========================================|");
        System.out.println();

        ArrayList<Coordinate> queue = new ArrayList<Coordinate>();
        Coordinate Start = maze.getStart();
        Coordinate G1 = maze.getG1();
        Coordinate G2 = maze.getG2();

        int NumberofNodesExpanded = 0;
        Coordinate solution = null;
        ArrayList<Coordinate> path = new ArrayList<Coordinate>();
        Start.setParent(null);
        boolean foundSolution = false;
        Start.calculateMinimumDistanceFromTheTwoSolutions(G1,G2);
        Start.setStartCoordinateTotalCost();
        queue.add(Start);
        Coordinate toSearch = Start;


        while (!queue.isEmpty()) {
            double minTotalCost=queue.get(queue.size()-1).getTotalCost();
            toSearch=queue.get(0);
            for (Coordinate i : queue) {
                if(i.getTotalCost()<minTotalCost){
                    toSearch=i;
                }
            }
            //check if is solution
            if ((toSearch.getX() == G1.getX() && toSearch.getY() == G1.getY()) || (toSearch.getX() == G2.getX() && toSearch.getY() == G2.getY())) {
                System.out.println("Solution found" + "X:" + toSearch.getX() + "Y:" + toSearch.getY());
                System.out.println("Number of Nodes Expanded: " + NumberofNodesExpanded);
                solution = toSearch;
                foundSolution = true;
                break;
            }
            //System.out.println("Total cost:   "+toSearch.getTotalCost());

            boolean hasChild = false;
            if (!maze.getVisited()[toSearch.getX()][toSearch.getY()]) {
                maze.getVisited()[toSearch.getX()][toSearch.getY()] = true;

                if (maze.canMoveLeft(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX()][toSearch.getY() - 1];
                    child.setParent(toSearch);
                    child.calculateMinimumDistanceFromTheTwoSolutions(G1,G2);
                    child.calculateTotalCostFromTheSolution();
                    queue.add(child);
                    hasChild = true;

                }
                if (maze.canMoveRight(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX()][toSearch.getY() + 1];
                    child.setParent(toSearch);
                    child.calculateMinimumDistanceFromTheTwoSolutions(G1,G2);
                    child.calculateTotalCostFromTheSolution();
                    queue.add(child);
                    hasChild = true;

                }
                if (maze.canMoveUp(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX() - 1][toSearch.getY()];
                    child.setParent(toSearch);
                    child.calculateMinimumDistanceFromTheTwoSolutions(G1,G2);
                    child.calculateTotalCostFromTheSolution();
                    queue.add(child);
                    hasChild = true;
                }
                if (maze.canMoveDown(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX() + 1][toSearch.getY()];
                    child.setParent(toSearch);
                    child.calculateMinimumDistanceFromTheTwoSolutions(G1,G2);
                    child.calculateTotalCostFromTheSolution();
                    queue.add(child);
                    hasChild = true;
                }
                if (maze.canMoveUpLeft(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX() - 1][toSearch.getY() - 1];
                    child.setParent(toSearch);
                    child.calculateMinimumDistanceFromTheTwoSolutions(G1,G2);
                    child.calculateTotalCostFromTheSolution();
                    queue.add(child);
                    hasChild = true;
                }
                if (maze.canMoveUpRight(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX() - 1][toSearch.getY() + 1];
                    child.setParent(toSearch);
                    child.calculateMinimumDistanceFromTheTwoSolutions(G1,G2);
                    child.calculateTotalCostFromTheSolution();
                    queue.add(child);
                    hasChild = true;
                }
                if (maze.canMoveDownLeft(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX() + 1][toSearch.getY() - 1];
                    child.setParent(toSearch);
                    child.calculateMinimumDistanceFromTheTwoSolutions(G1,G2);
                    child.calculateTotalCostFromTheSolution();
                    queue.add(child);
                    hasChild = true;
                }
                if (maze.canMoveDownRight(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX() + 1][toSearch.getY() + 1];
                    child.setParent(toSearch);
                    child.calculateMinimumDistanceFromTheTwoSolutions(G1,G2);
                    child.calculateTotalCostFromTheSolution();
                    queue.add(child);
                    hasChild = true;
                }

            }
            if (hasChild) {
                NumberofNodesExpanded++;
                //System.out.println("===============NODE EXPANDED===============");
            }
            queue.remove(toSearch);

        }
        //Checking if the maze is solved
        if (foundSolution) {

            while (solution.getParent() != null) {
                path.add(solution);
                solution = solution.getParent();
            }

            System.out.println("======================= List of Coordinates that were visited =========================");
            Iterator<Coordinate> iter = path.iterator();
            iter.next();
            while (iter.hasNext()) {
                Coordinate current = iter.next();
                maze.getMaze()[current.getX()][current.getY()].setCellValue(5);
                System.out.print(current.getX() + "," + current.getY());
                System.out.println();

            }
            System.out.println("\n\n");
            System.out.println(maze.toString());
        } else {
            System.out.println(maze.toString());
            System.out.println("Maze unsolvable.");
        }
    }
}