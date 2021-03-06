//============================================================================
// Name        : TD4.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include "node.h"
#include "Person.h"
using namespace std;


template<>
class NodePolicy<int>{
public:
	static void print(ostream& out, int& value){
		out << "$i" << value;
	}
	static void print(ostream& out, int* value){
		out << "*i" << *value;
	}

};

template<>
class NodePolicy<Person>{
public:
	static void print(ostream& out, Person& value){
		out << "$p" << value;
	}
	static void print(ostream& out, Person* value){
		out << "*p" << *value;
	}

};

template<>
class NodePolicy<Person *>{
public:
	static void print(ostream& out, Person& value){
		out << "$pp" << value;
	}
	static void print(ostream& out, Person* value){
		out << "*pp" << *value;
	}

};
//===================================================
void node_int(){
	//	   4
	//	  /  \
	//	 3	  5
	//  / \
	// 1   2
	//
	// donne 1 3 2 4 5

	Node<int, NodePolicy<int> > n1(1),n2(2),n3(3),n4(4),n5(5);
	n3.add(&n1);
	n3.add(&n2);
	n4.add(&n3);
	n4.add(&n5);

	cout << "initial = " << n4 << "\n";
	Node<int, NodePolicy<int> > *newC = n4.clone();
	cout << "cloner = " << *newC << "\n";
}
//===================================================

void node_person(){

	Node<Person, NodePolicy<Person> > n1(Person("D",1));
	Node<Person, NodePolicy<Person> > n2(Person("E",2));
	Node<Person, NodePolicy<Person> > n3(Person("B",3));
	Node<Person, NodePolicy<Person> > n4(Person("A",4));
	Node<Person, NodePolicy<Person> > n5(Person("C",5));

	n3.add(&n1);
	n3.add(&n2);
	n4.add(&n3);
	n4.add(&n5);

	cout << "initial = " << n4 << "\n";
	Node<Person,NodePolicy<Person> > *newC = n4.clone();
	cout << "cloner = " << *newC << "\n";
	cout << "counter = " << Person::counter << "\n";
}

//===================================================

void node_ptr_person(){

	Person *p1 = new Person("D",1);
	Person *p2 = new Person("E",2);
	Person *p3 = new Person("B",3);
	Person *p4 = new Person("A",4);
	Person *p5 = new Person("C",5);

	Node<Person *, NodePolicy<Person *> > n1(p1);
	Node<Person *, NodePolicy<Person *> > n2(p2);
	Node<Person *, NodePolicy<Person *> > n3(p3);
	Node<Person *, NodePolicy<Person *> > n4(p4);
	Node<Person *, NodePolicy<Person *> > n5(p5);

	n3.add(&n1);
	n3.add(&n2);
	n4.add(&n3);
	n4.add(&n5);

	cout << "initial = " << n4 << "\n";
	Node<Person *, NodePolicy<Person *> > *newC = n4.clone();
	cout << "cloner = " << *newC << "\n";
	cout << "counter = " << Person::counter << "\n";

}

//int main() {
//	node_int();
//	node_person();
//	node_ptr_person();
//	return 0;
//};
