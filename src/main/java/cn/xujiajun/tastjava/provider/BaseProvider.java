package cn.xujiajun.tastjava.provider;

public class BaseProvider {
    protected static boolean IsRegistered = false;
    protected final static boolean HAS_REGISTERED = true;
    protected final static boolean NOT_REGISTERED = false;

    public void setIsRegistered(boolean isRegistered) {
        IsRegistered = isRegistered;
    }

    public boolean getIsRegistered() {
        return IsRegistered;
    }
}
