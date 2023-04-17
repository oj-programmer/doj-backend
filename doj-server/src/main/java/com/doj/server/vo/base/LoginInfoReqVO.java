package com.doj.server.vo.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类描述: 登陆 VO
 *
 * @author kongweikun@163.com
 * @date 2023/4/16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginInfoReqVO {

    private String username;

    private String password;

}
