package data;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PseudoAnnotationsPropertyChangeListenerTestData {

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    private String name;

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        firePropertyChange("name", oldName, name);
    }
}
