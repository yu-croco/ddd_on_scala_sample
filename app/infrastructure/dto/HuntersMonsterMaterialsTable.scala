package dto
// AUTO-GENERATED Slick data model for table HuntersMonsterMaterials
trait HuntersMonsterMaterialsTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table HuntersMonsterMaterials
   *  @param id Database column id SqlType(bigserial), AutoInc, PrimaryKey
   *  @param hunterId Database column hunter_id SqlType(int8)
   *  @param monsterMaterialsId Database column monster_materials_id SqlType(int8) */
  case class HuntersMonsterMaterialsRow(id: Long, hunterId: Long, monsterMaterialsId: Long)
  /** GetResult implicit for fetching HuntersMonsterMaterialsRow objects using plain SQL queries */
  implicit def GetResultHuntersMonsterMaterialsRow(implicit e0: GR[Long]): GR[HuntersMonsterMaterialsRow] = GR{
    prs => import prs._
    HuntersMonsterMaterialsRow.tupled((<<[Long], <<[Long], <<[Long]))
  }
  /** Table description of table hunters_monster_materials. Objects of this class serve as prototypes for rows in queries. */
  class HuntersMonsterMaterials(_tableTag: Tag) extends profile.api.Table[HuntersMonsterMaterialsRow](_tableTag, "hunters_monster_materials") {
    def * = (id, hunterId, monsterMaterialsId) <> (HuntersMonsterMaterialsRow.tupled, HuntersMonsterMaterialsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(hunterId), Rep.Some(monsterMaterialsId))).shaped.<>({r=>import r._; _1.map(_=> HuntersMonsterMaterialsRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(bigserial), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column hunter_id SqlType(int8) */
    val hunterId: Rep[Long] = column[Long]("hunter_id")
    /** Database column monster_materials_id SqlType(int8) */
    val monsterMaterialsId: Rep[Long] = column[Long]("monster_materials_id")

    /** Foreign key referencing Hunters (database name hunters_monster_materials_hunter_id_fkey) */
    lazy val huntersFk = foreignKey("hunters_monster_materials_hunter_id_fkey", hunterId, Hunters)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing MonsterMaterials (database name hunters_monster_materials_monster_materials_id_fkey) */
    lazy val monsterMaterialsFk = foreignKey("hunters_monster_materials_monster_materials_id_fkey", monsterMaterialsId, MonsterMaterials)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table HuntersMonsterMaterials */
  lazy val HuntersMonsterMaterials = new TableQuery(tag => new HuntersMonsterMaterials(tag))
}
