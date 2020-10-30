import math
if __name__ == '__main__':
    N = int(input())
    mod = 987654321
    N //= 2
    print((math.factorial(2*N) // (math.factorial(N) * math.factorial(N + 1))) % mod)