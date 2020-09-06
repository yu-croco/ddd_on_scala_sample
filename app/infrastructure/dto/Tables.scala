package dto
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.PostgresProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.)
    Each generated XXXXTable trait is mixed in this trait hence allowing access to all the TableQuery lazy vals.
  */
trait Tables extends MonsterMaterialsTable with PlayEvolutionsTable with PlayEvolutionsLockTable with HuntersMonsterMaterialsTable with MonstersTable with HuntersTable {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(Hunters.schema, HuntersMonsterMaterials.schema, MonsterMaterials.schema, Monsters.schema, PlayEvolutions.schema, PlayEvolutionsLock.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

}
