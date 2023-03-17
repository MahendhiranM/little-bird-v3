package com.transport.buspass.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String rollNo;

    private Long registerNo;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String fatherName;
    
    @Column(nullable=false)
    private Integer semester;
    
    @Column(nullable=false)
    private String address;
    
    @Column(nullable=false)
    private String district;
    
    @Column(nullable=false)
    private Integer pincode;
    
    @Column(nullable=false)
    private Long phoneNo;
    
    @Column(nullable=false)
    private String boardingPoint;    
    
    @Column(nullable=false)
    private LocalDate createAt;
    
    @Column(nullable=false)
    private LocalDate updateAt;
    
    @Column(nullable=false)
    private String email;
    
    @Column(nullable=false)
    private Boolean isVerified;
    
    @Column(nullable=false)
    private String verificationCode;
	
    @ManyToOne
    @JoinColumn(name = "departmentId", referencedColumnName = "id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "collegeId", referencedColumnName = "id")
    private College college;

    @ManyToOne
    @JoinColumn(name = "periodId", referencedColumnName = "id")
    private Period period;

    @ManyToOne
    @JoinColumn(name = "routeId", referencedColumnName = "id")
    private Route route;
     
}
