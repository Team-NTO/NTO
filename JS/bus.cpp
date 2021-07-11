#include<cstdio>
#include<vector>
#include<queue>
using namespace std;
struct p {
    int s, e, n;
};
struct b {
    int x1, y1, x2, y2;
};
int M, N, K, visit[5001], sx, sy, dx, dy;
vector<p> ho[100001], ve[100001];
b bus[5001];
queue<int> q;
int bfs() {
    for (p v : ve[sx]) {
        if (sy >= v.s && sy <= v.e) {
            visit[v.n] = 1;
            q.push(v.n);
        }
    }
    for (p v : ho[sy]) {
        if (sx >= v.s && sx <= v.e) {
            visit[v.n] = 1;
            q.push(v.n);
        }
    }
    while (!q.empty()) {
        int t = q.front(); q.pop();
        int x1 = bus[t].x1, y1 = bus[t].y1;
        int x2 = bus[t].x2, y2 = bus[t].y2;
        if (y1 == y2) {
            if (y1 == dy && dx >= x1 && dx <= x2) return visit[t];
            for (int x = x1; x <= x2; x++)
                for (p v : ve[x]) {
                    if (!visit[v.n] && y1 >= v.s && y1 <= v.e) {
                        visit[v.n] = visit[t] + 1;
                        q.push(v.n);
                    }
                }
            for (p v : ho[y1]) {
                if (!visit[v.n] && !(x1 > v.e || x2 < v.s)) {
                    visit[v.n] = visit[t] + 1;
                    q.push(v.n);
                }
            }
        }
        else{
            if (x1 == dx && dy >= y1 && dy <= y2) return visit[t];
            for (int y = y1; y <= y2; y++)
                for (p v : ho[y]) {
                    if (!visit[v.n] && x1 >= v.s && x1 <= v.e) {
                        visit[v.n] = visit[t] + 1;
                        q.push(v.n);
                    }
                }
            for (p v : ve[x1]) {
                if (!visit[v.n] && !(y1 > v.e || y2 < v.s)) {
                    visit[v.n] = visit[t] + 1;
                    q.push(v.n);
                }
            }
        }
    }
}
int main() {
    scanf("%d%d%d", &M, &N, &K);
    for (int i = 0, b, x1, y1, x2, y2; i < K; i++) {
        scanf("%d%d%d%d%d", &b, &x1, &y1, &x2, &y2);
        if (y1 > y2) swap(y1, y2);
        if (x1 > x2) swap(x1, x2);
        if (x1 == x2) ve[x1].push_back({ y1, y2, b });
        else if (y1 == y2) ho[y1].push_back({ x1, x2, b });
        bus[b] = { x1, y1, x2, y2 };
    }
    scanf("%d%d%d%d", &sx, &sy, &dx, &dy);
    printf("%d",bfs());
    return 0;
}
