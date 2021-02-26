import sys
sys.setrecursionlimit(10**9)

def solution(now_dice, prev_top):
    global ans, prefix_sum
    if now_dice == N:
        ans = max(ans, prefix_sum)
        return
    for k in range(6):
        if dice[now_dice][k] != prev_top: continue
        mx = -1
        for j in range(3):
            if j == k or j + 3 == k: continue
            mx = max(mx, dice[now_dice][j])
            mx = max(mx, dice[now_dice][(j + 3) % 6])
        prefix_sum += mx
        solution(now_dice + 1, dice[now_dice][(k + 3) % 6])
        prefix_sum -= mx

if __name__ == '__main__':
    N = int(input())
    dice = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    for i in range(N):
        tmp = dice[i][5]
        dice[i][5] = dice[i][4]
        dice[i][4] = dice[i][3]
        dice[i][3] = tmp
    ans, prefix_sum = 0, 0
    for i in range(6):
        solution(0, dice[0][i])
    print(ans)