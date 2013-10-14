///*
// * TD2prof.cpp
// *
// *  Created on: 14 oct. 2013
// *      Author: dorian
// */
//
//#include "TD2prof.h"
//
//#include <vector>
//#include <set>
//#include <map>
//#include <iterator>
//#include <iostream>
//#include <algorithm>
//#include <functional>
//#include <numeric>
//
//using namespace std;
//
//
//int aleatoire() {
//	return (rand() % 10) + 1;
//}
//
//bool greater_than_five(int k) {
//	return (k > 5);
//}
//
//// =================
//bool compare_pair(const pair<int,int>& left, const pair<int,int>& right) {
//	return left.second < right.second;
//}
//
//// =================
//struct PairComparatorStruct {
//	bool operator() (const pair<int,int>& left, const pair<int,int>& right) {
//	return left.second < right.second;
//	}
//};
//
//// =================
//class PairComparatorClass {
//public:
//	bool operator() (const pair<int,int>& left, const pair<int,int>& right) {
//	return left.second < right.second;
//	}
//};
//
//
//void exercice_1() {
//	vector<int> v(100);
//
//	/*
//	for (int i=0; i<100; ++i) {
//		v.push_back((rand() % 10) + 1);
//	}
//	*/
//	generate_n(v.begin(), 100, aleatoire);
//
//	// C-implementation
//	for (unsigned int i=0; i<v.size(); ++i) {
//		cout << v[i] << " ";
//	}
//	cout << endl;
//
//	// STL with iterator
//	vector<int>::iterator iter_v;
//	for (iter_v = v.begin(); iter_v != v.end(); ++iter_v) {
//		cout << (*iter_v) << " ";
//	}
//	cout << endl;
//
//	// STL with algorithm copy
//	copy(v.begin(), v.end(), ostream_iterator<int>(cout, " "));
//	cout << endl;
//
//	// C++11  -std=c++11
//	for (int k : v) {
//		cout << k << " ";
//	}
//	cout << endl;
//
//	// macro that uses STL with algorithm copy
//	container_print(v);
//
//	set<int> s(v.begin(), v.end());
//	/*
//	for (int k : v) {
//		s.insert(k);
//	}
//	*/
//	cout << "s = ";
//	container_print(s);
//
//	for (int k : s) {
//		cout << k << " appears " << count(v.begin(), v.end(), k)
//			<< " times" << endl;
//	}
//
//	int n = count_if(v.begin(), v.end(), greater_than_five);
//	n = count_if(v.begin(), v.end(), bind2nd(greater<int>(),5));
//	n = count_if(v.begin(), v.end(), [](int k) -> bool {
//			return k > 5;
//		} );
//
//	cout << "number of values > 5: " << n << endl;
//}
//
//void exercice_2() {
//	vector<int> v(100);
//
//	/*
//	for (int i=0; i<100; ++i) {
//		v.push_back((rand() % 10) + 1);
//	}
//	*/
//	generate_n(v.begin(), 100, aleatoire);
//
//	// key=int (1..10), value=nb occ
//	map<int, int> m;
//	//m.insert(make_pair<int,int>(1,10));
//	//m.insert(make_pair<int,int>(2,20));
//
//	for (auto iter_v = v.begin();
//		iter_v != v.end(); ++iter_v) {
//
//		//map<int, int>::iterator iter_m = m.find(*iter_v);
//		auto iter_m = m.find(*iter_v);
//		if (iter_m == m.end()) {
//			// key k does not exists
//			m[*iter_v] = 1;
//		} else {
//			// key k does exist
//			++m[*iter_v];
//		}
//	}
//
//	for (auto p : m) {
//		cout << p.first << " => " << p.second << endl;
//	}
//
//	// impossible de trier un mapping, il faut passer
//	// par un vector de pair
//	vector<pair<int,int> > w;
//	for (auto p : m) {
//		w.push_back(p);
//	}
//
//	//sort(w.begin(), w.end(), compare_pair);
//	//sort(w.begin(), w.end(), PairComparatorStruct() );
//	//sort(w.begin(), w.end(), PairComparatorClass() );
//	sort(w.begin(), w.end(), [](const pair<int,int>& left, const pair<int,int>& right) -> bool {
//		return left.second < right.second;
//		}
//	);
//
//	for (auto p : w) {
//		cout << p.first << " appears " << p.second << " times" << endl;
//	}
//}
//
//void exercice_3() {
//	vector<int> v;
//
//	for (int i = 0; i<100; ++i) {
//		v.push_back(rand() % 1000);
//	}
//
//	//vector<int>::iterator iter = min_element(v.begin(), v.end());
//	auto iter_min = min_element(v.begin(), v.end());
//	cout << "min element = " << *iter_min << endl;
//	auto iter_max = max_element(v.begin(), v.end());
//	cout << "max element = " << *iter_max << endl;
//
//	int sum = 0;
//	for (auto iter = v.begin(); iter != v.end(); ++iter) {
//		sum += *iter;
//	}
//
//	sum = 0;
//	for (int k : v) sum += k;
//
//	sum = accumulate(v.begin(), v.end(), 0);
//
//	cout << "sum = " << sum << endl;
//
//}
//
//
//int main() {
//	srand(0);
//	exercice_1();
//	srand(0);
//	exercice_2();
//	exercice_3();
//}
