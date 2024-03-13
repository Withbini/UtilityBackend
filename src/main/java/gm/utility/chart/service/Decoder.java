package gm.utility.chart.service;

import gm.utility.global.exception.Base64DecodingException;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class Decoder {
    public byte[] decode(String data) throws Base64DecodingException {
        try {
            return Base64.getDecoder().decode(data);
        } catch (Exception e) {
            throw new Base64DecodingException("base64 디코딩에 실패했습니다. 확인해주세요.");
        }
    }
}