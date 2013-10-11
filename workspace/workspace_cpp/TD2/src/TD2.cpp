#include <set>
#include <map>
#include <iterator>
#include <iostream>
#include <algorithm>
#include <functional>
#include <numeric>
#include <vector>

using namespace std;

#define container_print(v) \
		cout << "(size =)" << v.size() << ") => "; \
		copy(v.begin(), v.end(), ostream_iterator<int>(cout," ")); \
		cout << endl;

int aleatoire(){
	return (rand() % 10)+1;
}

bool greater_than_five(int k){
	return (k > 5);
}
//==============================
bool compare_pair(const pair<int,int>& left, const pair<int,int>& right){
	return left.second < right.second;
}
//==============================
struct PairComparatorStruct {
	bool operator() (const pair<int,int>& left, const pair<int,int>& right){
		return left.second < right.second;
	}
};
//==============================
class PairComparatorStructClass {
public:
	bool operator() (const pair<int,int>& left, const pair<int,int>& right){
		return left.second < right.second;
	}
};
void exercice_1(){
	vector<int> v(100);

	//sans trop utiliser la std
	//	for (i = 0; i < 100; ++i) {
	//		v.push_back((rand()%10)+1);
	//	}

	//en utilisant la std proprement
	generate_n(v.begin(), 100, aleatoire);

	//affichage 1 => C - implementation
	for(int i=0; i<v.size();i++){
		cout << v[i] << " ";
	}
	cout << endl;

	//affichage 2 => STL with iterator
	vector<int>::iterator iter_v;
	for(iter_v = v.begin(); iter_v != v.end(); ++iter_v){
		cout << (*iter_v) << " ";
	}
	cout << endl;

	//affichage 3 => STL with algorithm copy
	copy(v.begin(), v.end(), ostream_iterator<int>(cout," "));
	cout << endl;

	//affichage 4 => C++11 , ajouter -std=c++11
	for (int k : v){
		cout << k << " ";
	}
	cout << endl;

	set<int> s (v.begin(), v.end());
	//	for(int k : v){
	//		s.insert(k);
	//	}
	//	cout << "s = ";

	container_print(s);
	cout << endl;

	for(int k : v){
		cout << k << " appears " << count(v.begin(), v.end(), k) << " time" << endl;
	}

	//question 4
	//façon 1 => en utilisant notre fonction
	int a = count_if(v.begin(), v.end(), greater_than_five);

	//façon 2 => en utilisant la STL
	//autre façon de faire, ici greater est binaire
	//template <class T>
	//bool greater(T a, T b){
	//return(a>b);
	//}
	//pour la passer en unaire utiliser bind2nd
	//bin2nd va remplacer la deuxieme valeur passer en parametre dans greater
	int b = count_if(v.begin(), v.end(), bind2nd(greater<int>(),5));

	//façon 3 => fonction lambda
	int c = count_if(v.begin(), v.end(),[](int k) -> bool {return k > 5;});
	//[block de capture]
	//il sert à dire comment sont traitées les variables externes à la fonction
	//[=] toutes les variables  sont accédées par valeur
	//[&] toutes les variables  sont accédées par référence
	//[=,&x] toutes les variables  sont accédées par valeur sauf x

	cout << "1) number of values > 5 : " << a << endl;
	cout << "2) number of values > 5 : " << b << endl;
	cout << "3) number of values > 5 : " << c << endl;
}

void exercice_2(){
	cout << "exercice 2" << endl;
	vector<int> v(100);

	generate_n(v.begin(), 100, aleatoire);

	//key = int (1..10), value=nb occ

	map<int, int> m;
	//	m.insert(make_pair<int,int>(1,10));
	//	m.insert(make_pair<int,int>(2,20));


	//façon 1
	vector<int>::iterator iter_v;
	for (iter_v = v.begin(); iter_v != v.end(); iter_v++){
		if (m.find(*iter_v) == m.end()) {
			// key k doesn't exists
			m[*iter_v] = 1;
		}else{
			//key k exists
			++m[*iter_v];
		}
	}

	//façon 2 autre solution utiliser auto qui va trouver tout seul ce qu'est iter_v
	//	for (auto iter_v = v.begin(); iter_v != v.end(); iter_v++){
	//		auto iter_m;
	//		if (iter_m == m.end()) {
	//			// key k doesn't exists
	//			m[*iter_v] = 1;
	//		}else{
	//			//key k exists
	//			++m[*iter_v];
	//		}
	//	}

	for(pair<int,int> k : m){
		cout << k.first << "=>" << k.second << endl;
	}

	//impossible de trier un mapping, il faut passe par un vector de oair
	vector<pair<int,int> > w;
	for (auto p :m ){
		w.push_back(p);
	}

	//façon 1 avec une fonction
	//sort(w.begin(), w.end(), compare_pair);

	//façon 2 avec une structure
	//sort(w.begin(), w.end(), PairComparatorStruct());

	//façon 3 avec une classe
	//sort(w.begin(), w.end(), PairComparatorStructClass());

	//façon 4 en utilisant une fonction lambda
	sort(w.begin(), w.end(), [](const pair<int,int>& left, const pair<int,int>& right) -> bool {
		return left.second < right.second;
	}
	);


	for (auto p : w){
		cout << p.first << " appears " << p.second << " times " << endl;
	}
}

void exercice_3(){
	vector<int> v;

	for (int i = 0; i < 100; ++i) {
		v.push_back(rand() % 1000);
	}

	//	vector<int>::iterator iter = min_element(v.begin(), v.end());
	auto iter_min = min_element(v.begin(), v.end());
	cout << "min element = " << *iter_min << endl;

	auto iter_max = max_element(v.begin(), v.end());
	cout << "max element = " << *iter_max << endl;

	//calcul de la somme
	//façon 1
	int sum_1 = 0;
	for(auto iter = v.begin(); iter != v.end(); iter++ ){
		sum_1+= *iter;
	}

	cout << "sum = " << sum_1 << endl;

	//façon 2
	int sum_2 = 0;
	for(int k : v) sum_2 += k;
	cout << "sum_2 = " << sum_2 << endl;

	//façon 3
	int sum_3 = accumulate(v.begin(), v.end(), 0);
	cout << "sum_3 = " << sum_3 << endl;
}
int main() {
	srand(time(NULL));
	exercice_1();
	exercice_2();
	exercice_3();
}
