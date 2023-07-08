package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "mydbtest.cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private int series;

    @OneToOne(mappedBy = "car")
    private User user;

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public Car() {
    }

    @Override
    public String toString() {
        return "Car {  " +
                "id = " + id +
                ", model = " + model +
                ", series = " + series +
                ", user = " + user +
                "  }";
    }
}
