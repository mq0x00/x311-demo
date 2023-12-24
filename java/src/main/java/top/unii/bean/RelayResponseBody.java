package top.unii.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;


/**
 *  自定义请求的响应体。
 */
@Data
@Accessors(chain = true)
public class RelayResponseBody {
    
    // 转发给你的会话ID
    public String conversationId;
    
    // 消息主体
    public List<Body> body;
    
    // 消息主体
    @Data
    public static class Body {
    
        // 消息类型：text文本消息 sendredpack红包消息 charge收费消息
        private String messageType;
        
        /*
            text文本消息
         */
        // messageType=text时的消息内容
        private String content;
    
        // 是否是html富文本，0 不是，1 是的。messageType=text时有效
        private Integer isHtml;
        
        /*
            sendredpack红包消息: redpackMoneyFen红包总金额 redpackName红包发送者名称 redpackWishing祝福语
         */
        // 红包总金额/单位分
        private Integer redpackMoneyFen;
    
        // 红包名称
        private String redpackName;
        
        // 祝福语
        private String redpackWishing;
        
        /*
            charge收费: chargeMoneyFen收费总金额 chargeTitle收费项目名称 chargeBillDetails账单/收费明细
         */
        // 收费总金额/单位分
        private Integer chargeMoneyFen;
    
        // 收费项目名称
        private String chargeTitle;
    
        // 账单/收费明细
        private String chargeBillDetails;
        
        /*
            订单号，如果响应的是红包消息或者收费消息时必填。
         */
        // 交易订单号
        private String transactionId;
        
    }
    
}






























