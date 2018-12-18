import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Post")
@Table(name = "post")
@Data
public class Post {

   @Id
   @GeneratedValue
   private Long id;

   private String title;

   public Post() {}

   public Post(String title) {
       this.title = title;
   }

   @ManyToMany(cascade = {
           CascadeType.PERSIST,
           CascadeType.MERGE
   })
   @JoinTable(name = "post_tag",
           joinColumns = @JoinColumn(name = "post_id"),
           inverseJoinColumns = @JoinColumn(name = "tag_id")
   )
   private List<Tag> tags = new ArrayList<>();

}
