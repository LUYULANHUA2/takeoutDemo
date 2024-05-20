package com.lanhua.dto;


//import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
//@ApiModel(description = "员工登录时传递的数据模型")
//@Schema(description = "部门")
public class EmployeeLoginDTO implements Serializable {

//    @Schema(description = "名称")
    private String username;

//    @Schema(description = "密码")
    private String password;

}
