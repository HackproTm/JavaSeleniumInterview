package Core.Dto;

import Core.Enums.PreferenceDataType;

public class UserProfilePreferenceDto {
    private String preference;

    private PreferenceDataType dataType;

    private String value;

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public PreferenceDataType getDataType() {
        return dataType;
    }

    public void setDataType(PreferenceDataType dataType) {
        this.dataType = dataType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
