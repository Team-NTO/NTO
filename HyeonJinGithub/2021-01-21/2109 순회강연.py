import sys
import heapq

if __name__ == '__main__':
    N = int(input())
    arr = []
    for _ in range(N):
        p, d = map(int, sys.stdin.readline().split())
        arr.append((d, p))
    arr.sort(reverse=True)
    hq = []
    idx, ans = 0, 0
    for i in range(10000, 0, -1):
        while idx < N and i <= arr[idx][0]:
            heapq.heappush(hq, -arr[idx][1])
            idx += 1
        if hq:
            ans += -heapq.heappop(hq)
    print(ans)