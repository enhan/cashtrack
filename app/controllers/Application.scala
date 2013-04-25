package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import models.Account
import models.Operation
import play.api.libs.functional.syntax._

object Application extends Controller {
  
  def accounts = Action{ request =>
    Ok(Json.toJson(Account.all()))
  }

  def newAccount = Action(parse.json){ request =>
    val a = Json.fromJson[Account](request.body).get
    Account.create(a)
    Ok
  }

  def deleteAccount(id:Long) = Action{
    Account.delete(id)
    Ok
  }


  def account(id:Long) = Action{ request =>
    Ok(Json.toJson(Account.byId(id)))
  }

  def operations(id:Long) = Action{ request =>
    Ok(Json.toJson(Operation.allForAccount(id)))
  }

  def newOperation(id:Long) = Action(parse.json){ request =>
    Operation.create(Json.fromJson[Operation](request.body).get)
    Ok
  }

}