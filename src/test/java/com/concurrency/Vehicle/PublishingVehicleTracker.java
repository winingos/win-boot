package com.concurrency.Vehicle;

import com.concurrency.annotation.GuardedBy;
import com.concurrency.annotation.ThreadSafe;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 王宁 on 2018/2/24.
 */
@ThreadSafe
public class PublishingVehicleTracker {
    private final Map<String, SafePoint> locations;
    private final Map<String, SafePoint> unmodifiableMap;

    public PublishingVehicleTracker(Map<String, SafePoint> locations) {
        this.locations = new ConcurrentHashMap<>(locations);
        this.unmodifiableMap = Collections.unmodifiableMap(this.locations);
    }

    public Map<String, SafePoint> getLocations() {
        return unmodifiableMap;
    }
    public SafePoint getLocation(String id) {
        return locations.get(id);
    }
    public void setLocation(String id, int x, int y) {
        if (!locations.containsKey(id))
            throw new IllegalArgumentException(
                    "invalid vehicle name: " + id);
        locations.get(id).set(x, y);
    }
}
@ThreadSafe
class SafePoint {
    @GuardedBy("this")
    private int x, y;
    private SafePoint(int[] a) { this(a[0], a[1]); }
    public SafePoint(SafePoint p) { this(p.get()); }
    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public synchronized int[] get() {
        return new int[] { x, y };
    }
    public synchronized void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}