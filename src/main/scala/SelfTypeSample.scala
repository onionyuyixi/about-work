
object SelfCase {
  self =>   //等价于 this:SelfCase=> / alias:SelfCase=> 然object的时候无法使用this的别名
  val num = 5
  def main(args: Array[String]): Unit = {
    println(this.num)	//打印结果：5
    println(self.num)	//打印结果：5
  }
}

class This_Self {
  this: This_Self=>  // 案this 只能不能用于object 参照Alias_Self 可以资发此处
  val num = 5
  def test(): Unit = {
    println(this.num)	//打印结果：5
    println(num)	//打印结果：5
  }
}

object This_Self extends App{
    new This_Self().test()
}


object Alias_Self extends App {
  alias: This_Self=>  //
  val num = 5
  def test(): Unit = {
    println(alias.num)	//打印结果：5
    println(num)	//打印结果：5
  }

  test()
}



case class User(userName: String)

trait VipCustom {
  this: User => //这个表示 VipCustom 本身也必须是一个 User   => 表示待实现  二者结合 能知道全貌
  // 同时可以知道 这样的用法 一般需要体现在子类上（只有子类方可以 继承 混入新的血脉）

  def vip(vip: String) = {
    println(s"$userName: $vip") //正因为VipCustom 也是一个User  故而可以直接获取userName的属性值
  }
}


trait VipCustom1 {
  abc: User => //  abc与this的作用一样 都做别名使
  def vip(vip: String) = {
    println(s"$userName: $vip")
  }
}

class ShopVip(name: String) extends User(name) with VipCustom {

}

class ShopVip1(name: String) extends User(name) with VipCustom1 {

}

object SelfTypeSample extends App {
  val onion = new ShopVip("onion")
  onion.vip("welcome ! it is my honor of service you ") //onion: welcome ! it is my honor of service you

  val yuyixi = new ShopVip1("yuyixi")
  yuyixi.vip("welcome ! it is my honor of service you ") //yuyixi: welcome ! it is my honor of service you
}



