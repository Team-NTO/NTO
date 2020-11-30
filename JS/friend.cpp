#include <iostream>

int main() {
    int students, relation, money, cnt=0;
    std::cin >> students >> relation >> money;

    std::pair<int, int> *group= new std::pair<int, int>[relation];
    int *info= new int[students];
    int *res= new int[students];

    for(int i=0; i<students; i++) {
        std::cin >> info[i];
        res[i] = 0;
    }

    for(int i=0; i<relation; i++)
        std::cin >> group[i].first >> group[i].second;


    while(money > 0) {
        if(info[cnt]<money) {
            money = money - info[cnt];
            res[cnt] = 1;
            for(int tmp = 0; tmp<relation; tmp++){
                if(group[tmp].first == (cnt+1)){
                    res[group[tmp].second] = 1;
                    break;
                }
            }
            cnt++;
        }
        else {
            cnt++;
        }
    }

    for(int i=0; i<students; i++)
        std::cout << res[i] << ' ';

    return 0;
}
