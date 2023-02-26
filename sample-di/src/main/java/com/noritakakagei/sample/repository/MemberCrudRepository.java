package com.noritakakagei.sample.repository;

import org.springframework.data.repository.CrudRepository;

import com.noritakakagei.sample.entity.Member;

public interface MemberCrudRepository extends CrudRepository<Member, Integer> {}