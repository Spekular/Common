package spekular.common.vector

case class PositionalFloat (vertexSet: Set[Vec2Float]) {
	lazy val leftToRight = vertexSet.toSeq.sortBy(_.x)
	lazy val bottomToTop = vertexSet.toSeq.sortBy(_.y)
	lazy val rightMost: Seq[Vec2Float] =
		leftToRight.reverse.takeWhile(_.x == leftToRight.last.x)
	lazy val leftMost: Seq[Vec2Float] =
		leftToRight.takeWhile(_.x == leftToRight.head.x)
	lazy val topMost: Seq[Vec2Float] =
		bottomToTop.reverse.takeWhile(_.y == bottomToTop.last.y)
	lazy val bottomMost: Seq[Vec2Float] =
		bottomToTop.takeWhile(_.y == bottomToTop.head.y)

	lazy val maxX: Float = rightMost.head.x
	lazy val minX: Float = leftMost.head.x
	lazy val maxY: Float = topMost.head.y
	lazy val minY: Float = bottomMost.head.y

	lazy val width = maxX - minX
	lazy val height = maxY - minY

	def rightOf(that: PositionalFloat) = { minX > that.maxX }
	def leftOf(that: PositionalFloat)  = { maxX < that.minX }
	def above(that: PositionalFloat) = { minY > that.maxY }
	def below(that: PositionalFloat) = { maxY < that.minY }
}

object Positional {
	def apply (verts: Vec2Float*) = new PositionalFloat(verts.toSet)
}
