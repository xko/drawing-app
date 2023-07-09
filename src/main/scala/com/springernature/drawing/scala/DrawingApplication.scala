package com.springernature.drawing.scala


object DrawingApplication {
    def consoleIn = Command.parseAll(io.Source.stdin.getLines())

    def consoleOut(msg: Option[String]) = {
        msg.foreach(println)
        print("Enter command:")
    }

    def main(args: Array[String]): Unit = {
        Start.walk(consoleIn).map(_.render).foreach(consoleOut)
    }
}
