import sys
import heapq
INF = sys.maxsize
G = 5000
dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

def dijkstra(q):
    while q:
        d, x, y = heapq.heappop(q)
        if board[x][y] == 'F':
            print(dist[x][y] // G, dist[x][y] % G)
            return
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < M:
                nd = d + a[nx][ny]
                if nd < dist[nx][ny]:
                    dist[nx][ny] = nd
                    heapq.heappush(q, (nd, nx, ny))

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    board = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(N)]
    a = [[0] * M for _ in range(N)]
    dist = [[INF] * M for _ in range(N)]
    q = []

    for i in range(N):
        for j in range(M):
            if board[i][j] == 'S':
                dist[i][j] = 0
                heapq.heappush(q, (0, i, j))
            elif board[i][j] == 'g':
                a[i][j] = G
                for k in range(4):
                    ni, nj = i + dx[k], j + dy[k]
                    if 0 <= ni < N and 0 <= nj < M:
                        if board[ni][nj] == '.':
                            a[ni][nj] = 1
    dijkstra(q)