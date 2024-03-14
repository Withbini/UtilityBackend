package gm.utility.ssh.service;

import gm.utility.global.exception.Base64DecodingException;
import gm.utility.global.exception.Base64EncodingException;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class Encoder {
    public byte[] encode(String data) {
        try {
            return Base64.getEncoder().encode(data.getBytes());
        } catch (Exception e) {
            throw new Base64EncodingException("base64 인코딩에 실패했습니다.");
        }
    }
}