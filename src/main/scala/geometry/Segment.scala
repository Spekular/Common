package spekular.common.geometry

import spekular.common.vector._

case class Segment (first: Vec2Float, last: Vec2Float) {
	require(first != last, "Segments of length zero (same start and end position) are not permitted.")
	val pos = Positional(first, last)

	lazy val length = scala.math.sqrt(pos.width*pos.width + pos.height*pos.height)

	def contains(point: Vec2Float): Boolean = {
		xAtYPos(point.y) match {
			case Left(seg) => return (point.x >= pos.minX && point.x <= pos.maxX )
			case Right(vec) if (vec.size >= 1) => true
			case _ => false
		}
	}

	def xAtYPos(y: Float): Either[Segment, Vector[Float]] = {
		if (y >= pos.minY && y <= pos.maxY){
			if (pos.height == 0) return Left(this) //The entire segment intersects
			val percentage = (y - pos.minY)/pos.height
			Right( Vector(pos.bottomMost.head.interpolateTo(pos.topMost.head, percentage).x) )
		}
		else Right( Vector.empty[Float] )
	}

	def yAtXPos(x: Float): Either[Segment, Vector[Float]] = {
		if (x >= pos.minX && x <= pos.maxX){
			if (pos.width == 0) return Left(this)
			val percentage = (x - pos.minX)/pos.width
			Right( Vector(pos.leftMost.head.interpolateTo(pos.rightMost.head, percentage).y) )
		}
		else Right( Vector.empty[Float] )
	}
}
