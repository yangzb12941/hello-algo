/**
 * File: climbing_stairs_dfs_mem.java
 * Created Time: 2023-06-30
 * Author: krahets (krahets@163.com)
 */

package chapter_dynamic_programming;

import java.util.Arrays;

public class climbing_stairs_dfs_mem {
    /* 记忆化搜索 */
    public static long dfs(int i, long[] mem) {
        // 已知 dp[1] 和 dp[2] ，返回之
        if (i == 1 || i == 2)
            return i;
        // 若存在记录 dp[i] ，则直接返回之
        if (mem[i] != -1)
            return mem[i];
        // dp[i] = dp[i-1] + dp[i-2]
        long count = dfs(i - 1, mem) + dfs(i - 2, mem);
        // 记录 dp[i]
        mem[i] = count;
        return count;
    }

    /* 爬楼梯：记忆化搜索 */
    public static long climbingStairsDFSMem(int n) {
        // mem[i] 记录爬到第 i 阶的方案总数，-1 代表无记录
        long[] mem = new long[n + 1];
        Arrays.fill(mem, -1);
        mem[0] = 0;
        mem[1] = 1;
        mem[2] = 2;
        long dfs = dfs(n, mem);
        System.out.println("每一阶楼梯"+ Arrays.toString(mem));
        return dfs;
    }

    public static void main(String[] args) {
        int n = 32;

        long res = climbingStairsDFSMem(n);
        System.out.println(String.format("爬 %d 阶楼梯共有 %d 种方案", n, res));
    }
}