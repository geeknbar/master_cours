#ifndef EXPRESSION_H
#define EXPRESSION_H

#include <vector>
#include <set>
#include <map>
#include <iterator>
#include <iostream>
#include <algorithm>
#include <functional>
#include <numeric>
#include <cassert>

using namespace std;

class Expression
{
protected:
    char expr;
public:
    Expression() {expr=' ';}
    Expression(char c) {expr=c;}
    char get() {return expr;}
    void display(ostream& out) {out << expr;}
    friend ostream& operator<<(ostream& out, Expression& p) {p.display(out); return out;}
};

#endif // EXPRESSION_H
