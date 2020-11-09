import sys
from collections import deque

def bfs(A, B):
    q = deque([(A, B)])
    check[A][B] = True
    while q:
        a, b = q.popleft()
        c = D - a - b
        if a == b == c:
            print(1)
            exit(0)
        for x, y in (a, b), (b, c), (a, c):
            if x < y:
                y -= x
                x += x
            elif x > y:
                x -= y
                y += y
            else:
                continue
            z = D - x - y
            X = min(x, y, z)
            Y = max(x, y, z)
            if not check[X][Y]:
                q.append((X, Y))
                check[X][Y] = True
    print(0)

if __name__ == '__main__':
    A, B, C = map(int, sys.stdin.readline().split())
    D = A + B + C
    check = [[False] * D for _ in range(D)]

    if D % 3:
        print(0)
    else:
        bfs(A, B)