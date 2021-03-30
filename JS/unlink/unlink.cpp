#include <stdio.h>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

#define INF 99999999
int N, M;
vector <pair<int, int>> adj[1001];
vector <int> parent;
int b_city1 = 0, b_city2 = 0;
bool first = true;

bool unlink(int a, int b) {
	if (b_city1 == a && b_city2 == b) return true;
	else if (b_city1 == b && b_city2 == 1) return true;
	return false;
}

int dijkstra() {
	priority_queue <pair<int, int>> qu;
	vector <int> dist(N + 1, INF);
	dist[1] = 0;
	qu.push({ 0,1 });
	while(!qu.empty())
	{
		int cost = -qu.top().first;
		int here = qu.top().second;
		
		qu.pop();
		if(cost > dist[here]) continue;
		for (int i = 0; i < adj[here].size(); i++){
			int next = adj[here][i].first;
			if(unlink(here, next)) continue;
			int next_cost = adj[here][i].second + cost;
			if (next_cost < dist[next]){
				if(first) parent[next] = here;
				dist[next] = next_cost;
				qu.push({ -next_cost, next });
			}
		}
	}
	return dist[N];
}

int main() {
	scanf("%d %d", &N, &M);
	parent.resize(N+1, -1);
	for(int i = 0; i<M; i++){
		int a, b, t;
		scanf("%d %d %d",&a,&b,&t);
		adj[a].push_back({ b,t });
		adj[b].push_back({ a,t });
	}
	parent[1] = 1;
	int time = dijkstra();
	first = false;
	
	int max_time = 0;
	for (int p = N; p!=parent[p]; p=parent[p]){
		b_city1=p;
		b_city2=parent[p];
		max_time=max(max_time, dijkstra());
		if(max_time == INF) break;
	}
	if(max_time == INF) printf("-1");
	else printf("%d", max_time - time);
	
	return 0;
}