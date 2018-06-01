// ISURU SAPUMAL (w1583345//2015562 )

import java.util.Scanner;

public class PathFindingOnSquaredGrid {

    public static void show(boolean[][] a, boolean which) {
        int N = a.length;
        StdDraw.setXscale(-1, N);
        StdDraw.setYscale(-1, N);

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                //    JOptionPane.showInputDialog(null, a[i][j], "");
                if (a[i][j] == which) {
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.square(j, N - i - 1, .5);
                    StdDraw.setPenColor(StdDraw.WHITE);
                    StdDraw.filledSquare(j, N - i - 1, .48);
                } else {
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.filledSquare(j, N - i - 1, .5);
                }
            }
    }

    public static void comparePath(boolean[][] a, boolean which, int x1, int y1, int x2, int y2, boolean isDiagonal, boolean visitedcells) {
        show(a, which);
        int m = 0;
        int blockedSize = 0;
        int N = a.length;
        StdDraw.setXscale(-1, N);
        StdDraw.setYscale(-1, N);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (a[i][j] != which) {
                    blockedSize++;
                }

        int[][] blocked = new int[blockedSize][2];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (a[i][j] == which) {
                    if ((j == x1 && i == y1) || (j == x2 && i == y2)) {
                        StdDraw.setPenColor(StdDraw.BLUE);
                        StdDraw.filledCircle(x1, N - y1 - 1, .4);
                        StdDraw.setPenColor(StdDraw.RED);
                        StdDraw.filledCircle(x2, N - y2 - 1, .4);
                        StdDraw.setPenColor(StdDraw.BLACK);
                    } else {
                        // StdDraw.square(j, N - i - 1, .5);
                    }
                } else {// StdDraw.filledSquare(j, N-i-1, .5);
                    blocked[m][0] = i;
                    blocked[m][1] = j;
                    m++;
                }
        StopWatchNano timerFlow2 = new StopWatchNano();
        FindShortestPath as = new FindShortestPath(isDiagonal, "manhatton", visitedcells);
        FindShortestPath.test(N, N, y1, x1, y2, x2, blocked);
        int[][] manhatton = FindShortestPath.getPath();
        StdOut.println("* Elapsed Time for Manhatton Shortest Path From Micro Seconds = " + timerFlow2.elapsedTime());
        for (int j = 1; j < manhatton.length; j++) {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledCircle(x1, N - y1 - 1, .4);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(x2, N - y2 - 1, .4);
            StdDraw.setPenColor(StdDraw.MAGENTA);
            StdDraw.filledCircle(manhatton[j - 1][1], N - manhatton[j - 1][0] - 1, .4);
            StdDraw.line(manhatton[j - 1][1], N - manhatton[j - 1][0] - 1, manhatton[j][1], N - manhatton[j][0] - 1);
        }
        StopWatchNano timerFlow3 = new StopWatchNano();
        FindShortestPath as1 = new FindShortestPath(isDiagonal, "euclidion", visitedcells);
        FindShortestPath.test(N, N, y1, x1, y2, x2, blocked);
        int[][] euclidion = FindShortestPath.getPath();
        StdOut.println("* Elapsed Time for euclidion Shortest Path From Micro Seconds = " + timerFlow3.elapsedTime());
        for (int j = 1; j < euclidion.length; j++) {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledCircle(x1, N - y1 - 1, .4);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(x2, N - y2 - 1, .4);
            StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
            StdDraw.filledCircle(euclidion[j - 1][1], N - euclidion[j - 1][0] - 1, .3);
            StdDraw.line(euclidion[j - 1][1], N - euclidion[j - 1][0] - 1, euclidion[j][1], N - euclidion[j][0] - 1);

        }
        StopWatchNano timerFlow4 = new StopWatchNano();
        FindShortestPath as2 = new FindShortestPath(isDiagonal, "chebershev", visitedcells);
        FindShortestPath.test(N, N, y1, x1, y2, x2, blocked);
        int[][] chebshev = FindShortestPath.getPath();
        StdOut.println("* Elapsed Time for chebyshev Shortest Path From Micro Seconds = " + timerFlow4.elapsedTime());
        for (int j = 1; j < chebshev.length; j++) {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledCircle(x1, N - y1 - 1, .4);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(x2, N - y2 - 1, .4);
            StdDraw.setPenColor(StdDraw.ORANGE);
            StdDraw.filledCircle(chebshev[j - 1][1], N - chebshev[j - 1][0] - 1, .2);
            StdDraw.line(chebshev[j - 1][1], N - chebshev[j - 1][0] - 1, chebshev[j][1], N - chebshev[j][0] - 1);

        }
    }

    public static void manhattonPath(boolean[][] a, boolean which, int x1, int y1, int x2, int y2, boolean isDiagonal, boolean visitedcells) {
        show(a, which);
        int m = 0;
        int blockedSize = 0;
        int N = a.length;
        StdDraw.setXscale(-1, N);
        StdDraw.setYscale(-1, N);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (a[i][j] != which) {
                    blockedSize++;
                }

        int[][] blocked = new int[blockedSize][2];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (a[i][j] == which) {
                    if ((j == x1 && i == y1) || (j == x2 && i == y2)) {
                        StdDraw.setPenColor(StdDraw.BLUE);
                        StdDraw.filledCircle(x1, N - y1 - 1, .4);
                        StdDraw.setPenColor(StdDraw.RED);
                        StdDraw.filledCircle(x2, N - y2 - 1, .4);
                        StdDraw.setPenColor(StdDraw.BLACK);
                    } else {
                        // StdDraw.square(j, N - i - 1, .5);
                    }
                } else {// StdDraw.filledSquare(j, N-i-1, .5);
                    blocked[m][0] = i;
                    blocked[m][1] = j;
                    m++;
                }
        StopWatchNano timerFlow2 = new StopWatchNano();
        FindShortestPath as = new FindShortestPath(isDiagonal, "manhatton", visitedcells);
        FindShortestPath.test(N, N, y1, x1, y2, x2, blocked);
        int[][] manhatton = FindShortestPath.getPath();
        StdOut.println("* Elapsed Time for Manhatton Shortest Path From Micro Seconds = " + timerFlow2.elapsedTime());
        for (int j = 1; j < manhatton.length; j++) {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledCircle(x1, N - y1 - 1, .4);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(x2, N - y2 - 1, .4);
            StdDraw.setPenColor(StdDraw.MAGENTA);
            StdDraw.filledCircle(manhatton[j - 1][1], N - manhatton[j - 1][0] - 1, .3);
            StdDraw.line(manhatton[j - 1][1], N - manhatton[j - 1][0] - 1, manhatton[j][1], N - manhatton[j][0] - 1);
        }
    }

    public static void euclidionPath(boolean[][] a, boolean which, int x1, int y1, int x2, int y2, boolean isDiagonal, boolean visitedcells) {
        show(a, which);

        int m = 0;
        int blockedSize = 0;
        int N = a.length;
        StdDraw.setXscale(-1, N);
        StdDraw.setYscale(-1, N);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (a[i][j] != which) {
                    blockedSize++;
                }

        int[][] blocked = new int[blockedSize][2];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (a[i][j] == which) {
                    if ((j == x1 && i == y1) || (j == x2 && i == y2)) {
                        StdDraw.setPenColor(StdDraw.BLUE);
                        StdDraw.filledCircle(x1, N - y1 - 1, .4);
                        StdDraw.setPenColor(StdDraw.RED);
                        StdDraw.filledCircle(x2, N - y2 - 1, .4);
                        StdDraw.setPenColor(StdDraw.BLACK);
                    } else {
                        //  StdDraw.square(j, N - i - 1, .5);
                    }
                } else {// StdDraw.filledSquare(j, N-i-1, .5);
                    blocked[m][0] = i;
                    blocked[m][1] = j;
                    m++;
                }
        StopWatchNano timerFlow2 = new StopWatchNano();
        FindShortestPath as1 = new FindShortestPath(isDiagonal, "euclidion", visitedcells);
        FindShortestPath.test(N, N, y1, x1, y2, x2, blocked);
        int[][] euclidion = FindShortestPath.getPath();
        StdOut.println("* Elapsed Time for euclidion Shortest Path From Micro Seconds = " + timerFlow2.elapsedTime());
        for (int j = 1; j < euclidion.length; j++) {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledCircle(x1, N - y1 - 1, .4);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(x2, N - y2 - 1, .4);
            StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
            StdDraw.filledCircle(euclidion[j - 1][1], N - euclidion[j - 1][0] - 1, .3);
            StdDraw.line(euclidion[j - 1][1], N - euclidion[j - 1][0] - 1, euclidion[j][1], N - euclidion[j][0] - 1);

        }
    }

    public static void chebyshevPath(boolean[][] a, boolean which, int x1, int y1, int x2, int y2, boolean isDiagonal, boolean visitedcells) {
        show(a, which);
        int m = 0;
        int blockedSize = 0;
        int N = a.length;
        StdDraw.setXscale(-1, N);
        StdDraw.setYscale(-1, N);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (a[i][j] != which) {
                    blockedSize++;
                }

        int[][] blocked = new int[blockedSize][2];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (a[i][j] == which) {
                    if ((j == x1 && i == y1) || (j == x2 && i == y2)) {
                        StdDraw.setPenColor(StdDraw.BLUE);
                        StdDraw.filledCircle(x1, N - y1 - 1, .4);
                        StdDraw.setPenColor(StdDraw.RED);
                        StdDraw.filledCircle(x2, N - y2 - 1, .4);
                        StdDraw.setPenColor(StdDraw.BLACK);
                    } else {
                        // StdDraw.square(j, N - i - 1, .5);
                    }
                } else { //StdDraw.filledSquare(j, N-i-1, .5);
                    blocked[m][0] = i;
                    blocked[m][1] = j;
                    m++;
                }
        StopWatchNano timerFlow2 = new StopWatchNano();
        FindShortestPath as2 = new FindShortestPath(isDiagonal, "chebershev", visitedcells);
        FindShortestPath.test(N, N, y1, x1, y2, x2, blocked);
        int[][] chebshev = FindShortestPath.getPath();
        StdOut.println("* Elapsed Time for chebyshev Shortest Path From Micro Seconds = " + timerFlow2.elapsedTime());
        for (int j = 1; j < chebshev.length; j++) {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledCircle(x1, N - y1 - 1, .4);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(x2, N - y2 - 1, .4);
            StdDraw.setPenColor(StdDraw.ORANGE);
            StdDraw.filledCircle(chebshev[j - 1][1], N - chebshev[j - 1][0] - 1, .3);
            StdDraw.line(chebshev[j - 1][1], N - chebshev[j - 1][0] - 1, chebshev[j][1], N - chebshev[j][0] - 1);

        }
    }

    public static boolean[][] random(int N, double p) {
        boolean[][] a = new boolean[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                a[i][j] = StdRandom.bernoulli(p);
        return a;
    }

    public static void getPathtype(boolean[][] a, boolean which, int x1, int y1, int x2, int y2, boolean isDiagonal, boolean visited) {
        boolean more = true;

        while (more) {
            Scanner type = new Scanner(System.in);
            System.out.println("Manhatton: 1");
            System.out.println("Euclidion: 2");
            System.out.println("Chebyshev: 3");
            System.out.println("Compare Four Paths: 4");
            System.out.println("Enter distance type:");

            int dType = type.nextInt();
            if (dType == 1) {
                Stopwatch totaltimerFlow = new Stopwatch();
                manhattonPath(a, true, x1, y1, x2, y2, isDiagonal, visited);
                StdOut.println("*Total Elapsed time(with drawing time) time in Seconds= " + totaltimerFlow.elapsedTime());
                StdOut.println("*Sum of final costs on path = " + FindShortestPath.getTotalCost());
                StdOut.println("*Total Movement cost = " + FindShortestPath.getMovementCost());
            } else if (dType == 2) {
                Stopwatch totaltimerFlow = new Stopwatch();
                euclidionPath(a, true, x1, y1, x2, y2, isDiagonal, visited);
                StdOut.println("*Total Elapsed time(with drawing time) time in Seconds= " + totaltimerFlow.elapsedTime());
                StdOut.println("*Sum of final costs on path = " + FindShortestPath.getTotalCost());
                StdOut.println("*Total Movement cost = " + FindShortestPath.getMovementCost());
            } else if (dType == 3) {
                Stopwatch totaltimerFlow = new Stopwatch();
                chebyshevPath(a, true, x1, y1, x2, y2, isDiagonal, visited);
                StdOut.println("*Total Elapsed time(with drawing time) time in Seconds= " + totaltimerFlow.elapsedTime());
                StdOut.println("*Sum of final costs on path = " + FindShortestPath.getTotalCost());
                StdOut.println("*Total Movement cost = " + FindShortestPath.getMovementCost());
            } else if (dType == 4) {
                Stopwatch totaltimerFlow = new Stopwatch();
                comparePath(a, true, x1, y1, x2, y2, isDiagonal, false);
                StdOut.println("*Total Elapsed time(with drawing time) time in Seconds= " + totaltimerFlow.elapsedTime());
            } else {
                System.out.println("Invalid Number");
            }

            System.out.println("Do you want Continue (Y)?");

            String cont = type.next();
            if (cont.equalsIgnoreCase("y")) {
                more = true;
            } else {
                more = false;
                Runtime.getRuntime().exit(0);
            }
        }
    }

    public static void main(String[] args) {
        Scanner nsize = new Scanner(System.in);
        System.out.println("Enter grid the size: ");
        int grid = nsize.nextInt();
        boolean isDiagonal = false;
        boolean visitedcells = false;
        boolean[][] randomlyGenMatrix = random(grid, 0.8);
        StdArrayIO.print(randomlyGenMatrix);
        show(randomlyGenMatrix, true);
        System.out.println();

        Scanner in = new Scanner(System.in);
        System.out.println("Enter i for A > ");
        int Ai = in.nextInt();

        System.out.println("Enter j for A > ");
        int Aj = in.nextInt();

        System.out.println("Enter i for B > ");
        int Bi = in.nextInt();

        System.out.println("Enter j for B > ");
        int Bj = in.nextInt();

        System.out.println("Diagonal Movments Allowed?");
        System.out.println("1- Yes");
        System.out.println("2- No");

        int Dm = in.nextInt();
        if (Dm == 1) {
            isDiagonal = true;
        } else if (Dm == 2) {
            isDiagonal = false;
        } else {
            System.out.println("Invalid response");
        }

        System.out.println("Show visited cells( Enter y)? ");
        String vs = in.next();
        if (vs.equalsIgnoreCase("y")) {
            visitedcells = true;
        } else {
            visitedcells = false;
        }
        getPathtype(randomlyGenMatrix, true, Ai, Aj, Bi, Bj, isDiagonal, visitedcells);
    }

}


