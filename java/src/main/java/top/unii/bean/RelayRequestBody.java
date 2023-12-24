package top.unii.bean;


import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class RelayRequestBody {
    
    // 消息类型：verify服务器验证 conversation消息 notice通知
    private String messageType;
    
    // 本次会话的ID
    private String conversationId;
    
    // 用户在平台的ID，官方公众号下用户ID相同。第三方公众号下用户ID不同。
    private String userId;
    
    // 用户输入的文本消息内容，原始的。
    private String content;
    
    // 用户输入的文本消息内容，平台修剪后的。可以自行使用原始内容content自行解析
    private String contentTrim;
    
    // 匹配规则：mention @提及关键字，equals和关键字一致
    private String matchRule;
    
    // 访问的关键字域名
    private String keyword;
    
    /*
        以下 4个参数（noticeCode、noticeCode、transactionId、accountBalance）在 messageType为 notice通知的时候才会存在。
     */
    // 通知代码 notice时 1000收费成功 2000发红包成功 3000发红包失败
    private String noticeCode;
    
    // 通知文本 noticeCode = 1000收费成功 2000发红包成功 3000发红包失败-余额不足-对方账号风控
    private String noticeText;
    
    // 交易订单号，由你响应的。
    private String transactionId;
    
    // 你的账户当前账户余额
    private BigDecimal accountBalance;
    
    /*
        以下 3个参数（appId、scene、windowType）在 header头中传递
     */
    // 用户访问域名时所使用的公众号ID
    public String appId;
    
    // 用户访问域名时所使用的公众号ID对应的openID
    public String openId;
    
    // 用户访问域名时的场景: subscribe新关注、scan扫码、msg发消息、voice语音
    private String scene;
    
    // 用户访问域名时的窗口类型: 电脑PC网页web_pc、手机h5网页web_m、公众号mp、小程序ma、
    private String windowType;
    
    
    @Override
    public String toString() {
        return "RelayRequestBody{" +
                "messageType='" + messageType + '\'' +
                ", conversationId='" + conversationId + '\'' +
                ", userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", contentTrim='" + contentTrim + '\'' +
                ", matchRule='" + matchRule + '\'' +
                ", keyword='" + keyword + '\'' +
                ", noticeCode='" + noticeCode + '\'' +
                ", noticeText='" + noticeText + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", accountBalance=" + accountBalance +
                ", appId='" + appId + '\'' +
                ", scene='" + scene + '\'' +
                ", windowType='" + windowType + '\'' +
                '}';
    }
    
}

























