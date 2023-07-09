package com.springernature.drawing.scala

import org.scalatest.LoneElement
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class CommandsSpec extends AnyFunSpec with Matchers with LoneElement{

    def replay(cmds: String*): Seq[String] = Start.walk(Command.parseAll(cmds)).flatMap(_.render).toSeq

    describe("at the start") {
        it("crates and prints the canvas") {
            replay("C 3 3").loneElement shouldBe
                """-----
                  ||   |
                  ||   |
                  ||   |
                  |-----""".stripMargin
        }

        it("rejects DrawLine"){
            replay("L 10 10 10 9").loneElement shouldBe "Please create a canvas first"
        }

        it("works after error") {
            replay("sdgfsdf", "C 1 1") shouldBe Seq(
                "Unknown command",
                """---
                  || |
                  |---""".stripMargin
                )
        }
    }

    describe("after canvas created") {
        it("draws horizontal line") {
            replay("C 2 1", "L 1 1 2 1") shouldBe Seq(
                """----
                  ||  |
                  |----""".stripMargin,
                """----
                  ||xx|
                  |----""".stripMargin
                )
        }

        it("draws vertical line") {
            replay("C 4 2","L 1 1 1 2").last shouldBe
                """------
                  ||x   |
                  ||x   |
                  |------""".stripMargin
        }

        it("draws line with outside coordinates") {
            replay("C 4 3","L 0 2 10 2").last shouldBe
                """------
                  ||    |
                  ||xxxx|
                  ||    |
                  |------""".stripMargin
        }

        it("rejects subsequent C") {
            replay("C 1 1","C 5 5") shouldBe Seq(
                """----
                  ||  |
                  |----""".stripMargin,
                "Drawing command expected"
                )
        }

        it("works after error") {
            replay("C 4 2","Doo", "L 1 1 1 2").takeRight(2) shouldBe Seq (
                "Unknown command",
                """------
                  ||x   |
                  ||x   |
                  |------""".stripMargin
                )
        }
    }


    describe("at any stage") {

        it("rejects garbage") {
            replay("sdgfsdf").loneElement shouldBe "Unknown command"
            replay("C 3 3","sdgfsdf").last shouldBe "Unknown command"
        }

        it("rejects emtpy input") {
            replay("").loneElement shouldBe "Unknown command"
            replay("C 3 3","").last shouldBe "Unknown command"
        }

        it("rejects tilted line") {
            replay("C 7 7", "L 5 5 4 4").last shouldBe "Invalid 'draw line' command (line is not straight)"
        }

        it("rejects canvas with wrong args") {
            replay("C doo").loneElement shouldBe "Unknown command" //TODO: In java version: "Invalid command"
        }

        it("rejects line with wrong args") {
            replay("L doo").loneElement shouldBe "Unknown command" //TODO: In java version: "Invalid command"
        }


    }

}
