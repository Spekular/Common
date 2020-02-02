package spekular.common.geometry

import spekular.common.vector._

case class Polygon (vertices: Seq[Vec2Float]) {
	lazy val edges: Seq[Segment] = vertices.sliding(2).map( seg =>
		new Segment(seg(0), seg(1))
	).toSeq :+ new Segment(vertices.last, vertices.head)

	lazy val triangulated: Seq[Tri] = triangulate(vertices)

	def triangulate (verts: Seq[Vec2Float]): Seq[Tri] = {
		if (verts.size == 3) return Seq( new Tri(verts) )
		else {
			if ( contains( Vec2Float.average(vertices.take(3)) ) ){
				val remaining = new Polygon( Seq(verts.head) ++ verts.drop(2) )
				remaining.triangulated :+ new Tri( verts.take(3) )
			}
			else {
				triangulate( verts.drop(1) :+ verts.head )
			}
		}
	}

	def boundaryContains (point: Vec2Float) = edges.exists( _.contains(point) )

	def contains (point: Vec2Float, includeBoundary: Boolean = true): Boolean = {
		if ( boundaryContains(point) ) return includeBoundary
		else {
			var iterate = 0
			edges.map( edge => windingNumber(edge, point) )
			.filter(_.isDefined).flatten.foldLeft(0)( (left, right) =>
				if (left == right) right
				else left + right
			) != 0
		}
	}

	def windingNumber (edge: Segment, point: Vec2Float): Option[Int] = {
		//Imagine shooting a horizontal ray to the right of the point
		edge.xAtYPos(point.y) match {
			case Right(is) if (is.size < 1)       => Some(0) //Ray doesn't intersect
			case Right(is) if (is.head > point.x) => {       //Ray intersects
				if   (edge.first.y > edge.last.y)    Some( 1)
				else                                 Some(-1)
			}
			case _ => None //Point intersects edge, or horizontal edge. Ignore.
		}
	}
}
