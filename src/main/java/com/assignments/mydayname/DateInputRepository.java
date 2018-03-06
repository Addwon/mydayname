package com.assignments.mydayname;

import org.springframework.data.repository.CrudRepository;

public interface DateInputRepository extends CrudRepository<DateInput,Long> {
    DateInput findById(long id);
}
