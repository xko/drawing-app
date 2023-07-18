package com.springernature.drawing.scala

import scala.collection.IterableOnceOps
import scala.util.Try

sealed trait Command

object Command {
    val RECreate = "C (\\d+) (\\d+)".r
    val REDrawLine = "L (\\d+) (\\d+) (\\d+) (\\d+)".r
    val REDrawRect = "R (\\d+) (\\d+) (\\d+) (\\d+)".r
    val Q = "Q"

    def parse(str: String): Try[Command] = Try {
        str match {
            case RECreate(w, h) => CreateCanvas(w.toInt, h.toInt)
            case REDrawLine(x1, y1, x2, y2) if x1 == x2 => DrawLineV(x1.toInt, y1.toInt, y2.toInt)
            case REDrawLine(x1, y1, x2, y2) if y1 == y2 => DrawLineH(x1.toInt, x2.toInt, y1.toInt)
            case REDrawLine(_, _ , _ , _) => throw new IllegalArgumentException("Invalid 'draw line' command (line is not straight)")
            case REDrawRect(x1, y1, x2, y2) => CompoundCommand(
                DrawLineV(x1.toInt, y1.toInt, y2.toInt),
                DrawLineV(x2.toInt, y1.toInt, y2.toInt),
                DrawLineH(x1.toInt, x2.toInt, y1.toInt),
                DrawLineH(x1.toInt, x2.toInt, y2.toInt)
            )
            case Q => Quit
            case _ => throw new IllegalArgumentException("Unknown command")
        }
    }

    def parseAll(input: IterableOnce[String]):Iterator[Try[Command]] = input.iterator.map(parse)
}

sealed trait DrawingCommand extends Command {
    def draw(canvas: Canvas):Canvas
}

case class CompoundCommand(commands: DrawingCommand*) extends DrawingCommand {
    override def draw(canvas: Canvas):Canvas = {
        commands.foldLeft(canvas) { (cnv, cmd) =>
            cmd.draw(cnv)
        }
    }
}

case class CreateCanvas(width: Int, height:Int) extends Command {
    def create = Canvas(width, height)
}

case class DrawLineV(x: Int, y1: Int, y2: Int) extends DrawingCommand {
    override def draw(canvas: Canvas): Canvas = (y1 to y2).foldLeft(canvas)( _.paint(x, _) )
}

case class DrawLineH(x1: Int, x2: Int, y: Int) extends DrawingCommand {
    override def draw(canvas: Canvas): Canvas = (x1 to x2).foldLeft(canvas)( _.paint(_, y) )
}

case object Quit extends Command



