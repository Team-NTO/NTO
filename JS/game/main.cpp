#include <cstdio>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int N;
int cost[501];
int ans[501];
int indegree[501];
vector<int> vec[501];

void bfs(){
    queue<int> q;

    for (int n = 1; n <= N; n++){
        if (indegree[n] == 0){
            q.push(n);
            ans[n] = cost[n];
        }
    }

    while (!q.empty()){
        int nedge = q.front();
        q.pop();

        for (int m = 0; m < vec[nedge].size(); m++){
            int e = vec[nedge][m];
            ans[e] = max(ans[e], ans[nedge] + cost[e]);
            if (--indegree[e] == 0)
                q.push(e);
        }
    }
}

int main(){
    scanf("%d", &N);
    for (int n = 1; n <= N; n++){
        int edge;
        scanf("%d", &cost[n]);
        while (scanf("%d", &edge), edge != -1){
            vec[edge].push_back(n);
            indegree[n]++;
        }
    }

    bfs();

    for (int n = 1; n <= N; n++)
        printf("%d\n", ans[n]);

    return 0;
}