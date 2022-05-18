package com.divatt.designer.service;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import com.divatt.designer.sequence.enity.DatabaseSequence;
 */


import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.divatt.designer.sequence.enity.DatabaseSequence;



@Service
public class SequenceGenerator {                                          

	@Autowired
	private MongoOperations  mongoOperations;

	public Integer getCurrentSequenceNumber(String seqName) {
		//get sequence no
		Query query = new Query(Criteria.where("id").is(seqName));
		//update the sequence no
		Update update = new Update().inc("seq", 1);
		//modify in document
		DatabaseSequence counter = mongoOperations
				.findAndModify(query,
						update, ((FindAndModifyOptions) options()).returnNew(true).upsert(true),
						DatabaseSequence.class);
		return (int) (!Objects.isNull(counter) ? counter.getSeq() : 1);


	}

}
