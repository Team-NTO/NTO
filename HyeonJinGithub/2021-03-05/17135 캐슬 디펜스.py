import sys
import heapq
from itertools import combinations

def solution(h_location):
    check = [[False] * M for _ in range(N)]
    hunter_check = [False] * 3
    hq = []
    for i in range(N):
        for j in range(M):
            if tmp_board[i][j]:
                for idx, h_l in enumerate(h_location):
                    heapq.heappush(hq, (abs(i - h_l[0]) + abs(j - h_l[1]), j, -i, idx))
    enemy = 0
    while hq:
        if hunter_check[0] and hunter_check[1] and hunter_check[2]: break
        d, y, x, idx = heapq.heappop(hq)
        if d > D: break
        if hunter_check[idx]: continue
        x = -x
        if d <= D and not check[x][y]:
            enemy += 1
            check[x][y] = True
            tmp_board[x][y] = 0
        hunter_check[idx] = True
    return enemy

def move_down():
    global tmp_board, cnt
    for i in range(M):
        for j in range(N - 1, mx_h - 1, -1):
            if not tmp_board[j][i]: continue
            if j != N - 1:
                tmp_board[j+1][i] = tmp_board[j][i]
            tmp_board[j][i] = 0

if __name__ == '__main__':
    N, M, D = map(int, sys.stdin.readline().split())
    board = [list(map(int, sys.stdin.readline().split())) for _ in range(N)] + [[2] * M]
    hunter = [i for i in range(M)]
    ans = 0
    mx_h = -1
    for i in range(N):
        for j in range(M):
            if board[i][j] == 1:
                mx_h = i
                break
        if mx_h != -1: break
    for h in combinations(hunter, 3):
        h_location = []
        for h_idx in h:
            h_location.append((N, h_idx))
        tmp_board = [x[:] for x in board]
        tmp = 0
        for cnt in range(N, mx_h, -1):
            tmp += solution(h_location)
            move_down()
        ans = max(ans, tmp)
    print(ans)
