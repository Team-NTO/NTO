import sys

if __name__ == '__main__':
    N = int(input())
    arr = sorted(list(map(int, sys.stdin.readline().split())))
    ans = 1
    for i in range(N):
        if arr[i] > ans:
            break
        ans += arr[i]
    print(ans)