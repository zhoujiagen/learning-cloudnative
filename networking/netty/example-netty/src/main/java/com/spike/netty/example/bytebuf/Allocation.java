package com.spike.netty.example.bytebuf;

import com.google.common.base.Preconditions;
import com.spike.netty.support.ByteBufs;
import io.netty.buffer.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * {@link ByteBuf}的分配
 * <p>
 * Netty默认使用{@link PooledByteBufAllocator}, 覆盖这一行为的方法: ChannelConfig; bootstrapping.
 * <p>
 * ByteBufAllocator可以从{@link Channel}, {@link ChannelHandlerContext}中获取.
 *
 * @see ByteBufAllocator
 * @see PooledByteBufAllocator
 * @see UnpooledByteBufAllocator
 * @see Channel#alloc()
 * @see ChannelHandlerContext#alloc()
 * @see Unpooled
 */
public class Allocation {
    public static void main(String[] args) {
        PooledByteBufAllocator allocator = new PooledByteBufAllocator(false);
        Preconditions.checkNotNull(allocator);

        // heap or direct
        ByteBuf buf = allocator.buffer();
        Preconditions.checkNotNull(buf);
        System.out.println(ByteBufs.introspect(buf));

        // heap
        ByteBuf heapBuf = allocator.heapBuffer();
        Preconditions.checkNotNull(heapBuf);
        System.out.println(ByteBufs.introspect(heapBuf));
        // 比较两个ByteBuf是否相等
        System.out.println(ByteBufUtil.equals(buf, heapBuf));

        // direct
        ByteBuf directBuf = allocator.directBuffer();
        Preconditions.checkNotNull(directBuf);
        System.out.println(ByteBufs.introspect(directBuf));

        // io
        ByteBuf ioBuf = allocator.ioBuffer();
        Preconditions.checkNotNull(ioBuf);
        System.out.println(ByteBufs.introspect(ioBuf));

        // composite
        CompositeByteBuf compositeByteBuf = allocator.compositeBuffer();
        Preconditions.checkNotNull(compositeByteBuf);
        System.out.println(ByteBufs.introspect(compositeByteBuf));

        // 使用Unpooled
        System.out.println(ByteBufs.introspect(Unpooled.buffer()));
        System.out.println(ByteBufs.introspect(Unpooled.directBuffer()));
        System.out.println(ByteBufs.introspect(Unpooled.compositeBuffer()));
    }
}
