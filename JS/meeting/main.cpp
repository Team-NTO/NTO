#include <iostream>
#include <algorithm>
using namespace std;
using ll = long long;
#define MOD 987654321LL

ll d[5001] = { 1 };
int n;
int main() {
    cin >> n;
    n /= 2;
    for (int i = 1; i < n + 1; i++) {
        for (int j = 0; j < i; j++) {
            d[i] += (d[j] % MOD) * (d[i - 1 - j] % MOD) % MOD;
            d[i] %= MOD;
        }
        d[i] %= MOD;
    }

    cout << d[n];
}