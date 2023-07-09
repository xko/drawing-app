package com.springernature.drawing.scala

import scala.collection.IterableOnceOps
import scala.util.{Failure, Success, Try}

sealed trait Step {
    def next(cmd: Command): Step

    def tryNext(cmd: Command) = Try(next(cmd))

    def walk(cmds: Iterator[Try[Command]]) = cmds.scanLeft[Step](this) { (stp, cmd) =>
        cmd.flatMap(stp.tryNext).fold(ex => Error(ex.getMessage, stp), identity)
    }.takeWhile(_ != End)
}


case object Start extends Step {
    override def next(cmd: Command) = cmd match {
        case c: CreateCanvas => Draw(c.create)
        case Quit => End
        case _ => Error("Please create a canvas first", this)
    }
}

case class Draw(canvas: Canvas) extends Step {
    override def next(cmd: Command) = cmd match {
        case c: DrawingCommand => Draw(c.draw(canvas))
        case Quit => End
        case _ => Error("Drawing command expected", this)
    }
}

case class Error(msg: String, step: Step) extends Step {
    override def next(cmd: Command) = step.next(cmd)
}

case object End extends Step {
    override def next(cmd: Command): Step = throw new IllegalStateException("It's over, baby")
}