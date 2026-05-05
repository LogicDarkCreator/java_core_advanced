package interfaces;

public interface Breakable {
    boolean isBroken();
    void repair();
    double getBreakProbability();
}