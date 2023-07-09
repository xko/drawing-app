package com.springernature.drawing.scala

import org.scalatest.TryValues._
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CommandsSpec extends AnyWordSpec with Matchers {
    "DrawLine" should {
        "parse single point" in {
            Command.parse("L 2 1 2 1").success.value shouldBe DrawLineV(2,1,1)
        }
        "parse vertical" in {
            Command.parse("")
        }
    }

}
