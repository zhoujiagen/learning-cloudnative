package com.spike.netty.example.codec.provided.protocol;

import com.spike.netty.support.ChannelHandlers;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 基于长度的协议支持
 *
 * @see io.netty.handler.codec.FixedLengthFrameDecoder
 * @see io.netty.handler.codec.LengthFieldBasedFrameDecoder
 */
public class LengthBasedInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        int maxFrameLength = ChannelHandlers.CONTENT_LENGTH_64B; // 最大帧长
        int lengthFieldOffset = 0; // 长度字段的偏移量
        int lengthFieldLength = 8; // 长度字段的长度
        pipeline.addLast(//
                new LengthFieldBasedFrameDecoder(maxFrameLength, lengthFieldOffset, lengthFieldLength));

        pipeline.addLast(ChannelHandlers.SIMPLE());
    }

}
