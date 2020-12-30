import sys

if __name__ == '__main__':
    N = int(input())
    arr = list(map(int, sys.stdin.readline().split()))

    result = 0
    dp = [[0] * 21 for _ in range(N)]
    # dp[idx번째][현재까지의 수] = 가능한 경우의 수
    dp[0][arr[0]] += 1
    for i in range(1, N - 1):
        for j in range(21):
            if dp[i - 1][j]:
                if j + arr[i] <= 20: dp[i][j + arr[i]] += dp[i - 1][j]
                if j - arr[i] >= 0: dp[i][j - arr[i]] += dp[i - 1][j]
    print(dp[N - 2][arr[N - 1]])