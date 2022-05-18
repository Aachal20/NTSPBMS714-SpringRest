package com.divatt.designer.sequence.enity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequences")
public class DatabaseSequence {

	
	 @Id
	    private String id;

	    private int seq;                        //int also
	    
        public DatabaseSequence() {
			// TODO Auto-generated constructor stub
		}
        
		public DatabaseSequence(String id, int seq) {
			super();
			this.id = id;
			this.seq = seq;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public long getSeq() {
			return seq;
		}

		public void setSeq(int seq) {
			this.seq = seq;
		}


		@Override
		public String toString() {
			return "DatabaseSequence [id=" + id + ", seq=" + seq + "]";
		}

}
