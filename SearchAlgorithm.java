import java.util.*;

public class SearchAlgorithm {

    public static void UCS(Maze maze) {
        System.out.println("This is the Uniform Cost Search Algorithm.");
        System.out.println();
        ArrayList<Coordinate> queue = new ArrayList<Coordinate>();
        Coordinate Start = maze.getStart();
        Start.setDepth(-1);
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
        /*
         * search the coordinate with << cost ,delete ,examine it
         */

        while (!queue.isEmpty()) {
            //System.out.println("mphka while");
            boolean samecost=true;
            for (Coordinate i : queue) {

                if (i.getCostfromstart()==treeDepth-1) {
                    samecost=false;
                }
            }
            if(samecost){
                toSearch=queue.get(0);
            }else {
                for (Coordinate i : queue) {

                    if (i.getCostfromstart() < treeDepth) {
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
                    child.setCostfromstart(toSearch.getCostfromstart()+1);
                    child.setParent(toSearch);
                    queue.add(child);
                    hasChild = true;

                }
                if (maze.canMoveRight(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX()][toSearch.getY() + 1];
                    child.setCostfromstart(toSearch.getCostfromstart()+1);

                    child.setParent(toSearch);
                    queue.add(child);

                    hasChild = true;

                }
                if (maze.canMoveUp(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX() - 1][toSearch.getY()];
                    child.setCostfromstart(toSearch.getCostfromstart()+1);
                    child.setParent(toSearch);
                    queue.add(child);

                    hasChild = true;
                }
                if (maze.canMoveDown(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX() + 1][toSearch.getY()];
                    child.setCostfromstart(toSearch.getCostfromstart()+1);
                    child.setParent(toSearch);
                    queue.add(child);

                    hasChild = true;
                }
                if (maze.canMoveUpLeft(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX() - 1][toSearch.getY() - 1];
                    child.setCostfromstart(toSearch.getCostfromstart());
                    child.incrementCostfromstart();
                    child.setParent(toSearch);
                    queue.add(child);

                    hasChild = true;
                }
                if (maze.canMoveUpRight(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX() - 1][toSearch.getY() + 1];
                    child.setCostfromstart(toSearch.getCostfromstart()+1);
                    child.setParent(toSearch);
                    queue.add(child);
                    child.setDepth(toSearch.getDepth() + 1);
                    hasChild = true;
                }
                if (maze.canMoveDownLeft(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX() + 1][toSearch.getY() - 1];
                    child.setCostfromstart(toSearch.getCostfromstart()+1);
                    child.setParent(toSearch);
                    queue.add(child);

                    hasChild = true;
                }
                if (maze.canMoveDownRight(toSearch)) {
                    Coordinate child = maze.getMaze()[toSearch.getX() + 1][toSearch.getY() + 1];
                    child.setCostfromstart(toSearch.getCostfromstart()+1);
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

        if (foundSolution) {

            while (solution.getParent() != null) {
                path.add(solution);
                solution = solution.getParent();
            }

            Iterator<Coordinate> iter = path.iterator();
            iter.next();
            while (iter.hasNext()) {
                Coordinate current = iter.next();
                maze.getMaze()[current.getX()][current.getY()].setCellValue(5);
                System.out.print(current.getX() + "," + current.getY() + " ");

            }
            System.out.println(" ");
            System.out.println(maze.toString());
        } else {
            System.out.println("Maze unsolvable ,sorry matey :/");
        }

    }
}