package com.springernature.drawing.scala
import shapeless._

object Parsers {
    type FromCertainString[A] = PartialFunction[String,A]

    trait FromString[A] {
        def apply(s: String): A
    }

    implicit val cNilFromString: FromString[CNil] = s => throw new Exception(s"No match for:$s")

    implicit def coProdFromString[H, T <: Coproduct](implicit hParser: FromCertainString[H], tParser: FromString[T]): FromString[H :+: T] =
        s => if(hParser.isDefinedAt(s)) Inl(hParser(s)) else Inr(tParser(s))

    implicit def genericFromString[A, R](implicit gen: Generic.Aux[A, R], reprParser: Lazy[FromString[R]]): FromString[A] =
        s => gen.from(reprParser.value(s))

    def fromString[T:FromString](s:String) = implicitly[FromString[T]].apply(s)
}