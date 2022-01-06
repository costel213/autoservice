package com.costelmitrea.autoservice.service.map;

import com.costelmitrea.autoservice.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    protected Map<Long, T> map = new HashMap<>();

    T findById(ID id) {
        return map.get(id);
    }

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    T save(T object) {
       if(object != null) {
           if(object.getId() == null) {
               object.setId(this.getNewID());
           }
           map.put(object.getId(), object);
       } else {
           throw new RuntimeException("Object cannot be null.");
       }

       return object;
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    private Long getNewID() {
        Long nextId = null;
        try {
            nextId = Collections.max(map.keySet()) + 1;
        } catch(NoSuchElementException e) {
            nextId = 1L;
        }

        return nextId;
    }
}
