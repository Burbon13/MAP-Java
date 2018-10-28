package cmd;

import service.Service;

public abstract class AbstractCommand implements Command {
    protected Service service;

    public AbstractCommand(Service service) {
        this.service = service;
    }
}
