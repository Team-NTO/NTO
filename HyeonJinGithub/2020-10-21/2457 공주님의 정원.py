import sys

if __name__ == '__main__':
    N = int(input())
    flowers = []
    for _ in range(N):
        s_m, s_d, d_m, d_d = map(int, sys.stdin.readline().split())
        flowers.append((s_m * 100 + s_d, d_m * 100 + d_d))
    flowers.sort()
    i = 301
    idx, result, tmp = -1, 0, 0
    while i <= 1130 and idx < N:
        flag = False
        check = False
        idx += 1
        for j in range(idx, N):
            if flowers[j][0] > i: break
            if flowers[j][1] > tmp:
                tmp = flowers[j][1]
                flag = True
                idx = j
        if flag:
            result += 1
        else:
            print(0)
            check = True
            break
        i = tmp
    if not check:
        print(result)