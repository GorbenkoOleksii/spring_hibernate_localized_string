package o.horbenko.hibernate.entity;


import o.horbenko.helpers.localizationstring.LocalizedString;

import javax.persistence.*;

@Entity
@Table(name = "test_table")
public class TestEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private LocalizedString localizedParam; //in entities we just replace String or Map<String, Object> TO LocalizedString

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public LocalizedString getLocalizedParam() {
        return localizedParam;
    }

    public void setLocalizedParam(LocalizedString localizedParam) {
        this.localizedParam = localizedParam;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", localizedParam=" + localizedParam +
                '}';
    }
}
