import sys
from collections import deque
sys.setrecursionlimit(10**9)

def dfs(x, cnt):
    if check[x]:
        if cnt - dist[x] >= 3: return x
        else: return -1
    check[x] = 1
    dist[x] = cnt
    for y in adj_list[x]:
        cycleStartNode = dfs(y, cnt + 1)
        if cycleStartNode != -1:
            check[x] = 2
            if x == cycleStartNode: return -1
            else: return cycleStartNode
    return -1

if __name__ == '__main__':
    N = int(input())
    adj_list = [[] * (N + 1) for _ in range(N + 1)]
    check = [0] * (N + 1)
    dist = [0] * (N + 1)

    for _ in range(N):
        u, v = map(int, sys.stdin.readline().split())
        adj_list[u].append(v)
        adj_list[v].append(u)
    dfs(1, 0)

    q = deque()
    for i in range(1, N + 1):
        if check[i] == 2:
            q.append(i)
            dist[i] = 0
        else:
            dist[i] = -1
    while q:
        x = q.popleft()
        for y in adj_list[x]:
            if dist[y] == -1:
                q.append(y)
                dist[y] = dist[x] + 1
    print(' '.join(map(str, dist[1:])))