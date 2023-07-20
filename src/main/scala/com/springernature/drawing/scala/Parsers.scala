package com.springernature.drawing.scala
import shapeless._

import scala.util.Try

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

    def fromString[T:FromString](s:String): T = implicitly[FromString[T]].apply(s)

    def fromStringTry[T:FromString](s:String): Try[T] = Try(fromString[T](s))

    def fromStringAll[T:FromString](input: IterableOnce[String]):Iterator[Try[T]] = input.iterator.map(fromStringTry[T])

}