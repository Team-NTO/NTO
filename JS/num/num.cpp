#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N, oneCnt, zeroCnt;
vector<int> positive;
vector<int> negative;

int main()
{
	cin >> N;
	for (int tmp, i = 0; i < N; i++) {
		cin >> tmp;
		if (tmp > 1) positive.push_back(tmp);
		else if (tmp < 0) negative.push_back(-tmp);
		else if (tmp == 0) zeroCnt++;
		else oneCnt++;
	}

	if (positive.size() % 2 == 1)
		positive.push_back(1);
	if (negative.size() % 2 == 1) {
		if (zeroCnt == 0) negative.push_back(-1);
		else negative.push_back(0);
	}

	sort(positive.begin(), positive.end());
	sort(negative.begin(), negative.end());


	int sum = oneCnt;
	for (int i = 0; i < positive.size(); i += 2) 
		sum += positive[i] * positive[i + 1];
	for (int i = 0; i < negative.size(); i += 2)
		sum += negative[i] * negative[i + 1];
	cout << sum;
	
}