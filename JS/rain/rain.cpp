#include <iostream>
#include <string> 
#include <queue> 
#include <map> 

using namespace std; 

const int MAX = 50; 
const int MAX_STUFF = 5; 
typedef struct 
{ 
	int y, x; 
}Dir; 

Dir moveDir[4] = { { 1, 0 },{ -1, 0 },{ 0, 1 },{ 0, -1 } }; 

typedef struct 
{ 
	int y, x; 
	int time; 
	int checked; 
}State; 

int N, M; 
string house[MAX]; 
bool visited[MAX][MAX][1 << MAX_STUFF]; 

int main() 
{ 
	ios_base::sync_with_stdio(0); 
	cin.tie(0); 
	cin >> N >> M; 
	pair<int, int> start, end; 
	
	int idx = 0; 
	for (int i = 0; i < M; i++) 
	{ 
		cin >> house[i]; 
		for (int j = 0; j < N; j++) 
		{ 
			switch (house[i][j]) 
			{ 
				case 'S': 
					start = { i, j }; 
					break; 
				case 'E': 
					end = { i, j }; 
					break; 
				case 'X': 
					house[i][j] = idx + '0'; 
					idx++; 
					break; 
			} 
		} 
	} 
	
	queue<State> q; 
	q.push({ start.first, start.second, 0, 0 }); 
	visited[start.first][start.second][0] = true; 
	while (!q.empty()) 
	{ 
		int y = q.front().y; 
		int x = q.front().x; 
		int time = q.front().time; 
		int checked = q.front().checked; 
		q.pop(); 
		if (house[y][x] == 'E' && checked == (1 << idx) - 1) 
		{ 
			cout << time << "\n"; 
			return 0; 
		} 
		
		for (int k = 0; k < 4; k++) 
		{ 
			int nextY = y + moveDir[k].y; 
			int nextX = x + moveDir[k].x; 
			if (nextY < 0 || nextY >= M || nextX < 0 || nextX >= N) 
			{ continue; } 
			if (house[nextY][nextX] == '#') 
			{ continue; } 
			
			int nextChecked = checked | (1 << (house[nextY][nextX] - '0')); 
			if (house[nextY][nextX] >= '0' && house[nextY][nextX] < '5' && visited[nextY][nextX][nextChecked] == false) 
			{ 
				visited[nextY][nextX][nextChecked] = true; 
				q.push({ nextY, nextX, time + 1, nextChecked }); 
				continue; 
			} 
			if (visited[nextY][nextX][checked] == false) 
			{ 
				visited[nextY][nextX][checked] = true; 
				q.push({ nextY, nextX, time + 1, checked }); 
			} 
		} 
	} 
	return 0; 
}
