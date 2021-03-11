import sys
import heapq

def find(x):
    if x == parent[x]:
        return x
    parent[x] = find(parent[x])
    return parent[x]

def union(x, y):
    x, y = find(x), find(y)
    if x != y:
        parent[x] = y

if __name__ == '__main__':
    N = int(input())
    hq = []
    parent = [i for i in range(N + 1)]
    for i in range(1, N + 1):
        cost = int(input())
        heapq.heappush(hq, (cost, i))
    for i in range(1, N + 1):
        cost = list(map(int, sys.stdin.readline().split()))
        for j in range(len(cost)):
            heapq.heappush(hq, (cost[j], i * 301 + j + 1))
    ans, cnt = 0, 0

    while hq:
        cost, idx_sum = heapq.heappop(hq)
        start, end = idx_sum // 301, idx_sum % 301

        if start == end: continue
        if find(start) == find(end): continue
        union(start, end)
        ans += cost
        cnt += 1
        if cnt == N: break
    print(ans)