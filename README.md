## The Drawing APP (scala+java)
This is re-implementation in scala of the application described below. 
The scala project is described as usual in [build.sbt](build.sbt).
Scala implementation is in the new package [com.springernature.drawing.scala](src/main/scala/com/springernature/drawing/scala), 
original java implementation remains intact and is also compilable/runnable from sbt. 

To run scala version:
```bash
sbt "runMain com.springernature.drawing.scala.DrawingApplication"
```
To run scala tests:
```bash
sbt test
```
To java version:
```bash
sbt "runMain com.springernature.drawing.DrawingApplication"
```
Note: Java tests are not runnable from sbt at the moment, but if sbt project is imported into IntelliJ, 
it can run both java and scala tests.


----------------------------------------

## The problem

In the pairing interview we will be extending a simple console version of a drawing program.
Some of the requirements have been implemented already. The program works as follows:

1. create a new canvas.
2. start drawing on the canvas by issuing various commands.
3. quit.

The program currently supports the following commands:

* `C w h` - Create a new canvas of width w and height h.
* `L x1 y1 x2 y2` - Draw a new line from `(x1,y1)` to `(x2,y2)`. Currently only horizontal or vertical lines are supported. Horizontal and vertical lines are drawn using the `x` character.
* `Q` - Quit the program.

The next command we will add together during the pairing session is:

* `R x1 y1 x2 y2` - Create a new rectangle, whose upper left corner is `(x1,y1)` and lower right corner is `(x2,y2)`. Horizontal and vertical lines are drawn using the `x` character.

The solution is not necessarily our idea of good or bad Java or testing practice. In our jobs we often have to
work with or extend existing code of varying quality. The aim of the interview is to see what
pair programming with you would be like and also to use the session to discuss design and implementation decisions.

Please don't implement the rectangle command before the pairing session!

Please make sure you can build and run the project on your computer and that you are set up with a
development environment/editor of your choice and _briefly_ familiarise yourself with the code.

You will need to share your screen during the pairing session (we will try to use https://pop.com/home but if that is problematic we will use Google meet).

## Instructions

To build:
```bash
./gradlew build
```

To start the application:
```bash
java -jar build/libs/springer-drawing-1.0-SNAPSHOT.jar
```

## Sample I/O

Below is a sample of the output the program should produce. User input is prefixed with `enter command:`.


	enter command: C 20 4
	----------------------
	|                    |
	|                    |
	|                    |
	|                    |
	----------------------
	enter command: L 1 2 6 2
	----------------------
	|                    |
	|xxxxxx              |
	|                    |
	|                    |
	----------------------
	enter command: L 6 3 6 4
	----------------------
	|                    |
	|xxxxxx              |
	|     x              |
	|     x              |
	----------------------
	enter command: R 16 1 20 3
	----------------------
	|               xxxxx|
	|xxxxxx         x   x|
	|     x         xxxxx|
	|     x              |
	----------------------
	enter command: Q

