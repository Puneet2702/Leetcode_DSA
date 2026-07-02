import java.util.*;

class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        int startHealth = health - grid.get(0).get(0);
        if (startHealth <= 0) return false;

        int[][] best = new int[m][n];
        for (int[] row : best) Arrays.fill(row, -1);

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> b[0] - a[0]
        );

        pq.offer(new int[]{startHealth, 0, 0});
        best[0][0] = startHealth;

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int hp = cur[0];
            int r = cur[1];
            int c = cur[2];

            if (r == m - 1 && c == n - 1)
                return true;

            if (hp < best[r][c]) continue;

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                    continue;

                int nextHp = hp - grid.get(nr).get(nc);

                if (nextHp > 0 && nextHp > best[nr][nc]) {
                    best[nr][nc] = nextHp;
                    pq.offer(new int[]{nextHp, nr, nc});
                }
            }
        }

        return false;
    }
}
