package com.springernature.drawing.scala

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class CanvasSpec extends AnyFunSpec with Matchers {
    it("has basic string representation") {
        Canvas(3,5).render shouldBe
            """-----
              `|   |
              `|   |
              `|   |
              `|   |
              `|   |
              `-----""".stripMargin('`')
    }
    it("paints at 1,1") {
        Canvas(1,1).paint(1,1).render shouldBe
            """---
              `|x|
              `---""".stripMargin('`')
    }

    it("paints at far corner") {
        Canvas(3,5).paint(3,5).render shouldBe
        """-----
          `|   |
          `|   |
          `|   |
          `|   |
          `|  x|
          `-----""".stripMargin('`')
    }

    it("paints many times") {
        Canvas(3,5).paint(3,5).paint(2,4).paint(2,3).render shouldBe
        """-----
          `|   |
          `|   |
          `| x |
          `| x |
          `|  x|
          `-----""".stripMargin('`')
    }

    it("ignores invalid coordinates") {
        Canvas(3, 5).paint(-1, 5).paint(23, 4).paint(100,100).render shouldBe
            """-----
              `|   |
              `|   |
              `|   |
              `|   |
              `|   |
              `-----""".stripMargin('`')
    }

}
