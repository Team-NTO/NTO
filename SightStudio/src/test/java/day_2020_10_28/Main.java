package day_2020_10_28;

import java.util.*;


//feature/sdm-2020-10-28
public class Main {

    // https://www.acmicpc.net/problem/1941
    static char[][] map;
    static boolean[][] check;
    static boolean[][] visited;
    static boolean[] visit;
    static int count = 0;
    static int tmp;

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        map=new char[5][5];

        for(int i=0; i<5; i++) {
            map[i]=sc.next().toCharArray();
        }

        for(int i=0; i<25; i++) {
            visit=new boolean[25];
            check=new boolean[5][5];

            //현재 숫자 및 위치 체크해주기
            visit[i]=true;
            check[i/5][i%5]=true;

            //S이면 1추가 아니면 0
            if(map[i/5][i%5]=='S') {
                BACK(i,1,1); //현재 위치, 숫자 몇개 뽑았나, s의 갯수
            }else {
                BACK(i,1,0);
            }
        }
        System.out.println(count);
    }

    private static void BACK(int v, int cnt, int s) {
        if(s>=4 && cnt==7) {
            tmp=0;
            visited=new boolean[5][5];
            loop: for(int i=0; i<5; i++) { //DFS돌면서 7개의 숫자가 인접해 있나 확인.
                for(int j=0; j<5; j++) {
                    if(check[i][j] && !visited[i][j]) {
                        visited[i][j]=true;
                        tmp=1;
                        DFS(i,j);
                        break loop;
                    }
                }
            }
            return;
        }
        for(int i=v+1; i<25; i++) {
            if(visit[i]==false) {
                visit[i]=true;
                check[i/5][i%5]=true;

                if(map[i/5][i%5]=='S') {
                    BACK(i,cnt+1,s+1); //현재 위치, 숫자 몇개 뽑았나, s의 갯수
                }else {
                    BACK(i,cnt+1,s);
                }

                visit[i]=false;
                check[i/5][i%5]=false;
            }
        }
    }
    static int[] dx= {-1,0,1,0};
    static int[] dy= {0,-1,0,1};
    private static void DFS(int x, int y) {
        if(tmp==7) {
            count=count+1;
        }else {
            for(int i=0; i<4; i++) {
                int xx=dx[i]+x;
                int yy=dy[i]+y;
                if(xx>=0 && yy>=0 && xx<5 && yy<5) {
                    if(check[xx][yy] && !visited[xx][yy]) {
                        visited[xx][yy]=true;
                        tmp=tmp+1;
                        DFS(xx,yy);
                    }
                }
            }
        }
    }
}