package dto
// AUTO-GENERATED Slick data model for table Hunters
trait HuntersTable {

  self:Tables  =>

  import profile.api._
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Hunters
   *  @param id Database column id SqlType(text), PrimaryKey
   *  @param name Database column name SqlType(text)
   *  @param life Database column life SqlType(bigserial), AutoInc
   *  @param defensePower Database column defense_power SqlType(bigserial), AutoInc
   *  @param offensePower Database column offense_power SqlType(bigserial), AutoInc */
  case class HuntersRow(id: String, name: String, life: Long, defensePower: Long, offensePower: Long)
  /** GetResult implicit for fetching HuntersRow objects using plain SQL queries */
  implicit def GetResultHuntersRow(implicit e0: GR[String], e1: GR[Long]): GR[HuntersRow] = GR{
    prs => import prs._
    HuntersRow.tupled((<<[String], <<[String], <<[Long], <<[Long], <<[Long]))
  }
  /** Table description of table hunters. Objects of this class serve as prototypes for rows in queries. */
  class Hunters(_tableTag: Tag) extends profile.api.Table[HuntersRow](_tableTag, "hunters") {
    def * = (id, name, life, defensePower, offensePower) <> (HuntersRow.tupled, HuntersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(name), Rep.Some(life), Rep.Some(defensePower), Rep.Some(offensePower))).shaped.<>({r=>import r._; _1.map(_=> HuntersRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(text), PrimaryKey */
    val id: Rep[String] = column[String]("id", O.PrimaryKey)
    /** Database column name SqlType(text) */
    val name: Rep[String] = column[String]("name")
    /** Database column life SqlType(bigserial), AutoInc */
    val life: Rep[Long] = column[Long]("life", O.AutoInc)
    /** Database column defense_power SqlType(bigserial), AutoInc */
    val defensePower: Rep[Long] = column[Long]("defense_power", O.AutoInc)
    /** Database column offense_power SqlType(bigserial), AutoInc */
    val offensePower: Rep[Long] = column[Long]("offense_power", O.AutoInc)
  }
  /** Collection-like TableQuery object for table Hunters */
  lazy val Hunters = new TableQuery(tag => new Hunters(tag))
}
