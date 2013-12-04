/*
 * Expression.cpp
 *
 *  Created on: 20 nov. 2013
 *      Author: dorian
 */

#include "Expression.h"

using namespace std;


int Expression::counter = 0;
Expression::Expression() {
	expr = ' ';
	++counter;
}

Expression::Expression(char c) {
	expr = c;
	++counter;
}

void Expression::display(ostream& out) {
	out << "[EXPRESSION]" << " " << expr;
}
