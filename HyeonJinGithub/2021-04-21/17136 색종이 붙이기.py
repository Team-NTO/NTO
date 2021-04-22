import sys

def solution(x, y, cnt):
    global ans
    if y >= 10:
        ans = min(ans, cnt)
        return
    if x >= 10:
        solution(0, y + 1, cnt)
        return
    if board[x][y]:
        for i in range(5):
            if paper[i] >= 5: continue
            if x + i >= 10 or y + i >= 10: continue
            flag = False
            for p1 in range(x, x + i + 1):
                for p2 in range(y, y + i + 1):
                    if not board[p1][p2]:
                        flag = True
                        break
                if flag: break
            if not flag:
                for p1 in range(x, x + i + 1):
                    for p2 in range(y, y + i + 1):
                        board[p1][p2] = 0
                paper[i] += 1
                solution(x + i + 1, y, cnt + 1)
                paper[i] -= 1
                for p1 in range(x, x + i + 1):
                    for p2 in range(y, y + i + 1):
                        board[p1][p2] = 1
    else:
        solution(x + 1, y, cnt)

if __name__ == '__main__':
    board = [list(map(int, sys.stdin.readline().split())) for _ in range(10)]
    paper = [0] * 5
    ans = sys.maxsize
    solution(0, 0, 0)
    print(ans if ans != sys.maxsize else -1)