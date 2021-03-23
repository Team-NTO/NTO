import sys

if __name__ == '__main__':
    N = int(input())
    arr = list(map(int, sys.stdin.readline().split()))
    money = int(input())

    mn = min(arr)
    mn_idx = arr.index(mn)
    ans = []

    while money >= mn:
        money -= mn
        ans.append(mn_idx)
    s = 0
    for i in range(len(ans)):
        for j in range(N - 1, -1, -1):
            if arr[j] <= money + mn:
                money += mn
                money -= arr[j]
                ans[i] = j
                break
        if ans[s] == 0:
            s += 1
            money += mn
    print(''.join(map(str, ans[s:])) if s != len(ans) else 0)