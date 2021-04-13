import sys

if __name__ == '__main__':
    N = int(input())
    arr = list(map(int, sys.stdin.readline().split()))
    arr.sort()
    for _ in range(N - 1):
        tmp = list(map(int, sys.stdin.readline().split()))
        arr.extend(tmp)
        arr.sort()
        arr = arr[N:]
    print(arr[0])