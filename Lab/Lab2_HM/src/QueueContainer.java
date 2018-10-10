public class QueueContainer extends AbstractContainer {
    @Override
    public Task remove() {
        return c.remove(0);
    }
}
