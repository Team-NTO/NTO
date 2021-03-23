import sys
sys.setrecursionlimit(10**6)

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
    N, M = map(int, sys.stdin.readline().split())
    parent = [i for i in range(N + 1)]
    ans = 0
    for _ in range(M):
        a, b = map(int, sys.stdin.readline().split())
        if find(a) != find(b):
            union(a, b)
        else:
            ans += 1

    for i in range(1, N + 1):
        if i == N:
            tmp1, tmp2 = find(i), find(1)
        else:
            tmp1, tmp2 = find(i), find(i + 1)
        if tmp1 != tmp2:
            union(tmp1, tmp2)
            ans += 1
    print(ans)
