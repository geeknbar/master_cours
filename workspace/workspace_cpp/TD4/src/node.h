/*
 * node.h
 *
 *  Created on: 23 oct. 2013
 *      Author: dorian
 */

#ifndef NODE_H_
#define NODE_H_

#include <vector>
#include <string>
#include <iostream>
#include <algorithm>

using namespace std;
template<class T>
class NodePolicy{
public:
	static void print(ostream& out, T& value){
		out << "$" << value;
	}
	static void print(ostream& out, T* value){
		out << "*" << *value;
	}
};


template<class T, typename NodePolicy>
class Node{
protected:
	Node<T, NodePolicy> *parent;
	vector<Node<T, NodePolicy>*> offspring;
	T value;
public:
	//	ce constructeur n'est pas viable car on ne peut pas savoir la valeur que l'utilisateur veut
	//  on pourait mettre valeur = 0; mais si l'utilisateur utilise des valeur Personne cela ne fonctionne plus;
	//	Node(){
	//		parent = NULL;
	//	};

	Node(T v){
		parent = NULL;
		value = v;
	}

	void add(Node<T,NodePolicy> *node){
		offspring.push_back(node);
	}

	// parcours infix
	//	 2
	//	/ \
	// 1   3   donne 1 2 3
	void print(ostream& out){
		if(offspring.size()==0){
			//leaf
			//			out << " "<< value;
			NodePolicy::print(out, value);
		}else{
			offspring[0]->print(out);//gauche, dans le vector
			//			out << " "<< value;
			NodePolicy::print(out, value);
			offspring[1]->print(out);//droite
		}
	}

	Node<T,NodePolicy> *clone(){
		Node<T,NodePolicy> *newNode;
		if(offspring.size()==0){
			newNode = new Node<T,NodePolicy>(value);
		}else{
			newNode = new Node<T,NodePolicy>(value);
			typename vector<Node<T,NodePolicy> *>::iterator iter;
			for (iter = offspring.begin(); iter != offspring.end(); ++iter) {
				newNode->add((*iter)->clone());
			}
		}

		return newNode;
	}

	friend ostream& operator << (ostream& out, Node<T, NodePolicy>&Node){
		Node.print(out);
		return out;
	}


};


#endif /* NODE_H_ */
