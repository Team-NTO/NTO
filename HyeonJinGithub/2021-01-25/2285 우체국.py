import sys

if __name__ == '__main__':
    N = int(input())
    arr = []
    s = 0
    for _ in range(N):
        a, b = map(int, sys.stdin.readline().split())
        s += b
        arr.append((a, b))
    arr.sort()
    tmp = 0
    for i in range(N):
        tmp += arr[i][1]
        if tmp * 2 >= s:
            print(arr[i][0])
            break