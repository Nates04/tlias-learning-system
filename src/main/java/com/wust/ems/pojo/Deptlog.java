package com.wust.ems.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deptlog {
    private Integer id;              // 日志ID
    private String description;      // 日志描述
    private LocalDateTime createTime; // 创建时间
}
