package top.unii.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应的消息类型: text文本消息 sendredpack红包消息 charge收费消息
 */
@Getter
@AllArgsConstructor
public enum ResponseMessageTypeEnum {
    /**
     * 消息类型: text文本消息 sendredpack红包消息 charge收费消息
     */
    TEXT("text", "文本消息"),
    SENDREDPACK("sendredpack", "红包消息"),
    CHARGE("charge", "收费消息"),
    ;
    
    public final String id;
    public final String title;
    
    
}































