package models

import play.api.Play.current
import anorm._
import anorm.SqlParser._

import play.api.libs.json._

import play.api.libs.functional.syntax._
import play.api.db.DB

/**
 * Created with IntelliJ IDEA.
 * User: ENHAN
 * Date: 24/04/13
 * Time: 11:07
 * To change this template use File | Settings | File Templates.
 */
case class Account(id:Pk[Long], name:String, initialBalance:Long)

object Account{



  val account = {
    get[Pk[Long]]("id") ~
    get[String]("name")~
    get[Long]("initialBalance") map {
      case id~name~initialBalance => Account(id, name, initialBalance)
    }
  }

  def all():List[Account] = DB.withConnection {
    implicit c => SQL("select * from account").as(account *)
  }

  def create(name:String, initialBalance:Long){
    DB.withConnection {
      implicit c =>
        SQL(s"insert into account(name, initialBalance) values ('${name}', ${initialBalance})").executeUpdate()
    }
  }

  def create(account : Account){
    create(account.name, account.initialBalance)
  }

  def delete(id : Long){
    DB.withConnection{
      implicit c =>
        SQL(s"delete from account where id = $id").executeUpdate()
    }
  }

  def byId(l: Long):Option[Account] = DB.withConnection{
    implicit c => SQL("select * from account").as(account.singleOpt)
  }


  implicit  val accountFormat = (
    (__ \ "id").formatNullable[Long] and
    (__ \ "name").format[String] and
      (__ \ "initialBalance").format[Long]
    )(
      (id, name, initialBalance) => Account(id.map(Id(_)).getOrElse(NotAssigned), name, initialBalance ),
      (a:Account) => (a.id.toOption, a.name, a.initialBalance)
    )




}


