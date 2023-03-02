package cloud.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "支付信息")
public class Payment implements Serializable {
    @ApiModelProperty(value = "商品id")
    private long id;
    @ApiModelProperty(value = "商品序列号")
    private String serial;
}
