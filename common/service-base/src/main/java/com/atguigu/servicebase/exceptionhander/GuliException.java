package com.atguigu.servicebase.exceptionhander;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SongYu
 * @version 1.0.0
 * @ClassName GuliException.java
 * @Description TODO
 * @createTime 2022年03月26日 18:03:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException{

    @ApiModelProperty(value = "状态码")
    private Integer code;
    private String msg;
}
