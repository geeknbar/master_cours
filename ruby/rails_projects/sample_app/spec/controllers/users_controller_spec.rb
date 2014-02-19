# encoding: UTF-8
require 'spec_helper'
require 'rack/test'

describe UsersController do
	render_views
	
  describe "GET 'new'" do
    it "devrait réussir" do
      get 'new'
      response.should be_success
    end

    it "devrait avoir le titre adéquat" do
      get 'new'
      response.should have_selector("title", :content => "Inscription")
    end

    it "devrait avoir un champ nom" do
      get 'new'
      response.should have_selector("input[name='user[nom]'][type='text']")
    end

    it "devrait avoir un champ email" do
      get 'new'
      response.should have_selector("input[name='user[email]'][type='text']")
    end

    it "devrait avoir un champ dateNaissance" do
      get 'new'
      response.should have_selector("select[name='user[dateNaissance(1i)]']")
    end

       it "devrait avoir un champ MoisNaissance" do
      get 'new'
      response.should have_selector("select[name='user[dateNaissance(1i)]']")
    end

    it "devrait avoir un champ JourNaissance" do
      get 'new'
      response.should have_selector("select[name='user[dateNaissance(3i)]']")
    end

    it "devrait avoir un champ poidsActuel" do
      get 'new'
      response.should have_selector("input[name='user[poidsActuel]'][type='text']")
    end

    it "devrait avoir un champ poidsIdeal" do
      get 'new'
      response.should have_selector("input[name='user[poidsIdeal]'][type='text']")
    end

    it "devrait avoir un champ taille" do
      get 'new'
      response.should have_selector("input[name='user[taille]'][type='text']")
    end

    it "devrait avoir un champ estSportif" do
      get 'new'
      response.should have_selector("input[name='user[estSportif]'][type='radio']")
    end

    it "devrait avoir un champ souhaitePratiquerSport" do
      get 'new'
      response.should have_selector("input[name='user[souhaitePratiquerSport]'][type='radio']")
    end

    it "devrait avoir un champ cv" do
      get 'new'
      response.should have_selector("input[name='user[cvpdf]'][type='file']")
    end
  end

  describe "GET 'index'" do

    before(:each) do
      @date = Date.new(2001,2,3)
      @cv_exemple = Rack::Test::UploadedFile.new('spec/fixtures/files/cv_exemple.pdf')

      first  = User.create!({ :nom => "New User1", :email => "user1@example.com", :dateNaissance => @date, :poidsActuel => 70,
              :poidsIdeal => 65, :taille => 176, :estSportif => true, :souhaitePratiquerSport => false, :cvpdf => @cv_exemple})
      second = User.create!({ :nom => "New User2", :email => "user2@example.com", :dateNaissance => @date, :poidsActuel => 105,
              :poidsIdeal => 100, :taille => 190, :estSportif => true, :souhaitePratiquerSport => false, :cvpdf => nil})
      third  = User.create!({ :nom => "New User3", :email => "user3@example.com", :dateNaissance => @date, :poidsActuel => 60,
              :poidsIdeal => 55, :taille => 170, :estSportif => true, :souhaitePratiquerSport => false, :cvpdf => nil})

      @users = [first, second, third]



    end

    it "devrait réussir" do
      get :index
      response.should be_success
    end

    it "devrait avoir le bon titre" do
      get :index
      response.should have_selector("title", :content => "Liste des utilisateurs")
    end

    it "devrait avoir un élément pour chaque utilisateur" do
      get :index
      @users.each do |user|
        response.should have_selector("td", :content => user.nom)
        response.should have_selector("td", :content => user.email)
        response.should have_selector("td", :content => user.dateNaissance.to_s)
        response.should have_selector("td", :content => user.poidsActuel.to_s)
        response.should have_selector("td", :content => user.poidsIdeal.to_s)
        response.should have_selector("td", :content => user.taille.to_s)
        response.should have_selector("td", :content => user.estSportif ? "oui" : "non")
        response.should have_selector("td", :content => user.souhaitePratiquerSport ? "oui" : "non")
        response.should have_selector("td", :content => "Download") if user.cvpdf.size

      end
    end
  end

  describe "POST 'create'" do

    describe "échec" do

      before(:each) do
        @attr = { :nom => "", :email => "", :dateNaissance => nil, :poidsActuel => "",
                  :poidsIdeal => "", :taille => "", :estSportif => "", :souhaitePratiquerSport => "", :cvpdf => nil }
      end

      it "ne devrait pas créer d'utilisateur avec un nom et un email vide" do
        lambda do
          post :create, :user => @attr
        end.should_not change(User, :count)
      end

      it "devrait avoir le bon titre" do
        post :create, :user => @attr
        response.should have_selector("title", :content => "Inscription")
      end

      it "devrait rendre la page 'new'" do
        post :create, :user => @attr
        response.should render_template('new')
      end
    end


    describe "succès" do

      before(:each) do
        @date = Date.today
        @cv_exemple = Rack::Test::UploadedFile.new('spec/fixtures/files/cv_exemple.pdf')
        @attr = { :nom => "New User", :email => "user@example.com", :dateNaissance => @date, :poidsActuel => 60,
                  :poidsIdeal => 40, :taille => 170, :estSportif => :true, :souhaitePratiquerSport => :false, :cvpdf =>@cv_exemple}
      end

      it "devrait créer un utilisateur" do
        lambda do
          post :create, :user => @attr
        end.should change(User, :count).by(1)
      end

      it "devrait rediriger vers la page d'affichage de l'utilisateur" do
        post :create, :user => @attr
        response.should redirect_to(user_path(assigns(:user)))
      end

      it "devrait avoir un message de bienvenue" do
        post :create, :user => @attr
        flash[:success].should =~ /Bienvenue dans l'Application Exemple/i
      end
    end
  end
end
