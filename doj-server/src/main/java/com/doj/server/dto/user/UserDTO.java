package com.doj.server.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类描述：用户信息DTO
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String userId;

    private String username;

    private String avater;

    private String gender;
}
