package Maze;
import java.util.*;

/**
 * Nicholas Colonna
 * "I pledge my honor that I have abided by the Stevens Honor System." ncolonna
 */

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
    		System.out.println(findAllMazePaths(0,0));
    		System.out.println("Shortest Path: " + findMazePathMin(0,0));
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
    		if(x<0 || y<0) {   //x or y falls outside the grid
    			return false;
        }else if(x >= maze.getNCols() || y >= maze.getNRows()) {   //x or y falls outside of the grid
        		return false;
        }else if(!maze.getColor(x, y).equals(NON_BACKGROUND)) {	//No possible path through this cell
        		return false;
        }
    		
    		
    		if(x == maze.getNCols()-1 && y == maze.getNRows()-1) {	//reached exit cell
    			maze.recolor(x, y, PATH);
    			return true;
    		} else {
    			maze.recolor(x, y, PATH);
    			if((findMazePath(x, y-1) || findMazePath(x, y+1) || findMazePath(x-1, y) || findMazePath(x+1, y)) == true) {  //checks all 4 neighboring cells as possible paths
    				return true;
    			}else {		//cell was a dead end
    				maze.recolor(x, y, TEMPORARY);
    				return false;
    			}
    		}
    		
    }

    /**
     * Modifies findMazePath so that it returns an ArrayList of all the solutions to the maze
     * @param x
     * @param y
     * @return ArrayList of all solutions to the maze
     */
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y){
		ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
		Stack<PairInt> trace = new Stack<>(); 
		findMazePathStackBased(0,0,result, trace);
		return result;
    }
    
    /**
     * Helper function for findAllMazePaths
     * @param x -> x coordinate
     * @param y -> y coordinate
     * @param result -> list of successful paths recorded up to now
     * @param trace -> trace of the current path being explored
     */
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
		if(x<0 || y<0) {   //x or y falls outside the grid
			return;
		}else if(x >= maze.getNCols() || y >= maze.getNRows()) {   //x or y falls outside of the grid
    			return;
		}else if(!maze.getColor(x, y).equals(NON_BACKGROUND)) {	//No possible path through this cell
    			return;
		}
		
		
		if(x == maze.getNCols()-1 && y == maze.getNRows()-1) {	//reached exit cell
			trace.push(new PairInt(x,y));
			ArrayList<PairInt> temp = new ArrayList<>();
			temp.addAll(trace);
			result.add(temp);
			trace.pop();
		} else {		//explore the neighboring cells
			maze.recolor(x, y, PATH);
			trace.push(new PairInt(x,y));
			findMazePathStackBased(x, y-1, result, trace);
			findMazePathStackBased(x, y+1, result, trace);
			findMazePathStackBased(x-1, y, result, trace);
			findMazePathStackBased(x+1, y, result, trace);
			maze.recolor(x, y, NON_BACKGROUND);
			trace.pop();
		}
    }
    
    /**
     * Finds the shortest path to solve the maze
     * @param x -> x coordinate
     * @param y -> y coordinate
     * @return ArrayList of the shortest path solution
     */
    public ArrayList<PairInt> findMazePathMin(int x, int y){
    		ArrayList<ArrayList<PairInt>> allPaths = findAllMazePaths(x,y);
    		if(allPaths.size() == 0) {	//no solutions to maze
    			allPaths.add(new ArrayList<PairInt>());
    			return allPaths.get(0);
    		}
    		int shortestPath = 0;
    		int shortest = allPaths.get(shortestPath).size();
    		for(int i=0; i<allPaths.size(); i++) {
    			int test = allPaths.get(i).size();
    			
    			if(test < shortest) {
    				shortest = test;
    				shortestPath = i;
    			}
    		}
    		return allPaths.get(shortestPath);
    }
    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
