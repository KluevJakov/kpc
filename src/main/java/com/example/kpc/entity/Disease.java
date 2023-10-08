package com.example.kpc.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/* сущность история болезни */

@Entity
@Table(name = "diseases")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    protected Date dateStart; //дата возникновения
    protected Date dateEnd; //дата окончания
    protected Date dateToTherapy; //дата поступления на стационарное лечение
    protected String firstDiagnosis; //первоначальный диагноз
    protected String secondDiagnosis; //последующий диагноз
    protected String anamnesis; //анамнез

    /* ----------------------------------------- */
    protected float temperature; //температура
    protected int pulse; //пульс
    protected int breath; //дыхание
    protected String commonHealth; //общее состояние
    protected String fatness; //упитанность

    /* -----------------состояние--------------- */
    protected String externalSkinStatus; //наружных покровов
    protected String internalShellStatus; //наружных покровов
    protected String lymphStatus; //наружных покровов
    /* ---------------исследование-------------- */
    protected String gastroSystemResearch; //пищеварительной
    protected String breathSystemResearch; //дыхательной
    protected String heartSystemResearch; //сердечно-сосудистой
    protected String nervousSystemResearch; //нервной
    protected String urogenitalSystemResearch; //мочеполовой

    /* ----------------------------------------- */

    @ManyToOne
    protected Sick sick; //диагноз

    @ManyToOne
    protected User curator; //куратор

    @ManyToOne
    protected Animal animal; //кто болел

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Disease disease = (Disease) o;
        return id != null && Objects.equals(id, disease.id);
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
