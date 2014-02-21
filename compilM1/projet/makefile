CC = gcc
CFLAGS = -g3 -Wall -c -fmessage-length=0
EXEC_NAME = main
INCLUDES = 
LIBS = 
OBJ_FILES = main.o tableSymboles.o lex.yy.o
FLEX_FILES = lex.yy.c a.out
LEX=flex

all : $(EXEC_NAME)

clean :
	rm $(EXEC_NAME) $(OBJ_FILES) $(FLEX_FILES)

$(EXEC_NAME) : $(OBJ_FILES)
	$(CC) -o $(EXEC_NAME) $(OBJ_FILES) $(LIBS)

%.o: %.c
	gcc $(CFLAGS) $(INCLUDES) -o $@ -c $<

lex.yy.c: exercice4.l
	flex exercice4.l

a.out: lex.yy.c tableSymboles.c
	gcc lex.yy.c tableSymboles.c -lfl  