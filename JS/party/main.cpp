#include <vector>
#include <queue>
#include<iostream>
#include<algorithm>

using namespace std;
int N, M, X, a, b, c;
int mm = 0;
vector<pair<int, int> > v[1001];
vector<pair<int, int> > revV[1001];
priority_queue<pair<int, int> > pq;
bool visited[1001], visited2[1001];
int dis[1001], revDis[1001];
int INF = 1000000000;

void dijkstra1() {
    dis[X] = 0;
    pq.push(make_pair(0, X));
    while (!pq.empty()) {
        int current = pq.top().second;
        int value = -pq.top().first;
        pq.pop();
        if (visited[current] == false) {
            visited[current] = true;
            for (int i = 0; i < v[current].size(); i++) {
                if (dis[v[current][i].first] > value + v[current][i].second) {
                    dis[v[current][i].first] = value + v[current][i].second;
                    pq.push(make_pair(-dis[v[current][i].first], v[current][i].first));
                }
            }
        }
    }
}

void dijkstra2() {
    revDis[X] = 0;
    pq.push(make_pair(0, X));
    while (!pq.empty()) {
        int current = pq.top().second;
        int value = -pq.top().first;
        pq.pop();
        if (visited2[current] == false) {
            visited2[current] = true;
            for (int i = 0; i < revV[current].size(); i++) {
                if (revDis[revV[current][i].first] > value + revV[current][i].second) {
                    revDis[revV[current][i].first] = value + revV[current][i].second;
                    pq.push(make_pair(-revDis[revV[current][i].first], revV[current][i].first));
                }
            }
        }

    }
}

int main() {
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> M >> X;
    for (int i = 1; i <= N; i++) {
        dis[i] = INF;
        revDis[i] = INF;
    }
    for (int i = 0; i < M; i++) {
        cin >> a >> b >> c;
        v[a].push_back(make_pair(b, c));
        revV[b].push_back(make_pair(a, c));
    }
    dijkstra1(); //집으로 돌아가는데 최단거리 dis[ ]
    dijkstra2(); //X로 가는데 최단거리 disRev[ ]
    for (int i = 1; i <= N; i++) {
        mm = max(mm, dis[i] + revDis[i]);
    }
    cout << mm;

}