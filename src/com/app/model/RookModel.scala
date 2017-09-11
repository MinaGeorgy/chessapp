package com.app.model

import scala.collection.mutable.ArrayBuffer
import collection.immutable.IndexedSeq

class RookModel extends ChessModel {

  {
    super.chessType_(ChessEnum.ROOK.toString())

  }

  override def isValidBoard(board: Array[Array[Char]], row: Int, col: Int): Boolean = {

    if (board(row)(col) != 'F') return false
    for {
      r <- 0 until board.length
    } {
      if (board(r)(col) != 'T' && board(r)(col) != 'F') return false
      board(r)(col) = 'T'
    }
    for {
      j <- 0 until board(0).length
    } {
      if (board(row)(j) != 'T' && board(row)(j) != 'F') return false
      board(row)(j) = 'T'
    }

    board(row)(col) = chessType.charAt(0)
    true
  }
}