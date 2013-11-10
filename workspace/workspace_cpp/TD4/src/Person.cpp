/*
 * Person.cpp
 *
 *  Created on: 23 oct. 2013
 *      Author: dorian
 */
#include "Person.h"

int Person::counter = 0;
Person::Person() {
	nom = "xxx";
	age = -1;
	++counter;
}

Person::Person(string n, int a) {
	nom = n;
	age = a;
	++counter;
}

void Person::display(ostream& out) {
	out << "[PERSON]" << " " << nom << " " << age;
}