package com.springernature.drawing.scala

import com.springernature.drawing.scala.Parsers.FromCertainString

object ConsoleParsers {
    val RECreateCanvas = "C (\\d+) (\\d+)".r
    implicit val parseCreateCanvas: FromCertainString[CreateCanvas] = {
        case RECreateCanvas(w, h) => CreateCanvas(w.toInt, h.toInt)
    }

    val REDrawLine = "L (\\d+) (\\d+) (\\d+) (\\d+)".r
    implicit val parseDrawLine: FromCertainString[DrawLineCommand] = {
        case REDrawLine(x1, y1, x2, y2) if x1 == x2 => DrawLineV(x1.toInt, y1.toInt, y2.toInt)
        case REDrawLine(x1, y1, x2, y2) if y1 == y2 => DrawLineH(x1.toInt, x2.toInt, y1.toInt)
        case REDrawLine(_, _, _, _) => throw new IllegalArgumentException("Invalid 'draw line' command (line is not straight)")
    }

    implicit val parseDrawLineV: FromCertainString[DrawLineV] = { case _ => ??? }

    implicit val parseDrawLineH: FromCertainString[DrawLineH] = { case _ => ??? }

    val REDrawRect = "R (\\d+) (\\d+) (\\d+) (\\d+)".r
    implicit val parseDrawRect: FromCertainString[CompoundCommand] = {
        case REDrawRect(x1, y1, x2, y2) => CompoundCommand(
            DrawLineV(x1.toInt, y1.toInt, y2.toInt),
            DrawLineV(x2.toInt, y1.toInt, y2.toInt),
            DrawLineH(x1.toInt, x2.toInt, y1.toInt),
            DrawLineH(x1.toInt, x2.toInt, y2.toInt)
        )
    }

    implicit val parseQuit: FromCertainString[Quit.type] = { case "Q" => Quit}

    implicit val parseUndo: FromCertainString[Undo.type] = { case "U" => Undo}

}
