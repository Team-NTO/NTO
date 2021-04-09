import sys
sys.setrecursionlimit(10**6)
dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

def dfs(x, y):
    flag = False
    for k in range(4):
        res = 1
        nx, ny = x + dx[k], y + dy[k]
        if 0 <= nx < N and 0 <= ny < N:
            if board[nx][ny] > board[x][y]:
                flag = True
                if dp[nx][ny] >= 0:
                    dp[x][y] = max(dp[x][y], dp[nx][ny] + res)
                else:
                    res += dfs(nx, ny)
                    dp[x][y] = max(res, dp[x][y])
    if not flag:
        dp[x][y] = 1
        return 1
    return dp[x][y]

if __name__ == '__main__':
    N = int(input())
    board = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    dp = [[-1] * N for _ in range(N)]

    for i in range(N):
        for j in range(N):
            if dp[i][j] == -1:
                dp[i][j] = 1
                dfs(i, j)
    ans = 0
    for i in range(N):
        for j in range(N):
            ans = max(ans, dp[i][j])
    print(ans)
