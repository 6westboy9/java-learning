package com.westboy.observer.practice.example02;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 铃声事件类：用于封装事件源及一些与事件相关的参数
 *
 * @author pengbo.wang
 * @date 2019/7/27
 * @since 1.0
 */
@Data
@AllArgsConstructor
public class RingEvent {
    /**
     * true表示上课铃声,false表示下课铃声
     */
    private boolean sound;
}
