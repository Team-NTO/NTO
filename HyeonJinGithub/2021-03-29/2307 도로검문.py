import sys
import heapq
from collections import deque

INF = sys.maxsize

def dijkstra(start):
    dp[start] = 0
    heapq.heappush(hq, (0, start))

    while hq:
        weight, now = heapq.heappop(hq)
        if dp[now] < weight: continue
        for w, nxt in adj_list[now]:
            if w == 0: continue
            nxt_weight = w + weight
            if dp[nxt] > nxt_weight:
                dp[nxt] = nxt_weight
                heapq.heappush(hq, (nxt_weight, nxt))

def bfs():
    q = deque()
    q.append(N)

    while q:
        after = q.popleft()
        if after == 1: continue
        for w, before in adj_list[after]:
            if dp[before] + w == dp[after]:
                if (before, after, w) not in shortest_path:
                    shortest_path.add((before, after, w))
                    q.append(before)

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    adj_list = [[] for _ in range(N + 1)]
    for _ in range(M):
        u, v, t = map(int, sys.stdin.readline().split())
        adj_list[u].append((t, v))
        adj_list[v].append((t, u))
    hq = []
    dp = [INF] * (N + 1)
    dijkstra(1)
    shortest_path = set()
    d = dp[N]
    bfs()
    ans = -1
    for path in shortest_path:
        dp = [INF] * (N + 1)
        u, v, t = path
        adj_list[u].remove((t, v))
        adj_list[v].remove((t, u))
        dijkstra(1)
        tmp = dp[N]
        adj_list[u].append((t, v))
        adj_list[v].append((t, u))
        if tmp == INF:
            print(-1)
            exit(0)
        ans = max(ans, abs(tmp - d))
    print(ans)
