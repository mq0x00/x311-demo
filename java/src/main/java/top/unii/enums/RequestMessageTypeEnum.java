package top.unii.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 请求的消息类型: verify服务器验证 conversation消息 notice通知
 */
@Getter
@AllArgsConstructor
public enum RequestMessageTypeEnum {
    
    /**
     * 消息类型: verify服务器验证 conversation消息 notice通知
     */
    VERIFY("verify", "服务器验证"),
    CONVERSATION("conversation", "消息"),
    NOTICE("notice", "通知"),
    ;
    
    public final String id;
    public final String title;
    
}































