package com.app.model

import scala.collection.mutable.ArrayBuffer
import collection.immutable.IndexedSeq

class KnightModel extends ChessModel {

  {
    super.chessType_(ChessEnum.KNIGHT.toString())

  }

  override def isValidBoard(board: Array[Array[Char]], row: Int, col: Int): Boolean = {

    if (board(row)(col) != 'F') return false
    var rows = Array(1, 1, -1, -1, 2, 2, -2, -2)
    var cols = Array(2, -2, -2, 2, 1, -1, 1, -1)
    for (i <- 0 until rows.length) {

      if (row + rows(i) < board.length && row + rows(i) >= 0 && col + cols(i) < board.length && col + cols(i) >= 0) {

        if (board(row + rows(i))(col + cols(i)) != 'T' && board(row + rows(i))(col + cols(i)) != 'F') return false
        board(row + rows(i))(col + cols(i)) = 'T'
      }

    }

    board(row)(col) = chessType.charAt(0)
    true
  }
}