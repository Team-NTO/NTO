import sys

if __name__ == '__main__':
    N = int(input())
    q = []
    tmp = ''
    for _ in range(N):
        flag = False
        cmd, ch, t = map(str, sys.stdin.readline().split())
        if cmd == 'type':
            tmp += ch
            q.append((int(t), tmp))
        else:
            ch, t = int(ch), int(t)
            for i in range(len(q) - 1, -1, -1):
                if q[i][0] >= t - ch: continue
                flag = True
                now = q[i][1]
                tmp = now
                q.append((t, now))
                break
            if not flag:
                tmp = ''
                q.append((t, tmp))
    print(q[-1][1])
