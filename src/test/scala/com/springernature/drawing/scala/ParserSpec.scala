package com.springernature.drawing.scala

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers


class ParserSpec extends AnyFunSpec with Matchers{
    it("parses coproducts"){
        import Parsers._
        sealed trait Thum

        case object Go extends Thum
        implicit val goFromString: FromCertainString[Go.type] = {case "G" => Go}
        case object Bo extends Thum
        implicit val boFromString: FromCertainString[Bo.type] = {case "B" => Bo}
        Thum.parse("G") shouldBe Go
        Thum.parse("B") shouldBe Bo
    }


}
