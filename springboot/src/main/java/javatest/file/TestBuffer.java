package javatest.file;

import java.nio.ByteBuffer;

/**
 * @Description:   每个DirectByteBuffer都会有一个幻影引用的Cleaner  PhantomReference 进行 ummap
 * @author: zhbo
 * @date 2020/11/25 15:14
 */
public class TestBuffer {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(6);
        byte[] bytes = {1,2,3,5};
        buffer.put(bytes);
        buffer.flip();
        System.out.println(buffer.get());

        ByteBuffer slice = buffer.slice();/*复制一套buffer,为原buffer的子序列，起始位置为之前buffer的position位置
        ，byte[]相同，position为0、mark为-1 ，但是slice的capacity、limit会变*/

        buffer.flip();/*交换读写位置*/
        System.out.println(buffer.get());

        buffer.rewind(); //重置position mark
        System.out.println(buffer.get());
    }
}
