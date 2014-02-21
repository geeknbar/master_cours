NAME = program
SRC = tableSymboles.c
OBJ = $(SRC:.c=.o)
LIBS = -lfl
CFLAGS = -W -Wall
FLEXFILE = lex.yy.c
RULESFILE = exercice4.l
CC = gcc
LEX = flex
$(NAME): $(FLEXFILE) $(OBJ)
	$(CC) -o $(NAME) $(FLEXFILE) $(OBJ) $(LIBS) $(CFLAGS)
lex.yy.c:
	$(LEX) $(RULESFILE)
clean:
	rm $(NAME) $(FLEXFILE) *.o 