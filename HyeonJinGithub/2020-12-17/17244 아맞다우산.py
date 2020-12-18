import sys
from collections import deque
from itertools import permutations

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

def bfs(start, end):
    q = deque([(start[0], start[1], 0)])

    while q:
        x, y, d = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if (nx, ny) == end:
                return d + 1
            if 0 <= nx < M and 0 <= ny < N:
                if board[nx][ny] == '#': continue
                if visited[nx][ny] == visited_value: continue
                q.append((nx, ny, d + 1))
                visited[nx][ny] = visited_value
    return -1

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    board = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(M)]
    sx, sy, ex, ey = 0, 0, 0, 0
    X = []
    for i in range(M):
        for j in range(N):
            if board[i][j] == 'S':
                sx, sy = i, j
            if board[i][j] == 'E':
                ex, ey = i, j
            if board[i][j] == 'X':
                X.append((i, j))
    X_cnt = len(X)

    arr = [(sx, sy), *X, (ex, ey)]
    dist = [[-1] * (X_cnt + 2) for _ in range(X_cnt + 2)]
    visited = [[0] * N for _ in range(M)]
    visited_value = 0

    for i in range(X_cnt + 2):
        for j in range(i + 1, X_cnt + 2):
            visited_value += 1
            d = bfs(arr[i], arr[j])
            dist[i][j] = d
            dist[j][i] = d

    ans = sys.maxsize
    tmp = [x for x in range(1, X_cnt + 1)]
    for y in permutations(tmp):
        route = [0] + list(y) + [X_cnt + 1]
        td = 0
        for i in range(X_cnt + 1):
            td += dist[route[i]][route[i + 1]]
        ans = min(ans, td)
    print(ans)