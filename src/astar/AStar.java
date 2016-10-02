/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Safat
 */
public class AStar {

    static ArrayList<Node> open = new ArrayList<Node>();
    static ArrayList<Node> closed = new ArrayList<Node>();
    static int gridSize=15;
    static boolean grid[][] = new boolean[gridSize][gridSize];
    int randomToClose=18;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        AStar obj = new AStar();
        Node reached = obj.function_A_Star();
        System.out.println(reached);
    }

    

    public Node function_A_Star() {
//        int count=1;
//        Scanner sc=new Scanner(System.in);
        printGrid();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (i == 0 || i == gridSize-1 || j == 0 || j == gridSize-1) {
                    closed.add(new Node(i, j));
                    grid[i][j] = true;

                }
            }
        }
            // closing some random ones as well
            for (int n = 0; n < randomToClose; n++) {
                int rx = 1 + (int) (Math.random() * ((gridSize-2 - 1) + 1));
                int ry = 1 + (int) (Math.random() * ((gridSize-2 - 1) + 1));
                closed.add(new Node(rx, ry));
                grid[rx][ry] = true;
                printGrid();
            }
        
//        printGrid();
//        if(true) return null;

        Node start = new Node(1, 1);
        start.f = 0;
        //put starting node on open list
        open.add(start);
        while (!open.isEmpty()) {
            Node q = open.get(getLeastNodeFromOPEN());
            //generate successors

            int xtp = -1, ytp = -1;
//            System.out.println("Parent is : " + "(" + q.x + "," + q.y + ")");
            for (int eight = 1; eight <= 8; eight++) {

                switch (eight) {
                    case 1:
                        xtp = q.x - 1;
                        ytp = q.y;
                        break;
                    case 2:
                        xtp = q.x + 1;
                        ytp = q.y;
                        break;
                    case 3:
                        xtp = q.x;
                        ytp = q.y + 1;
                        break;
                    case 4:
                        xtp = q.x;
                        ytp = q.y - 1;
                        break;
                    case 5:
                        xtp = q.x - 1;
                        ytp = q.y + 1;

                        break;
                    case 6:
                        xtp = q.x + 1;
                        ytp = q.y + 1;
                        break;
                    case 7:
                        xtp = q.x - 1;
                        ytp = q.y - 1;
                        break;
                    case 8:
                        xtp = q.x + 1;
                        ytp = q.y - 1;
                        break;
                }
                System.out.println("for succ: \n");
                printGrid(ytp, ytp);
                Node r = new Node(xtp, ytp, q);
                
                r.g = q.g + getFromParent(r);
//                System.out.println(getFromParent(r));

                r.h = getDistanceToGoal(r);
//                System.out.println(getDistanceToGoal(r));
                r.f = r.g + r.h;
                if (isSuccessorGoal(r)) {
                    //goal found this successor is the goal
                    return r;
                }
                boolean skip = false;
                if (!open.isEmpty() && isNodeInOPENwithLowerF(r)) {
                    //skip 
                    skip = true;
                }
                if (!open.isEmpty() && isNodeInCLOSEDwithLowerF(r)) {
                    skip = true;
                }
                if (!skip) {
//                    System.out.println("SGSHSGDHSGDHSDHSHDSHDGJHSG");
                    open.add(r);
                }
///
            }
            open.remove(q);
            closed.add(q);

        }
        return null;
    }

    private static double getDistanceToGoal(Node r) {
        return Math.sqrt((goal.x - r.x) * (goal.x - r.x) + (goal.y - r.y) * (goal.y - r.y));
    }

    private static double getFromParent(Node r) {//getDistanceFromParent
        return 1;
    }

    private static boolean isNodeInOPENwithLowerF(Node l) {


        for (int i = 0; i < open.size(); i++) {
            Node get = open.get(i);
//            System.out.println("in open: "+i);
            System.out.println(open.get(i));
            if (l.x == get.x && l.y == get.y && l.f > get.f) {
                return true;
            }
        }
        System.out.println("\n");
        return false;
    }

    private static boolean isNodeInCLOSEDwithLowerF(Node l) {

        for (int i = 0; i < closed.size(); i++) {
            Node get = closed.get(i);
            if (l.x == get.x && l.y == get.y && l.f > get.f) {
                return true;
            }
        }
        return false;
    }

    private int getLeastNodeFromOPEN() {
        double min = 999;
        int minPos = 0, i = 0;
        if (!open.isEmpty()) {
            Iterator<Node> iterator = open.iterator();
            while (iterator.hasNext()) {
                Node temp = iterator.next();
                if (temp.f < min) {
                    min = temp.f;
                    minPos = i;
                }
                i++;
            }
        } else {
            System.out.println("OPEN Empty");
            return -1;
        }
//        System.out.println("min pos: " + minPos);
        return minPos;
    }

    private boolean isSuccessorGoal(Node s) {
        if (goal.x == s.x && goal.y == s.y) {
            System.out.println("succ: is GOAL");
            return true;
        }
        return false;
    }

    
    
    
    public void printGrid() {

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j]) {
                    System.out.print("O");
                } else {
                    System.out.print("-");
                }
            }
            System.out.print("\n");
        }
    }

    public void printGrid(int gx, int gy) {

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (i == gx && j == gy) {
                    System.out.print("X");
                } else if (grid[i][j]) {
                    System.out.print("O");
                } else {
                    System.out.print("-");
                }
            }
            System.out.print("\n");
        }
    }
    static Node goal = new Node(gridSize-4, gridSize-3);
}
