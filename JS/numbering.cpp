#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int find_min(vector<int> n, int input){
	sort(n.begin(), n.end(), greater<int>());
	return n[input-1];
}

int main(){
	vector<int> n;
	int cnt;

	cin >> cnt;
	for(int x=0;x<cnt;x++){
		for(int i=0;i<cnt;i++){
			int num;
			cin >> num;
			n.push_back(num);
		}
	}
	cout << find_min(n,cnt);
	return 0;
}
