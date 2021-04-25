package d_2021_04_19;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int [] parents ;
    static int N, M;
    static Queue<Point> input = new LinkedList<>();
    static int answer =0;

    public static class Point{
        int x;
        int y;
        public Point(int r, int c ) {
            this.x= r;
            this.y = c;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        for( int i=0; i<M; i++) {
            input.add(new Point(sc.nextInt(), sc.nextInt()));
        }

        parents = new int[N];
        for( int i=0; i<N; i++) {
            parents[i] =i;
        }

        solution();

        System.out.println(answer);

    }

    private static void solution()	{

        while ( !input.isEmpty()) {
            Point Current = input.poll();
            answer ++;

            int a = Current.x;
            int b = Current.y;

            int pa = getParent(a);
            int pb = getParent(b);

            if( pa == pb )  {
                return;
            }
            union(pa,pb);
        }
        answer = 0 ;
    }
    private static void union(int px, int py) {

        if( px < py ) {
            parents[py] = px;
        } else {
            parents[px] = py;
        }
    }

    private static int getParent(int x) {
        if ( parents[x] == x ) return x;
        return parents[x] = getParent( parents[x]);
    }
}
