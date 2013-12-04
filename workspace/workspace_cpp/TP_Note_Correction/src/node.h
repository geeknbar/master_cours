#ifndef NODE_H
#define NODE_H

#include <vector>
#include <set>
#include <map>
#include <iterator>
#include <iostream>
#include <algorithm>
#include <functional>
#include <numeric>

#include "expression.h"

using namespace std;

template<class T>
class NodePolicy {
    public:
        static void print(ostream& out, T& value) {
            out << value;
        }

        static void print(ostream& out, T* value) {
            out << *value;
        }
};

template<class T, typename NodePolicy>
class Node {
protected:
    Node<T, NodePolicy> *parent;
    vector<Node<T, NodePolicy> *> offspring;
    T value;
public:
    Node() {
        parent = NULL;
    }
    Node(T v) {
        parent = NULL;
        value = v;
    }
    void add(Node<T, NodePolicy> *node) {
        offspring.push_back(node);
        node->parent = this;
    }
    void print(ostream& out) {
        if(offspring.size() == 0) {
            NodePolicy::print(out, value);
        } else {
            if(offspring.size() == 1) {
                NodePolicy::print(out, value);
                offspring[0]->print(out);
            } else {
                if (offspring.size() == 2) {
                    offspring[0]->print(out);
                    NodePolicy::print(out, value);
                    offspring[1]->print(out);
                }
            }
        }
    }

    T get_value() {
        return value;
    }
    Node<T, NodePolicy> * get_left() {
        return offspring[0];
    }
    Node<T, NodePolicy> * get_right() {
        return offspring[1];
    }

    Node<T, NodePolicy> *clone();
    friend ostream& operator<<(ostream& out, Node<T, NodePolicy>& node) {
        node.print(out);
        return out;
    }
};

#endif // NODE_H
