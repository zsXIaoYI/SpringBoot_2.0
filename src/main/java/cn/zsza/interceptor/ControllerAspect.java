package cn.zsza.interceptor;

import cn.zsza.Vo.MsgResponse;
import cn.zsza.Vo.U;
import cn.zsza.annotation.MyAnnotation;
import cn.zsza.exception.CustomException;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created By zhangsong
 * 19:27 2018/8/29
 */
@Configuration
@Aspect
public class ControllerAspect {

	@Autowired
	private RedisTemplate redisTemplate;

	@Pointcut("execution ( * cn.zsza.controller..*.*(..))")
	private void anyMethod(){
	}

//	@Before("anyMethod()")
//	public void doAccessCheck(){
//		System.out.println("before....");
//	}

	@Around("anyMethod()")
	public Object methodAround(ProceedingJoinPoint pjp) throws Throwable {
		/**
		 * 得到Request对象
		 */
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		String sessionId = request.getSession().getId();

		Signature signature = pjp.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);

		if (annotation != null){
			U u = (U) redisTemplate.opsForValue().get("session:" + sessionId);
			Integer val = u.getVal();
			if (val > annotation.val()){
				return pjp.proceed();
			}else {
				throw new CustomException(403,"你无权限");
			}
		}

		return pjp.proceed();
	}
}
