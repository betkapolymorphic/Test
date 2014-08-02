package mhibernate.table;

import javax.persistence.*;

/**
 * Created by Beta on 8/2/14.
 */

public class UserDogs {

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public UserDogs(User user, Dog dog) {
        this.user = user;
        this.dog = dog;
    }

    public UserDogs() {

    }

    @JoinColumn(name="userId",referencedColumnName = "id")
    private User user;

    @JoinColumn(name="dogId",referencedColumnName = "id")
    private Dog dog;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "UserDogs{" +
                "id=" + id +
                ", user=" + user +
                ", dog=" + dog +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
}
