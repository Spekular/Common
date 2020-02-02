package spekular.common.geometry

import spekular.common.vector._

case class Tri (vertices: Seq[Vec2Float]) {
	require(vertices.size == 3, "Triangle must have three vertices.")

	lazy val edges = Seq(
		new Segment(vertices(0), vertices(1)),
		new Segment(vertices(1), vertices(2)),
		new Segment(vertices(2), vertices(0))
	)
}
