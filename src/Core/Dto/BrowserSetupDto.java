package Core.Dto;

import Core.Enums.BrowserType;

public class BrowserSetupDto {
    private BrowserType browserType;

    private boolean useMinimumResolution;

    private String minimumResolution;

    private String[] arguments;

    private UserProfilePreferenceDto[] userProfilePreferences;

    public BrowserType getBrowserType() {
        return browserType;
    }

    public void setBrowserType(BrowserType browserType) {
        this.browserType = browserType;
    }

    public boolean isUseMinimumResolution() {
        return useMinimumResolution;
    }

    public void setUseMinimumResolution(boolean useMinimumResolution) {
        this.useMinimumResolution = useMinimumResolution;
    }

    public String getMinimumResolution() {
        return minimumResolution;
    }

    public void setMinimumResolution(String minimumResolution) {
        this.minimumResolution = minimumResolution;
    }

    public String[] getArguments() {
        return arguments;
    }

    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }

    public UserProfilePreferenceDto[] getUserProfilePreferences() {
        return userProfilePreferences;
    }

    public void setUserProfilePreferences(UserProfilePreferenceDto[] userProfilePreferences) {
        this.userProfilePreferences = userProfilePreferences;
    }
}
