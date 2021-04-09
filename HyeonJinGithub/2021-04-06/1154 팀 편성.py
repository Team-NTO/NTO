import sys
sys.setrecursionlimit(10**6)

def dfs(u, p):
    for v in range(1, N + 1):
        if v == u or v == p: continue
        if arr[u][v]: continue
        if team[v]:
            if team[u] == team[v]: return False
            continue
        team[v] = 3 - team[u]
        if not dfs(v, u): return False
    return True

if __name__ == '__main__':
    N = int(input())
    arr = [[False] * (N + 1) for _ in range(N + 1)]
    team = [0] * (N + 1)

    while True:
        a, b = map(int, sys.stdin.readline().split())
        if a == -1 and b == -1: break
        if a > N or b > N:
            print(-1)
            exit(0)
        arr[a][b] = True
        arr[b][a] = True
    flag = True

    for i in range(1, N + 1):
        if team[i]: continue
        team[i] = 1
        if not dfs(i, -1):
            flag = False
    for i in range(1, N + 1):
        for j in range(1, N + 1):
            if i == j: continue
            if not arr[i][j] and team[i] == team[j]:
                flag = False
    print(1 if flag else -1)
    if flag:
        team1, team2 = [], []
        for i in range(1, N + 1):
            if team[i] == 1: team1.append(i)
            else: team2.append(i)
        team1.append(-1)
        team2.append(-1)
        print(' '.join(map(str, team1)))
        print(' '.join(map(str, team2)))

