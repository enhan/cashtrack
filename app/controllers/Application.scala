package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import models.Account
import play.api.libs.functional.syntax._

object Application extends Controller {
  
  def accounts = Action{
    Ok(Json.toJson(Account.all()))
  }

  def newAccount = Action(parse.json){ request =>
    val a = Json.fromJson[Account](request.body).get
    Account.create(a)
    Ok
  }

  def deleteAccount(id:Long) = TODO



}