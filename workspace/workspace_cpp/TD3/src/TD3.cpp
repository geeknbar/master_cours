using namespace std;

class Person {

public:
	String name;
	int age;

	Person();

	Person(String n, int a);

	String getName();
	int getAge();
};


Person(String n, int a){
	name = n;
	age = a;
};
