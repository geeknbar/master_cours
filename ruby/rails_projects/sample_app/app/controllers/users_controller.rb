require 'user_statistics'

class UsersController < ApplicationController

  def new
    @user = User.new
    @titre = "Inscription"
  end

  def show
    @user = User.find(params[:id])
  end

  def index
    @titre = "Liste des utilisateurs"
    @users = User.all
    @user_statistics = UserStatistics.new(@users)

    respond_to do |format|
      format.html
      format.pdf
    end
  end

	def create
    @user = User.new(params[:user])
    if @user.save
      flash[:success] = "Bienvenue dans l'Application Exemple!"
      redirect_to @user
    else
      @titre = "Inscription"
      render 'new'
    end
  end

  def sportif
    @users = User.where(estSportif: false, souhaitePratiquerSport: true)
    render 'index'
  end

end
