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
 * Date: 25/04/13
 * Time: 14:32
 * To change this template use File | Settings | File Templates.
 */
case class Operation(id:Pk[Long], label:String, diff:Long, account:Long)

object Operation{

  val operation = {
    get[Pk[Long]]("id") ~
    get[String]("label") ~
    get[Long]("diff") ~
    get[Long]("account") map {
      case id~label~diff~account => Operation(id, label, diff, account)
    }

  }

  def allForAccount(idAccount: Long):List[Operation] = DB.withConnection{
    implicit c => SQL(s"select * from operation where account = $idAccount").as(operation *)
  }

  implicit val operationFormat = (
    (__ \ "id").formatNullable[Long] and
      (__ \ "label").format[String] and
      (__ \ "diff").format[Long] and
      (__ \ "account").format[Long]
    )(
    (id, label, diff, account)=>Operation(id.map(Id(_)).getOrElse(NotAssigned), label, diff, account),
    (o : Operation) => (o.id.toOption, o.label,o.diff, o.account)
  )



}
