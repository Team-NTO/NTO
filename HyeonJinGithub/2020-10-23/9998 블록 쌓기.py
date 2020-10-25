import sys

def search(mid):
    s = 0

    for i in range(N // 2 + 1):
        if i == 0:
            s = s + abs(Y[N // 2] - mid)
        else:
            s = s + abs(Y[N // 2 - i] - (mid + i)) + abs(Y[N // 2 + i] - (mid + i))
    for i in range(N // 2 + 1):
        if i == 0:
            s = s + abs(D[N // 2] - mid)
        else:
            s = s + abs(D[N // 2 - i] - (mid + i)) + abs(D[N // 2 + i] - (mid + i))
    return s

if __name__ == '__main__':
    N = int(input())
    mn = -1
    cnt = 0
    Y = list(map(int, sys.stdin.readline().split()))
    low = min(Y)
    high = max(Y)
    D = list(map(int, sys.stdin.readline().split()))

    for d in D:
        low = min(low, d)
        high = max(high, d)

    while low <= high:
        mid = (low + high) // 2
        mid2 = mid + 1
        std = search(mid)
        std2 = search(mid2)
        if std < std2:
            if mn == -1 or mn > std:
                mn = std
            high = mid - 1
        else:
            if mn == -1 or mn > std2:
                mn = std2
            low = mid2 + 1
    cnt = cnt + mn
    print(cnt)