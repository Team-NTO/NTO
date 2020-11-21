#include <cstdio>
#include <queue>
using namespace std;

struct forest {
    int d, x, y;
    bool operator < (const forest t) const { return d > t.d; }
};

int n, m;
int a[50][50];
char b[50][50];
int dist[50][50];
priority_queue<forest> q;
const int dx[] = {-1, 0, 1, 0}, dy[] = {0, 1, 0, -1};
const int G = 2500;

bool out(int x, int y) {
    return x < 0 || x >= n || y < 0 || y >= m;
}

void dijkstra() {
    while (!q.empty()) {
        int d = q.top().d, x = q.top().x, y = q.top().y; q.pop();
        if (b[x][y] == 'F') {
            printf("%d %d\n", dist[x][y]/G, dist[x][y]%G);
            return;
        }
        for (int i=0; i<4; i++) {
            int nx = x+dx[i], ny = y+dy[i];
            if (out(nx, ny)) continue;
            int nd = d+a[nx][ny];
            if (dist[nx][ny] > nd) {
                dist[nx][ny] = nd;
                q.push({nd, nx, ny});
            }
        }
    }
}

int main() {
    scanf("%d %d", &n, &m);
    for (int i=0; i<n; i++) scanf("%s", b[i]);
    for (int i=0; i<n; i++) {
        for (int j=0; j<m; j++) {
            dist[i][j] = 1e9;
            if (b[i][j] == 'S') {
                q.push({0, i, j});
                dist[i][j] = 0;
            } else if (b[i][j] == 'g') {
                a[i][j] = G;
                for (int k=0; k<4; k++) {
                    int ni = i+dx[k], nj = j+dy[k];
                    if (!out(ni, nj) && b[ni][nj] == '.') a[ni][nj] = 1;
                }
            }
        }
    }
    dijkstra();
    return 0;
}
