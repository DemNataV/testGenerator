package ClassXml;

import java.util.Objects;

public class State {
    int  priority;
    String epic;
    String page;
    String table;
    String value;
    String object;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getEpic() {
        return epic;
    }

    public void setEpic(String epic) {
        this.epic = epic;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String name(){
        return this.object + " " + this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return page.equals(state.page) &&
                table.equals(state.table) &&
                value.equals(state.value) &&
                object.equals(state.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priority, epic, page, table, value, object);
    }

    @Override
    public String toString() {
        return "State{" +
                "priority=" + priority +
                ", epic='" + epic + '\'' +
                ", page='" + page + '\'' +
                ", table='" + table + '\'' +
                ", value='" + value + '\'' +
                ", object='" + object + '\'' +
                '}';
    }


}
