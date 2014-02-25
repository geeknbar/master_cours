class Model
     def get_post
          {:title => "A test",:body =>"An example.."}
     end
end

class View
     def display(items)
          puts "Title:" + items[:title] + "\n" +'Body:' + items[:body]
     end
end     
class Controller
     def initialize
          @model = Model.new
          @view  = View.new
     end
     def main
          post = @model.get_post
          @view.display(post)
     end
end

mvc = Controller.new
mvc.main
     