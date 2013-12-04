/*
 * Expression.h
 *
 *  Created on: 20 nov. 2013
 *      Author: dorian
 */

#ifndef EXPRESSION_H_
#define EXPRESSION_H_

using namespace std;
#include "Node.h"

class Expression {
protected:
	char expr;
	//counter pour afficher le nombre d'instance qui ont été créé
	static int counter;

public:
	Expression();
	Expression(char c);
	char get() { return expr; }

	Expression& operator=(const Expression& e){
		++counter;
		if(this != &e){
			expr = e.expr;
		}
		return *this;
	}
	void display(ostream& out);

	friend ostream& operator<<(ostream& out, Expression& e) {e.display(out); return out;}


};

#endif /* EXPRESSION_H_ */
