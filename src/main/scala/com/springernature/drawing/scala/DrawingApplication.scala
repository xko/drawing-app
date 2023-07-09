package com.springernature.drawing.scala

import scala.util.{Success, Try, Using}

object DrawingApplication {
    def consoleIn = Command.parseAll(io.Source.stdin.getLines())

    def consoleOut(step: Step) = {
        step match {
            case d:Draw => println(d.canvas.render)
            case e:Error => println(e.msg)
            case _ => ()
        }
        print("Enter command:")
    }

    def main(args: Array[String]): Unit = {
        Start.walk(consoleIn).foreach(consoleOut)
    }
}
