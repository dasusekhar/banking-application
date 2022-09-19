package com.example.Banking.application1.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="b1")
public class MyBank {
  @Id
  //@GeneratedValue(strategy = GenerationType.AUTO)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "b1_GENERATOR")
  @SequenceGenerator(name = "b1_GENERATO", sequenceName = "b1_SEQ", allocationSize = 1)
  private Long id;
  private String name;
  @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
  @JoinColumn(name = "c_id", nullable = false)
  private List<Deposit> deposit;
  @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
 @JoinColumn(name = "with_draw_id")
  private List<WithDraw> withDraw;
  private Long totalAmountAvaliable;
  private String email;
  private Long mobileNumber;
  private String accountNumber;


@OneToOne(mappedBy = "myBank",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
private LoginDetail loginDetail;


}
