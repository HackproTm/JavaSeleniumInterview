package Core.Dto;

public class TestConfigDto {
    private boolean loadData;
    private boolean clearData;
    private int timeOut;
    private BrowserSetupDto browserSetup;

    public boolean isLoadData() {
        return loadData;
    }

    public void setLoadData(boolean loadData) {
        this.loadData = loadData;
    }

    public boolean isClearData() {
        return clearData;
    }

    public void setClearData(boolean clearData) {
        this.clearData = clearData;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public BrowserSetupDto getBrowserSetup() {
        return browserSetup;
    }

    public void setBrowserSetup(BrowserSetupDto browserSetup) {
        this.browserSetup = browserSetup;
    }
}
