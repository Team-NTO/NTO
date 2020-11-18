import sys
INF = sys.maxsize

def flyod_warshall():
    for k in range(1, N + 1):
        for i in range(1, N + 1):
            if k == i: continue
            for j in range(1, N + 1):
                if k == j: continue
                if graph[i][j] == graph[i][k] + graph[k][j]:
                    check[i][j] = False
                elif graph[i][j] > graph[i][k] + graph[k][j]:
                    print(-1)
                    exit(0)

if __name__ == '__main__':
    N = int(input())
    graph = [[INF] * (N + 1) for _ in range(N + 1)]
    check = [[True] * (N + 1) for _ in range(N + 1)]
    for i in range(1, N + 1):
        graph[i] = [INF] + list(map(int, sys.stdin.readline().split()))
    ans = 0
    flyod_warshall()
    for i in range(1, N + 1):
        for j in range(1, N + 1):
            if check[i][j]: ans += graph[i][j]
    print(ans // 2)