import sys
from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

def simulation():
    time = 0
    x, y, d = 0, 0, 0
    snake = deque([(0, 0)])
    cmd_idx = 0

    while True:
        time += 1
        x, y = snake[0]
        nx, ny = x + dx[d], y + dy[d]
        if nx < 0 or nx >= N or ny < 0 or ny >= N: break
        if (nx, ny) in snake: break
        if board[nx][ny] == 1:
            board[nx][ny] = 0
            snake.appendleft((nx, ny))
        else:
            snake.appendleft((nx, ny))
            snake.pop()
        if cmd_idx < len(cmd) and time == cmd[cmd_idx][0]:
            if cmd[cmd_idx][1] == 'D': d = (d + 1) % 4
            else: d = (d - 1) % 4
            cmd_idx += 1
    return time

if __name__ == '__main__':
    N = int(input())
    board = [[0] * N for _ in range(N)]
    cmd = []

    K = int(input())
    for _ in range(K):
        x, y = map(int, sys.stdin.readline().split())
        board[x - 1][y - 1] = 1

    L = int(input())
    for _ in range(L):
        t, d = map(str, sys.stdin.readline().split())
        cmd.append((int(t), d))
    print(simulation())