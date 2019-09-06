package com.example.openapi.demo.delegate

import com.example.openapi.demo.api.BoardApiDelegate
import com.example.openapi.demo.api.model.BoardCreateDto
import com.example.openapi.demo.api.model.BoardDto
import com.example.openapi.demo.exception.DataNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneOffset

@Component
class BoardApiDelegateImpl : BoardApiDelegate {
    override fun boardGet(): ResponseEntity<MutableList<BoardDto>> {
        val list = emptyList<BoardDto>().toMutableList()
        for (i in 1..5) {
            val board = BoardDto()
            board.id(i.toLong())
            board.title("제목$i")
            board.content("내용$i")
            board.created(LocalDateTime.now().toInstant(ZoneOffset.UTC).atOffset(ZoneOffset.UTC))
            board.updated(LocalDateTime.now().toInstant(ZoneOffset.UTC).atOffset(ZoneOffset.UTC))
            list.add(board)
        }
        if (list.size < 1) {
            throw DataNotFoundException("empty", "자료가 없습니다.")
        }
        return ResponseEntity(list, HttpStatus.OK)
    }

    override fun boardSavePut(boardCreateDto: BoardCreateDto?): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.OK)
    }

    override fun boardIdUpdatedPost(id: Long?, boardCreateDto: BoardCreateDto?): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.OK)
    }

    override fun boardIdDeletedDelete(id: Long?): ResponseEntity<Void> {
        return ResponseEntity(HttpStatus.OK)
    }
}
