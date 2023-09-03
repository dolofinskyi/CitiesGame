package ua.project.utils;

import java.util.List;

public class LoadManager {
    public static List<String> load(boolean isOnline){
        if (isOnline) {
            return OnlineLoader.load();
        }
        return OfflineLoader.load();
    }
}
