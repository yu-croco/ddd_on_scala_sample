package infrastructure.helpers.ops

trait UuidGeneratorOps {
  def genUUID = java.util.UUID.randomUUID.toString
}
