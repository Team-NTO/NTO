import sys

if __name__ == '__main__':
    S = list(map(str, sys.stdin.readline().rstrip()))
    T = list(map(str, sys.stdin.readline().rstrip()))

    s, t = len(S) - 1, len(T) - 1

    while len(T) > 0:
        if s == t: break
        x = T.pop()
        if x == 'B':
            T.reverse()
        t -= 1
    print(1 if S == T else 0)