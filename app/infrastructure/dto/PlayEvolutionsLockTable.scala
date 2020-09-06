package dto
// AUTO-GENERATED Slick data model for table PlayEvolutionsLock
trait PlayEvolutionsLockTable {

  self:Tables  =>

  import profile.api._
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table PlayEvolutionsLock
   *  @param lock Database column lock SqlType(int4), PrimaryKey */
  case class PlayEvolutionsLockRow(lock: Int)
  /** GetResult implicit for fetching PlayEvolutionsLockRow objects using plain SQL queries */
  implicit def GetResultPlayEvolutionsLockRow(implicit e0: GR[Int]): GR[PlayEvolutionsLockRow] = GR{
    prs => import prs._
    PlayEvolutionsLockRow(<<[Int])
  }
  /** Table description of table play_evolutions_lock. Objects of this class serve as prototypes for rows in queries. */
  class PlayEvolutionsLock(_tableTag: Tag) extends profile.api.Table[PlayEvolutionsLockRow](_tableTag, "play_evolutions_lock") {
    def * = lock <> (PlayEvolutionsLockRow, PlayEvolutionsLockRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(lock)).shaped.<>(r => r.map(_=> PlayEvolutionsLockRow(r.get)), (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column lock SqlType(int4), PrimaryKey */
    val lock: Rep[Int] = column[Int]("lock", O.PrimaryKey)
  }
  /** Collection-like TableQuery object for table PlayEvolutionsLock */
  lazy val PlayEvolutionsLock = new TableQuery(tag => new PlayEvolutionsLock(tag))
}
