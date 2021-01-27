import sys
from collections import deque

def bfs(c):
    q = deque()
    q.append(start)
    check = [False] * (N + 1)
    check[start] = True

    while q:
        x = q.popleft()
        for y, w in adj_list[x]:
            if not check[y] and w >= c:
                check[y] = True
                q.append(y)
    return True if check[end] else False

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    adj_list = [[] for _ in range(N + 1)]
    for _ in range(M):
        a, b, c = map(int, sys.stdin.readline().split())
        adj_list[a].append((b, c))
        adj_list[b].append((a, c))
    start, end = map(int, sys.stdin.readline().split())
    mx, mn = 1000000000, 1
    ans = 0
    while mn <= mx:
        mid = (mn + mx) // 2
        if bfs(mid):
            ans = mid
            mn = mid + 1
        else:
            mx = mid - 1
    print(ans)
