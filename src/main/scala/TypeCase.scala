
class TypeSample {
  type X
  def test(x:X)=System.err.println(x)
}

class IntTypeCase extends TypeSample{
  type X = Int
}

class StringTypeCase extends TypeSample{
  type X = String
}
case class TypeUser(name:String)
object TypeCase extends App{

   val typeSample = new TypeSample{
     type X =TypeUser
   }

  typeSample.test(TypeUser("typeUser"))

   val intTypeCase = new IntTypeCase
  intTypeCase.test(100)

  val strTypeCase = new StringTypeCase
  strTypeCase.test("abc")


}



