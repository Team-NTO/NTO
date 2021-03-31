#include <iostream>
#include <algorithm>
using namespace std;

int dp[10001] = { 0, };
int n;
int t = 0, cnt = 0, value = 0;
int result = 0;

int main(){
	cin >> n;
		for (int i = 1; i <= n; i++) {
			cin >> t >> cnt;
			if (cnt == 0) {
				dp[i] = t;
				continue;
			}
			int maxed = 0;
			for (int j = 0; j < cnt; j++) {
				cin >> value;
				maxed = max(maxed, dp[value]);
			}
			dp[i] = t + maxed;
		}
		
		for (int i = 1; i <= n; i++)
			result = max(result, dp[i]);

		cout << result << endl;


		return 0;
	}