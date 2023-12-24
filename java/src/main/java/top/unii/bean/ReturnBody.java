package top.unii.bean;


import java.util.ArrayList;
import java.util.List;
import top.unii.enums.*;
/**
 * 工具类
 */
public class ReturnBody {
    
    /**
     * 返回text文本消息
     * @param conversationId 会话ID
     * @param text 文本内容
     * @return 返回的消息格式
     */
    public static RelayResponseBody text(String conversationId, String text) {
        List<RelayResponseBody.Body> bodyList = new ArrayList<>();
        bodyList.add(new RelayResponseBody.Body().setMessageType(ResponseMessageTypeEnum.TEXT.id).setContent(text));
        return new RelayResponseBody().setConversationId(conversationId).setBody(bodyList);
    }
    
    /**
     * 返回text文本消息 - html富文本
     * @param conversationId 会话ID
     * @param text 文本内容
     * @return 返回的消息格式
     */
    public static RelayResponseBody html(String conversationId, String text) {
        List<RelayResponseBody.Body> bodyList = new ArrayList<>();
        bodyList.add(new RelayResponseBody.Body().setMessageType(ResponseMessageTypeEnum.TEXT.id).setContent(text).setIsHtml(1));
        return new RelayResponseBody().setConversationId(conversationId).setBody(bodyList);
    }
    
    /**
     * 返回charge收费消息
     * @param conversationId 会话ID
     * @param transactionId 交易订单号
     * @param chargeMoneyFen 收费总金额/单位分
     * @param chargeTitle 收费项目名称
     * @param chargeBillDetails 账单/收费明细
     * @return 响应主体
     */
    public static RelayResponseBody charge(String conversationId, String transactionId, Integer chargeMoneyFen, String chargeTitle, String chargeBillDetails) {
        List<RelayResponseBody.Body> bodyList = new ArrayList<>();
        bodyList.add(new RelayResponseBody.Body()
                .setMessageType(ResponseMessageTypeEnum.CHARGE.id).setTransactionId(transactionId)
                .setChargeMoneyFen(chargeMoneyFen).setChargeTitle(chargeTitle).setChargeBillDetails(chargeBillDetails)
        );
        return new RelayResponseBody().setConversationId(conversationId).setBody(bodyList);
    }
    
    /**
     * 返回空数组
     * @param conversationId 会话ID
     * @return 返回的消息格式
     */
    public static RelayResponseBody nobody(String conversationId) {
        return new RelayResponseBody().setConversationId(conversationId).setBody(new ArrayList<>());
    }
    
}






















