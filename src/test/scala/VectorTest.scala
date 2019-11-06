package spekular.common.tests
import spekular.common.vector._

object Main {
	val myIntegerVector = Vec2[Int](3, 7)
	val myDoubleVector = Vec2Rect(3d, 7d)
	val intDividedVec = myIntegerVector / myDoubleVector
}
