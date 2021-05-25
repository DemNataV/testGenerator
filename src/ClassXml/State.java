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

    public String nameSl(){
        return this.object + " " + this.value;
    }

    public String name(){
        return nameSl().replaceAll("\"", "");
    }




    public int height() {
        return (int)Math.ceil(name().length()/20);
    }

    public String name20() {
        String acvar20 = new String();


        for (int o = 0; o < height() + 1; o++) {

            if (o < height()) {
                String acvaro = name().substring(o * 20, ((o + 1) * 20));
                acvar20 = acvar20 + acvaro + "\\n";
            } else {
                String acvaro = name().substring(o * 20, name().length());
                acvar20 = acvar20 + acvaro;
            }

        }
        return acvar20;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(value, state.value) &&
                Objects.equals(object, state.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, object);
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
