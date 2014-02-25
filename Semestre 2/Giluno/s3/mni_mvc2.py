class View():
     def user(self,users):
          print(users.find('two'))

class Control:
     def find(self,user):
          return self._look(user)

     def _look(self,user):
          if user in self.users:
               return self.users[user]
          else:
               return 'The data class ({}) has no {}'.format(self.userName(),user)
     
     def userName(self):
          return self.__class__.__name__.lower()

class Model(Control):
     users=dict(one='Bob',two='Michael',three='Dave')

def main():
     users=Model()
     find=View()
     print('--> The user two\'s "real name" is:\n')
     find.user(users)

if __name__=="__main__":
     main()