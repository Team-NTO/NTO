#include <iostream>
#define INF 100000
using namespace std;
 
int mat[401][401]; 
int d[401][401]; 
int V, E; 
int a, b, c; 
int Min = INF; 

void setting() {
	for (int i = 1; i <= V; i++) {
		for (int j = 1; j <= V; j++) {
			if (i == j) {
				mat[i][j] = 0;
			}
			else {
				mat[i][j] = INF;
			}
		}
	}
}

void FloydWarshall() {
	for (int i = 1; i <= V; i++) {
		for (int j = 1; j <= V; j++) {
			d[i][j] = mat[i][j];
		}
	}
 
	for (int k = 1; k <= V; k++) { //거처가는 곳의 좌표
		for (int i = 1; i <= V; i++) { //출발하는 곳의 좌표
			for (int j = 1; j <= V; j++) { //도착하는 곳의 좌표
				if (d[i][k] + d[k][j] < d[i][j]) { //k를 거쳐가는 방법이 지금 i->j로 가는 방법보다 빠르면
					d[i][j] = d[i][k] + d[k][j]; //갱신
				}
			}
		}
	}
}
 
int main() {	
	cin >> V >> E;
	setting();
 
	for (int i = 0; i < E; i++) {
		cin >> a >> b >> c;
		mat[a][b] = c;
	}
 
	FloydWarshall();
 
	for (int i = 1; i <= V; i++) {
		for (int j = 1; j <= V; j++) {
			if (i == j) {
				continue;
			}
			else {
				if (d[i][j] + d[j][i] < Min) {
					Min = d[i][j] + d[j][i];
				}
			}
		}
	}
	if (Min == INF) { 
		cout << -1;
	}
	else {
		cout << Min;
	}
}