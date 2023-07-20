package com.springernature.drawing.scala

sealed trait Command

case class CreateCanvas(width: Int, height:Int) extends Command {
    def create = Canvas(width, height)
}

sealed trait DrawingCommand extends Command {
    def draw(canvas: Canvas):Canvas
}

sealed trait DrawLineCommand extends DrawingCommand

case class DrawLineV(x: Int, y1: Int, y2: Int) extends DrawLineCommand {
    override def draw(canvas: Canvas): Canvas = (y1 to y2).foldLeft(canvas)( _.paint(x, _) )
}

case class DrawLineH(x1: Int, x2: Int, y: Int) extends DrawLineCommand {
    override def draw(canvas: Canvas): Canvas = (x1 to x2).foldLeft(canvas)( _.paint(_, y) )
}

case class CompoundCommand(commands: DrawingCommand*) extends DrawingCommand {
    override def draw(canvas: Canvas):Canvas = {
        commands.foldLeft(canvas) { (cnv, cmd) =>
            cmd.draw(cnv)
        }
    }
}

case object Quit extends Command

case object Undo extends Command



