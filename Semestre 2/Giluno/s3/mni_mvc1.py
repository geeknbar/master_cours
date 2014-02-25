class Model:
     def get_post(self):
          return {"title":"A test","body":"An example.."}
     
class View:
     def display(self,items):
          print 'Title:',items['title'],'\n'+'Body:',items['body']
     
class Controller:
     def __init__(self):
          self.model = Model()
          self.view  = View()
     
     def main(self):
          post = self.model.get_post()
          self.view.display(post)
     
mvc = Controller()
mvc.main()
     