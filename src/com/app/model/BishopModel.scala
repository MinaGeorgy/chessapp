package com.app.model

import scala.collection.mutable.ArrayBuffer
import collection.immutable.IndexedSeq

class BishopModel extends ChessModel {

  {
    super.chessType_(ChessEnum.BISHOP.toString())

  }

  override def isValidBoard(board: Array[Array[Char]], row: Int, col: Int): Boolean = {

    if (board(row)(col) != 'F') return false
    var r = row; var c = col
    while (r >= 0 && c >= 0) {
      if (board(r)(c) == 'F') board(r)(c) = 'T'
      else if (board(r)(c) != 'T') return false

      r -= 1
      c -= 1
    }
    r = row; c = col
    while (r < board.length && c < board(0).length) {
      if (board(r)(c) == 'F') board(r)(c) = 'T'
      else if (board(r)(c) != 'T') return false

      r += 1
      c += 1
    }
    r = row; c = col
    while (r >= 0 && c < board(0).length) {
      if (board(r)(c) == 'F') board(r)(c) = 'T'
      else if (board(r)(c) != 'T') return false

      r -= 1
      c += 1
    }
    r = row; c = col
    while (r < board.length && c >= 0) {
      if (board(r)(c) == 'F') board(r)(c) = 'T'
      else if (board(r)(c) != 'T') return false

      r += 1
      c -= 1
    }
    board(row)(col) = chessType.charAt(0)
    true
  }
}