package dto
// AUTO-GENERATED Slick data model for table MonsterMaterials
trait MonsterMaterialsTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table MonsterMaterials
   *  @param id Database column id SqlType(text), PrimaryKey
   *  @param name Database column name SqlType(text)
   *  @param rarity Database column rarity SqlType(bigserial), AutoInc
   *  @param monsterId Database column monster_id SqlType(text) */
  case class MonsterMaterialsRow(id: String, name: String, rarity: Long, monsterId: String)
  /** GetResult implicit for fetching MonsterMaterialsRow objects using plain SQL queries */
  implicit def GetResultMonsterMaterialsRow(implicit e0: GR[String], e1: GR[Long]): GR[MonsterMaterialsRow] = GR{
    prs => import prs._
    MonsterMaterialsRow.tupled((<<[String], <<[String], <<[Long], <<[String]))
  }
  /** Table description of table monster_materials. Objects of this class serve as prototypes for rows in queries. */
  class MonsterMaterials(_tableTag: Tag) extends profile.api.Table[MonsterMaterialsRow](_tableTag, "monster_materials") {
    def * = (id, name, rarity, monsterId) <> (MonsterMaterialsRow.tupled, MonsterMaterialsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(name), Rep.Some(rarity), Rep.Some(monsterId))).shaped.<>({r=>import r._; _1.map(_=> MonsterMaterialsRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(text), PrimaryKey */
    val id: Rep[String] = column[String]("id", O.PrimaryKey)
    /** Database column name SqlType(text) */
    val name: Rep[String] = column[String]("name")
    /** Database column rarity SqlType(bigserial), AutoInc */
    val rarity: Rep[Long] = column[Long]("rarity", O.AutoInc)
    /** Database column monster_id SqlType(text) */
    val monsterId: Rep[String] = column[String]("monster_id")

    /** Foreign key referencing Monsters (database name monster_materials_monster_id_fkey) */
    lazy val monstersFk = foreignKey("monster_materials_monster_id_fkey", monsterId, Monsters)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table MonsterMaterials */
  lazy val MonsterMaterials = new TableQuery(tag => new MonsterMaterials(tag))
}
