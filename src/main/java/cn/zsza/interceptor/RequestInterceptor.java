package cn.zsza.interceptor;

import cn.zsza.Vo.MsgResponse;
import cn.zsza.Vo.U;
import cn.zsza.annotation.MyAnnotation;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
/**
 * Created By zhangsong
 * 21:18 2018/8/28
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String sessionId = request.getSession().getId();
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
		if (annotation != null){
			U u = (U) redisTemplate.opsForValue().get("session:" + sessionId);
			Integer val = u.getVal();
			if (val > annotation.val()){
				return true;
			}else {
				MsgResponse msg = new MsgResponse(00,u.getName() + "无权访问");
				response.setHeader("Content-type", "text/json;charset=UTF-8");
				response.getWriter().write(JSON.toJSONString(msg));
				return false;
			}
		}
		return true;
	}
}
