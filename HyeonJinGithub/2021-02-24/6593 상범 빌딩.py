import sys
from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]
dz = [1, -1]

def bfs(h, x, y):
    dist = [[[-1] * C for _ in range(R)] for _ in range(L)]
    q = deque([(h, x, y)])
    dist[h][x][y] = 0
    while q:
        h, x, y = q.popleft()
        if h == eh and x == ex and y == ey: return dist[h][x][y]
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < R and 0 <= ny < C:
                if board[h][nx][ny] == '.' and dist[h][nx][ny] == -1:
                    dist[h][nx][ny] = dist[h][x][y] + 1
                    q.append((h, nx, ny))
        for k in range(2):
            nh = h + dz[k]
            if 0 <= nh < L:
                if board[nh][x][y] == '.' and dist[nh][x][y] == -1:
                    dist[nh][x][y] = dist[h][x][y] + 1
                    q.append((nh, x, y))
    return -1

if __name__ == '__main__':
    while True:
        L, R, C = map(int, sys.stdin.readline().split())
        if not L and not R and not C: break
        board = [[[0] * C for _ in range(R)] for _ in range(L)]
        for i in range(L):
            for j in range(R):
                board[i][j] = list(map(str, sys.stdin.readline().rstrip()))
            input()
        for h in range(L):
            for i in range(R):
                for j in range(C):
                    if board[h][i][j] == 'S':
                        sh, sx, sy = h, i, j
                        board[h][i][j] = '.'
                    if board[h][i][j] == 'E':
                        eh, ex, ey = h, i, j
                        board[h][i][j] = '.'
        ans = bfs(sh, sx, sy)
        print(f'Escaped in {ans} minute(s).' if ans != -1 else 'Trapped!')

