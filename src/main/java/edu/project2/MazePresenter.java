package edu.project2;

import java.util.List;
import java.util.Scanner;

public final class MazePresenter {

    private MazePresenter() {
    }

    private final static Scanner SCANNER = new Scanner(System.in);
    private final static Renderer RENDERER = new SimpleCharRenderer();

    @SuppressWarnings({"checkstyle:UncommentedMain", "checkstyle:RegexpSinglelineJava"})
    public static void main(String[] args) {
        System.out.println("Select maze generator: \n1 - Common perfect maze\n2 - Strange cellular maze");
        int answer = 0;
        while (answer != 1 && answer != 2) {
            answer = SCANNER.nextInt();
        }

        MazeGenerator mazeGenerator = new PrimGenerator();
        if (answer == 2) {
            mazeGenerator = new CellularAutomata();
        }

        System.out.println("Select size of maze (height width)");
        int height = SCANNER.nextInt();
        int width = SCANNER.nextInt();

        Maze maze = mazeGenerator.generate(height, width);

        System.out.println(RENDERER.render(maze));

        System.out.println("Select maze solver: \n1 - Depth search\n2 - Breadth search");
        answer = 0;
        while (answer != 1 && answer != 2) {
            answer = SCANNER.nextInt();
        }

        Solver solver = new DepthFirstSearch();
        if (answer == 2) {
            solver = new BreadthFirstSearch();
        }

        while (true) {
            System.out.println(
                "Enter start and end cord (start_x start_y end_x end_y\nEnter negative start cord to exit");

            int startX = SCANNER.nextInt();
            if (startX < 0) {
                break;
            }
            int startY = SCANNER.nextInt();
            int endX = SCANNER.nextInt();
            int endY = SCANNER.nextInt();

            List<Coordinate> solution = solver.solve(maze, new Coordinate(startX, startY), new Coordinate(endX, endY));

            System.out.println(RENDERER.render(maze, solution));
        }
    }
}
