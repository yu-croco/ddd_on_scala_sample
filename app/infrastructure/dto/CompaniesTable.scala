package dto
// AUTO-GENERATED Slick data model for table Companies
trait CompaniesTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Companies
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(text) */
  case class CompaniesRow(id: Long, name: String)
  /** GetResult implicit for fetching CompaniesRow objects using plain SQL queries */
  implicit def GetResultCompaniesRow(implicit e0: GR[Long], e1: GR[String]): GR[CompaniesRow] = GR{
    prs => import prs._
    CompaniesRow.tupled((<<[Long], <<[String]))
  }
  /** Table description of table companies. Objects of this class serve as prototypes for rows in queries. */
  class Companies(_tableTag: Tag) extends profile.api.Table[CompaniesRow](_tableTag, "companies") {
    def * = (id, name) <> (CompaniesRow.tupled, CompaniesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(name))).shaped.<>({r=>import r._; _1.map(_=> CompaniesRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(text) */
    val name: Rep[String] = column[String]("name")
  }
  /** Collection-like TableQuery object for table Companies */
  lazy val Companies = new TableQuery(tag => new Companies(tag))
}
