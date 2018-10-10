public abstract class Task {
    private String taskID, description;

    Task() {
        this.taskID = "N/A";
        this.description = "N/A";
    }

    Task(String taskID, String description) {
        this.taskID = taskID;
        this.description = description;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    abstract public void execute();

    @Override
    public String toString() {
        return "TaskID: " + taskID + "| Description: " + description;
    }
}
