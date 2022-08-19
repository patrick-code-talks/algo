import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class PaintLego {
    public static void main(String[] args) {

        int[] bricks = new int[] {
                1, 3, 3,
        };

        int[][] testCases = new int[][] {
                // negative case
                {1, 1, 1, 1, 1},
                // positive case
                {1, 2, 3},
                {1, 2, 2},
                {1, 2, 2, 1},
                {1, 2, 2, 2, 1}
        };



        PaintLego paintLego = new PaintLego();
//        int result = paintLego.minCost(bricks);

        for (int[] test : testCases) {
            int result = paintLego.minCost(test);
            System.out.println("result = " + result);
        }

    }

    /**
     *
     * @param bricks bricks[i] represents the color
     * @return
     */
    int UPPER_COLOR = 1000;
    int TAG = -1;
    Set<Integer> colors;
    public int minCost(int[] bricks) {
        int[][] costs = generateCosts(bricks);

        int result = getMinCost(bricks, costs);

        return result;
    }

    private int[][] generateCosts(int[] bricks) {

        // costs[i][j]表示第i块砖染成第j个颜色的cost
        int[][] costs = new int[bricks.length][UPPER_COLOR + 1];

        colors = Arrays.stream(bricks).boxed().collect(Collectors.toSet());

        for (int i = 0; i < costs.length; i++) {
            for (int j = 0; j < costs[i].length; j++) {
                if (colors.contains(j)) {
                    costs[i][j] = Math.abs(j - bricks[i]);
                } else {
                    costs[i][j] = TAG;
                }
            }
        }

        return costs;
    }

    private int getMinCost(int[] bricks, int[][] costs) {
        // 第i块砖使用第j个颜色涂色的最小开销
        int[][] totalMin = new int[bricks.length][UPPER_COLOR + 1];
        for (int col = 1; col < totalMin[0].length; col++) {
            int min = Integer.MAX_VALUE;
            // 第0块砖，可以使用第col的颜色上色
            if (costs[0][col] != TAG) {
                min = Math.min(min, costs[0][col]);
                totalMin[0][col] = min;
            } else {
                // 第col个颜色不可用
                totalMin[0][col] = TAG;
            }
        }

        for (int row = 1; row < totalMin.length; row++) {
            int[] preTotal = totalMin[row - 1];
            int minColor = -1, secondMinColor = -1;

            for (int j = 1; j < preTotal.length; j++) {
                if (preTotal[j] == TAG) continue;

                int cost = preTotal[j];

                if (minColor == -1 || cost < preTotal[minColor]) {
                    secondMinColor = minColor;
                    minColor = j;
                } else if (secondMinColor == -1 || cost < preTotal[secondMinColor]) {
                    secondMinColor = j;
                }
            }


            for (int j = 1; j < preTotal.length; j++) {
                if (preTotal[j] == TAG) {
                    totalMin[row][j] = TAG;
                    continue;
                }

                if (j == minColor) {
                    if (secondMinColor == -1) {
                        totalMin[row][j] = -1;
                    } else {
                        totalMin[row][j] = preTotal[secondMinColor] + costs[row][j];
                    }
                } else {
                    totalMin[row][j] = preTotal[minColor] + costs[row][j];
                }

            }
        }

        int result = Integer.MAX_VALUE;
        int lastRow = totalMin.length - 1;

        for (int i = 1; i < totalMin[0].length; i++) {
            if (totalMin[lastRow][i] != TAG) {
                result = Math.min(result, totalMin[lastRow][i]);
            }
        }

        if (result == Integer.MAX_VALUE) {
            return -1;
        }
        return result;
    }
}
