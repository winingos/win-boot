package com.comment.concurrency;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by Administrator on 17/4/18.
 * fork/join框架会将任务分发给线程池中的工作线程
 * fork/join框架的独特之处在与它使用工作窃取(work-stealing)算法
 * fork/join框架的核心是ForkJoinPool类，它是对AbstractExecutorService类的扩展。
 * ForkJoinPool实现了工作偷取算法，并可以执行ForkJoinTask任务<br>
 * <blockquote><pre>
 * if (当前这个任务工作量足够小)
 *    直接完成这个任务
 * else
 *    将这个任务或这部分工作分解成两个部分
 *    分别触发(invoke)这两个子任务的执行，并等待结果
 * </pre></blockquote>
 * 你需要将这段代码包裹在一个ForkJoinTask的子类中。
 * 不过，通常情况下会使用一种更为具体的的类型，或者是RecursiveTask(会返回一个结果)，或者是RecursiveAction。
 * 当你的ForkJoinTask子类准备好了，创建一个代表所有需要完成工作的对象，
 * 然后将其作为参数传递给一个ForkJoinPool实例的invoke()方法即可
 */


class ForkBlur extends RecursiveAction {
    private int[] mSource;
    private int mStart;
    private int mLength;
    private int[] mDestination;

    // Processing window size; should be odd.  
    private int mBlurWidth = 15;

    public ForkBlur(int[] src, int start, int length, int[] dst) {
        mSource = src;
        mStart = start;
        mLength = length;
        mDestination = dst;
    }

    // Average pixels from source, write results into destination.
    protected void computeDirectly() {
        int sidePixels = (mBlurWidth - 1) / 2;
        for (int index = mStart; index < mStart + mLength; index++) {
            // Calculate average.  
            float rt = 0, gt = 0, bt = 0;
            for (int mi = -sidePixels; mi <= sidePixels; mi++) {
                int mindex = Math.min(Math.max(mi + index, 0), mSource.length - 1);
                int pixel = mSource[mindex];
                rt += (float) ((pixel & 0x00ff0000) >> 16) / mBlurWidth;
                gt += (float) ((pixel & 0x0000ff00) >> 8) / mBlurWidth;
                bt += (float) ((pixel & 0x000000ff) >> 0) / mBlurWidth;
            }

            // Reassemble destination pixel.  
            int dpixel = (0xff000000) | (((int) rt) << 16) | (((int) gt) << 8) | (((int) bt) << 0);
            mDestination[index] = dpixel;
        }
    }

    /**
     * 接下来你需要实现父类中的compute()方法，它会直接执行模糊处理，或者将当前的工作拆分成两个更小的任务。
     * 数组的长度可以作为一个简单的阀值来判断任务是应该直接完成还是应该被拆分。
     */
    protected static int sThreshold = 100000;

    @Override
    protected void compute() {
        if (mLength < sThreshold) {
            computeDirectly();
            return;
        }

        int split = mLength / 2;

        invokeAll(new ForkBlur(mSource, mStart, split, mDestination),
                new ForkBlur(mSource, mStart + split, mLength - split, mDestination));

    }


    public static BufferedImage blur(BufferedImage srcImage) {
        int w = srcImage.getWidth();
        int h = srcImage.getHeight();

        int[] src = srcImage.getRGB(0, 0, w, h, null, 0, w);
        int[] dst = new int[src.length];

        System.out.println("Array size is " + src.length);
        System.out.println("Threshold is " + sThreshold);

        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(Integer.toString(processors) + " processor"
                + (processors != 1 ? "s are " : " is ")
                + "available");

        ForkBlur fb = new ForkBlur(src, 0, src.length, dst);

        ForkJoinPool pool = new ForkJoinPool();

        long startTime = System.currentTimeMillis();
        pool.invoke(fb);
        long endTime = System.currentTimeMillis();

        System.out.println("Image blur took " + (endTime - startTime) +
                " milliseconds.");

        BufferedImage dstImage =
                new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        dstImage.setRGB(0, 0, w, h, dst, 0, w);

        return dstImage;
    }

}

public class ForkJoint {
    // Plumbing follows.
    public static void main(String[] args) throws Exception {
        String srcName = "red-tulips.jpg";
        File srcFile = new File(srcName);
        BufferedImage image = ImageIO.read(srcFile);

        System.out.println("Source image: " + srcName);

        BufferedImage blurredImage = ForkBlur.blur(image);

        String dstName = "blurred-tulips.jpg";
        File dstFile = new File(dstName);
        ImageIO.write(blurredImage, "jpg", dstFile);

        System.out.println("Output image: " + dstName);

    }
}
