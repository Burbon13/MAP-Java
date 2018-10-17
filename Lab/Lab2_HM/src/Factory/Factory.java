package Factory;

import Strategy.Strategy;
import Container.Container;

public interface Factory {
    Container createContainer(Strategy strategy);
}
