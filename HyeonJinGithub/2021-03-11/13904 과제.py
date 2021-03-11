import sys

if __name__ == '__main__':
    N = int(input())
    homework = []
    check = [False] * N
    last_day = 0

    for _ in range(N):
        d, w = map(int, sys.stdin.readline().split())
        last_day = max(last_day, d)
        homework.append((w, d))
    homework.sort(reverse=True)
    ans = 0
    for day in range(last_day, 0, -1):
        for i in range(N):
            if check[i]: continue
            if homework[i][1] >= day:
                check[i] = True
                ans += homework[i][0]
                break
    print(ans)