import sys
sys.setrecursionlimit(10**9)
def dfs(now, cnt):
    global check
    if cnt == 4:
        print(1)
        exit()
    check[now] = True
    for nxt in adj_list[now]:
        if not check[nxt]:
            dfs(nxt, cnt + 1)
            check[nxt] = False

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    adj_list = [[] for _ in range(N)]
    for _ in range(M):
        a, b = map(int, sys.stdin.readline().split())
        adj_list[a].append(b)
        adj_list[b].append(a)
    cnt = 0
    check = [False] * N
    for i in range(N):
        adj_list[i].sort()
    for i in range(N):
        check = [False] * N
        dfs(i, 0)
    print(0)