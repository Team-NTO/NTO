import sys
from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

def bfs(x, y):
    q = deque([(x, y, 4, 0, 0)])
    cnt = 0
    while q:
        size = len(q)
        for _ in range(size):
            x, y, d, c, k = q.popleft()
            if c == 1 and k == 1:
                return cnt
            for i in range(4):
                if d == i: continue
                nx, ny = x + dx[i], y + dy[i]
                nc, nk = c, k
                if 0 <= nx < N and 0 <= ny < M:
                    if check[nx][ny][i][c][k]: continue
                    if board[nx][ny] == '#': continue
                    if board[nx][ny] == 'C': nc = 1
                    if board[nx][ny] == 'K': nk = 1
                    check[nx][ny][i][nc][nk] = True
                    q.append((nx, ny, i, nc, nk))
        cnt += 1
    return -1

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    board = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(N)]
    check = [[[[[False] * 2 for _ in range(2)] for _ in range(5)] for _ in range(M)] for _ in range(N)]
    flag = False
    for i in range(N):
        for j in range(M):
            if board[i][j] == 'S':
                sx, sy = i, j
                check[sx][sy][4][0][0] = True
            if not flag and board[i][j] == 'C':
                flag = True
                board[i][j] = 'K'
    print(bfs(sx, sy))

