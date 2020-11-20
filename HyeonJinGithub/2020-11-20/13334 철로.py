import sys
import heapq

if __name__ == '__main__':
    N = int(input())
    hq = []
    for _ in range(N):
        a, b = map(int, sys.stdin.readline().split())
        if a > b: a, b = b, a
        heapq.heappush(hq, (b, a))
    limit = int(input())
    q = []
    ans = 0
    while hq:
        b, a = heapq.heappop(hq)
        if b - a <= limit:
            heapq.heappush(q, a)
        while q:
            if b - q[0] <= limit:
                break
            else:
                heapq.heappop(q)
        ans = max(ans, len(q))
    print(ans)
