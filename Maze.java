import java.util.*;
import java.lang.Math;
public class Maze {

    private Coordinate [][] Maze;
    private Coordinate Start;
    private Coordinate G1;
    private Coordinate G2;
    private int N;
    private boolean [][] visited;

    //constructor
    public Maze(int N){
        System.out.println("Give the cell obstacle probability from 0-100. (100 probabilty means that every cell is an obstacle)");
        Scanner probability = new Scanner(System.in);
        int p = probability.nextInt();
        this.N=N;
        this.Maze=new Coordinate[N][N];

        for (int i=0; i < this.Maze.length; i++){
            for (int j=0; j< this.Maze[i].length; j++){
                this.Maze[i][j]=new Coordinate(i,j,p);
            }
        }

        System.out.println("Give the Start coordinates separated with comma. ");
        Scanner start = new Scanner(System.in);
        String startCoordinates = start.next();
        String [] split;
        split = startCoordinates.split(",");
        Integer x = Integer.valueOf(split[0]);
        Integer y = Integer.valueOf(split[1]);
        this.Maze[x][y].setCellValue(2);
        Start = this.Maze[x][y];

        System.out.println("Give the G1 coordinates separated with comma. ");
        Scanner g1 = new Scanner(System.in);
        String G1Coordinates = g1.next();
        String [] splitG1;
        splitG1 = G1Coordinates.split(",");
        Integer xG1 = Integer.valueOf(splitG1[0]);
        Integer yG1 = Integer.valueOf(splitG1[1]);
        this.Maze[xG1][yG1].setCellValue(3);
        G1 = this.Maze[xG1][yG1];

        System.out.println("Give the G2 coordinates separated with comma. ");
        Scanner g2 = new Scanner(System.in);
        String G2Coordinates = g2.next();
        String [] splitG2;
        splitG2 = G2Coordinates.split(",");
        Integer xG2 = Integer.valueOf(splitG2[0]);
        Integer yG2 = Integer.valueOf(splitG2[1]);
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
        for (int row=0; row < Maze.length; row++){
            String add="";
            for (int column=0; column < Maze[row].length; column++){
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

    public Coordinate getG1() {
        return G1;
    }

    public Coordinate getG2() {
        return G2;
    }

    public boolean[][] getVisited() {
        return visited;
    }
    public double calculateDistance(int Xs,int Ys,int Xf,int Yf) {
    	/*int X=(Xs-Xf)^2;
    	int Y=(Ys-Yf)^2;
		double distance=Math.sqrt(X+Y);
		return distance;*/
        return Math.sqrt((Ys - Yf) * (Ys - Yf) + (Xs - Xf) * (Xs - Xf));


    }

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
        Maze SearchMaze = new Maze(N);
        System.out.println(SearchMaze.toString());
        //SearchAlgorithm ucs = new SearchAlgorithm();
        SearchAlgorithm.UCS(SearchMaze);
        //SearchAlgorithm.Astar(SearchMaze);

    }

}
