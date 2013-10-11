#include <iostream>
#include <string>
#include <algorithm>
#include <vector>


using namespace std;

const char *white_spaces= " \t\n\r";

//question 1
void trim_left(string& s) {
	string::size_type pos = s.find_first_not_of(white_spaces);
	if(pos == string::npos) return;
	s.erase(0, pos);
}

//question 2
void trim_right(string& s) {
	string::size_type pos = s.find_last_not_of(white_spaces);
	if(pos == string::npos) return;
	s.erase(pos +1, string::npos);
}

//question 3
void trim(string& s){
	trim_left(s);
	trim_right(s);
}

void to_upper(string& s){
	//Exemple doc
	//std::transform(s.begin(), s.end(), s.begin(), std::ptr_fun<int, int>(std::toupper));

	//Exemple prof
	transform(s.begin(), s.end(), s.begin(), (int (*) (int)) std::toupper);
}

void explode(string& s, vector<string>& v, const string& delim){
	v.clear();
	string::size_type first_pos, last_pos, word_size;

	first_pos=0;
	last_pos=0;
	while(1){
		first_pos = s.find_first_not_of(delim, last_pos);
		if (first_pos == string::npos) break;
		last_pos = s.find_first_of(delim, first_pos);

		cout << "first pos = " << first_pos << endl;
		cout << "last pos = " << last_pos << endl;
		if(last_pos != string::npos){
			word_size = last_pos - first_pos +1;
		}else{
			word_size = last_pos;
		}
		string word = s.substr(first_pos, last_pos - first_pos +1);
		cout << "word = " << word << endl;
		v.push_back(word);
		if (last_pos == string::npos) break;
		++last_pos;
	}
}

void replace(string& s, string& from, string& to){

}

void test_trim_left() {
	string s = "\t \n bonjour \t  \n";

	cout << "s left = [" << s << "]" << endl;
	trim_left(s);
	cout << "s final left = [" << s << "]" << endl;
}

void test_trim_right() {

	string s = "\t \n bonjour \t  \n";

	cout << "s right = [" << s << "]" << endl;
	trim_right(s);
	cout << "s final right = [" << s << "]" << endl;
}

void test_explode(){
	string s = "\t \n bonjour les loulou\t  \n";
	vector<string> words;
	string delimiters = white_spaces;

	explode(s, words, delimiters);

	int i=1;
	vector<string>::iterator iter;
	for(iter = words.begin(); iter != words.end(); ++iter){
		cout << i << ":" << (*iter) << endl;
		 ++i;
	}
}
int main() {
	string s = "\t \n bonjour \t  \n";
	cout << "s start = [" << s << "]" << endl;
	trim(s);
	cout << "s end = [" << s << "]" << endl;
	to_upper(s);
	cout << "s upper = [" << s << "]" << endl;
	test_explode();
	return 0;
}
