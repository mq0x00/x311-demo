package top.unii.service;


import cn.hutool.json.JSON;
import org.springframework.stereotype.Service;
import top.unii.bean.*;
import top.unii.enums.*;
import top.unii.bean.ReturnBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;


/**
 * 此示例的功能是返回当前时间
 */
@Service
public class TestService {
    
    /**
     * 对转发的请求进行处理并响应。要求在 6秒内响应。6秒内无法响应的可以稍后调用API发送会话消息
     * 详细的参数字段要求和长度，请你阅读文档：https://unii.top/#/?id=bzku7
     * @param body 转发请求参数
     * @param request 上下文
     * @return 响应体
     */
    public RelayResponseBody relayRequest(RelayRequestBody body, HttpServletRequest request) {
        /*
            获取请求头参数。
         */
        // 用户使用的公众号ID：比如 推条公众号wx6912623d37ef2535
        String appId = request.getHeader("x-app-id");
        // 用户使用的公众号对应的openID  o5N426Ax45Tx8fGx_hiDBLZNyilM
        String openId = request.getHeader("x-open-id");
        // 场景: subscribe新关注、scan扫码、msg发消息、
        String scene = request.getHeader("x-scene");
        // 窗口类型: 电脑PC网页web_pc、手机h5网页web_m、公众号mp、小程序ma
        String windowType = request.getHeader("x-window-type");
        
        body.setAppId(appId);
        body.setOpenId(openId);
        body.setScene(scene);
        body.setWindowType(windowType);
        
        System.out.println("===>请求参数打印：" + body);
        
        /*
            处理文本消息和通知消息。
         */
        if (body.getMessageType().equals(RequestMessageTypeEnum.CONVERSATION.id)) {
            /*
                根据匹配的规则，对访问域名，和提及域名（就是带有参数的访问），分别作出响应。
                实际业务中，你可以判断提及域名时的参数，做出不同的响应
             */
            // 匹配规则：mention @提及关键字，equals和关键字一致
            if("mention".equals(body.getMatchRule())) {
                
                // 响应一条收费信息，详情见 https://unii.top/#/?id=ddbxf
                if(body.getContentTrim().contains("收费")) {
                    // 生成交易订单号  限制长度 10-100之间
                    String transactionId = "id_" + System.currentTimeMillis();
                    return ReturnBody.charge(body.getConversationId(), transactionId,
                            100, "包月VIP充值", "这是收费消息的详情，如：VIP充值一个月，有效期截止下个月今天。");
                    
                    // 响应一条html文本消息，消息将以URL链接的形式触达用户，用户需要点击URL链接查看消息详情。
                } else if(body.getContentTrim().contains("富文本") || body.getContentTrim().contains("html")) {
                    // 内容长度限60000个字节以内（1个汉字占3个字节）
                    return ReturnBody.html(body.getConversationId(), "<span style='color:red;'>消息内容：" + new Date() + "</span>");
                    
                } else {
                    // 响应字符串时间。
                    return ReturnBody.text(body.getConversationId(), "消息内容：" + new Date() + "\n\n接收到参数：" + body.getContentTrim());
                }
                
                
            } else {
                // 非提及时，响应毫秒时间。
                return ReturnBody.text(body.getConversationId(), "消息内容：" + System.currentTimeMillis());
            }
            
            
        } else if (body.getMessageType().equals(RequestMessageTypeEnum.NOTICE.id)) {
            /*
                通知消息，根据 noticeCode 状态码回应
                通知代码：SENDREDPACK.SUCCESS红包发送成功 SENDREDPACK.FAIL红包发送失败 CHARGE.SUCCESS收费成功
             */
            if ("CHARGE.SUCCESS".equals(body.getNoticeCode())) {
    
                return ReturnBody.text(body.getConversationId(), "充值成功，感谢你的支持。");
                
            } else if("SENDREDPACK.SUCCESS".equals(body.getNoticeCode())) {
                
                return ReturnBody.text(body.getConversationId(), "恭喜你领到红包了，感谢你的支持。");
                
            } else if("SENDREDPACK.FAIL".equals(body.getNoticeCode())) {
                // 对于红包发送失败的情况，可以判断是否是余额不足，否则可能是对方的账号被微信风控，比如撸羊毛的。
                System.out.println("当前账户余额：" + body.getAccountBalance());
                return ReturnBody.text(body.getConversationId(), "红包发送失败，原因：" + body.getNoticeText());
            }
        }
        
        
        /*
            服务器验证消息的处理，配置服务器时需要验证接口的可用性。
         */
        if (body.getMessageType().equals(RequestMessageTypeEnum.VERIFY.id)) {
            return ReturnBody.nobody(body.getConversationId());
        }
        //
        return ReturnBody.nobody(body.getConversationId());
    }
    
    
    /**
     * 针对接入到第三方公众号中使用的示例
     * 第三方公众号中仅支持返回文本消息，不支持返回红包消息和收费消息以及异步消息。
     *
     * 对转发的请求进行处理并响应。要求在 6秒内响应。6秒内无法响应的可以稍后调用API发送会话消息
     * 详细的参数字段要求和长度，请你阅读文档：https://unii.top/#/?id=bzku7
     * @param body 转发请求参数
     * @param request 上下文
     * @return 响应体
     */
    public RelayResponseBody third(RelayRequestBody body, HttpServletRequest request) {
    
        /*
            获取请求头参数。
         */
        // 用户使用的公众号ID：比如 推条公众号wx6912623d37ef2535
        String appId = request.getHeader("x-app-id");
        // 用户使用的公众号对应的openID  o5N426Ax45Tx8fGx_hiDBLZNyilM
        String openId = request.getHeader("x-open-id");
        // 场景: subscribe新关注、scan扫码、msg发消息、
        String scene = request.getHeader("x-scene");
        // 窗口类型: 电脑PC网页web_pc、手机h5网页web_m、公众号mp、小程序ma
        String windowType = request.getHeader("x-window-type");
    
        body.setAppId(appId);
        body.setOpenId(openId);
        body.setScene(scene);
        body.setWindowType(windowType);
    
        System.out.println("===>请求参数打印：" + body);
        
        /*
            处理文本消息和通知消息。
         */
        if (body.getMessageType().equals(RequestMessageTypeEnum.CONVERSATION.id)) {
            /*
                根据匹配的规则，对访问域名，和提及域名（就是带有参数的访问），分别作出响应。
                实际业务中，你可以判断提及域名时的参数，做出不同的响应
             */
            // 匹配规则：mention @提及关键字，equals和关键字一致
            if("mention".equals(body.getMatchRule())) {
                
                // 响应一条html文本消息，消息将以URL链接的形式触达用户，用户需要点击URL链接查看消息详情。
                if(body.getContentTrim().contains("富文本") || body.getContentTrim().contains("html")) {
                    // 内容长度限60000个字节以内（1个汉字占3个字节）
                    return ReturnBody.html(body.getConversationId(), "<span style='color:red;'>消息内容：" + new Date() + "</span>");
                    
                } else {
                    // 响应字符串时间。
                    return ReturnBody.text(body.getConversationId(), "消息内容：" + new Date() + "\n\n接收到参数：" + body.getContentTrim());
                }
                
            } else {
                // 非提及时，响应毫秒时间。
                return ReturnBody.text(body.getConversationId(), "消息内容：" + System.currentTimeMillis());
            }
        
        }
        /*
            服务器验证消息的处理，配置服务器时需要验证接口的可用性。
         */
        if (body.getMessageType().equals(RequestMessageTypeEnum.VERIFY.id)) {
            return ReturnBody.nobody(body.getConversationId());
        }
        //
        return ReturnBody.nobody(body.getConversationId());
    }

}








































