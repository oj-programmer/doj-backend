package com.doj.server.infrastructure.interceptor;

import com.doj.server.dto.user.LoginTicketDTO;
import com.doj.server.dto.user.UserDTO;
import com.doj.server.infrastructure.constant.SystemConstant;
import com.doj.server.infrastructure.enums.ServiceCodeEnum;
import com.doj.server.infrastructure.exception.DojServerException;
import com.doj.server.infrastructure.utils.CookieUtil;
import com.doj.server.infrastructure.utils.EmptyUtil;
import com.doj.server.infrastructure.utils.ThreadUtil;
import com.doj.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 类描述: 用户信息拦截器
 *
 * @author kongweikun@163.com
 * @date 2023/4/18
 */
@Slf4j
@Component
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ticket = CookieUtil.getValue(request, SystemConstant.COOKIE_TICKET);
        if (!EmptyUtil.isEmpty(ticket)) {
            LoginTicketDTO loginTicket = userService.getLoginTicket(ticket);
            // 检测凭证是否有效
            if (!EmptyUtil.isEmpty(loginTicket) && loginTicket.getStatus() == 0) {
                    // TODO ticket 设置
                    // && loginTicket.getExpired().after(new Date())) {
                // 根据凭证查询用户
                UserDTO user = userService.getUserByUserId(loginTicket.getUserId());
                // 在本次请求中持有user
                ThreadUtil.setUser(user);
                // TODO Spring security 信息填充
                // 构建用户认证的结果,并存入SecurityContext,以便于Security进行授权.
//                Authentication authentication = new UsernamePasswordAuthenticationToken(
//                        user, user.getPassword(), userService.getAuthorities(user.getId()));
//                SecurityContextHolder.setContext(new SecurityContextImpl(authentication));
            } else {
                throw new DojServerException(ServiceCodeEnum.USER_TICKET_EXPIRED,
                        String.format("ticket 过期, ticket = [%s]", ticket));
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadUtil.clearUser();
        log.info("清理 user 成功...");
    }
}
