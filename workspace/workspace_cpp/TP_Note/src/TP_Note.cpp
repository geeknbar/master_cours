//============================================================================
// Name        : TP_Note.cpp
// Author      : Dorian Coffinet
// Version     : 1.0
// Description : TP Note, UE1 Genie Logiciel
//============================================================================

#include <iostream>
using namespace std;
#include "Expression.h"
#include "Node.h"


template<>
class NodePolicy<char>{
public:
	static void print(ostream& out, char& value){
		out << "$i" << value;
	}
	static void print(ostream& out, char* value){
		out << "*i" << *value;
	}

};

template<>
class NodePolicy<Expression>{
public:
	static void print(ostream& out, Expression& value){
		out << "$i" << value;
	}
	static void print(ostream& out, Expression* value){
		out << "*i" << *value;
	}

};

Node<Expression, NodePolicy<Expression>>generate_tree(vector<char>& v){
	Node<Expression,NodePolicy<Expression>> *arbre(Expression());
	vector<char> v_temp = v;

	Expression faux = Expression('0');
	Expression vrai = Expression('1');
	Expression et = Expression('&');
	Expression ou = Expression('|');
	Expression ou_exclusif = Expression('^');
	Expression non = Expression('-');


	while(v_temp.empty()){
		char c = v_temp[0];
		Node<Expression,NodePolicy<Expression>> n(Expression(c));
		if(v_temp.size()== v.size()){
			arbre->add(&n);
			v_temp.pop_back();
		}else{
			if(c == non.get()){

			}else{
				arbre.add(&n);
			}
		}
	}
	return arbre;
}
// Interpréter l'expression revient à faire un parcours infix
void interpret_tree(Node<Expression, NodePolicy<Expression> > *node){
	if(node->offspring.size()==0){
		node->value;
	}else{
		interpret_tree(node->offspring[0]);//gauche
		node->value;
		interpret_tree(node->offspring[1]);//droite
	}
}



int main() {

	vector<char> v = { '1', '0', '-', '&', '0', '1', '1', '&', '|', '^'};
	Node<Expression,NodePolicy<Expression>> root = generate_tree(v);
	cout << root;

	Node<Expression, NodePolicy<Expression> > *clone = root->clone();
	cout << interpret_tree(clone);
	return 0;
}
