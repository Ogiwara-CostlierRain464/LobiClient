package jp.ogiwara.test.lobitest;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Person extends RealmObject {
    @PrimaryKey
    private long id;
    private String name;
    private RealmList<Dog> dogs;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDogs(RealmList<Dog> dogs) {
        this.dogs = dogs;
    }

    public long getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public RealmList<Dog> getDogs() {
        return dogs;
    }
}
