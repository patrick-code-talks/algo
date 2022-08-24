import java.util.Arrays;

public class Avg {
    public static void main(String[] args) {
//        int[][] imgs = new int[][] {
//                {3, 0, 2, 5},
//                {1, 2, 3, 4},
//                {2, 3, 2, 3}
//        };
//        int[][] imgs = new int[][] {
//                {3, 0, 2, 5},
//        };
        int[][] imgs = new int[][] {
                {3},
        };

        Avg test1 = new Avg();
        double[][] test = test1.solution(imgs);

        for (double[] d : test) {
            System.out.println(Arrays.toString(d));
        }
    }

    double[][] solution(int[][] images) {
        if (images == null || images.length == 0) {
            return new double[0][0];
        }

        double[][] result = new double[images.length][images[0].length];

        for (int i = 0; i < images.length; i++) {
            for (int j = 0; j < images[i].length; j++) {
                double avg = findAvg(images, i, j);
                result[i][j] = avg;
            }
        }

        return result;
    }

    private double findAvg(int[][] image, int row, int col) {
        double num = 0;
        double sum = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (isValidPosition(image, i, j, row, col)) {
                    sum += (double)(image[i][j]);
                    num += 1.0;
                }
            }
        }
        if (num == 0) {
            return 0.0;
        }
        return sum / num;
    }

    private boolean isValidPosition(int[][] image, int x, int y, int row, int col) {
        if (x < 0 || x >= image.length) {
            return false;
        }

        if (y < 0 || y >= image[0].length) {
            return false;
        }

        if (x == row && y == col) {
            return false;
        }

        return true;
    }
}
