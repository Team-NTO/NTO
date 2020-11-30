#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int main(int argc, char* argv[])
{
    int n;
    int s1, s2, e1, e2;
    int start = 300;
    int end = 301;
    int result = 0;
    int maxEnd = 0;
    int maxIndex = 0;
    vector<pair<int, int> > v;

    cin >> n;

    for (int i = 0; i < n; ++i) {
        cin >> s1 >> s2 >> e1 >> e2;

        if (s1 * 100 + s2 < 301) {
            s1 = 3;
            s2 = 1;
        }

        if (e1 * 100 + e2 > 1130) {
            e1 = 12;
            e2 = 01;
        }

        v.push_back({ s1 * 100 + s2, e1 * 100 + e2 });
    }

    sort(v.begin(), v.end());

    for (int i = 0; i < n; ++i) {
        if (v[i].first > start && v[i].first <= end) {
            if (maxEnd < v[i].second) {
                maxEnd = v[i].second;
                maxIndex = i;
            }
            if (v[i].second == 1201) {
                ++result;
                end = maxEnd;
                break;
            }
            continue;
        }
        else {
            if (end >= maxEnd) {
                break;
            }

            ++result;
            start = end;
            end = maxEnd;
            --i;
        }
    }

    if (end == 1201) {
        cout << result << endl;
    }
    else {
        cout << 0 << endl;
    }

    return 0;
}