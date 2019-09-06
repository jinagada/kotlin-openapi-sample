package com.example.openapi.demo.exception

sealed class DemoCommonException(val code: String, override val message: String): Exception(message)
fun DemoCommonException.toExceptionCodeMessage() = ExceptionCodeMessage(this.code, this.message)
data class ExceptionCodeMessage(val code: String, val message: String)

class DataNotFoundException(code: String, message: String) : DemoCommonException(code, message)
class UnkownException(code: String, message: String) : DemoCommonException(code, message)


