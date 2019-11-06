package spekular.common.tests
import spekular.common.vector._
import spekular.common.vector.Vec2._

object Main {
	//This is the worst test ever, of all time
	val myIntVector = Vec2(3, 7)
	myIntVector + myIntVector
	myIntVector - myIntVector
	myIntVector * myIntVector
	myIntVector / myIntVector
	myIntVector * 2
	myIntVector / 2
	myIntVector * 2f
	myIntVector / 2f

	val myFloatVector = Vec2(3f, 7f)
	myFloatVector + myFloatVector
	myFloatVector - myFloatVector
	myFloatVector * myFloatVector
	myFloatVector / myFloatVector
	myFloatVector * 2f
	myFloatVector / 2f
	myFloatVector * 2
	myFloatVector / 2

	myIntVector + myFloatVector
	myIntVector - myFloatVector
	myIntVector * myFloatVector
	myIntVector / myFloatVector
	myFloatVector + myIntVector
	myFloatVector - myIntVector
	myFloatVector * myIntVector
	myFloatVector / myIntVector
}
