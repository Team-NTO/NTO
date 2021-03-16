import sys

if __name__ == '__main__':
    N = int(input())
    arr = [[] for _ in range(4)]

    for _ in range(N):
        a, b, c, d = map(int, sys.stdin.readline().split())
        arr[0].append(a); arr[1].append(b); arr[2].append(c); arr[3].append(d)
    ab_dic = dict()

    for a in arr[0]:
        for b in arr[1]:
            if a + b not in ab_dic:
                ab_dic[a + b] = 1
            else:
                ab_dic[a + b] += 1
    ans = 0
    for c in arr[2]:
        for d in arr[3]:
            if -(c + d) in ab_dic:
                ans += ab_dic[-(c + d)]
    print(ans)