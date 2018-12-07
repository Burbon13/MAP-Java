package cmd;

import service.Service;

public abstract class AbstractServiceCommand implements Command {
    protected Service service;

    public AbstractServiceCommand(Service service) {
        this.service = service;
    }
}
