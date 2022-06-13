package com.kaankaplan.blog_app.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@NoArgsConstructor
@Builder
@Entity
@Table(name = "visitors")
public class Visitor extends User{


}
