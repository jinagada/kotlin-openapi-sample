package com.example.openapi.demo.config

import com.example.openapi.demo.exception.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.reflect.UndeclaredThrowableException
import javax.servlet.http.HttpServletResponse
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class DefaultExceptionHandler {
    /**
     * 기본 오류 처리
     */
    @ExceptionHandler(value = [Exception::class])
    fun connectError(ex: Exception, response: HttpServletResponse) : ResponseEntity<ExceptionCodeMessage>  {
        return when(ex) {
            is UndeclaredThrowableException -> when(ex.undeclaredThrowable) {
                is DataNotFoundException -> ResponseEntity(
                        (ex.undeclaredThrowable as DataNotFoundException).toExceptionCodeMessage(),
                        HttpStatus.NOT_FOUND)
                else -> ResponseEntity(UnkownException("unknown", ex.message ?: "").toExceptionCodeMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR)
            }
            else -> ResponseEntity(UnkownException("unknown", ex.message ?: "").toExceptionCodeMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    /**
     * 제약조건 오류 처리
     */
    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun onConstraintViolation(ex: ConstraintViolationException, response: HttpServletResponse) : ResponseEntity<ExceptionCodeMessage> {
        val error = ExceptionCodeMessage("constraint_violation_exception", ex.constraintViolations.joinToString(", ") { it.message })
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    /**
     * 파라메터 오류 처리
     */
    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun argumentNotValidException(ex: MethodArgumentNotValidException, response: HttpServletResponse) : ResponseEntity<ExceptionCodeMessage> {
        val error = ExceptionCodeMessage(ex.bindingResult.fieldError!!.code!!
                + " " + ex.bindingResult.fieldError!!.field!!,
                ex.bindingResult.fieldError!!.field!! + "은(는) " + ex.bindingResult.fieldError!!.defaultMessage!!)
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
}

