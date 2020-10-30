import sys
from itertools import combinations
from collections import deque

def bfs(tmp_board, group, group_count, x, y):
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    q = deque([(x, y)])

    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < 5 and 0 <= ny < 5:
                if tmp_board[nx][ny] == 1 and group[nx][ny] == 0:
                    q.append((nx, ny))
                    group[nx][ny] = group_count

def adjacency(location):
    tmp_board = [[0] * 5 for _ in range(5)]
    for i in range(25):
        if i not in location: continue
        r, c = i // 5, i % 5
        tmp_board[r][c] = 1
    group = [[0] * 5 for _ in range(5)]
    group_count = 1
    for i in range(5):
        for j in range(5):
            if tmp_board[i][j] == 1 and group[i][j] == 0:
                bfs(tmp_board, group, group_count, i, j)
                group_count += 1
    if group_count == 2: return True
    else: return False

if __name__ == '__main__':
    board = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(5)]
    check = [i for i in range(25)]
    ans = 0
    for y in combinations(check, 7):
        y_count = 0
        for i in range(25):
            if i not in y: continue
            r, c = i // 5, i % 5
            if board[r][c] == 'Y': y_count += 1
        if y_count >= 4: continue
        if adjacency(y): ans += 1
    print(ans)