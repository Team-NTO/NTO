import sys

def find(x):
    if x == parent[x]:
        return x
    parent[x] = find(parent[x])
    return parent[x]

def union(x, y):
    x, y = find(x), find(y)
    if x > y: parent[x] = y
    else: parent[y] = x

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    parent = [i for i in range(N + 1)]
    for i in range(M):
        a, b = map(int, sys.stdin.readline().split())
        if find(a) == find(b):
            print(i + 1)
            exit(0)
        union(a, b)
    print(0)
