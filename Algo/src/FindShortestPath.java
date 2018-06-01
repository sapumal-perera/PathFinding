import java.util.PriorityQueue;

/**
 * ISURU SAPUMAL (w1583345//2015562 )
 * <p>
 * Created by Isuru on 3/27/2017.
 */
public class FindShortestPath {
    public static double diagonalMoveCost = Math.pow(2, 0.5);
    public static double cellMoveCost = 1;
    public static int[][] carr;
    public static double totalCost = 0;
    public static double movementCost = 0;
    public static boolean isDiagonal = false;
    public static String heuristicType = "";
    public static boolean showVisited = false;

    public FindShortestPath(boolean diagonalMove, String heuristic, boolean visited) {
        isDiagonal = diagonalMove;
        heuristicType = heuristic;
        movementCost = 0;
        totalCost = 0;
        showVisited = visited;
        if (heuristicType.equalsIgnoreCase("manhatton")) {
            diagonalMoveCost = 2;
        } else if (heuristicType.equalsIgnoreCase("euclidion")) {
            diagonalMoveCost = Math.pow(2, 0.5);
        } else {
            diagonalMoveCost = 1;
        }
    }

    static class Cell {
        Cell parent;
        int i, j;
        double heuristicCost = 0;
        double finalCost = 0;

        Cell(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public String toString() {
            return "[" + this.i + ", " + this.j + "]";
        }
    }

    static Cell[][] grid = new Cell[5][5];

    static PriorityQueue<Cell> open;
    static int startI, startJ;
    static int goalI, goalJ;
    static boolean closed[][];

    public static void setStartCell(int i, int j) {
        startI = i;
        startJ = j;
    }

    public static void setGoalCell(int i, int j) {
        goalI = i;
        goalJ = j;
    }

    public static void setBlocked(int i, int j) {
        grid[i][j] = null;
    }

    static void costRecalculate(Cell nowCell, Cell t, double cost) {
        if (t == null || closed[t.i][t.j]) return;
        double t_final_cost = t.heuristicCost + cost;

        boolean inOpen = open.contains(t);
        if (!inOpen || t_final_cost < t.finalCost) {
            t.finalCost = t_final_cost;
            t.parent = nowCell;
            if (!inOpen) open.add(t);
        }
    }

    public static void setTotalCost(double cost) {
        totalCost += cost;
    }

    public static double getTotalCost() {
        return totalCost;
    }

    public static void test(int x, int y, int si, int sj, int ei, int ej, int[][] blocked) {
        grid = new Cell[x][y];
        closed = new boolean[x][y];
        open = new PriorityQueue<>((Object o1, Object o2) -> {
            Cell c1 = (Cell) o1;
            Cell c2 = (Cell) o2;
            return c1.finalCost < c2.finalCost ? -1 :
                    c1.finalCost > c2.finalCost ? 1 : 0;
        });

        setStartCell(si, sj);
        setGoalCell(ei, ej);

        for (int i = 0; i < x; ++i) {
            for (int j = 0; j < y; ++j) {
                grid[i][j] = new Cell(i, j);
                double manhaton = Math.abs(i - goalI) + Math.abs(j - goalJ);
                double euclidion = Math.pow((Math.pow((Math.abs(i - goalI)), 2) + Math.pow((Math.abs(j - goalJ)), 2)), 0.5);
                double chebershev = Math.max(Math.abs(i - goalI), +Math.abs(j - goalJ));
                if (heuristicType.equalsIgnoreCase("manhatton")) {
                    grid[i][j].heuristicCost = manhaton;
                } else if (heuristicType.equalsIgnoreCase("euclidion")) {
                    grid[i][j].heuristicCost = euclidion;
                } else {
                    grid[i][j].heuristicCost = chebershev;
                }
            }
        }
        grid[si][sj].finalCost = 0;
        for (int i = 0; i < blocked.length; ++i) {
            setBlocked(blocked[i][0], blocked[i][1]);
        }

        SearchCell();
        int i = 1;
        int k = 1;
        if (closed[goalI][goalJ]) {
            Cell currentCell = grid[goalI][goalJ];
            while (currentCell.parent != null) {
                k++;
                currentCell = currentCell.parent;
            }
            int[][] arr = new int[k][2];
            currentCell = grid[goalI][goalJ];
            arr[0][0] = ei;
            arr[0][1] = ej;
            while (currentCell.parent != null) {
                currentCell = currentCell.parent;
                int a = currentCell.i;
                int b = currentCell.j;
                arr[i][0] = a;
                arr[i][1] = b;
                setTotalCost(currentCell.finalCost);
                if (!(currentCell == grid[startI][startJ])) {
                }
                i++;
            }
            for (int l = 0; l < arr.length - 1; l++) {
                setMovementCost(arr[l][0], arr[l][1], arr[l + 1][0], arr[l + 1][1]);
            }
            setPath(arr);
            System.out.println();
        } else System.out.println("No possible path");
    }

    public static void setMovementCost(int x1, int y1, int x2, int y2) {
        double moveCost = 0;
        if (Math.abs(x2 - x1) > 0 && Math.abs(y2 - y1) > 0) {
            moveCost = diagonalMoveCost;

        } else if ((Math.abs(x2 - x1) > 0 && Math.abs(y2 - y1) == 0) || (Math.abs(x2 - x1) == 0 && Math.abs(y2 - y1) > 0)) {
            moveCost = cellMoveCost;
        }
        movementCost += moveCost;
    }

    public static void SearchCell() {
        open.add(grid[startI][startJ]);
        Cell current;
        while (true) {
            current = open.poll();
            if (current == null) break;
            closed[current.i][current.j] = true;
            if (showVisited) {
                StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
                StdDraw.filledCircle(current.j, grid.length - current.i - 1, .3);
            }
            if (current.equals(grid[goalI][goalJ])) {
                return;
            }

            Cell t;
            if (current.i - 1 >= 0) {
                t = grid[current.i - 1][current.j];
                costRecalculate(current, t, current.finalCost + cellMoveCost);

                if (isDiagonal) {
                    if (current.j - 1 >= 0) {
                        t = grid[current.i - 1][current.j - 1];
                        costRecalculate(current, t, current.finalCost + diagonalMoveCost);
                    }

                    if (current.j + 1 < grid[0].length) {
                        t = grid[current.i - 1][current.j + 1];
                        costRecalculate(current, t, current.finalCost + diagonalMoveCost);
                    }
                }
            }

            if (current.i + 1 < grid.length) {
                t = grid[current.i + 1][current.j];
                costRecalculate(current, t, current.finalCost + cellMoveCost);
                if (isDiagonal) {
                    if (current.j - 1 >= 0) {
                        t = grid[current.i + 1][current.j - 1];
                        costRecalculate(current, t, current.finalCost + diagonalMoveCost);
                    }

                    if (current.j + 1 < grid[0].length) {
                        t = grid[current.i + 1][current.j + 1];
                        costRecalculate(current, t, current.finalCost + diagonalMoveCost);
                    }
                }
            }

            if (current.j - 1 >= 0) {
                t = grid[current.i][current.j - 1];
                costRecalculate(current, t, current.finalCost + cellMoveCost);
            }

            if (current.j + 1 < grid[0].length) {
                t = grid[current.i][current.j + 1];
                costRecalculate(current, t, current.finalCost + cellMoveCost);
            }
        }
    }

    public static double getMovementCost() {
        return movementCost;
    }

    public static void setPath(int[][] a) {
        carr = a;
    }

    public static int[][] getPath() {
        return carr;
    }

}