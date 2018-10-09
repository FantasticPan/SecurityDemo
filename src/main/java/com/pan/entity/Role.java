package com.pan.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 角色类
 *
 * Created by FantasticPan on 2018/6/4.
 */
@Entity
@Table
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
