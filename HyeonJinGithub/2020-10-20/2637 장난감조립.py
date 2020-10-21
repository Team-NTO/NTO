import sys
from collections import defaultdict
from collections import deque

def topological_sort(adj_list):
    q = deque()
    for i in range(1, N + 1):
        if in_degree[i] == 0:
            q.append(i)
            check[i] = True
            dist[i][i] = 1
    for _ in range(N):
        if not q:
            return
        now = q.popleft()
        for y in adj_list[now]:
            next, cost = y[0], y[1]
            for j in range(1, N + 1):
                dist[next][j] += dist[now][j] * cost
            in_degree[next] -= 1
            if in_degree[next] == 0:
                q.append(next)

if __name__ == '__main__':
    N = int(input())
    M = int(input())
    adj_list = defaultdict(lambda :[])
    check = [False] * (N + 1)
    in_degree = [0] * (N + 1)
    dist = [[0] * (N + 1) for _ in range(N + 1)]

    for _ in range(M):
        x, y, k = map(int, sys.stdin.readline().split())
        in_degree[x] += 1
        adj_list[y].append((x, k))

    topological_sort(adj_list)
    for i in range(1, N + 1):
        if check[i]:
            print(i, dist[N][i])
