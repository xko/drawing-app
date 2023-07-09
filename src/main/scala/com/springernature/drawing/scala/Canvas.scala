package com.springernature.drawing.scala

import scala.+:

case class Canvas private(w: Int, h: Int, content:Seq[Boolean]) {
    def isValid(x: Int, y: Int): Boolean = x > 0 && x <= w && y > 0 && y <= h

    def paint(x:Int, y:Int):Canvas = if(isValid(x,y)) copy(content = content.updated(w*(y-1)+(x-1),true)) else this


    def renderPos(pos: Boolean) = if(pos) "x" else " "

    def renderLine(line: Seq[Boolean]) = "|"+line.map(renderPos).mkString("")+"|"

    def renderLines = ("-" * (w+2)) +: content.grouped(w).map(renderLine).toSeq :+ ("-" * (w+2) )

    def render = renderLines.mkString("\n")
}

object Canvas {
    def apply(w:Int, h:Int): Canvas = Canvas(w, h, Vector.fill(w * h)(false))
}