package com.wust.ems.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

//操作日志实体类

@Data
@NoArgsConstructor
@AllArgsConstructor
public class operateLog {
    private Integer id;            // ID
    private Integer operateUser;   // 操作人ID
    private LocalDateTime operateTime; // 操作时间
    private String className;      // 操作的类名
    private String methodName;     // 操作的方法名
    private String methodParams;   // 方法参数
    private String returnValue;    // 返回值
    private Long costTime;         // 方法执行耗时，单位:ms
}
