import sys, heapq

def dij(x):
    dist = [INF] * N
    dist[x] = 0
    q = []
    heapq.heappush(q, [0, x])

    while q:
        cost, pos = heapq.heappop(q)
        for p, c in road[pos]:
            c += cost
            if dist[p] > c:
                dist[p] = c
                heapq.heappush(q, [c, p])
    return dist
if __name__ == '__main__':
    N, M, X = map(int, sys.stdin.readline().split())
    INF = 1e9
    road = [[] for _ in range(N)]
    for _ in range(M):
        u, v, t = map(int, sys.stdin.readline().split())
        road[u - 1].append([v - 1, t])
    result = [0] * N
    for i in range(N):
        tmp = dij(i)
        if i == X - 1:
            for idx, value in enumerate(tmp):
                result[idx] += value
        else:
            result[i] += tmp[X-1]
    print(max(result))