package com.springernature.drawing.scala

import com.springernature.drawing.scala.Canvas.Clean


case class Canvas private(w: Int, h: Int, content:Seq[Char]) {
    import Canvas.Painted

    def isValid(x: Int, y: Int): Boolean = x > 0 && x <= w && y > 0 && y <= h

    def paint(x:Int, y:Int):Canvas =
        if(isValid(x,y)) copy(content = content.updated(w*(y-1)+(x-1),Painted)) else this

    def get(x: Int, y:Int) = if (isValid(x,y)) content(w*(y-1)+(x-1)) else Clean


    def renderLine(line: Seq[Char]) = "|"+line.mkString("")+"|"

    def renderLines = ("-" * (w+2)) +: content.grouped(w).map(renderLine).toSeq :+ ("-" * (w+2) )

    def render = renderLines.mkString("\n")
}

object Canvas {
    val Clean = ' '
    val Painted = 'x'
    def apply(w:Int, h:Int): Canvas = Canvas(w, h, Vector.fill(w * h)(Clean))
}