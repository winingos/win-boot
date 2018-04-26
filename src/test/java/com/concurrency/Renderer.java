package com.concurrency;

import com.sun.scenario.effect.ImageData;

import java.util.List;
import java.util.concurrent.*;

import static com.concurrency.building_blocks.Preloader.launderThrowable;

/**
 * Created by 王宁 on 2018/4/26.
 * CompletionService 的使用,管理一组计算任务结果
 */
public class Renderer {
    private final ExecutorService executor;
    Renderer(ExecutorService executor) { this.executor = executor; }
    void renderPage(CharSequence source) {
        final List<ImageInfo> info = scanForImageInfo(source);
        CompletionService<ImageData> completionService =
                new ExecutorCompletionService<ImageData>(executor);
        for (final ImageInfo imageInfo : info)
            completionService.submit(new Callable<ImageData>() {
                public ImageData call() {
                    return imageInfo.downloadImage();
                }
            });
        renderText(source);
        try {
            for (int t = 0, n = info.size(); t < n; t++) {
                Future<ImageData> f = completionService.take();
                ImageData imageData = f.get();
                renderImage(imageData);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause());
        }
    }


    /**
     * CompletionService
     * @param imageData
     */



    private void renderImage(ImageData imageData) {
    }

    private void renderText(CharSequence source) {

    }

    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        return null;
    }
}
class ImageInfo{
    public ImageData downloadImage() {
        return null;
    }
}
