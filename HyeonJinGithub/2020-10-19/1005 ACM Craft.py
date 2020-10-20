import sys
from collections import deque

def topological_sort(adj_list):
    q = deque()
    for i in range(1, N + 1):
        if not in_degree[i]:
            q.append(i)
            dist[i] = D[i]
    for _ in range(N):
        if not q:
            return

        target = q.popleft()
        for y in adj_list[target]:
            dist[y] = max(dist[y], dist[target] + D[y])
            in_degree[y] -= 1

            if not in_degree[y]:
                q.append(y)
    print(dist[W])

if __name__ == '__main__':
    for _ in range(int(input())):
        N, K = map(int, sys.stdin.readline().split())
        D = [0] + list(map(int, sys.stdin.readline().split()))
        adj_list = [[] for _ in range(N + 1)]
        dist = [0] * (N + 1)
        in_degree = [0] * (N + 1)
        for _ in range(K):
            u, v = map(int, sys.stdin.readline().split())
            adj_list[u].append(v)
            in_degree[v] += 1
        W = int(input())
        topological_sort(adj_list)