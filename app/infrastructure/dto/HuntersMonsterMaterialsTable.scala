package dto
// AUTO-GENERATED Slick data model for table HuntersMonsterMaterials
trait HuntersMonsterMaterialsTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table HuntersMonsterMaterials
   *  @param id Database column id SqlType(text), PrimaryKey
   *  @param hunterId Database column hunter_id SqlType(text)
   *  @param monsterMaterialsId Database column monster_materials_id SqlType(text) */
  case class HuntersMonsterMaterialsRow(id: String, hunterId: String, monsterMaterialsId: String)
  /** GetResult implicit for fetching HuntersMonsterMaterialsRow objects using plain SQL queries */
  implicit def GetResultHuntersMonsterMaterialsRow(implicit e0: GR[String]): GR[HuntersMonsterMaterialsRow] = GR{
    prs => import prs._
    HuntersMonsterMaterialsRow.tupled((<<[String], <<[String], <<[String]))
  }
  /** Table description of table hunters_monster_materials. Objects of this class serve as prototypes for rows in queries. */
  class HuntersMonsterMaterials(_tableTag: Tag) extends profile.api.Table[HuntersMonsterMaterialsRow](_tableTag, "hunters_monster_materials") {
    def * = (id, hunterId, monsterMaterialsId) <> (HuntersMonsterMaterialsRow.tupled, HuntersMonsterMaterialsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(hunterId), Rep.Some(monsterMaterialsId))).shaped.<>({r=>import r._; _1.map(_=> HuntersMonsterMaterialsRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(text), PrimaryKey */
    val id: Rep[String] = column[String]("id", O.PrimaryKey)
    /** Database column hunter_id SqlType(text) */
    val hunterId: Rep[String] = column[String]("hunter_id")
    /** Database column monster_materials_id SqlType(text) */
    val monsterMaterialsId: Rep[String] = column[String]("monster_materials_id")

    /** Foreign key referencing Hunters (database name hunters_monster_materials_hunter_id_fkey) */
    lazy val huntersFk = foreignKey("hunters_monster_materials_hunter_id_fkey", hunterId, Hunters)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing MonsterMaterials (database name hunters_monster_materials_monster_materials_id_fkey) */
    lazy val monsterMaterialsFk = foreignKey("hunters_monster_materials_monster_materials_id_fkey", monsterMaterialsId, MonsterMaterials)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table HuntersMonsterMaterials */
  lazy val HuntersMonsterMaterials = new TableQuery(tag => new HuntersMonsterMaterials(tag))
}
