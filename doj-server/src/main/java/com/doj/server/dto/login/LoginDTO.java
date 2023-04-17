package com.doj.server.dto.login;

import lombok.*;

/**
 * 类描述: 登陆 DTO
 *
 * @author kongweikun@163.com
 * @date 2023/4/16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    private String ticket;

    private String username;

    private String password;

}
