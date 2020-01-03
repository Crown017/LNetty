package com.clown.netty.myprotocol;

/**
 * 简单协议
 *
 * 报文长度 + 报文内容
 */
public class MyProtocol {

    private Integer length;

    private byte[] content;

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
