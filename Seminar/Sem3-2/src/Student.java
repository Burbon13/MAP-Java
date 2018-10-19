import java.util.Objects;

public class Student {
    private String nume;
    private float medie;

    public Student(String nume, float medie) {
        this.nume = nume;
        this.medie = medie;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public float getMedie() {
        return medie;
    }

    public void setMedie(float medie) {
        this.medie = medie;
    }

    @Override
    public String toString() {
        return "Student{" +
                "nume='" + nume + '\'' +
                ", medie=" + medie +
                '}';
    }

    public boolean equals(Student st)
    {
        return this.nume.equals(st.getNume())&& this.medie==st.getMedie();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;//cast
        return Float.compare(student.medie, medie) == 0 &&
                Objects.equals(nume, student.nume);
    }

    @Override
    public int hashCode() {
        System.out.println("Hash code: "+Objects.hash(nume, medie));
        return Objects.hash(nume, medie);
        //return super.hashCode();
    }
}
