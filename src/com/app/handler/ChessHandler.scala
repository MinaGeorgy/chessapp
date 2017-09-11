package com.app.handler

import scala.collection.mutable.ArrayBuffer
import com.app.model.ChessModel
import com.app.model.ChessEnum
import com.app.model.ChessModel
import com.app.model.ChessModel
import com.app.model.KingModel
import com.app.model.KingModel
import com.app.model.ChessModel
import com.app.model.RookModel
import java.util.Arrays
import com.app.model.KnightModel
import com.app.model.BishopModel
import com.app.model.QueenModel
import java.util.Formatter.DateTime
import java.util.Date
import javax.swing.text.DateFormatter
import java.time.format.DateTimeFormatter
import java.util.Formatter.DateTime
import java.util.Calendar
import java.text.SimpleDateFormat

class ChessHandler {

  def buildChessPiecesList(chessPiecesList: ArrayBuffer[ChessModel], pieces: Array[String]): Boolean = {
  
    //fill the list with chess pieces regarding its type
    for (i <- 0 to pieces.length - 1) {

      i match {
        case 0 => buildChessItems(ChessEnum.KING.toString(),
          pieces(i), chessPiecesList)
        case 1 => buildChessItems(ChessEnum.ROOK.toString(),
          pieces(i), chessPiecesList)
        case 2 => buildChessItems(ChessEnum.KNIGHT.toString(),
          pieces(i), chessPiecesList)
        case 3 => buildChessItems(ChessEnum.BISHOP.toString(),
          pieces(i), chessPiecesList)
        case 4 => buildChessItems(ChessEnum.QUEEN.toString(),
          pieces(i), chessPiecesList)
        case _ => println("Too Many parameters,Only 5 params are allowed. "); return false
      }
    }
    true
  }

  def copy(array: Array[Array[Char]]): Array[Array[Char]] = {
    val result = Array.ofDim[Array[Char]](array.length)
    for (x <- 0 until array.length) {
      result(x) = Array.ofDim[Char](array(x).length)
      for (y <- 0 until array(x).length) {
        result(x)(y) = array(x)(y)
      }
    }
    result
  }

  def buildChessItems(chessType: String, count: String, list: ArrayBuffer[ChessModel]): ArrayBuffer[ChessModel] = {
    var regex = "[0-9]*"
    if (count == null || count == "" || !count.matches(regex))
      return list
    for (i <- 0 to count.toInt - 1) {
      list += getChessModel(chessType)
    }
    list
  }

  def getChessModel(chessType: String): ChessModel = {
    chessType match {
      case "K" => new KingModel()
      case "R" => new RookModel()
      case "N" => new KnightModel()
      case "B" => new BishopModel()
      case "Q" => new QueenModel()
    }
  }

  def fillChessBoard(board: Array[Array[Char]]): Unit = {
    for {
      r <- 0 to board.length - 1
      c <- 0 to board(0).length - 1
    } yield board(r)(c) = 'F'

  }

  def getChessSolution(board: Array[Array[Char]], piecesList: ArrayBuffer[ChessModel], index: Int, result: ArrayBuffer[Array[Array[Char]]], row: Int, col: Int): Boolean = {

    if (index >= piecesList.length)
      return true
    var srow = 0; var scol = 0

    //check the current chess Item ,if equal the last chess item ,start from its position  
    if (index > 0 && piecesList(index).chessType == piecesList(index - 1).chessType) {
      srow = row; scol = col
    }
    var tempBoard = copy(board)
    for {
      r <- srow to board.length - 1
      c <- scol to board(0).length - 1
    } {
      //check the cell is empty 'F' 
      if (board(r)(c) == 'F') {
        if (piecesList(index).isValidBoard(tempBoard, r, c) && getChessSolution(tempBoard, piecesList, index + 1, result, r, c)) {
          result += tempBoard
          printSolution(tempBoard)
        }
        tempBoard = copy(board)
      }
      if (c == board(0).length - 1)
        scol = 0
    }
    false
  }

  def printSolution(board: Array[Array[Char]]): Unit = {
    board.foreach { x => println(x.mkString("   ")) }
    println("========================")

  }
}