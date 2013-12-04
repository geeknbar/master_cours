/*
 * Matrix.cpp
 *
 *  Created on: 23 oct. 2013
 *      Author: dorian
 */
#include <initializer_list>
#include <iostream>
#include <stdint.h>
#include <vector>
#include <cassert>

using namespace std;
template<class T>
class Matrix {
protected:
	uint32_t n_rows, n_cols;
	//pour représenter la matrice on peut :
	//T *values; new T[n_rows,n_cols];
	//T**values; new T*[n_rows]
	//				new T[n_cols];
	//vector<T>;
	//vector<vector<T> >values;
	vector<vector<T> >values;
public:

	Matrix(){
		n_rows = n_cols = 0;
	};

	Matrix(uint32_t r, uint32_t c, T initial = 0){
		n_rows = r;
		n_cols = c;
		for (uint32_t r = 0; r < n_rows ; ++ r) {
			values.push_back(vector<T>(n_cols, initial));
		}
	};

	Matrix(Matrix<T>& m){
		n_cols = m.n_cols;
		n_rows = m.n_rows;
		for (uint32_t r = 0; r < n_rows ; ++ r) {
			values.push_back(m.values[r]);
		}
	};

	Matrix(const initializer_list<T>& il) {
		typename initializer_list<T>::iterator iter;
		iter = il.begin();
		n_rows = *iter++;
		n_cols = *iter++;
		for (uint32_t r = 0; r < n_rows ; ++ r) {
			values.push_back(vector<T>(n_cols, (T)0));
		}
		uint32_t r = 0;
		uint32_t c = 0;
		while(iter != il.end()){
			values[r][c] = *iter++;
			++c;
			if(c== n_cols){
				++r;
				c = 0;
			}
		}
	};

	Matrix(uint r, uint c, const initializer_list<T>& il) {

	};
	Matrix<T>& operator=(Matrix<T>& m){
		if(this != &m){
			values.clear();
			n_rows = m.n_rows;
			n_cols = m.n_cols;
			for (uint32_t r = 0; r < n_rows ; ++ r) {
				values.push_back(m.values[r]);
			}
		}
		return *this;
	};

	void sum(Matrix<T>& a, Matrix<T>& b){
		assert(a.n_cols == b.n_cols);
		assert(a.n_rows == b.n_rows);

		//si la matrice dans laquelle on veut n'a pas le même nombre de ligne et col on la refait à l'image d'une des matrice
		if((n_rows != a.n_rows) || (n_cols != a.n_cols) ){
			values.clear();
			n_rows = a.n_rows;
			n_cols = a.n_cols;
			for (uint32_t r = 0; r < n_rows ; ++ r) {
				values.push_back(a.values[r]);
			}
		}
		for ( uint32_t i = 0; i < a.n_rows; i++ ) {
			for ( uint32_t j = 0; j < a.n_cols; j++ ) {
				values[i][j] = a.values[i][j] + b.values[i][j];
			}
		}
	};

	void prod(Matrix<T>& a, Matrix<T>& b){
		assert(a.n_cols == b.n_cols);
		assert(a.n_rows == b.n_rows);

		//si la matrice dans laquelle on veut n'a pas le même nombre de ligne et col on la refait à l'image d'une des matrice
		if((n_rows != a.n_rows) || (n_cols != a.n_cols) ){
			values.clear();
			n_rows = a.n_rows;
			n_cols = a.n_cols;
			for (uint32_t r = 0; r < n_rows ; ++ r) {
				values.push_back(a.values[r]);
			}
		}
		for ( uint32_t i = 0; i < a.n_rows; i++ ) {
			for ( uint32_t j = 0; j < a.n_cols; j++ ) {
				values[i][j] = a.values[i][j] * b.values[i][j];
			}
		}
	};

	void display(ostream& out){
		for (uint32_t r = 0;  r < n_rows; ++r) {
			for(uint32_t c = 0;  c < n_cols; ++c){
				cout << values[r][c] << " ";
			}
			out << endl;
		}
	};

	friend ostream& operator << (ostream& out, Matrix<T>& m){
		m.display(out);
		return out;
	};


};

