#ifndef MA_LIB_H
#define MA_LIB_H

#define container_print(v) \
	cout << "(size = " << v.size() << ") => "; \
	copy(v.begin(), v.end(), ostream_iterator<int>(cout, " ")); \
	cout << endl;
	
#endif	
	
