import sys

def test(turn):
    for i in range(3):
        if board[i][0] == turn and board[i][0] == board[i][1] == board[i][2]: return True
    for i in range(3):
        if board[0][i] == turn and board[0][i] == board[1][i] == board[2][i]: return True
    if board[0][0] == turn and board[0][0] == board[1][1] == board[2][2]: return True
    if board[0][2] == turn and board[0][2] == board[1][1] == board[2][0]: return True
    return False

def solution(turn):
    if test(3 - turn): return -1
    mn = 2
    for i in range(3):
        for j in range(3):
            if board[i][j] == 0:
                board[i][j] = turn
                mn = min(mn, solution(3 - turn))
                board[i][j] = 0
    if mn == 2 or mn == 0: return 0
    return -mn
if __name__ == '__main__':
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(3)]
    cnt = 0
    for i in range(3):
        for j in range(3):
            if board[i][j] != 0: cnt += 1
    if cnt % 2 == 0: res = solution(1)
    else: res = solution(2)

    if res == 1: print('W')
    elif res == 0: print('D')
    else: print('L')