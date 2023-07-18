package com.springernature.drawing.scala

import com.springernature.drawing.scala.Canvas.Clean


case class Canvas private(w: Int, h: Int, content:Seq[Char]) {
    import Canvas.Painted

    def validate(x: Int, y: Int) = {
        if(x <=0 || x > w) throw new IllegalArgumentException(s"x:$x out of range (0,$w]")
        if(y <=0 || y > h) throw new IllegalArgumentException(s"y:$y out of range (0,$h]")
        this
    }

    def paint(x:Int, y:Int):Canvas = validate(x, y).copy(content = content.updated(w *(y -1) +(x -1), Painted))

    def get(x: Int, y:Int) = validate(x, y).content(w *(y -1) +(x -1))


    def renderLine(line: Seq[Char]) = "|"+line.mkString("")+"|"

    def renderLines = ("-" * (w+2)) +: content.grouped(w).map(renderLine).toSeq :+ ("-" * (w+2) )

    def render = renderLines.mkString("\n")
}

object Canvas {
    val Clean = ' '
    val Painted = 'x'
    def apply(w:Int, h:Int): Canvas = Canvas(w, h, Vector.fill(w * h)(Clean))
}