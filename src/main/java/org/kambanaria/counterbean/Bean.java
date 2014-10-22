package org.kambanaria.counterbean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(name = Bean.Queries.ALL, query = "SELECT b FROM Bean b")
})
@Table(name = "BEANS")
public class Bean implements Serializable {

    @Id
    @SequenceGenerator(name = "beans_id_generator", sequenceName = "beans_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "beans_id_generator")
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "CNT", nullable = false)
    private long count;

    public Bean() {
    }

    public Bean(long id, String name, long count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getCount() {
        return count;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return new StringBuilder("Bean{id=").append(id).append("; name=").append(name).append("; count=").append(count).append("}").toString();
    }

    interface Queries {

        final String ALL = "ALL";
    }
}
