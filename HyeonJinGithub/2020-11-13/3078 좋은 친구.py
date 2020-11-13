import sys
from collections import defaultdict

if __name__ == '__main__':
    N, K = map(int, sys.stdin.readline().split())
    ans = 0
    q = defaultdict(lambda: [])
    for i in range(1, N + 1):
        word = sys.stdin.readline().rstrip()
        if not q[len(word)]:
            q[len(word)].append(i)
        else:
            while i - q[len(word)][0] > K:
                q[len(word)].pop(0)
                if not q[len(word)]:
                    break
            ans += len(q[len(word)])
            q[len(word)].append(i)
    print(ans)
