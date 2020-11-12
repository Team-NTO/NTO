import sys
from collections import defaultdict
from collections import deque

def topological_sort():
    global ans
    q = deque()

    for i in range(1, N + 1):
        if in_degree[i] == 0:
            q.append(i)
    while q:
        x = q.popleft()
        ans.append(x)
        for y in adj_list[x]:
            in_degree[y] -= 1
            if in_degree[y] == 0:
                q.append(y)
if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    ans = []
    in_degree = [0] * (N + 1)
    adj_list = defaultdict(lambda: [])
    for _ in range(M):
        singer = list(map(int, sys.stdin.readline().split()))[1:]
        for j in range(len(singer) - 1):
            in_degree[singer[j + 1]] += 1
            adj_list[singer[j]].append(singer[j + 1])
    topological_sort()
    if len(ans) != N:
        print(0)
    else:
        for y in ans:
            print(y)