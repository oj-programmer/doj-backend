package com.doj.server.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 类描述: TODO
 *
 * @author kongweikun@163.com
 * @date 2023/4/18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginTicketDTO {

    private int id;

    /**
     * 用户 id
     */
    private String userId;

    /**
     * 凭证
     */
    private String ticket;

    /**
     * 状态
     */
    private int status;

    /**
     * 过期时间
     */
    private Date expired;
}
