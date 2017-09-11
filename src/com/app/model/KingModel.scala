package com.app.model

import scala.collection.mutable.ArrayBuffer
import collection.immutable.IndexedSeq

class KingModel extends ChessModel {

  {
    super.chessType_(ChessEnum.KING.toString())

  }

  override def isValidBoard(board: Array[Array[Char]], row: Int, col: Int): Boolean = {

    if (board(row)(col) != 'F') return false
    for {
      i <- -1 to 1
      j <- -1 to 1
      if (i + row >= 0 && i + row <= board.length - 1)
      if (j + col >= 0 && j + col <= board(0).length - 1)
    } {
      if (board(row + i)(col + j) != 'T' && board(row + i)(col + j) != 'F') return false
      board(row + i)(col + j) = 'T'
    }

    board(row)(col) = chessType.charAt(0)
    true
  }
}