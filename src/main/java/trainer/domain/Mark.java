package trainer.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "marks")
public enum Mark {
        INVALID,
        ABSENT,
        PRESENT,
        CORRECT;

        private String id;

        public void setId(String id) {
                this.id = id;
        }

        @Id
        public String getId() {
                return id;
        }
}
