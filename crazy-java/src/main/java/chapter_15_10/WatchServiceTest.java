package chapter_15_10;

import java.io.IOException;
import java.nio.file.*;

/**
 * @author pengbo
 * @since 2021/2/24
 */
public class WatchServiceTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get("crazy-java-tmp");

        path.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        while (true) {
            // 阻塞方法
            WatchKey watchKey = watchService.take();

            for (WatchEvent<?> event : watchKey.pollEvents()) {
                System.out.println(event.context() + " 文件发生了 " + event.kind() + " 事件！");
            }

            // 重设 watchKey
            boolean reset = watchKey.reset();
            if (!reset) {
                // 如果重设失败，推出监听
                break;
            }
        }
    }
}
