import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Tag")
@Table(name = "tag")
@Data
public class Tag {

   public Tag() {}

   @Id
   @GeneratedValue
   private Long id;

   private String name;

   @ManyToMany(mappedBy = "tags")
   private List<Post> posts = new ArrayList<>();

}
