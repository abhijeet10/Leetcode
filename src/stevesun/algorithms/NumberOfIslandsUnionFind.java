package stevesun.algorithms;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 Example 1:

 11110
 11010
 11000
 00000
 Answer: 1

 Example 2:

 11000
 11000
 00100
 00011
 Answer: 3
 */

public class NumberOfIslandsUnionFind {

    class UnionFind{
        int count;
        int m, n;
        int[] ids;

        public UnionFind(char[][] grid){
            m = grid.length;
            n = grid[0].length;
            ids = new int[m*n];
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    if(grid[i][j] == '1') {
                        count++;
                        ids[i*n+j] = i*n+j;
                    }
                }
            }
        }

        public void union(int i, int j){
            int x = find(ids, i);
            int y = find(ids, j);
            if(x != y) {//note: this is when x != y, only in this case, we should union these two nodes, which makes sense naturally.
                count--;
                ids[x] = y;
            }
        }

        public int find(int[] ids, int i){
            if(ids[i] == i) return i;
            return find(ids, ids[i]);
        }
    }

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int[] dirs = new int[]{0,1,0,-1,0};
        UnionFind uf = new UnionFind(grid);
        int m = grid.length, n = grid[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    for(int k = 0; k < 4; k++){
                        int x = i+dirs[k];
                        int y = j+dirs[k+1];
                        if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1'){
                            int id1 = i*n+j;
                            int id2 = x*n+y;
                            uf.union(id1, id2);
                        }
                    }
                }
            }
        }
        return uf.count;
    }
}
