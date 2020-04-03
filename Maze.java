import java.util.*;

public class Maze {

    private Coordinate [][] Maze;
    private Coordinate Start;
    private Coordinate G1;
    private Coordinate G2;
    private int N;
    private boolean [][] visited;


    //constructor
    public Maze(int N, int p, Integer xStart, Integer yStart, Integer xG1, Integer yG1, Integer xG2, Integer yG2){
        this.N=N;
        this.Maze=new Coordinate[N][N];

        for (int i=0; i < this.Maze.length; i++){
            for (int j=0; j< this.Maze[i].length; j++){
                this.Maze[i][j]=new Coordinate(i,j,p);
            }
        }
        this.Maze[xStart][yStart].setCellValue(2);
        Start=this.Maze[xStart][yStart];

        this.Maze[xG1][yG1].setCellValue(3);
        G1=this.Maze[xG1][yG1];

        this.Maze[xG2][yG2].setCellValue(4);
        G2=this.Maze[xG2][yG2];

        this.visited = new boolean [N][N];
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                visited[i][j]=false;
            }
        }
    }

    //toString
    public String toString(){
        String ReturnMaze="";
        for (int row=0; row < this.N; row++){
            String add="";
            for (int column=0; column < this.N; column++){
                if(this.Maze[row][column].getCellValue()==1){
                    add+=" "+" 1";
                }else if(this.Maze[row][column].getCellValue()==2){
                    add+=" "+" S";
                }else if(this.Maze[row][column].getCellValue()==3){
                    add+=" "+"G1";
                }else if(this.Maze[row][column].getCellValue()==4){
                    add+=" "+"G2";
                }else if(this.Maze[row][column].getCellValue()==5){
                    add+=" "+" *";
                }else {
                    add+=" "+" 0";
                }
            }
            ReturnMaze+=add+"\n";
        }
        return ReturnMaze;
    }

    public Coordinate[][] getMaze() {
        return Maze;
    }

    public Coordinate getStart() {
        return Start;
    }

    public void setStart(Coordinate start) {
        Start = start;
    }

    public void setG1(Coordinate g1) {
        G1 = g1;
    }

    public void setG2(Coordinate g2) {
        G2 = g2;
    }

    public Coordinate getG1() {
        return G1;
    }

    public Coordinate getG2() {
        return G2;
    }

    public void setN(int n) {
        N = n;
    }

    public int getN() {
        return N;
    }
    public void setValues(Maze maze){
        for (int i=0; i <N; i++) {
            for (int j = 0; j < N; j++) {
                this.Maze[i][j].setCellValue(maze.getMaze()[i][j].getCellValue());
            }
        }
    }
    public boolean[][] getVisited() {
        return visited;
    }

    //==============================Movements==========================================//
    public boolean canMoveLeft(Coordinate Current){
        if(Current.getY()==0){
            return false;
        }
        return this.Maze[Current.getX()][Current.getY() - 1].getCellValue() != 1 && !visited[Current.getX()][Current.getY() - 1];
    }

    public boolean canMoveRight(Coordinate Current){
        if(Current.getY()==N-1){
            return false;
        }
        return this.Maze[Current.getX()][Current.getY() + 1].getCellValue() != 1 && !visited[Current.getX()][Current.getY() + 1];
    }

    public boolean canMoveUp(Coordinate Current){
        if(Current.getX()==0){
            return false;
        }
        return this.Maze[Current.getX() - 1][Current.getY()].getCellValue() != 1 && !visited[Current.getX() - 1][Current.getY()];
    }

    public boolean canMoveDown(Coordinate Current){
        if(Current.getX()==N-1){
            return false;
        }
        return this.Maze[Current.getX() + 1][Current.getY()].getCellValue() != 1 && !visited[Current.getX() + 1][Current.getY()];
    }

    public boolean canMoveUpLeft(Coordinate Current){
        if(Current.getX()==0 || Current.getY()==0 ){
            return false;
        }
        return this.Maze[Current.getX() - 1][Current.getY() - 1].getCellValue() != 1 && !visited[Current.getX() - 1][Current.getY() - 1];
    }

    public boolean canMoveUpRight(Coordinate Current){
        if(Current.getX()==0 || Current.getY()==N-1 ){
            return false;
        }
        return this.Maze[Current.getX() - 1][Current.getY() + 1].getCellValue() != 1 && !visited[Current.getX() - 1][Current.getY() + 1];
    }

    public boolean canMoveDownLeft(Coordinate Current){
        if(Current.getX()==N-1 || Current.getY()==0 ){
            return false;
        }
        return this.Maze[Current.getX() + 1][Current.getY() - 1].getCellValue() != 1 && !visited[Current.getX() + 1][Current.getY() - 1];
    }

    public boolean canMoveDownRight(Coordinate Current){
        if(Current.getX()==N-1 || Current.getY()==N-1){
            return false;
        }
        return this.Maze[Current.getX() + 1][Current.getY()+1].getCellValue() != 1 && !visited[Current.getX() + 1][Current.getY()+1];
    }







    public static void  main(String [] args){
        System.out.println("Give an integer for the maze dimensions. (NxN)");
        Scanner dimension = new Scanner(System.in);
        int N= dimension.nextInt();

        System.out.println("Give the cell obstacle probability from 0-100. (100 probabilty means that every cell is an obstacle)");
        Scanner probability = new Scanner(System.in);
        int p = probability.nextInt();

        System.out.println("Give the Start coordinates separated with comma. ");
        Scanner start = new Scanner(System.in);
        String startCoordinates = start.next();
        String [] split;
        split = startCoordinates.split(",");
        Integer xStart = Integer.valueOf(split[0]);
        Integer yStart = Integer.valueOf(split[1]);


        System.out.println("Give the G1 coordinates separated with comma. ");
        Scanner g1 = new Scanner(System.in);
        String G1Coordinates = g1.next();
        String [] splitG1;
        splitG1 = G1Coordinates.split(",");
        Integer xG1 = Integer.valueOf(splitG1[0]);
        Integer yG1 = Integer.valueOf(splitG1[1]);


        System.out.println("Give the G2 coordinates separated with comma. ");
        Scanner g2 = new Scanner(System.in);
        String G2Coordinates = g2.next();
        String [] splitG2;
        splitG2 = G2Coordinates.split(",");
        Integer xG2 = Integer.valueOf(splitG2[0]);
        Integer yG2 = Integer.valueOf(splitG2[1]);


        Maze UCS = new Maze(N,p,xStart,yStart,xG1,yG1,xG2,yG2);
        Maze Astar =new Maze(N,p,xStart,yStart,xG1,yG1,xG2,yG2);

        //Set the same cell values to both mazes.
        Astar.setValues(UCS);

        SearchAlgorithm.UCS(UCS);
        SearchAlgorithm.Astar(Astar);
    }
}
