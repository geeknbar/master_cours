#include "person.h"

bool person_compare_age(const Person&p, const Person& q){
	return const_cast<Person &>(p).get_age() <
			const_cast<Person &>(q).get_age();
	//return p.get_age() < q.get_age();
}

int main() {
	const int MAX_PERSONS = 5;
	Person *persons = new Person[MAX_PERSONS];

	persons[0].set_nom("riri");
	persons[0].set_age(10);
	persons[1].set_nom("fifi");
	persons[1].set_age(12);
	persons[2].set_nom("loulou");
	persons[2].set_age(11);
	persons[3].set_nom("donald");
	persons[3].set_age(40);
	/*persons[4].set_nom("tata");
	persons[4].set_age(50);*/

	vector<Person> v(&persons[0], &persons[MAX_PERSONS]);
	// tri suivant nom
	sort(v.begin(), v.end());
	cout << "TRI suivant nom : " << endl;
	copy(v.begin(), v.end(), ostream_iterator<Person&>(cout, "\n"));

	//tri suivant age
	sort(v.begin(), v.end(), person_compare_age);
	cout << "TRI suivant nom : " << endl;
	copy(v.begin(), v.end(), ostream_iterator<Person&>(cout, "\n"));
	return 0;

}
