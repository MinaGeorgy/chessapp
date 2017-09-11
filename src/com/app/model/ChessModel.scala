package com.app.model

import scala.collection.mutable.ArrayBuffer

abstract class ChessModel {

  var chessType: String = ""
  def chessType_(newChessType: String) = chessType = newChessType
  def _chessType = chessType

  def isValidBoard(board: Array[Array[Char]], row: Int, col: Int): Boolean
}