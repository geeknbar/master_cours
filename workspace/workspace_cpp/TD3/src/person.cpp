#include "person.h"

Person::Person() {
	nom = "xxx";
	age = -1;
}

Person::Person(string n, int a) {
	nom = n;
	age = a;
}

void Person::display(ostream& out) {
	out << "[PERSON]" << " " << nom << " " << age;
}
