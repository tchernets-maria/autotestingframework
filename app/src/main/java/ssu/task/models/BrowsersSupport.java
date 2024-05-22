package ssu.task.models;

import lombok.Getter;
import org.openqa.selenium.Capabilities;

import java.util.Map;

@Getter
public enum BrowsersSupport implements Capabilities {
    FIREFOX("firefox"),
    CHROME("chrome");

    private final String browserName;

    BrowsersSupport(String browserName) {
        this.browserName = browserName;
    }

    @Override
    public Map<String, Object> asMap() {
        return null;
    }

    @Override
    public Object getCapability(String capabilityName) {
        return null;
    }
}

