package com.app.config

import java.text.SimpleDateFormat
import java.util.Calendar

import scala.collection.mutable.ArrayBuffer

import com.app.model.ChessModel
import com.app.handler.ChessHandler

object Application {

  def main(args: Array[String]): Unit = {

    println("Please Enter M*N Board , Comma separated value")
    val dimension = scala.io.StdIn.readLine()
    val dimArray = dimension.split(",");
    val regex = "[0-9]*"
    if (dimArray == null || dimArray.length < 2) {
      print("Invalid Input parameters,Please Enter valid M*N Board")
      return
    }
    //check valid inputs
    if (!(dimArray(0).matches(regex) && dimArray(1).matches(regex))) {
      print("Invalid Input parameters,Please Enter valid M*N Board")
      return
    }

    val row: Int = dimArray(0).toInt
    val col: Int = dimArray(1).toInt

    //build Chess Board with M*N 2d array
    var chessBoard = Array.ofDim[Char](row, col)

    println("Please Enter Chess items ,comma separated values as following K,R,N,B,Q  ")
    val chessPieces = scala.io.StdIn.readLine()
    val chessArray = chessPieces.split(",")
    if (chessArray == null){
      print("Invalid Input parameters,Please Enter valid Number of Chess pieces")
      return
    }
    //Begin Execution to find unique configuration 
    execute(chessArray, chessBoard);
  }

  
  def execute(chessPieces: Array[String], chessBoard: Array[Array[Char]]): Unit = {
    
    val beforeExec = Calendar.getInstance.getTime()
    //Initiate chess object from ChessHandler.  
    val chess = new ChessHandler()

    //Fill Chess Board with 'F' default value
    chess.fillChessBoard(chessBoard)

    //Initiate the Chess pieces List if Not valid list return .
    var chessPiecesList = new ArrayBuffer[ChessModel]()
    if (!chess.buildChessPiecesList(chessPiecesList, chessPieces)) return

    val dateFormatter = new SimpleDateFormat("mm:ss.SSS")

    //Call The Method of chess class getChessSolution to calulate all unique values.
    var result = new ArrayBuffer[Array[Array[Char]]]
    chess.getChessSolution(chessBoard, chessPiecesList, 0, result, 0, 0)

    //Print The result of execution .
    val afterExec = Calendar.getInstance.getTime()
    println("Begin Time :     " + dateFormatter.format(beforeExec))
    println("End Time   :     " + dateFormatter.format(afterExec))
    println("Execution Time : " + dateFormatter.format(afterExec.getTime - beforeExec.getTime))
    println("Total Unique Configuration : " + result.size)
  }

}