package dto
// AUTO-GENERATED Slick data model for table Tasks
trait TasksTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Tasks
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(text)
   *  @param detail Database column detail SqlType(text)
   *  @param userId Database column user_id SqlType(int8) */
  case class TasksRow(id: Long, name: String, detail: String, userId: Long)
  /** GetResult implicit for fetching TasksRow objects using plain SQL queries */
  implicit def GetResultTasksRow(implicit e0: GR[Long], e1: GR[String]): GR[TasksRow] = GR{
    prs => import prs._
    TasksRow.tupled((<<[Long], <<[String], <<[String], <<[Long]))
  }
  /** Table description of table tasks. Objects of this class serve as prototypes for rows in queries. */
  class Tasks(_tableTag: Tag) extends profile.api.Table[TasksRow](_tableTag, "tasks") {
    def * = (id, name, detail, userId) <> (TasksRow.tupled, TasksRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(name), Rep.Some(detail), Rep.Some(userId))).shaped.<>({r=>import r._; _1.map(_=> TasksRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(text) */
    val name: Rep[String] = column[String]("name")
    /** Database column detail SqlType(text) */
    val detail: Rep[String] = column[String]("detail")
    /** Database column user_id SqlType(int8) */
    val userId: Rep[Long] = column[Long]("user_id")

    /** Foreign key referencing Users (database name tasks_user_id_fkey) */
    lazy val usersFk = foreignKey("tasks_user_id_fkey", userId, Users)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Tasks */
  lazy val Tasks = new TableQuery(tag => new Tasks(tag))
}
