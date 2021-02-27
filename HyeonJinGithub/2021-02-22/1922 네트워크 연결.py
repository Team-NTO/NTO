import sys

def find(x):
    if x == parent[x]:
        return x
    parent[x] = find(parent[x])
    return parent[x]

def union(x, y):
    x, y = find(x), find(y)
    parent[x] = y

if __name__ == '__main__':
    N = int(input())
    M = int(input())
    arr = [list(map(int, sys.stdin.readline().split())) for _ in range(M)]
    arr = sorted(arr, key=lambda x: x[2])
    parent = [i for i in range(N + 1)]
    ans = 0
    for start, end, weight in arr:
        if find(start) == find(end):
            continue
        ans += weight
        union(start, end)
    print(ans)