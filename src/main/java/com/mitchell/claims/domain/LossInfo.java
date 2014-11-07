package com.mitchell.claims.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Khaled Ayoubi
 */
@Entity
public class LossInfo {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String causeOfLoss;
    @Column
    private Date reportedDate;
    @Column
    private String lossDescription;
}
