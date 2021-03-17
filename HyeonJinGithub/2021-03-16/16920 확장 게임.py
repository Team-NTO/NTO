import sys
from _collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

def bfs(player):
    for _ in range(S[player]):
        size = len(Q[player])
        for _ in range(size):
            x, y = Q[player].popleft()
            for k in range(4):
                nx, ny = x + dx[k], y + dy[k]
                if 0 <= nx < N and 0 <= ny < M:
                    if tmp_board[nx][ny] == -1 and board[nx][ny] != '#':
                        Q[player].append((nx, ny))
                        ans[player] += 1
                        tmp_board[nx][ny] = i
        if not Q[player]:
            end[player] = 1
            break

if __name__ == '__main__':
    N, M, P = map(int, sys.stdin.readline().split())
    S = list(map(int, sys.stdin.readline().split()))
    board = [sys.stdin.readline().rstrip() for _ in range(N)]
    tmp_board = [[-1] * M for _ in range(N)]
    ans = [0] * P
    Q = [deque() for _ in range(P)]

    for i in range(N):
        for j in range(M):
            if board[i][j].isdigit():
                num = int(board[i][j]) - 1
                tmp_board[i][j] = num
                Q[num].append((i, j))
                ans[num] += 1
    end = [0] * P
    while True:
        if sum(end) == P: break
        for i in range(P):
            if end[i]: continue
            bfs(i)
    print(*ans)