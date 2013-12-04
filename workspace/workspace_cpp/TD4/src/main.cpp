/*
 * main.cpp
 *
 *  Created on: 27 nov. 2013
 *      Author: dorian
 */

#include "Matrix.cpp"
using namespace std;
int main() {
	Matrix<int> m1(2,3,1);
	Matrix<int> m2(m1);
	Matrix<int> m3(2,2);
	Matrix<int> m4(2,3,6);
	Matrix<int> m5;
	Matrix<int> m6;
	cout << "m1 =" << endl << m1 << endl;
	cout << "m2 =" << endl << m2 << endl;

	cout << "affectation m3 = m1" << endl;
	m3 = m1;
	cout << "m3 =" << endl << m3 << endl;

	cout << "somme deux matrices" << endl;
	m5.sum(m1,m4);
	cout << "m5 =" << endl << m5 << endl;

	cout << "produit deux matrices" << endl;
	m6.prod(m4,m5);
	cout << "m6 =" << endl << m6 << endl;


	Matrix<int> m7 { 2, 3, 1, 2, 3, 4, 5, 6};
	cout << "m7 =" << endl << m7 << endl;
	Matrix<int> m8 (2,3,{ -1, -2, -3, -4, -5, -6});
	cout << "m8 =" << endl << m8 << endl;

	return 0;
};
