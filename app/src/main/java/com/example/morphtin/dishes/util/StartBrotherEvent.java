package com.example.morphtin.dishes.util;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by elevation on 18-4-4.
 */

public class StartBrotherEvent {
    public SupportFragment targetFragment;

    public StartBrotherEvent(SupportFragment targetFragment) {
        this.targetFragment = targetFragment;
    }

}
