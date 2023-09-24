package com.example.kpc.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/* сущность осмотра */

@Entity
@Table(name = "inspects")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Inspect {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    protected Date planDate;

    @Enumerated(value = EnumType.STRING)
    protected InspectStatus inspectStatus;

    @ManyToOne
    protected Animal animal;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Inspect inspect = (Inspect) o;
        return id != null && Objects.equals(id, inspect.id);
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
