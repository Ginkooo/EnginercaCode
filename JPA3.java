import lombok.Data;
import javax.persistence.*;

@Entity(name = "Post")
@Table(name = "post")
@Data
public class Post {

   @Id
   @GeneratedValue
   private Long id;

   private String title;

   @OneToOne(mappedBy = "post", cascade = CascadeType.ALL,
           fetch = FetchType.LAZY, optional = false)
   private PostDetails details;

   Post() {}
}
