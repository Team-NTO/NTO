import sys
from collections import deque

def topological_sort(adj_list):
    q = deque()
    for i in range(1, M + 1):
        if not in_degree[i]:
            q.append(i)
            dist[i] = 1
    for _ in range(M):
        if not q:
            return
        now = q.popleft()
        for y in adj_list[now]:
            if in_degree[y] == 1:
                dist[y] = max(dist[now], dist[y])
            else:
                dist[y] = max(dist[y], dist[now] + 1)
            in_degree[y] -= 1
            if not in_degree[y]:
                q.append(y)


if __name__ == '__main__':
    for _ in range(int(input())):
        K, M, P = map(int, sys.stdin.readline().split())
        adj_list = [[] for _ in range(M + 1)]
        in_degree = [0] * (M + 1)
        dist = [0] * (M + 1)
        for i in range(P):
            u, v = map(int, sys.stdin.readline().split())
            adj_list[u].append(v)
            in_degree[v] += 1
        topological_sort(adj_list)
        print(K, dist[M])