#include "node.h"
#include "expression.h"

using namespace std;

template<>
class NodePolicy<Expression> {
public:
    static void print(ostream& out, Expression& value) {
        out << value;
    }

    static void print(ostream& out, Expression* value) {
        out << *value;
    }
};

typedef Node<Expression, NodePolicy<Expression> > ENode;

enum {
    ZERO =  '0',
    ONE = '1',
    OR = '|',
    AND = '&',
    XOR = '^',
    NOT = '-'
};

ENode *generate_tree(vector<char>& v) {
    ENode *n;

    if (v.size() == 0) throw exception();
    char c = v[ v.size() - 1 ];
    v.pop_back();
    n = new ENode(Expression(c));

    if ((c == AND) || (c == OR) || (c == XOR)) {
        ENode *left = generate_tree(v);
        ENode *right = generate_tree(v);
        n->add(left);
        n->add(right);
    } else if (c == NOT) {
        ENode *left = generate_tree(v);
        n->add(left);
    }

    assert(n != NULL);
    return n;
}

int interpret_tree(ENode *n) {
    int v = 0;

    switch(n->get_value().get()) {
    case ZERO: v = 0; break;
    case ONE: v = 1; break;
    case AND: v = interpret_tree(n->get_left()) & interpret_tree(n->get_right()); break;
    case OR: v = interpret_tree(n->get_left()) | interpret_tree(n->get_right()); break;
    case XOR: v = interpret_tree(n->get_left()) ^ interpret_tree(n->get_right()); break;
    case NOT: v = interpret_tree(n->get_left()); v = (v == 1) ? 0: 1;  break;
    };
    return v;
}

int main() {
    vector<char> v = {'1','0','-','&','0','1','1','&','|','^'};
    ENode *root = generate_tree(v);
    cout << *root << "\n";
    int r = interpret_tree(root);
    cout << "r = " << r << endl;
    return 0;
}
