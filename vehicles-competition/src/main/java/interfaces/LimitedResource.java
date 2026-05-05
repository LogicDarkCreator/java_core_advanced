package interfaces;

public interface LimitedResource {
    int getRemainingRange();
    boolean hasResource();
    void recharge();
    int getResourceLevel();
}
