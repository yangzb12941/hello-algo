/**
 * File: climbing_stairs_constraint_dp.java
 * Created Time: 2023-07-01
 * Author: krahets (krahets@163.com)
 */

package chapter_dynamic_programming;

public class climbing_stairs_constraint_dp {
    /* 带约束爬楼梯：动态规划 */
    static int climbingStairsConstraintDP(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        // 初始化 dp 表，用于存储子问题的解
        int[][] dp = new int[n + 1][3];
        // 初始状态：预设最小子问题的解
        dp[1][1] = 1;//当前第一阶，前一轮是走1步上来的，只有一种情况
        dp[1][2] = 0;//当前第一阶，前一轮是走2步上来的，只有0种情况
        dp[2][1] = 0;//当前第2阶，前一轮是走1步上来的，只有0种情况
        dp[2][2] = 1;//当前第2阶，前一轮是走2步上来的，只有1种情况
        // 状态转移：从较小子问题逐步求解较大子问题
        for (int i = 3; i <= n; i++) {
            dp[i][1] = dp[i - 1][2];
            dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
        }
        return dp[n][1] + dp[n][2];
    }

    // 给定一个共有n阶的楼梯，你每步可以上1阶或者 2阶，但不能连续两轮跳1阶，请问有多少种方案可以爬到楼顶？
    public static void main(String[] args) {
        int n = 9;

        int res = climbingStairsConstraintDP(n);
        System.out.println(String.format("爬 %d 阶楼梯共有 %d 种方案", n, res));
    }
}
