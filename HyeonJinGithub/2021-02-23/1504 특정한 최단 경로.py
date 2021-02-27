import sys
import heapq

INF = sys.maxsize

def dijkstra(start):
    dist = [INF] * (N + 1)
    dist[start] = 0

    hq = []
    heapq.heappush(hq, (0, start))

    while hq:
        d, now_node = heapq.heappop(hq)
        for next_node, length in adj_list[now_node]:
            nd = dist[now_node] + length
            if nd < dist[next_node]:
                dist[next_node] = nd
                heapq.heappush(hq, (nd, next_node))
    return dist

if __name__ == '__main__':
    N, E = map(int, sys.stdin.readline().split())
    adj_list = [[] for _ in range(N + 1)]

    for _ in range(E):
        a, b, c = map(int, sys.stdin.readline().split())
        adj_list[a].append((b, c))
        adj_list[b].append((a, c))
    v1, v2 = map(int, sys.stdin.readline().split())

    one_d = dijkstra(1)
    v1_d = dijkstra(v1)
    v2_d = dijkstra(v2)
    mn = min(one_d[v1] + v1_d[v2] + v2_d[N], one_d[v2] + v2_d[v1] + v1_d[N])
    print(mn if mn < INF else -1)