package Lists;

import Components.AbstractComponent;
import Components.Interfaces.ID;
import com.google.common.collect.ForwardingList;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractComponentList<E extends ID> extends ForwardingList<E> {
    protected List<E> internalList;

    public AbstractComponentList() {
       this.internalList = new ArrayList<>();
    }

    @Override
    protected List<E> delegate() {
        return internalList;
    }
}
