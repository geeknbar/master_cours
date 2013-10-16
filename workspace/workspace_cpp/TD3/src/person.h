#ifndef PERSON_H
#define PERSON_H

#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>

using namespace std;

class Person {
	protected:
		string nom;
		int age;
	public:
		// Constructeurs
		Person();
		Person(string n, int a);

		string get_nom() {return nom;}
		int get_age() {return age;}

		void set_nom(string n) {nom = n;}
		void set_age(int a) {age = a;}

		void display(ostream& out);

		friend ostream& operator<<(ostream& out, Person& p) {p.display(out); return out;}

		friend bool operator<(const Person& p, const Person& q) {
			return p.nom < q.nom;
		}
};

#endif
