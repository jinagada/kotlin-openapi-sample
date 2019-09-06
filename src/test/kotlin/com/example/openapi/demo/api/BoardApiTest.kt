package com.example.openapi.demo.api

import com.example.openapi.demo.api.model.BoardCreateDto
import com.example.openapi.demo.api.model.BoardDto
import com.example.openapi.demo.api.model.ErrorDto
import org.junit.jupiter.api.Test

import org.springframework.http.ResponseEntity

class BoardApiTest {

    
    private val api: BoardApiController = BoardApiController()

    
    /**
    * Board 목록 조회
    *
    * 
    *
    * @throws ApiException
    *          if the Api call fails
    */
    @Test
    fun boardGetTest() {
        val response: ResponseEntity<List<BoardDto>> = api.boardGet()

        // TODO: test validations
    }
    
    /**
    * Board 삭제
    *
    * 
    *
    * @throws ApiException
    *          if the Api call fails
    */
    @Test
    fun boardIdDeletedDeleteTest() {
        val id:kotlin.Long? = null
        val response: ResponseEntity<Unit> = api.boardIdDeletedDelete(id!!)

        // TODO: test validations
    }
    
    /**
    * Board 수정
    *
    * 
    *
    * @throws ApiException
    *          if the Api call fails
    */
    @Test
    fun boardIdUpdatedPostTest() {
        val id:kotlin.Long? = null
        val boardCreateDto:BoardCreateDto? = null
        val response: ResponseEntity<Unit> = api.boardIdUpdatedPost(id!!, boardCreateDto!!)

        // TODO: test validations
    }
    
    /**
    * Board 등록
    *
    * 
    *
    * @throws ApiException
    *          if the Api call fails
    */
    @Test
    fun boardSavePutTest() {
        val boardCreateDto:BoardCreateDto? = null
        val response: ResponseEntity<Unit> = api.boardSavePut(boardCreateDto!!)

        // TODO: test validations
    }
    
}
