@Entity(name = "User")
@Table(name = "user")
@Data
@AllArgsConstructor
public class User {

   @Id
   @GeneratedValue
   private Long id;

   @NotNull
   @Size(max=45)
   @Column(name="first_name")
   private String firstName;
   
   @NotNull
   @Size(max=45)
   @Column(name="second_name")
   private String secondName;
   
   @NotNull
   @Column(name="mail")
   @Pattern(regexp = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}")
   private String mail;

}
