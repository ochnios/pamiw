package pl.ochnios.pamiw.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public interface Model {
    void addListener(String name, PropertyChangeListener listener);
}
