package com.company;

public class Main {

    public static void main(String[] args) {
        // write your code here
    }
}

class Solution {
    private static final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int orangesRotting(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;
        int n = grid.length, m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> list = new LinkedList<>();

        int fresh = 0, time = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    list.offer(new int[]{i, j});
                    visited[i][j] = true;
                    continue;
                }

                if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        if (list.isEmpty())
            return fresh == 0 ? 0 : -1;

        while (!list.isEmpty()) {
            int size = list.size();
            time++;
            for (int s = 0; s < size; s++) {
                int[] curr = list.poll();
                for (int i = 0; i < 4; i++) {
                    int nextR = curr[0] + dir[i][0], nextC = curr[1] + dir[i][1];
                    if (isValid(grid, nextR, nextC, visited)) {
                        fresh--;
                        visited[nextR][nextC] = true;
                        list.offer(new int[]{nextR, nextC});
                    }
                }
            }
        }

        return fresh == 0 ? time : -1;
    }

    private boolean isValid(int[][] grid, int r, int c, boolean[][] visited) {
        int n = grid.length, m = grid[0].length;
        return r >= 0 && c >= 0 && r < n && c < m && !visited[r][c] && grid[r][c] == 1;
    }
}