import sys
from itertools import combinations

def check(word):
    ja, mo = 0, 0
    for c in word:
        if c == 'a' or c == 'e' or c == 'i' or c == 'o' or c == 'u': mo += 1
        else: ja += 1
    return mo >= 1 and ja >= 2

if __name__ == '__main__':
    L, C = map(int, sys.stdin.readline().split())
    ch = list(map(str, sys.stdin.readline().split()))
    ch.sort()
    for y in list(combinations(ch, L)):
        if check(y):
            print(''.join(y))