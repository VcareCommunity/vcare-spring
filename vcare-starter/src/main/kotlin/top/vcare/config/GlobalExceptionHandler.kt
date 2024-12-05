package top.vcare.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolationException
import org.babyfish.jimmer.sql.exception.ExecutionException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException
import top.vcare.common.asserts.BaseAssertEnum
import top.vcare.common.dto.ApiResult
import top.vcare.common.exception.VcareException

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)


    @ExceptionHandler(NoHandlerFoundException::class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    fun handleNotFoundException(e: NoHandlerFoundException): ApiResult<Void> {
        log.error("访问资源不存在[ {} {} ]", e.httpMethod, e.requestURL, e)
        val apiResult = ApiResult.err(BaseAssertEnum.NOT_FOUND)
        return apiResult
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValidException(
        e: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ApiResult<Void> {
        log.error("[{}]请求参数错误:{}", request.requestURL, e.message, e)
        val errors = mutableListOf<String>()
        e.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.defaultMessage ?: "[${fieldName}] is Invalid value"
            errors.add(errorMessage)
        }
        return ApiResult.err(BaseAssertEnum.PARAMS_INVALID.code, errors.joinToString(";"), null)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun handleConstraintViolationException(
        e: ConstraintViolationException,
        request: HttpServletRequest
    ): ApiResult<Void> {
        log.error("[{}]请求参数错误:{}", request.requestURL, e.message, e)
        val errors = mutableListOf<String>()
        e.constraintViolations.forEach { violation ->
            val errorMessage = violation.message
            errors.add(errorMessage)
        }
        return ApiResult.err(BaseAssertEnum.PARAMS_INVALID.code, errors.joinToString(";"), null)
    }

    @ExceptionHandler(VcareException::class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleVcareException(e: VcareException, request: HttpServletRequest): ApiResult<Void> {
        val requestURI: String = request.servletPath
        log.error("业务异常：{}，${e.code}:${e.msg}", requestURI, e)
        return ApiResult.err(e.code, e.msg, null)
    }


    @ExceptionHandler(ExecutionException::class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleExecutionException(e: ExecutionException, request: HttpServletRequest): ApiResult<Void> {
        val requestURI: String = request.servletPath
        log.error("sql执行异常：{}", requestURI, e)
        return ApiResult.err(BaseAssertEnum.INTERNAL_SERVER_ERROR)
    }


    @ExceptionHandler(Exception::class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(e: Exception, request: HttpServletRequest): ApiResult<Void> {
        val requestURI: String = request.servletPath
        log.error("发生未知异常：{}", requestURI, e)
        return ApiResult.err(BaseAssertEnum.INTERNAL_SERVER_ERROR)
    }


}