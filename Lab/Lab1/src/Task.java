public abstract class Task {
    protected String taskId, description;

    public Task(String taskId, String description) {
        this.taskId = taskId;
        this.description = description;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskId() {

        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public abstract void execute();

    @Override
    public String toString() {
        return "Task id: " + taskId + " ; Description: " + description;
    }
}
