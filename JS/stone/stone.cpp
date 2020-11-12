#include<iostream>

using namespace std;

int main(){
	int A, B, C;
	cin >> A >> B >> C;
	if((A+B+C)%3!=0)
	{
		cout << "0";
		return 0;
	}
	else
	{
		int sum= A+B+C;
		int res= sum/3;
		if(((sum-res)%2 == 0) || ((sum-res)%3 == 0))
		{
			cout << "1";
			return 0;
		}
		else
		{
			cout << "0";
			return 0;
		}
	}
}
