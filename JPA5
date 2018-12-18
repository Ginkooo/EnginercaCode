@Entity(name = "Post")
@Table(name = "post")
@Data
public class Post {

   @Id
   @GeneratedValue
   private Long id;

   private String title;

   @OneToMany(
           cascade = CascadeType.ALL,
           orphanRemoval = true
   )
   private List<PostComment> comments = new ArrayList<>();

}
