package spekular.common.vector

abstract class Vec2[T, V <: Vec2[T, V]](implicit n: Numeric[T]){
	def x: T
	def y: T

	def xy: V
	def yx: V

	def +(that: V): V
}

abstract case class Vec2Rect[T] (x: T, y: T)(implicit n: Numeric[T])
extends Vec2[T, Vec2Rect[T]]{
	def xy = this
	def yx = Vec2Rect(y, x)

	def +(that: Vec2Rect[T])
}

class Vec2RectInt[I](x: I, y: I)(implicit i: Integral[I])
extends Vec2Rect(x, y){
}

class Vec2RectFrac[F](x: F, y: F)(implicit f: Fractional[F])
extends Vec2Rect(x, y){
}

object Vec2Rect {
	def apply [N](x: N, y: N)(implicit n: Numeric[N]) = {
		import n._
		(x, y) match {
			case (xi: Int, yi: Int) => new Vec2RectInt(x, y)
			case _ => new Vec2RectFrac(x.toDouble, y.toDouble)
		}
	}
}
