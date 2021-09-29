package com.redrain.common.jwt;

import com.redrain.common.exception.CustomerException;
import com.redrain.common.result.ReturnData;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import net.minidev.json.JSONObject;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * @Author 张红雨【1351150492@qq.com】
 * @Date 2019/2/27 10:40
 */
public class JwtUtil {
    /**
     * 1.创建一个32-byte的密匙
     */

    private static final byte[] secret = "G@%DVwcWoU$I1jQQhm$A5o9m@B77U*LL".getBytes();


    //生成一个token
    public static String creatToken(Map<String, Object> payloadMap) {
        //3.先建立一个头部Header
        /**
         * JWSHeader参数：1.加密算法法则,2.类型，3.。。。。。。。
         * 一般只需要传入加密算法法则就可以。
         * 这里则采用HS256
         *
         * JWSAlgorithm类里面有所有的加密算法法则，直接调用。
         */
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        //建立一个载荷Payload
        Payload payload = new Payload(new JSONObject(payloadMap));

        //将头部和载荷结合在一起
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            //建立一个密匙
            JWSSigner jwsSigner = new MACSigner(secret);
            //签名
            jwsObject.sign(jwsSigner);
        } catch (JOSEException e) {
            throw new CustomerException("toke生成失败");
        }
        //生成token
        return jwsObject.serialize();
    }

    //解析一个token
    public static ReturnData getTokenData(String token) throws ParseException, JOSEException {
        ReturnData returnData = null;
//        解析token
        JWSObject jwsObject = JWSObject.parse(token);
        //获取到载荷
        Payload payload = jwsObject.getPayload();
        //建立一个解锁密匙
        JWSVerifier jwsVerifier = new MACVerifier(secret);
        //判断token
        if (jwsObject.verify(jwsVerifier)) {
            //载荷的数据解析成json对象。
            JSONObject jsonObject = payload.toJSONObject();
            //判断token是否过期
            if (jsonObject.containsKey("exp")) {
                Long expTime = Long.valueOf(jsonObject.get("exp").toString());
                Long nowTime = new Date().getTime();
                //判断是否过期
                if (nowTime < expTime) {
                    returnData = ReturnData.success(jsonObject);
                }else{
                    //已经过期
                    returnData = ReturnData.loginTimeout();
                }
            }
        } else {
            returnData = ReturnData.fail("token令牌有误");
        }
        return returnData;
    }
}
