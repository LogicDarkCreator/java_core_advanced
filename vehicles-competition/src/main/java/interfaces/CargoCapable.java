package interfaces;

public interface CargoCapable {
    void loadCargo(int weight);
    void unloadCargo();
    boolean hasCargo();
    int getCargoWeight();
}
