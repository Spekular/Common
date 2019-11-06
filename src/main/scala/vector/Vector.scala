package spekular.common.vector

abstract case class Vec2[N](x: N, y: N)(implicit n: Numeric[N]){
	import n._
	implicit def toDouble = new Vec2RectFrac[Double]( x.toDouble, y.toDouble )
}

object Vec2 {
	def apply[N](x: N, y: N)(implicit n: Numeric[N]) = Vec2Rect(x, y)


}

abstract class Vec2Rect[N, V <: Vec2Rect[N, V]](x: N, y: N)(implicit n: Numeric[N])
extends Vec2[N](x, y) {
	def xy = this
	def yx = Vec2Rect(y, x)

	import n._
	def +(that: V) = Vec2Rect(x + that.x, y + that.y)
	def -(that: V) = Vec2Rect(x - that.x, y - that.y)
	def *(that: V) = Vec2Rect(x * that.x, y * that.y)
	def /(that: V): Vec2RectFrac[_]

	//implicit def toDouble = new Vec2RectFrac[Double]( x.toDouble, y.toDouble )
}

class Vec2RectFrac[F](x: F, y: F)(implicit f: Fractional[F])
extends Vec2Rect[F, Vec2RectFrac[F]](x, y){
	import f._
	def /(that: Vec2RectFrac[F]) = Vec2Rect.frac(x / that.x, y / that.y)
}

class Vec2RectInt[I](x: I, y: I)(implicit i: Integral[I])
extends Vec2Rect[I, Vec2RectInt[I]](x, y){
	import i._
	def /(that: Vec2RectInt[I]) = Vec2Rect.frac(
		x.toDouble / that.x.toDouble,
		y.toDouble / that.y.toDouble
	)
}

object Vec2Rect {
	def apply[N](x: N, y: N)(implicit n: Numeric[N]): Vec2Rect[_, _] = {
		(x, y) match {
			case (xd: Double, yd: Double) =>
				return new Vec2RectFrac[Double](xd, yd)
			case (xf: Float, yf: Float) =>
				return new Vec2RectFrac[Float](xf, yf)
			case (xi: Int, yi: Int) =>
				return new Vec2RectInt[Int](xi, yi)
		}
	}

	def frac[F](x: F, y: F)(implicit f: Fractional[F]) = new Vec2RectFrac[F](x, y)
}
