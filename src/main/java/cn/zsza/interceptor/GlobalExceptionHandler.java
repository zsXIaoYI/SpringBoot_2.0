package cn.zsza.interceptor;

import cn.zsza.exception.CustomException;
import cn.zsza.exception.ErrorResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Created By zhangsong
 * 21:09 2018/8/29
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ErrorResponseEntity customExceptionHandler(HttpServletRequest request, final Exception e,
	                                                  HttpServletResponse response){
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		CustomException exception = (CustomException) e;
		return new ErrorResponseEntity(exception.getCode(),exception.getMessage());
	}
	@ExceptionHandler({RuntimeException.class})
	public ErrorResponseEntity runtimeExceptionHandler(HttpServletRequest request, final Exception e,
	                                                   HttpServletResponse response){
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		RuntimeException exception = (RuntimeException) e;
		return new ErrorResponseEntity(400,exception.getMessage());
	}
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
	                                                         HttpStatus status, WebRequest request) {
		if (ex instanceof MethodArgumentNotValidException){
			MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
			return new ResponseEntity<>(new ErrorResponseEntity(status.value(),
					exception.getBindingResult().getAllErrors().get(0).getDefaultMessage()),status);
		}
		if (ex instanceof MethodArgumentTypeMismatchException) {
			MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) ex;
			return new ResponseEntity<>(new ErrorResponseEntity(status.value(),
					"方法:{ " + exception.getParameter().getMethod().getName() + "} 参数: {" + exception.getName() + "}"),
					status);
		}
		return new ResponseEntity<>(new ErrorResponseEntity(status.value(), "参数转换失败"), status);
	}
}
