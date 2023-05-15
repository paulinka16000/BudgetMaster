package pl.coderslab.budgetmaster.savings;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "saving")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Saving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "saving_id")
    private Long id;
}

