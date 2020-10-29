import sys

def set_section(r, c):
    if r > 0 and c > 0:
        return 0
    if r > 0 and c <= 0:
        return 1
    if r <= 0 and c <= 0:
        return 2
    if r <= 0 and c > 0:
        return 3

def set_corners(n):
    tmp = []
    mx = (2 * n + 1) ** 2
    for i in range(4):
        tmp.append(mx - 2 * n * i)
    return tmp

def cal(r, c):
    global max_answer

    section = set_section(r, c)
    bigger = max(abs(r), abs(c))
    corners = set_corners(bigger)

    if section == 0:
        if abs(r) == bigger: # 3 2
            diff = bigger - abs(c)
            ans = corners[0] - diff
        else: # 2 3
            diff = bigger - abs(r)
            ans = corners[3] - 2 * bigger + diff
    elif section == 1:
        if abs(r) == bigger: # 3 -2
            diff = bigger - abs(c)
            ans = corners[1] + diff
        else: # 2 -3
            diff = bigger - abs(r)
            ans = corners[1] - diff
    elif section == 2:
        if abs(r) == bigger: # -3 -2
            diff = bigger - abs(c)
            ans = corners[2] - diff
        else: # -2 -3
            diff = bigger - abs(r)
            ans = corners[2] + diff
    elif section == 3:
        if abs(r) == bigger: # -3 2
            diff = bigger - abs(c)
            ans = corners[3] + diff
        else: # -2 3
            diff = bigger - abs(r)
            ans = corners[3] - diff
    max_answer = max(max_answer, ans)
    return ans


if __name__ == '__main__':
    r1, c1, r2, c2 = map(int, sys.stdin.readline().split())
    R, C = r2 - r1 + 1, c2 - c1 + 1
    board = [[0] * C for _ in range(R)]
    max_answer = -1

    for i in range(R):
        for j in range(C):
            board[i][j] = str(cal(r1 + i, c1 + j))

    strLen = len(str(max_answer))
    for i in range(R):
        for j in range(C):
            tmp = board[i][j]
            board[i][j] = ' ' * (strLen - len(tmp))
            board[i][j] += tmp
    for d in board:
        for i in d:
            print(i, end=' ')
        print()