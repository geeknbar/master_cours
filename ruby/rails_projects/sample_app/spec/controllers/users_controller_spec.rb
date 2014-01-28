# encoding: UTF-8
require 'spec_helper'

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
  end

  describe "POST 'create'" do

    describe "échec" do

      before(:each) do
        @attr = { :nom => "", :email => "", :dateNaissance => nil, :poidsActuel => "", :poidsIdeal => "", :taille => "", :estSportif => "", :souhaitePratiquerSport => "" }
      end

      it "ne devrait pas créer d'utilisateur" do
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
        @attr = { :nom => "New User", :email => "user@example.com", :dateNaissance => @date, :poidsActuel => 60, :poidsIdeal => 40, :taille => 170, :estSportif => :true, :souhaitePratiquerSport => :false}
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
