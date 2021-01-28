import sys
from itertools import combinations
from collections import deque

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

def bfs(chicken):
    q = deque(chicken)
    for t in chicken:
        dist[t[0]][t[1]] = 0
    tmp = 0
    while q:
        size = len(q)
        for _ in range(size):
            x, y = q.popleft()
            for k in range(4):
                nx, ny = x + dx[k], y + dy[k]
                if 0 <= nx < N and 0 <= ny < N:
                    if tmp_board[nx][ny] != 2 and dist[nx][ny] == -1:
                        q.append((nx, ny))
                        dist[nx][ny] = dist[x][y] + 1
    for i in range(N):
        for j in range(N):
            if tmp_board[i][j] == 1:
                tmp += dist[i][j]
    return tmp

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    board = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    chicken = []
    ans = sys.maxsize
    tmp_board = [[0] * N for _ in range(N)]
    for i in range(N):
        for j in range(N):
            if board[i][j] == 2: chicken.append((i, j))
            if board[i][j] == 1: tmp_board[i][j] = 1
    for c in combinations(chicken, M):
        dist = [[-1] * N for _ in range(N)]
        ans = min(ans, bfs(list(c)))
    print(ans)