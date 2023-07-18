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

    it("retrieves value at coordinate") {
        Canvas(4,4).paint(2,3).get(2,3) shouldBe 'x'
        Canvas(4,4).paint(2,3).get(3,3) shouldBe ' '
    }

    it("rejects invalid coordinates") {
        val c = Canvas(3, 5)
        the[IllegalArgumentException] thrownBy c.paint(-1, 5) should have message "x:-1 out of range (0,3]"
        the[IllegalArgumentException] thrownBy c.paint(23, 4) should have message "x:23 out of range (0,3]"
        the[IllegalArgumentException] thrownBy c.paint(2, 100) should have message "y:100 out of range (0,5]"

        an[IllegalArgumentException] should be thrownBy Canvas(1, 1).paint(9, 0).get(2, 3)
    }

}
