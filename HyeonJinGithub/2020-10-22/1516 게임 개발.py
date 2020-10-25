import sys
from collections import deque

def topological_sort(adj_list):
    q = deque()
    for i in range(1, N + 1):
        if not in_degree[i]:
            q.append(i)
            res[i] = dist[i]
    for _ in range(N):
        if not q:
            return
        x = q.popleft()
        for y in adj_list[x]:
            res[y] = max(res[y], res[x] + dist[y])
            in_degree[y] -= 1
            if not in_degree[y]:
                q.append(y)

if __name__ == '__main__':
    N = int(input())
    adj_list = [[] for _ in range(N + 1)]
    dist = [0] * (N + 1)
    in_degree = [0] * (N + 1)
    res = [0] * (N + 1)
    for i in range(1, N + 1):
        tmp = list(map(int, sys.stdin.readline().split()))
        for j in range(len(tmp)):
            if tmp[j] == -1: break
            if j == 0:
                dist[i] = tmp[j]
            else:
                adj_list[tmp[j]].append(i)
                in_degree[i] += 1
    topological_sort(adj_list)
    for i in range(1, N + 1):
        print(res[i])