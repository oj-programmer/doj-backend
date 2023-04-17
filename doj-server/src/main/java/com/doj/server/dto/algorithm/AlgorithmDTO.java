package com.doj.server.dto.algorithm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类描述：算法DTO
 *
 * @author kongweikun <kongweikun@baidu.com>
 * @date 2023/4/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlgorithmDTO {

    private String algorithmId;

    private String name;

    private String link;

    private String difficulty;
}
