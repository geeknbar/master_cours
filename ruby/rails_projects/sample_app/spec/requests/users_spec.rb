require 'spec_helper'

describe "Users" do
  describe "une inscription" do

    describe "ratée" do

      it "ne devrait pas créer un nouvel utilisateur" do
        lambda do
          visit signup_path
          fill_in "Nom", :with => ""
          fill_in "email", :with => ""
          select "2014", :from => "user[dateNaissance(1i)]"
          select "January", :from => "user[dateNaissance(2i)]"
          select "16", :from => "user[dateNaissance(3i)]"
          fill_in "poidsActuel", :with => ""
          fill_in "poidsIdeal", :with => ""
          fill_in "taille", :with => ""
          choose("user_estSportif_1")
          choose("user_souhaitePratiquerSport_1")
          click_button
          response.should render_template('users/new')
          response.should have_selector("div#error_explanation")
        end.should_not change(User, :count)
      end
    end

    describe "réussie" do

      it "devrait créer un nouvel utilisateurr" do
        lambda do
          visit signup_path
          fill_in "Nom", :with => "Example User"
          fill_in "eMail", :with => "user@example.com"
          select "2014", :from => "user[dateNaissance(1i)]"
          select "January", :from => "user[dateNaissance(2i)]"
          select "16", :from => "user[dateNaissance(3i)]"
          fill_in "poidsActuel", :with => 50
          fill_in "poidsIdeal", :with => 70
          fill_in "taille", :with => 100
          choose("user_estSportif_1")
          choose("user_souhaitePratiquerSport_1")
          click_button
          response.should have_selector("div.flash.success",
                                        :content => "Bienvenue")
          response.should render_template('users/show')
        end.should change(User, :count).by(1)
      end
    end
  end
end
