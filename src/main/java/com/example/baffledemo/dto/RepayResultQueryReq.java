package com.example.baffledemo.dto;

import com.example.baffledemo.utils.ValidationUtil;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ToString
public class RepayResultQueryReq implements Serializable {
    @Valid
    private MrPubHeadReq head;
    @Valid
    @NotNull
    private QueryRepayResultReq body;

    public String validate() {
        StringBuffer stringBuffer = new StringBuffer();
        ValidationUtil.ValidResult validHead = ValidationUtil.validateBean(head);
        if (validHead.hasErrors()) {
            stringBuffer.append(validHead.getAllErrors());
        }
        String errors = body.validate();
        if (!StringUtils.isBlank(errors)) {
            stringBuffer.append(errors);
        }
        return stringBuffer.toString();
    }


}
