import sys
INF = sys.maxsize

def floyd_warshall():
    # k : 중간에 거쳐가는 노드
    for k in range(1, V + 1):
        # i : 출발 노드
        for i in range(1, V + 1):
            if i == k: continue
            # j : 도착 노드
            for j in range(1, V + 1):
                if k == j: continue
                if graph[i][j] > graph[i][k] + graph[k][j]:
                    graph[i][j] = graph[i][k] + graph[k][j]
    mn = INF
    for i in range(1, V + 1):
        mn = min(mn, graph[i][i])
    print(mn if mn != INF else -1)

if __name__ == '__main__':
    V, E = map(int, sys.stdin.readline().split())
    graph = [[INF] * (V + 1) for _ in range(V + 1)]

    for _ in range(E):
        a, b, c = map(int, sys.stdin.readline().split())
        graph[a][b] = c
    floyd_warshall()