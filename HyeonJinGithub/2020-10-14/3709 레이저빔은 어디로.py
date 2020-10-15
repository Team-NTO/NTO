import sys

def solution(board, r_x, r_y):
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]
    if r_x == 0: d = 2
    elif r_x == n + 1: d = 0
    elif r_y == 0: d = 1
    elif r_y == n + 1: d = 3

    x, y = r_x, r_y
    visit = []
    check_visit = []
    while True:
        nx, ny = x + dx[d], y + dy[d]
        if board[nx][ny] == 0:
            x, y = nx, ny
            continue
        elif board[nx][ny] == 1:
            if (nx, ny) in visit:
                check_visit.append((nx, ny))
            else:
                visit.append((nx, ny))
            d = (d + 1) % 4
            x, y = nx, ny
            if visit == check_visit:
                print(0, 0)
                return
        elif board[nx][ny] == -1:
            print(nx, ny)
            return

if __name__ == '__main__':
    T = int(input())
    while T > 0:
        n, r = map(int, sys.stdin.readline().split())
        board = [[-1] * (n + 2)] + [[-1] + [0] * n + [-1] for _ in range(n)] + [[-1] * (n + 2)]
        for _ in range(r):
            x, y = map(int, sys.stdin.readline().split())
            board[x][y] = 1
        r_x, r_y = map(int, sys.stdin.readline().split())
        solution(board, r_x, r_y)
        T -= 1