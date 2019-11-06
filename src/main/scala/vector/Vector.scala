package spekular.common.vector

case class Vec2Int (x: Int, y: Int) {
	def xy = this
	def yx = Vec2(y, x)

	def toFloat = Vec2(x.toFloat, y.toFloat)

	def +(that: Vec2Int) = Vec2(x + that.x, y + that.y)
	def -(that: Vec2Int) = Vec2(x - that.x, y - that.y)
	def *(that: Vec2Int) = Vec2(x * that.x, y * that.y)
	def /(that: Vec2Int) = toFloat / that.toFloat
	def *(that: Int) = Vec2(x * that, y * that)
	def /(that: Int) = Vec2(x / that, y / that)
	def *(that: Float) = toFloat * that
	def /(that: Float) = toFloat / that

	def interpolateTo(that: Vec2Int, fac: Float): Vec2Float = {
		this.toFloat + (that - this) * fac
	}
}

object Vec2Int {
	def average (vecs: Seq[Vec2Int]): Vec2Float = vecs.reduce(_ + _) / vecs.size.toFloat
}

case class Vec2Float (x: Float, y: Float) {
	def +(that: Vec2Float) = Vec2(x + that.x, y + that.y)
	def -(that: Vec2Float) = Vec2(x - that.x, y - that.y)
	def *(that: Vec2Float) = Vec2(x * that.x, y * that.y)
	def /(that: Vec2Float) = Vec2(x / that.x, y / that.y)
	def *(that: Int) = Vec2(x * that, y * that)
	def /(that: Int) = Vec2(x / that, y / that)
	def *(that: Float) = Vec2(x * that, y * that)
	def /(that: Float) = Vec2(x / that, y / that)

	def interpolateTo(that: Vec2Float, fac: Float): Vec2Float = this + (that - this) * fac
}

object Vec2Float {
	def average (vecs: Seq[Vec2Float]): Vec2Float = vecs.reduce(_ + _) / vecs.size.toFloat
}

object Vec2 {
	def apply(x: Int, y: Int) = new Vec2Int(x, y)
	def apply(v: (Int, Int)): Vec2Int = Vec2(v._1, v._2)
	def apply(x: Float, y: Float) = new Vec2Float(x, y)
	def apply(v: (Float, Float)): Vec2Float = Vec2(v._1, v._2)

	implicit def ivToFv (some: Vec2Int): Vec2Float = some.toFloat
}
