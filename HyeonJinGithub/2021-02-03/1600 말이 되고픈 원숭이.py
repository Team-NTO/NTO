import sys
from collections import deque

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]
mx = [-1, -2, -2, -1, 1, 2, 2, 1]
my = [-2, -1, 1, 2, 2, 1, -1, -2]

def bfs(x, y, z):
    q = deque([(x, y, z)])
    dist = [[[-1] * (K + 1) for _ in range(W)] for _ in range(H)]
    dist[x][y][z] = 0

    while q:
        x, y, z = q.popleft()
        if z < K:
            for k in range(8):
                nx, ny, nz = x + mx[k], y + my[k], z + 1
                if 0 <= nx < H and 0 <= ny < W:
                    if board[nx][ny] == 0 and dist[nx][ny][nz] == -1:
                        dist[nx][ny][nz] = dist[x][y][z] + 1
                        q.append((nx, ny, nz))
        for k in range(4):
            nx, ny, nz = x + dx[k], y + dy[k], z
            if 0 <= nx < H and 0 <= ny < W:
                if board[nx][ny] == 0 and dist[nx][ny][nz] == -1:
                    dist[nx][ny][nz] = dist[x][y][z] + 1
                    q.append((nx, ny, nz))
    ans = sys.maxsize
    for k in range(K + 1):
        if dist[H - 1][W - 1][k] > -1:
            ans = min(ans, dist[H - 1][W - 1][k])
    return ans if ans != sys.maxsize else -1

if __name__ == '__main__':
    K = int(input())
    W, H = map(int, sys.stdin.readline().split())
    board = [list(map(int, sys.stdin.readline().split())) for _ in range(H)]
    print(bfs(0, 0, 0))