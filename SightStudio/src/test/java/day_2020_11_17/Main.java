package day_2020_11_17;

import java.util.StringTokenizer;

// feature/sdm-2020-11-17
import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// feature/sdm-2020-11-17
public class Main {

    static char map[][];
    static boolean visited[][];
    static int N,M;
    static node start;
    static List<Point> gg;
    static int dr[] = {1,-1,0,0};
    static int dc[] = {0,0,1,-1};

    /**
     * 2020/11/17 백준 1445 일요일 아침의 데이트
     * https://www.acmicpc.net/problem/1956
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];

        gg = new ArrayList<Point>();
        for(int i = 0 ; i < N ; i++) {
            String S = br.readLine();
            for(int j = 0 ; j < M ; j++) {
                map[i][j] = S.charAt(j);
                if(map[i][j] == 'S')
                    start = new node(i,j,0,0);
                if(map[i][j] == 'g')
                    gg.add(new Point(i,j));
            }
        }
        make_b();
        PriorityQueue<node> gogo = new PriorityQueue<node>();
        gogo.add(start);
        visited[start.x][start.y] = true;
        int resultg = 0;
        int resultb = 0;
        gg:while(!gogo.isEmpty()) {
            node now = gogo.poll();
            for(int i = 0 ; i < 4 ; i++) {
                int row = now.x + dr[i];
                int col = now.y + dc[i];
                int g = now.g;
                int b = now.b;
                if(row<0 || col <0 || row>= N || col >= M)
                    continue;
                if(visited[row][col])
                    continue;
                if(map[row][col] == 'F') {
                    resultg = g;
                    resultb = b;
                    break gg;
                }
                if(map[row][col] == 'g')
                    g++;
                if(map[row][col] == 'b')
                    b++;
                visited[row][col] = true;
                gogo.offer(new node(row, col, g, b));
            }

        }
        System.out.println(resultg+" "+resultb);

    }
    static void make_b() {
        for (int i = 0; i < gg.size(); i++) {
            Point now = gg.get(i);
            for (int d = 0; d < 4; d++) {
                int row = now.x + dr[d];
                int col = now.y + dc[d];
                if (row < 0 || col < 0 || row >= N || col >= M)
                    continue;
                if (map[row][col] == '.')
                    map[row][col] = 'b';
            }
        }
    }

    static class node implements Comparable<node>{
        int x;
        int y;
        int g;
        int b;
        public node(int x, int y, int g, int b) {
            this.x = x;
            this.y = y;
            this.g = g;
            this.b = b;
        }
        @Override
        public int compareTo(node o) {
            if(this.g == o.g) { return this.b - o.b; }
            return this.g - o.g;
        }

    }
}